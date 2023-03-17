import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        sc.close();
        int base = Integer.valueOf(input[2]);
        String sum = plus(input[0], input[1], base);
        String mul = multipli(input[0], input[1], base);
        // cut off starting zero
        mul = mul.replaceAll("^0+", "");
        String div = division(input[0], input[1], base);
        System.out.println(sum + " " + mul + " " + div);
        // System.out.println(compare(input[0], input[1]));

        // String sub = subtract(input[0], input[1], base);
        // System.out.println(sub);
    }

    public static String plus(String n1, String n2, int base) {
        Stack<Character> number1 = new Stack<>();
        Stack<Character> number2 = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int buffer = 0;

        for (char c : n1.toCharArray()) {
            number1.push(c);
        }
        for (char c : n2.toCharArray()) {
            number2.push(c);
        }

        while (!number1.isEmpty() && !number2.isEmpty()) {
            int d1 = number1.pop() - '0';
            int d2 = number2.pop() - '0';
            int sum = d1 + d2 + buffer;
            if (sum >= base) {
                sum = sum - base;
                buffer = 1;
            } else {
                buffer = 0;
            }
            sb.append((char) (sum + '0'));
        }

        while (!number1.isEmpty()) {
            int d1 = number1.pop() - '0';
            int sum = d1 + buffer;
            if (sum >= base) {
                sum = sum - base;
                buffer = 1;
            } else {
                buffer = 0;
            }
            sb.append((char) (sum + '0'));
        }
        while (!number2.isEmpty()) {
            int d2 = number2.pop() - '0';
            int sum = d2 + buffer;
            if (sum >= base) {
                sum = sum - base;
                buffer = 1;
            } else {
                buffer = 0;
            }
            sb.append((char) (sum + '0'));
        }

        if (buffer != 0) {
            sb.append((char) (buffer + '0'));
        }

        sb.reverse();
        return sb.toString();
    }

    public static String multipli(String n1, String n2, int base) {
        int d1 = n1.length();
        int d2 = n2.length();
        if (d1 != d2) {
            String[] fillZero = fillZero(n1, n2);
            n1 = fillZero[0];
            n2 = fillZero[1];
        }
        if (d1 == 1 && d2 == 1) {
            String result = mulDigit(Integer.valueOf(n1), Integer.valueOf(n2), base);
            return result;
        }
        int mid = n1.length() / 2;

        String a = n1.substring(0, mid);
        String b = n1.substring(mid);
        String c = n2.substring(0, mid);
        String d = n2.substring(mid);

        String ac = multipli(a, c, base);
        String bd = multipli(b, d, base);
        String ad = multipli(a, d, base);
        String bc = multipli(b, c, base);

        StringBuilder sbFirst = new StringBuilder();
        sbFirst.append(ac);
        for (int i = 0; i < (n1.length() - mid) * 2; i++) {
            sbFirst.append('0');
        }
        String first = sbFirst.toString();

        StringBuilder sbSecond = new StringBuilder();
        sbSecond.append(plus(ad, bc, base));
        for (int i = 0; i < (n1.length() - mid); i++) {
            sbSecond.append('0');
        }
        String second = sbSecond.toString();

        return plus(plus(first, second, base), bd, base);
    }

    public static String division(String n1, String n2, int base) {
        if (compare(n1, n2) == -1) {
            return "0";
        }
        if (compare(n1, n2) == 0) {
            return "1";
        }
        Queue<Character> dividendQueue = new LinkedList<>();
        StringBuilder sbQuotient = new StringBuilder();
        String dividend = "0";
        String mul = "0";

        for (char c : n1.toCharArray()) {
            dividendQueue.add(c);
        }
        while (!dividendQueue.isEmpty()) {
            int count = 0;
            while (compare(dividend, n2) == -1) {
                dividend += dividendQueue.poll();
                count++;
            }
            for(int i = 0; i < count-1; i++){
                sbQuotient.append('0');
            }
            dividend = dividend.replaceAll("^0+", "");
            System.out.println(dividend);
            if (compare(dividend, n2) != -1) {
                for (int i = 1; i < base; i++) {
                    // System.out.println(i);
                    mul = multipli(Integer.toString(i), n2, base).replaceAll("^0+", "");
                    if (compare(mul, dividend) == 0) {
                        mul = multipli(Integer.toString(i), n2, base).replaceAll("^0+", "");
                        // System.out.println("mul:" + mul);
                        sbQuotient.append(i);
                        break;
                    }
                    if (compare(mul, dividend) == 1) {
                        mul = multipli(Integer.toString(i - 1), n2, base).replaceAll("^0+", "");
                        
                        sbQuotient.append(i - 1);
                        break;
                    }
                }
                dividend = subtract(dividend, mul, base).replaceAll("^0+", "");
            }
        }

        return sbQuotient.toString();
    }

    public static String subtract(String n1, String n2, int base) {
        // help function for division, so n1 always larger than n2
        Stack<Character> number1 = new Stack<>();
        Stack<Character> number2 = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int buffer = 0;

        for (char c : n1.toCharArray()) {
            number1.push(c);
        }
        for (char c : n2.toCharArray()) {
            number2.push(c);
        }

        while (!number1.isEmpty() && !number2.isEmpty()) {
            int d1 = number1.pop() - '0';
            int d2 = number2.pop() - '0';
            int sum = d1 - d2;
            if (d1 < d2) {
                sum = base - d2 + d1 - buffer;
                buffer = 1;
            } else {
                buffer = 0;
            }
            sb.append((char) (sum + '0'));
        }

        while (!number1.isEmpty()) {
            int d1 = number1.pop() - '0';
            int sum = d1 - buffer;
            if (sum >= base) {
                sum = sum - base;
                buffer = 1;
            } else {
                buffer = 0;
            }
            sb.append((char) (sum + '0'));
        }

        if (buffer != 0) {
            sb.append((char) (buffer + '0'));
        }

        sb.reverse();
        return sb.toString().replaceAll("^0+", "");
    }

    public static int compare(String n1, String n2) {
        int d1 = n1.length();
        int d2 = n2.length();
        if (d1 > d2) {
            return 1;
        } else if (d2 > d1) {
            return -1;
        } else {
            for (int i = 0; i < d1; i++) {
                if (n1.charAt(i) > n2.charAt(i)) {
                    return 1;
                } else if (n1.charAt(i) < n2.charAt(i)) {
                    return -1;
                } else {
                    continue;
                }
            }
        }
        return 0;
    }

    public static String[] fillZero(String n1, String n2) {
        StringBuilder sb = new StringBuilder();
        int d1 = n1.length();
        int d2 = n2.length();

        if (d1 > d2) {
            for (int i = 0; i < d1 - d2; i++) {
                sb.append('0');
            }
            sb.append(n2);
            n2 = sb.toString();
        }
        if (d2 > d1) {
            for (int i = 0; i < d2 - d1; i++) {
                sb.append('0');
            }
            sb.append(n1);
            n1 = sb.toString();
        }
        return new String[] { n1, n2 };
    }

    public static String mulDigit(int d1, int d2, int base) {
        int result_NBase = (d1 * d2) / base * 10 + (d1 * d2) % base;
        return Integer.toString(result_NBase);
    }

}
