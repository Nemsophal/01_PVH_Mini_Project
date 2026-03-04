package exceptions;

public class Validation extends Exceptions {

    public Validation(String message) {
        super(message);
    }

    public static Validation emptyName() {
        return new Validation("Name cannot be empty!");
    }

    public static Validation invalidPrice() {
        return new Validation("Unit price must be greater than 0!");
    }

    public static Validation invalidQty() {
        return new Validation("Qty cannot be negative!");
    }

    public static Validation idNotFound(int id) {
        return new Validation("Product ID " + id + " not found!");
    }

    public static Validation invalidPage(int max) {
        return new Validation("Page must be between 1 and " + max);
    }

    public static Validation invalidRowCount() {
        return new Validation("Row count must be at least 1!");
    }

    public static Validation invalidNameFormat() {
        return new Validation("Name cannot contain special characters!");
    }
}
