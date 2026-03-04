package controllers;

import exceptions.Validation;
import models.Products;
import models.dao.ProductDaoI;
import models.Pagination;
import util.*;
import views.ProductsView;

import java.util.Scanner;

public class UpdateDeleteController {
    private ProductDaoI dao;
    private Pagination pagination;
    public UpdateDeleteController(ProductDaoI dao, Pagination pagination) {
        this.dao=dao;
        this.pagination=pagination;
    }
    public void updateProduct() {

        Scanner scanner = new Scanner(System.in);
        int id;

        while (true) {
            System.out.print("Enter Product ID to update: ");
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

        Products product = dao.findById(id);

        if (product == null) {
            System.out.println("Product with ID " + id + " does not exist.");
            InputUtil.pressEnterToContinue();
            return;
        }

        ProductsView.showSingleProduct(product);

        while (true) {

            System.out.print("1. Update Name");
            System.out.print("\t2. Update Unit Price");
            System.out.print("\t3. Update Qty");
            System.out.print("\t4. Update All Fields");
            System.out.print("\t5. Exit");
            System.out.println();

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    product.setName(name);
                    dao.addToUpdateBuffer(product);
                    System.out.println("Name added to update buffer.");
                    break;

                case "2":
                    while (true) {
                        System.out.print("Enter new unit price: ");
                        String priceInput = scanner.nextLine();

                        try {
                            double price = Double.parseDouble(priceInput);

                            if (price <= 0) {
                                System.out.println("Price must be greater than 0.");
                                continue;
                            }

                            product.setUnitPrice(price);
                            dao.addToUpdateBuffer(product);
                            System.out.println("Price added to update buffer.");
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter numbers only.");
                        }
                    }
                    break;

                case "3":
                    while (true) {
                        System.out.print("Enter new qty: ");
                        String qtyInput = scanner.nextLine();

                        try {
                            int qty = Integer.parseInt(qtyInput);

                            if (qty < 0) {
                                System.out.println("Qty cannot be negative.");
                                continue;
                            }

                            product.setQty(qty);
                            dao.addToUpdateBuffer(product);
                            System.out.println("Qty added to update buffer.");
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter numbers only.");
                        }
                    }
                    break;

                case "4":

                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();

                    if (newName.trim().isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    double newPrice;
                    while (true) {
                        System.out.print("Enter new unit price: ");
                        String priceInput = scanner.nextLine();

                        try {
                            newPrice = Double.parseDouble(priceInput);

                            if (newPrice <= 0) {
                                System.out.println("Price must be greater than 0.");
                                continue;
                            }

                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter numbers only.");
                        }
                    }

                    int newQty;
                    while (true) {
                        System.out.print("Enter new qty: ");
                        String qtyInput = scanner.nextLine();

                        try {
                            newQty = Integer.parseInt(qtyInput);

                            if (newQty < 0) {
                                System.out.println("Qty cannot be negative.");
                                continue;
                            }

                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter numbers only.");
                        }
                    }

                    product.setName(newName);
                    product.setUnitPrice(newPrice);
                    product.setQty(newQty);

                    dao.addToUpdateBuffer(product);
                    System.out.println("All fields added to update buffer.");
                    break;

                case "5":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
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

        Products product = dao.findById(id);

        if (product == null) {
            System.out.println("Product with ID " + id + " does not exist.");
            InputUtil.pressEnterToContinue();
            return;
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

