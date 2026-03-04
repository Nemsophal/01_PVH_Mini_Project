package controllers;

import models.dao.ProductDaoI;
import views.ProductsView;

import java.util.List;

public class SaveController {
    private ProductDaoI dao;

    public SaveController(ProductDaoI dao) {
        this.dao = dao;
    }

    public void saveInsert() {
        List buf = dao.getInsertBuffer();
        if (buf.isEmpty()) {
            System.out.println("empty");
            return;
        }
        ProductsView.showInsertBuffer(buf);
        dao.saveInsertBuffer();
        ProductsView.showSuccessMessage("All new products saved to database!");
    }


    public void saveUpdate() {

    }

    public void unsaveInsert() { // VIEW ONLY
        List buf = dao.getInsertBuffer();
        if(buf.isEmpty()){ProductsView.showErrorMessage("No pending inserts.");return;}
        ProductsView.showInsertBuffer(buf); // just show
    }

    public void unsaveUpdate() { // VIEW ONLY
        List buf = dao.getUpdateBuffer();
        if(buf.isEmpty()){
            ProductsView.showErrorMessage("No pending updates.");
            return;
        }
        ProductsView.showUpdateBuffer(buf);
    }
}