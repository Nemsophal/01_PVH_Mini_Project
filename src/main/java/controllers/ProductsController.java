package controllers;

import dao.ProductDaoI;
import models.Pagination;

public class ProductsController {
    private ProductDaoI dao;
    private Pagination pagination;
    public ProductsController(ProductDaoI repo, Pagination pagination) {
        this.dao=dao; this.pagination=pagination;
    }
    public void displayProducts(){

    }
    public void writeProduct(){

    }
    public void readProductById(){

    }
}
