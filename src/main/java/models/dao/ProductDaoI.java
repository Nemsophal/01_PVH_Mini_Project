package models.dao;

import models.Products;

import java.util.List;
public interface ProductDaoI {
    List<Products> getAll();

    List<Products> getByPage(int offset, int limit);

    Products findById(int id);

    List<Products> search(String keyword);

    int getTotalRow();

    void addToInsertBuffer(Products p);

    List<Products> getInsertBuffer();

    void saveInsertBuffer();

    void clearInsertBuffer();

    void addToUpdateBuffer(Products p);

    List<Products> getUpdateBuffer();

    void saveUpdateBuffer();

    void clearUpdateBuffer();

    boolean delete(int id);

    void backup(String fileName);

    void restore(String fileName);
}
