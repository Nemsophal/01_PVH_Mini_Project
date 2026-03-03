package controllers;

import models.dao.ProductDaoI;
import models.Pagination;

public class UpdateDeleteController {
    private ProductDaoI dao;
    private Pagination pagination;
    public void ProductsController(ProductDaoI repo, Pagination pagination) {
        this.dao=dao; this.pagination=pagination;
    }
    public void updateProduct(){

    }
    public void deleteProduct() {

    }
}
