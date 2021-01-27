package com.zs.ecom.Models;


import com.zs.ecom.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Product {

    String product_name;
    float price;
    int quantity;

    public static Connection connection = DbConnection.getConnection();

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name");
        product_name = scanner.nextLine();
        System.out.println("Enter product price");
        price = scanner.nextFloat();
        System.out.println("Enter product quantity");
        quantity = scanner.nextInt();
    }

    public void addProduct() throws SQLException {
        input();
        String query = "insert into product(product_name,quantity,price) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, product_name);
        statement.setInt(2, quantity);
        statement.setFloat(3, price);
        statement.executeUpdate();
        statement.close();
    }

    public void readProduct() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Product Id");
        int productId = scanner.nextInt();
        String query = "select * from product where product_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            productId = resultSet.getInt("product_id");
            product_name = resultSet.getString("product_name");
            quantity = resultSet.getInt("quantity");
            price = resultSet.getFloat("price");
            System.out.println(productId + " " + product_name + " " + quantity + " " + price);
        }
    }

    public void updateProduct() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Product Id");
        int productId = scanner.nextInt();
        String query = "update product set product_name=?, quantity=?, price=? where product_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        input();
        statement.setString(1, product_name);
        statement.setInt(2, quantity);
        statement.setFloat(3, price);
        statement.setInt(4, productId);
        statement.executeUpdate();
    }

    public void deleteProduct() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id");
        int productId = scanner.nextInt();
        String query = "delete from product where product_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        statement.executeUpdate();
    }

    public void addProductInOrder() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id");
        int productId = scanner.nextInt();
        System.out.println("Enter product quantity");
        quantity = scanner.nextInt();
        System.out.println("Enter order id");
        int orderId = scanner.nextInt();


        String query = "select quantity,price from product where product_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        int actualQuantity = 0;
        float price = 0;
        while (resultSet.next()) {
            actualQuantity = resultSet.getInt("quantity");
            price = resultSet.getFloat("price");
        }
        if (actualQuantity < quantity) {
            System.out.println("Quantity is less than specified one");
            return;
        }
        float total = quantity * price;

        String insertOrderProduct = "insert into order_product(product_id,order_id,quantity,total) values(?,?,?,?)";
        statement = connection.prepareStatement(insertOrderProduct);
        statement.setInt(1, productId);
        statement.setInt(2, orderId);
        statement.setInt(3, quantity);
        statement.setFloat(4, total);
        statement.executeUpdate();

        String updateOrderTotal = "update orders set total=total+" + total + " where order_id=?";
        statement = connection.prepareStatement(updateOrderTotal);
        statement.setInt(1, orderId);
        statement.executeUpdate();

        String updateProductQuantity = "update product set quantity=quantity-" + quantity + " where product_id=?";
        statement = connection.prepareStatement(updateProductQuantity);
        statement.setInt(1, productId);
        statement.executeUpdate();

    }

    public void readProductInSet() throws SQLException {
        String query = "select * from product";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        Set<Product> productsCatalog = new HashSet<>();
        while (resultSet.next()) {
            product_name = resultSet.getString("product_name");
            quantity = resultSet.getInt("quantity");
            price = resultSet.getFloat("price");
            Product product = new Product();
            product.price = price;
            product.product_name = product_name;
            product.quantity = quantity;
            productsCatalog.add(product);
        }
        for (Product product : productsCatalog) {
            System.out.println(product.product_name + " " + product.price + " " + product.quantity);
        }

    }

    @Override
    public String toString() {
        return "Product{" +
                "product_name='" + product_name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }


}
