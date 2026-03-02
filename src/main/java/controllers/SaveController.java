package controllers;

import dao.ProductDaoI;
import models.Pagination;

public class SaveController {
    private ProductDaoI dao;
    private Pagination pagination;
    public void SearchController(ProductDaoI dao, Pagination pagination) {
        this.dao=dao; this.pagination=pagination;
    }
    public void saveInsert() {

    }
}
