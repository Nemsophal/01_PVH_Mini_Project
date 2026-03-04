package org.group01;

import controllers.*;
import exceptions.Validation;
import models.Pagination;
import models.dao.ProductDaoI;
import models.dao.ProductDao;
import util.InputUtil;
import views.Color;
import views.MenuView;
import views.ProductsView;

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
                case "SE" -> {
                    Scanner scanner = new Scanner(System.in);
                    int rows;

                    while (true) {
                        System.out.print("Please input number of rows to display: ");
                        String input = scanner.nextLine();

                        try {
                            rows = Integer.parseInt(input);

                            if (rows <= 0) {
                                System.out.println("Number must be greater than 0.");
                                continue;
                            }

                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter numbers only.");
                        }
                    }

                    pagination.setRowPerPage(rows);
                }
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
                case "N" -> pagination.goNext();
                case "P" -> pagination.goPrev();
                case "F" -> pagination.goFirst();
                case "L" -> pagination.goLast();
                case "G" -> {
                    Scanner scanner = new Scanner(System.in);
                    int page;

                    while (true) {
                        try {
                            System.out.print("Page number: ");
                            String input = scanner.nextLine();

                            // Only digits
                            if (!input.matches("\\d+")) {
                                throw new Validation("Page must be a number!");
                            }

                            page = Integer.parseInt(input);

                            if (page <= 0) {
                                throw new Validation("Page number must be greater than 0!");
                            }

                            pagination.goToPage(page);
                            break;

                        } catch (Validation e) {
                            ProductsView.showErrorMessage(e.getMessage());
                        }
                    }
                }
                case "BA"-> bc.backup();
                case "RE"-> bc.restore();
                case "E" -> {
                    MenuView.showGoodbye();
                    return;
                }
                default -> System.out.println(" Invalid option.");
            }
        }
    }
}