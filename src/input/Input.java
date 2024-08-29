package input;

import java.util.Scanner;

public class Input {
    private static final Scanner input = new Scanner(System.in);
    public static int inputInteger(){
        do {
            try {
                String data = input.nextLine();
                return Integer.parseInt(data);
            } catch(NumberFormatException e){
                System.out.print("Yêu cầu nhập số nguyên: ");
            }
        } while (true);
    }

    public static String inputString() {
        return input.nextLine();
    }
}
