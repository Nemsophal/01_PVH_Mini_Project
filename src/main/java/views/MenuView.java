package views;
public class MenuView {
    public static void showMainMenu() {
        System.out.println("-------------------------------------------------- Menu -------------------------------------------------");
        System.out.println("   N. Next Page      P. Previous Page    F. First Page    L. Last Page    G. Goto");
        System.out.println();
        System.out.println("W) Write          R) Read (id)       U) Update        D) Delete        S) Search (name)    Se) Set rows");
        System.out.println("sa) Save          Un) Unsaved        Ba) Backup       Re) Restore      E) Exit");
        System.out.println("---------------------------------------------------------------------------------------------------------");
    }
    public static void showGoodbye() {
        System.out.println();
        System.out.println("Thank you for using Stock Management!");
        System.out.println("Goodbye! See you next time :)");
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
}