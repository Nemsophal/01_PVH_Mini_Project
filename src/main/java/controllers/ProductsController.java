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
    public void writeProduct(){
        try {
            int nextId = dao.getNextId();
            System.out.println("Product ID: " + nextId);
            String name = InputUtil.readNonEmpty("Enter Name: ");
            double price = InputUtil.readDouble("Enter Unit Price: ");
            int qty = InputUtil.readInt("Enter qty: ");

            if (name.isEmpty()) throw Validation.emptyName();
            if (price <= 0) throw Validation.invalidPrice();
            if (qty <= 0) throw Validation.invalidQty();
            Products newProduct = new Products(name, price, qty);
            //add time
            newProduct.setImportDate(java.time.LocalDate.now());
            //add nextId
            newProduct.setId(nextId);
            dao.addToInsertBuffer(newProduct);
        }catch (Validation e){
            ProductsView.showErrorMessage(e.getMessage());
        }

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
