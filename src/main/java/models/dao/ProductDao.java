package models.dao;

import db.DBConnection;
import models.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import views.ProductsView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ProductDao implements ProductDaoI{
    private List<Products> insertBuffer = new ArrayList<>();
    private List<Products> updateBuffer = new ArrayList<>();
    private Properties props = util.CredentialsLoader.loadProperties();

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
        List list = new ArrayList<>();
        try { ResultSet rs = DBConnection.getConnection()
                .prepareStatement("SELECT * FROM products").executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return list;
        }
        return List.of();
    }

    @Override public List getByPage(int offset, int limit) {
        List list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id LIMIT ? OFFSET ?";
        try { PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1,limit); ps.setInt(2,offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        return list;
    }

    @Override public Products findById(int id) {
        try { PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("SELECT * FROM products WHERE id=?");
            ps.setInt(1,id); ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        return null;
    }

    @Override
    public List<Products> search(String keyword) {
        List list = new ArrayList<>();
        try { PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("SELECT * FROM products WHERE name ILIKE ?");
            ps.setString(1,"%"+keyword+"%"); ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        return list;
    }

    @Override
    public int getTotalRow() {
        try { ResultSet rs = DBConnection.getConnection()
                .prepareStatement("SELECT COUNT(*) FROM products").executeQuery();
            if (rs.next()) return rs.getInt(1);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int getSpecificPage(int p) {
        System.out.print("Page number: ");
        return new Scanner(System.in).nextInt();
    }

    @Override
    public void addToInsertBuffer(Products p) {
        insertBuffer.add(p);

    }
    @Override
    public int getNextId() {
        int maxId = 0;
        try (Connection conn = DBConnection.getConnection();
             ResultSet rs = conn.prepareStatement("SELECT MAX(id) FROM products").executeQuery()) {
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for (Products p : insertBuffer) {
            if (p.getId() > maxId) maxId = p.getId();
        }
        return maxId + 1;
    }
    @Override
    public List<Products> getInsertBuffer() {
        return insertBuffer;
    }

    @Override public void saveInsertBuffer() {
        try { PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("INSERT INTO products (name,unit_price,qty) VALUES (?,?,?)");
            for (Products p : insertBuffer) {
                ps.setString(1,p.getName()); ps.setDouble(2,p.getUnitPrice());
                ps.setInt(3,p.getQty()); ps.executeUpdate();
            } clearInsertBuffer();
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public void clearInsertBuffer() {

    }

    @Override
    public void addToUpdateBuffer(Products product) {
        updateBuffer.removeIf(p -> p.getId() == product.getId());
        updateBuffer.add(product);
    }

    @Override public List getUpdateBuffer() { return updateBuffer; }

    @Override public void saveUpdateBuffer() {
        try { PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("UPDATE products SET name=?,unit_price=?,qty=? WHERE id=?");
            for (Products p : updateBuffer) {
                ps.setString(1,p.getName()); ps.setDouble(2,p.getUnitPrice());
                ps.setInt(3,p.getQty()); ps.setInt(4,p.getId()); ps.executeUpdate();
            } clearUpdateBuffer();
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }


    @Override
    public void clearUpdateBuffer() {

    }


    @Override
    public boolean delete(int id) {
        try (PreparedStatement ps = DBConnection.getConnection()
                .prepareStatement("DELETE FROM products WHERE id=?")) {
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void backup(String fileName) {
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password");
        String url = props.getProperty("db.url");

        if (user == null || pass == null || url == null) {
            System.err.println("Backup aborted: Credentials missing!");
            return;
        }

        String dbName = extractDbName(url);
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "/opt/homebrew/bin/pg_dump",
                    "-U", user,
                    "-d", dbName,
                    "-f", fileName
            );
            pb.environment().put("PGPASSWORD", pass);

            Process process = pb.start();

            if (process.waitFor() == 0) {
                System.out.println("Backup Success!");
            } else {
                String errorMsg = new String(process.getErrorStream().readAllBytes());
                System.err.println("Postgres Error: " + errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restore(String fileName) {
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password");
        String url = props.getProperty("db.url");

        //SAFETY CHECK TO PREVENT THE CRASH
        if (user == null || pass == null || url == null) {
            System.err.println("Restore aborted: Credentials missing from application.properties!");
            return;
        }

        String dbName = extractDbName(url);
        try {
            ProcessBuilder pb = new ProcessBuilder("psql", "-U", user, "-d", dbName, "-f", fileName);
            pb.environment().put("PGPASSWORD", pass);

            Process process = pb.start();
            if (process.waitFor() == 0) {
                ProductsView.showSuccessMessage("Database restored from " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String extractDbName(String url) {
        // Check if url is null or empty to prevent NullPointerException
        if (url == null || url.isEmpty()) {
            System.err.println("Database URL is null! Check application.properties.");
            return "default_db";
        }

        try {
            return url.substring(url.lastIndexOf("/") + 1);
        } catch (Exception e) {
            return "default_db";
        }
    }
}
