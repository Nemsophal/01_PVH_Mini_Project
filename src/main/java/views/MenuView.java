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
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║          SAVE MENU           ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  [si] Save Insert            ║");
        System.out.println("║  [su] Save Update            ║");
        System.out.println("║  [B]  Back                   ║");
        System.out.println("╚══════════════════════════════╝");
    }
    public static void showUnSaveMenu() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║          UNSAVE MENU         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  [ui] UnSave Insert          ║");
        System.out.println("║  [uu] UnSave Update          ║");
        System.out.println("║  [b]  Back                   ║");
        System.out.println("╚══════════════════════════════╝");
    }
}