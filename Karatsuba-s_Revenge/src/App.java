import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        sc.close();
        String sum = plus(input[0], input[1], input[2]);
        String mul = multipli(input[0], input[1], input[2]);

        System.out.println(sum + " " + mul + " " + "0" );
    }

    public static String plus(String n1, String n2, String baseStr){
        Stack<Character> number1 = new Stack<>();
        Stack<Character> number2 = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int buffer = 0;
        int base = Integer.valueOf(baseStr);

        for(char c: n1.toCharArray()){
            number1.push(c);
        }
        for(char c: n2.toCharArray()){
            number2.push(c);
        }

        while(!number1.isEmpty() && !number2.isEmpty()){
            int d1 = number1.pop() - '0';
            int d2 = number2.pop() - '0';
            int sum = d1 + d2 + buffer;
            if (sum >= base){
                sum = sum - base;
                buffer = 1;
            }else{
                buffer = 0;
            }
            sb.append((char)(sum + '0'));
        }

        while(!number1.isEmpty()){
            int d1 = number1.pop() - '0';
            int sum = d1 + buffer;
            if (sum >= base){
                sum = sum - base;
                buffer = 1;
            }else{
                buffer = 0;
            }
            sb.append((char)(sum + '0'));
        }
        while(!number2.isEmpty()){
            int d2 = number2.pop() - '0';
            int sum = d2 + buffer;
            if (sum >= base){
                sum = sum - base;
                buffer = 1;
            }else{
                buffer = 0;
            }
            sb.append((char)(sum + '0'));
        }

        if(buffer != 0){
            sb.append((char)(buffer + '0'));
        }

        sb.reverse();
        return sb.toString();
    }

    public static String multipli(String n1, String n2, String baseStr){
        int base = Integer.valueOf(baseStr);
        

        return null;
    }

    public static String mulDigit(int d1, int d2, int base){
        int result_NBase = (d1*d2)/base + (d1*d2)%base;
        return Integer.toString(result_NBase);
    }

}
