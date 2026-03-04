package controllers;

import models.dao.ProductDaoI;
import exceptions.Validation;
import models.Pagination;
import models.Products;
import util.InputUtil;
import views.ProductsView;

import java.util.List;

public class ProductsController {
    private final ProductDaoI dao;
    private final Pagination pagination;
    public ProductsController(ProductDaoI dao, Pagination pagination) {
        this.dao = dao;
        this.pagination = pagination;
    }
    public void displayProducts(){
        pagination.recalcTotalPage(dao.getTotalRow());
        List list = dao.getByPage(pagination.getOffset(), pagination.getRowPerPage());
        ProductsView.showProductTable(list, pagination);
    }
    public void writeProduct() {

        int nextId = dao.getNextId();
        System.out.println("Product ID: " + nextId);

        String name = null;
        double price = 0;
        int qty = 0;

        while (true) {
            try {
                name = InputUtil.readNonEmpty("Enter Name: ");

                if (name.isEmpty()) {
                    throw Validation.emptyName();
                }

                if (!name.matches("^[a-zA-Z0-9 ]+$")) {
                    throw Validation.invalidNameFormat();
                }

                break;
            } catch (Validation e) {
                ProductsView.showErrorMessage(e.getMessage());
            }
        }

        while (true) {
            try {
                price = InputUtil.readDouble("Enter Unit Price: ");

                if (price <= 0) {
                    throw Validation.invalidPrice();
                }

                break;
            } catch (Validation e) {
                ProductsView.showErrorMessage(e.getMessage());
            }
        }

        while (true) {
            try {
                qty = InputUtil.readInt("Enter Qty: ");

                if (qty <= 0) {
                    throw Validation.invalidQty();
                }

                break;
            } catch (Validation e) {
                ProductsView.showErrorMessage(e.getMessage());
            }
        }

        Products newProduct = new Products(name, price, qty);
        newProduct.setImportDate(java.time.LocalDate.now());
        newProduct.setId(nextId);

        dao.addToInsertBuffer(newProduct);
    }
    public void readProductById(){
        try {
            int id = InputUtil.readInt("Please input id to get record : ");
            Products p = dao.findById(id);
            if(p == null) throw Validation.idNotFound(id);
            ProductsView.showSingleProduct(p);
        } catch (Validation e) {
            System.out.println(e.getMessage()); }
        InputUtil.pressEnterToContinue();
    }
}
