package controllers;

import exceptions.Exceptions;
import models.*;
import models.dao.ProductDaoI;
import util.InputUtil;
import views.ProductsView;
import java.util.List;
public class SearchController {
    private ProductDaoI dao;
    private Pagination pagination;
    public SearchController(ProductDaoI dao, Pagination pagination) {
        this.dao=dao; this.pagination=pagination;
    }
    public void searchProduct() {
        String keyword = InputUtil.readNonEmpty(" Enter name to search : ");
        List result = dao.search(keyword);
        if(result.isEmpty()){
            ProductsView.showErrorMessage("Not found: "+keyword);
            return;
        }
        pagination.recalcTotalPage(result.size());
        ProductsView.showProductTable(result, pagination);
        InputUtil.pressEnterToContinue();
    }
}