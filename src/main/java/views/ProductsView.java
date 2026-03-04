package views;

import models.Pagination;
import models.Products;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
public class ProductsView {
    private static final CellStyle CENTER = new CellStyle(HorizontalAlign.CENTER);
    private static final CellStyle RIGHT = new CellStyle(HorizontalAlign.RIGHT);
    private static final CellStyle LEFT = new CellStyle(HorizontalAlign.LEFT);
    private static Table buildTable() {
        Table t = new Table(5,
                BorderStyle.UNICODE_BOX_DOUBLE_BORDER,
                ShownBorders.ALL);
        t.setColumnWidth(0, 20, 8); // ID
        t.setColumnWidth(1, 30 , 25); // Name
        t.setColumnWidth(2, 20, 14); // Unit Price
        t.setColumnWidth(3, 20, 10); // Qty
        t.setColumnWidth(4, 20, 16); // Import Date
        return t;
    }

    private static void addHeader(Table t) {
        t.addCell(Color.BLUE.code() + "ID" + Color.RESET.code(), CENTER);
        t.addCell(Color.BLUE.code() + "Name" + Color.RESET.code(), CENTER);
        t.addCell(Color.BLUE.code() + "Unit Price" + Color.RESET.code(), CENTER);
        t.addCell(Color.BLUE.code() + "Qty" + Color.RESET.code(), CENTER);
        t.addCell(Color.BLUE.code() + "Import Date" + Color.RESET.code(), CENTER);
    }

    private static void addProductRow(Table t, Products p) {
        // Change all styles to CENTER
        t.addCell(String.valueOf(p.getId()), CENTER);
        t.addCell(p.getName(), CENTER);
        t.addCell(String.format("%.2f", p.getUnitPrice()), CENTER);
        t.addCell(String.valueOf(p.getQty()), CENTER);
        t.addCell(p.getImportDate() != null
                ? p.getImportDate().toString() : "-", CENTER);
    }

    public static void showProductTable(List<Products> list, Pagination pg) {
        Table t = buildTable();
        addHeader(t);

        if (list != null && !list.isEmpty()) {
            for (Products p : list) {
                t.addCell(String.valueOf(p.getId()), CENTER);
                t.addCell(p.getName(), CENTER);
                t.addCell(String.valueOf(p.getUnitPrice()), CENTER);
                t.addCell(String.valueOf(p.getQty()), CENTER);
                t.addCell(p.getImportDate() != null ? p.getImportDate().toString() : "-", CENTER);
            }
        }

        t.addCell("Page : " + Color.YELLOW.code() + pg.getCurrentPage() + Color.RESET.code() + " of " + Color.RED.code() + pg.getTotalPage() + Color.RESET.code(), CENTER, 2);
        t.addCell("Total Record : " + Color.GREEN.code() + pg.getTotalRow() + Color.RESET.code(), CENTER, 3);

        System.out.println(t.render());
    }

    public static void showSingleProduct(Products p) {
        System.out.println();
        Table t = buildTable();
        addHeader(t);
        addProductRow(t, p);
        System.out.println(t.render());
        System.out.println();
    }
    public static void showInserted(List<Products> list) {
        System.out.println();
        if (list.isEmpty()) { showEmptyMessage(); return; }
        Table t = buildTable();
        t.addCell(" INSERTED TO PRODUCTS", CENTER, 6);
        addHeader(t);
        for (Products p : list) {
            addProductRow(t, p);
        }
        System.out.println(t.render());
        System.out.println();
    }
    public static void showInsertBuffer(List<Products> list) {
        System.out.println();
        if (list.isEmpty()) { showEmptyMessage(); return; }
        Table t = buildTable();
        t.addCell(" PENDING PRODUCTS (UNSAVED) ", CENTER, 6);
        addHeader(t);
        for (Products p : list) {
            addProductRow(t, p);
        }
        System.out.println(t.render());
        System.out.println();
    }

    public static void showUpdateBuffer(List<Products> list) {
        System.out.println();
        if (list.isEmpty()) { showEmptyMessage(); return; }
        Table t = buildTable();
        t.addCell(" UPDATED PRODUCTS (UNSAVED) ", CENTER, 6);
        addHeader(t);
        for (Products p : list) {
            addProductRow(t, p);
        }
        System.out.println(t.render());
        System.out.println();
    }

    public static void showEmptyMessage() {
        System.out.println(" No products found.");
    }

    public static void showSuccessMessage(String s) {
    }

    public static void showErrorMessage(String s) {
        System.out.println("Error: " + s);
    }
}