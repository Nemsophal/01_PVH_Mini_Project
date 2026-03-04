package org.group01;

import controllers.*;
import models.Pagination;
import models.dao.ProductDaoI;
import models.dao.ProductDao;
import util.InputUtil;
import views.Color;
import views.MenuView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductDaoI dao = new ProductDao();
        Pagination pagination = new Pagination();
        pagination.loadRowConfig();
        ProductsController pc = new ProductsController(dao, pagination);
        UpdateDeleteController udc = new UpdateDeleteController(dao, pagination);
        SearchController sc = new SearchController(dao, pagination);
        SaveController svc = new SaveController(dao);
        BackupController bc = new BackupController(dao);

        while (true) {
            pc.displayProducts();
            MenuView.showMainMenu();
            String choice = InputUtil.readOptions(Color.YELLOW.code() + "=> Choose an option() : " + Color.RESET.code()).trim().toUpperCase();
            switch (choice) {
                case "W" -> pc.writeProduct();
                case "R" -> pc.readProductById();
                case "U" -> udc.updateProduct();
                case "D" -> udc.deleteProduct();
                case "S" -> sc.searchProduct();
                case "SE"-> sc.setNumberRow();
                case "SI"-> svc.saveInsert();
                case "SA" -> {
                    while (true) {

                        MenuView.showSaveMenu();
                        String saveChoice = InputUtil.readOptions("=> Choose save option: ").trim().toLowerCase();
                        switch (saveChoice) {
                            case "si" -> svc.saveInsert();
                            case "su" -> svc.saveUpdate();
                            case "b"  -> System.out.println("Back to main menu.");
                            default   -> System.out.println("Invalid option.");
                        }
                        if (saveChoice.equalsIgnoreCase("b")) break;
                        InputUtil.pressEnterToContinue();
                    }
                }
                case "UN" -> {
                    while (true) {

                        MenuView.showUnSaveMenu();
                        String unSaveChoice = InputUtil.readOptions("=> Choose unsave option: ").trim().toLowerCase();
                        switch (unSaveChoice) {
                            case "ui" -> svc.unsaveInsert();
                            case "uu" -> svc.unsaveUpdate();
                            case "b"  -> { System.out.println("Back to main menu."); }
                            default   -> System.out.println("Invalid option.");
                        }
                        if (unSaveChoice.equals("b")) break;
                        InputUtil.pressEnterToContinue();
                    }
                }
//                case "su"-> svc.saveUpdate();
//                case "ui"-> svc.unsaveInsert();
//                case "uu"-> svc.unsaveUpdate();
                case "N" -> pagination.goNext();
                case "P" -> pagination.goPrev();
                case "F" -> pagination.goFirst();
                case "L" -> pagination.goLast();
                case "G" -> {
                    System.out.print("Page number: ");
                    pagination.goToPage(new Scanner(System.in).nextInt());
                }
                case "BA"-> bc.backup();
                case "RE"-> bc.restore();
//                case "Rc"-> bc.recovery();
                case "E" -> {
                    MenuView.showGoodbye();
                    return;
                }
                default -> System.out.println(" Invalid option.");
            }
        }
    }
}