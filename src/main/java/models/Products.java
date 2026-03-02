package models;
import java.time.LocalDate;
public class Products {
    private int id;
    private String name;
    private double unitPrice;
    private int qty;
    private LocalDate importDate;
    public Products() {}
    public Products(String name, double unitPrice, int qty)
    {
        this.name=name; this.unitPrice=unitPrice; this.qty=qty;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getUnitPrice() { return unitPrice; }
    public int getQty() { return qty; }
    public LocalDate getImportDate() { return importDate; }
    public void setId(int id) { this.id=id; }
    public void setName(String n) { this.name=n; }
    public void setUnitPrice(double p) { this.unitPrice=p; }
    public void setQty(int q) { this.qty=q; }
    public void setImportDate(LocalDate d) { this.importDate=d; }
}