package controllers;

import models.dao.ProductDaoI;
import models.Pagination;

import java.util.Scanner;

public class UpdateDeleteController {
    private ProductDaoI dao;
    private Pagination pagination;
    public UpdateDeleteController(ProductDaoI repo, Pagination pagination) {
        this.dao=dao;
        this.pagination=pagination;
    }
    public void updateProduct(){

    }
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID to delete: ");
        int id;

        while (true) {
            System.out.print("Enter Product ID to delete: ");
            String input = scanner.nextLine();

            try {
                id = Integer.parseInt(input);

                if (id <= 0) {
                    System.out.println("ID must be a positive number.");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers only.");
            }
        }

        System.out.print("Are you sure you want to delete this product? (y/n): ");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("y")) {

            boolean deleted = dao.delete(id);

            if (deleted) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
