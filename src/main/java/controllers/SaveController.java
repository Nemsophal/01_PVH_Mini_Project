package controllers;

import models.Products;
import models.dao.ProductDaoI;
import views.ProductsView;

import java.util.ArrayList;
import java.util.List;

public class SaveController {
    private ProductDaoI dao;

    public SaveController(ProductDaoI dao) {
        this.dao = dao;
    }

    public void saveInsert() {
        List buf = dao.getInsertBuffer();
        if (buf.isEmpty()) {
            ProductsView.showErrorMessage("No new products to save!");
            return;
        }
        List snapshot = new ArrayList<>(buf);
        dao.saveInsertBuffer();
        ProductsView.showInserted(snapshot);
        ProductsView.showSuccessMessage("New products saved to database");
    }

    public void saveUpdate() {
        List buf = dao.getUpdateBuffer();
        if (buf.isEmpty()) {
            ProductsView.showErrorMessage("No updates to save!");
            return;
        }
        List snapshot = new ArrayList<>(buf);
        dao.saveUpdateBuffer();
        ProductsView.showUpdated(snapshot);

        for (int j = 0; j < snapshot.size(); j++) {
            ProductsView.showSuccessMessage("Product " + (j + 1) + " successfully updated.");
        }
    }

    public void unsaveInsert() {
        List buf = dao.getInsertBuffer();
        if(buf.isEmpty()){ProductsView.showErrorMessage("No pending inserts.");return;}
        ProductsView.showInsertBuffer(buf);
    }

    public void unsaveUpdate() {
        List buf = dao.getUpdateBuffer();
        if(buf.isEmpty()){
            ProductsView.showErrorMessage("No pending updates.");
            return;
        }
        ProductsView.showUpdateBuffer(buf);
    }
}