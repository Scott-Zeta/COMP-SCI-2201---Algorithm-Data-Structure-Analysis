import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        sc.close();
        String sum = plus(input[0], input[1], input[21]);
        System.out.println(sum + " " + "0" + " " + "0" );
    }

    public static String plus(String n1, String n2, String digit){
        return "0";
    }
}
