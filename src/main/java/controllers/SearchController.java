package controllers;

import models.dao.ProductDaoI;
import models.Pagination;

import java.util.Scanner;

public class SearchController {
    private ProductDaoI dao;
    private Pagination pagination;
    public SearchController(ProductDaoI dao, Pagination pagination) {
        this.dao=dao; this.pagination=pagination;
    }
    public void searchProduct() {}
}
