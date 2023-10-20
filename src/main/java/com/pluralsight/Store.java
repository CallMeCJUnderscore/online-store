package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("Your options are:");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Which would you like to do? ");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        // This method should read a CSV file with product information and
        // populate the inventory ArrayList with Product objects. Each line
        // of the CSV file contains product information in the following format:
        //
        // id,name,price,quantity
        //
        // where id is a unique string identifier, name is the product name,
        // price is a double value representing the price of the product, and
        // quantity is an integer representing the number of items available
        // in the inventory.
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String input;
            while((input = bufferedReader.readLine()) != null){
                String[] tokens = input.split("\\|");
                String id = tokens[0];
                String name = tokens[1];
                float price = Float.parseFloat(tokens[2]);
                inventory.add(new Product(id, name, price));
            }
        } catch (Exception e){
            System.out.println("ERROR: Could not load inventory.");
        }

    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        // This method should display a list of products from the inventory,
        // and prompt the user to add items to their cart. The method should
        // prompt the user to enter the ID of the product they want to add to
        // their cart, and the quantity they want to add. The method should
        // add the selected product and quantity to the cart ArrayList.
        System.out.println("We carry the following inventory: ");
        for (Product product : inventory){
            System.out.println(product);
        }
        while (true) {
            System.out.println("\nYour options are:");
            System.out.println("1 - Search the product list");
            System.out.println("2- Add a product to your cart");
            System.out.println("3 - Return to the home screen");
            System.out.print("Which would you like to do?");
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    System.out.print("Please type the product ID you would like to search for: ");
                    String searchFor = scanner.nextLine();
                    Product found = findProductById(searchFor, inventory);
                    if(found == null) System.out.println("ERROR: Product not found with given ID!");
                    else System.out.println(found);
                    break;
                case 2:
                    if (scanner.nextLine().equalsIgnoreCase("Y")) {
                        while (true) {
                            boolean productFound = false;
                            System.out.print("Please type the SKU of the product you'd like to add: ");
                            String sku = scanner.nextLine().toUpperCase();

                            for (Product product : inventory) {
                                if (product.getSku().equals(sku)) {
                                    productFound = true;

                                    System.out.printf("How many %s would you like to add to your cart? Each one costs $%.2f. ", product.getName(), product.getPrice());
                                    int quantity = Integer.parseInt(scanner.nextLine());

                                    cart.add(new Product(product.getSku(), product.getName(), product.getPrice(), quantity));
                                    System.out.printf("%s has been added to your cart!", product.getName());
                                }
                            }
                            if (!productFound) {
                                System.out.println("ERROR: Product not found with given ID!");
                            }
                            System.out.print("Would you like to add another product (Y/N)? ");
                            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
                                return;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("ERROR: Invalid Choice. Defaulting to main menu...");
                    return;
            }
        }
    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        // This method should display the items in the cart ArrayList, along
        // with the total cost of all items in the cart. The method should
        // prompt the user to remove items from their cart by entering the ID
        // of the product they want to remove, and the quantity they want to
        // remove. The method should update the cart ArrayList and totalAmount
        // variable accordingly.
        while(true) {
        System.out.println("Your cart is as follows: ");
        for (Product product : cart){
            System.out.println(product);
            totalAmount += product.getPrice() * product.getQuantity();
        }
        System.out.println("Your total is: ");

            System.out.println("\nYour options are: ");
            System.out.println("1 - Check out");
            System.out.println("2 - Remove a product from cart");
            System.out.println("3 - Return to main menu");
            System.out.print("Which would you like to do?");
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1:
                    checkOut(cart, totalAmount);
                    break;
                case 2:
                    System.out.print("Please type the product ID you would like to remove: ");
                    String searchFor = scanner.nextLine();
                    Product found = findProductById(searchFor, cart);
                    if(found == null) {
                        System.out.println("ERROR: Product not found with given ID!\n");
                    }
                    else {
                        System.out.printf("%s has been removed from your cart!%n%n", found.getName());
                        cart.remove(found);
                    }
                    break;
                case 3:
                    System.out.println("Returning to main menu...\n\n");
                    return;
                default:
                    System.out.println("ERROR: Invalid Choice. Defaulting to main menu...\n\n");
                    return;
            }
        }
    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and deduct the total cost
        // from their account if they confirm.

    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product product : inventory){
            if (product.getSku().equals(id)){
                return product;
            }
        }
        return null;
    }
}