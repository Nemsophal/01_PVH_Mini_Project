package dao;

import db.DBConnection;
import models.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements ProductDaoI{
    private List<Products> insertBuffer = new ArrayList<>();
    private List<Products> updateBuffer = new ArrayList<>();

    private Products mapRow(ResultSet rs) throws SQLException{
        Products p = new Products();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setUnitPrice(rs.getDouble("unit_price"));
        p.setQty(rs.getInt("qty"));
        p.setImportDate(rs.getDate("import_date").toLocalDate());
        return p;
    }
    @Override
    public List<Products> getAll() {

        return List.of();
    }

    @Override
    public List<Products> getByPage(int offset, int limit) {
        return List.of();
    }

    @Override
    public Products findById(int id) {
        return null;
    }

    @Override
    public List<Products> search(String keyword) {
        return List.of();
    }

    @Override
    public int getTotalRow() {
        return 0;
    }

    @Override
    public void addToInsertBuffer(Products p) {

    }

    @Override
    public List<Products> getInsertBuffer() {
        return List.of();
    }

    @Override
    public void saveInsertBuffer() {

    }

    @Override
    public void clearInsertBuffer() {

    }

    @Override
    public void addToUpdateBuffer(Products p) {

    }

    @Override
    public List<Products> getUpdateBuffer() {
        return List.of();
    }

    @Override
    public void saveUpdateBuffer() {

    }

    @Override
    public void clearUpdateBuffer() {

    }

    @Override
    public void delete(int id) {

    }
}
