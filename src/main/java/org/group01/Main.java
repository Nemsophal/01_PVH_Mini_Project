package org.group01;

import controllers.*;
import db.DBConnection;
import models.Pagination;
import dao.ProductDaoI;
import dao.ProductDao;
import util.InputUtil;
import views.MenuView;
public class Main {
    public static void main(String[] args) {
        ProductDaoI dao = new ProductDao();
        Pagination pagination = new Pagination();
        ProductsController pc = new ProductsController(dao, pagination);
        UpdateDeleteController udc = new UpdateDeleteController();
        SearchController sc = new SearchController(dao, pagination);


        while (true) {
            pc.displayProducts();
            MenuView.showMainMenu();
            String choice = InputUtil.readOptions("=> Choose an option() : ");
            switch (choice) {
                case "W" -> pc.writeProduct();
                case "R" -> pc.readProductById();
                case "U" -> udc.updateProduct();
                case "D" -> udc.deleteProduct();
                case "S" -> sc.searchProduct();
                case "Se"-> sc.setNumberRow();
//                case "si"-> svc.saveInsert();
//                case "su"-> svc.saveUpdate();
//                case "ui"-> svc.unsaveInsert();
//                case "uu"-> svc.unsaveUpdate();
//                case "N" -> pgc.goNext();
//                case "P" -> pgc.goPrev();
//                case "F" -> pgc.goFirst();
//                case "L" -> pgc.goLast();
//                case "G" -> pgc.goToSpecificPage();
//                case "Ba"-> bc.backup();
//                case "Re"-> bc.restore();
//                case "Rc"-> bc.recovery();
                case "E" -> {
                    MenuView.showGoodbye();
                    return;
                }
                default -> System.out.println(" Invalid option.");
            }
            InputUtil.pressEnterToContinue();
        }
    }
}