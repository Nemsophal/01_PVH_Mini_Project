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
            String name = InputUtil.readNonEmpty("Enter Name: ");
            double price = InputUtil.readDouble("Enter Unit Price: ");
            int qty = InputUtil.readInt("Enter qty: ");
            if (name.isEmpty()) throw Validation.emptyName();
            if (price <= 0) throw Validation.invalidPrice();
            if (qty <= 0) throw Validation.invalidQty();
            dao.addToInsertBuffer(new Products(name, price, qty));
            ProductsView.showSuccessMessage("Add to buffer | use si");
        }catch (Validation e){
            ProductsView.showErrorMessage(e.getMessage());
        }

    }
    public void readProductById(){

    }
}
