package dao;

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

public class ProductDao implements ProductDaoI{
    private List<Products> insertBuffer = new ArrayList<>();
    private List<Products> updateBuffer = new ArrayList<>();
    // Initialize props by calling your util class
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
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
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
            // Use your specific Homebrew path here
            ProcessBuilder pb = new ProcessBuilder(
                    "/opt/homebrew/bin/pg_dump",
                    "-U", user,
                    "-d", dbName,
                    "-f", fileName
            );
            pb.environment().put("PGPASSWORD", pass);

            Process process = pb.start();

            // This part is vital: read why it failed if exit code != 0
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

        // ADD THIS SAFETY CHECK TO PREVENT THE CRASH
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

    // Helper to get the DB name from the URL (e.g., jdbc:postgresql://localhost:5432/stock_db)
    private String extractDbName(String url) {
        // Check if url is null or empty to prevent NullPointerException
        if (url == null || url.isEmpty()) {
            System.err.println("Database URL is null! Check application.properties.");
            return "default_db"; // Return a default name so the command doesn't crash
        }

        try {
            return url.substring(url.lastIndexOf("/") + 1);
        } catch (Exception e) {
            return "default_db";
        }
    }
}
