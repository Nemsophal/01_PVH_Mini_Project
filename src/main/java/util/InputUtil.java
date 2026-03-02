package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner sc = new Scanner(System.in);


    public static String readNonEmpty(String p){
        String in="";
        while(in.trim().isEmpty()){
            System.out.print(p);
            in=sc.nextLine();
            if(in.trim().isEmpty())
                System.out.println("Cannot be empty!");
        }
        return in.trim();
    }

    public static int readInt(String p){
        while(true){
            try{
                System.out.print(p);
                return Integer.parseInt(sc.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Invalid number!");
            }
        }
    }

    public static double readDouble(String p){
        while(true){
            try{
                System.out.print(p);
                return Double.parseDouble(sc.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Invalid number!");
            }
        }
    }

    public static String readOptions(String p){
        System.out.print(p);
        return sc.nextLine().trim();
    }

    public static void pressEnterToContinue(String p){
        System.out.print("Press Enter to Continue...");
        sc.nextLine();
    }
}
