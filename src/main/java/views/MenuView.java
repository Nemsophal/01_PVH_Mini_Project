package views;
public class MenuView {
    public static void showMainMenu() {

        System.out.println("-------------------------------------------------- Menu -------------------------------------------------");

        System.out.println("   "
                + Color.GREEN.code() + "N." + Color.RESET.code() + " Next Page      "
                + Color.GREEN.code() + "P." + Color.RESET.code() + " Previous Page    "
                + Color.GREEN.code() + "F." + Color.RESET.code() + " First Page    "
                + Color.GREEN.code() + "L." + Color.RESET.code() + " Last Page    "
                + Color.GREEN.code() + "G." + Color.RESET.code() + " Goto");

        System.out.println();

        System.out.println(
                Color.GREEN.code() + "W)" + Color.RESET.code() + " Write          "
                        + Color.GREEN.code() + "R)" + Color.RESET.code() + " Read (id)       "
                        + Color.GREEN.code() + "U)" + Color.RESET.code() + " Update        "
                        + Color.GREEN.code() + "D)" + Color.RESET.code() + " Delete        "
                        + Color.GREEN.code() + "S)" + Color.RESET.code() + " Search (name)    "
                        + Color.GREEN.code() + "Se)" + Color.RESET.code() + " Set rows"
        );

        System.out.println(
                Color.GREEN.code() + "Sa)" + Color.RESET.code() + " Save          "
                        + Color.GREEN.code() + "Un)" + Color.RESET.code() + " Unsaved        "
                        + Color.GREEN.code() + "Ba)" + Color.RESET.code() + " Backup       "
                        + Color.GREEN.code() + "Re)" + Color.RESET.code() + " Restore      "
                        + Color.GREEN.code() + "E)" + Color.RESET.code() + " Exit"
        );

        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
    public static void showGoodbye() {
        System.out.println();
        System.out.println("Thank you for using Stock Management!");
        System.out.println("Goodbye! See you next time.");
        System.out.println();
    }
    public static void showSaveMenu() {
        System.out.println(Color.GREEN.code()+"(si)"+Color.RESET.code() +" for saving insert products and "+Color.GREEN.code()+"(su)" + Color.RESET.code()+" for saving update products or "+Color.RED.code()+"(b)"+Color.RESET.code()+ " for back to menu "+Color.RESET.code());
    }
    public static void showUnSaveMenu() {
        System.out.println(Color.GREEN.code()+"(ui)"+Color.RESET.code() +" for viewing insert products and "+Color.GREEN.code()+"(uu)" + Color.RESET.code()+" for viewing update products or "+Color.RED.code()+"(b)"+Color.RESET.code()+ " for back to menu "+Color.RESET.code());
    }
}