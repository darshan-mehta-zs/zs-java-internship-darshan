package main.java.com.zs.ecom;


import main.java.com.zs.ecom.Models.Address;
import main.java.com.zs.ecom.Models.Orders;
import main.java.com.zs.ecom.Models.Product;
import main.java.com.zs.ecom.Models.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class Ecommerce {


    public static void user() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Add user");
        System.out.println("2.Update user");
        System.out.println("3.Delete user");
        System.out.println("4.Read user");
        int ch = scanner.nextInt();
        Users users = new Users();
        switch (ch) {
            case 1:
                users.addUser();
                break;
            case 2:
                users.updateUser();
                break;
            case 3:
                users.deleteUser();
                break;
            case 4:
                users.readUserByMobileNumber();
                break;
        }
    }

    public static void address() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Add Address");
        System.out.println("2.Update Address");
        System.out.println("3.Delete Address");
        System.out.println("4.Read Address");
        int ch = scanner.nextInt();
        Address address = new Address();
        switch (ch) {
            case 1:
                address.addAddress();
                break;
            case 2:
                address.updateAddress();
                break;
            case 3:
                address.deleteAddress();
                break;
            case 4:
                address.readAddress();
                break;
        }
    }

    public static void product() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Add Product");
        System.out.println("2.Update Product");
        System.out.println("3.Delete Product");
        System.out.println("4.Read Product");
        System.out.println("5.Add product in order");
        int ch = scanner.nextInt();
        Product product = new Product();
        switch (ch) {
            case 1:
                product.addProduct();
                break;
            case 2:
                product.updateProduct();
                break;
            case 3:
                product.deleteProduct();
                break;
            case 4:
                product.readProduct();
                break;
            case 5:
                product.addProductInOrder();
                break;
        }
    }

    public static void orders() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Add orders");
        System.out.println("2.Update orders");
        System.out.println("3.Delete orders");
        System.out.println("4.Read orders");
        System.out.println("5.Last X Orders");
        System.out.println("6.Top X Products");
        System.out.println("7.Total number of sales on pincode");
        int ch = scanner.nextInt();
        Orders orders = new Orders();
        switch (ch) {
            case 1:
                orders.addOrders();
                break;
            case 2:
                break;
            case 3:
                orders.deleteOrder();
                break;
            case 4:
                orders.readOrder();
                break;
            case 5:
                orders.lastXOrders();
                break;
            case 6:
                orders.topXProducts();
                break;
            case 7:
                orders.numberOfSaleOnPincode();
                break;
        }
    }

    public static void ecommerce() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int ch = 0;
        while (choice != 9) {

            System.out.println("1.User");
            System.out.println("2.Address");
            System.out.println("3.Products");
            System.out.println("4.Orders");
            System.out.println("9.Exit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user();
                    break;
                case 2:
                    address();
                    break;
                case 3:
                    product();
                    break;
                case 4:
                    orders();
                    break;
                case 9:
                    System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println("HELLO WORLD");
        ecommerce();

    }

}
