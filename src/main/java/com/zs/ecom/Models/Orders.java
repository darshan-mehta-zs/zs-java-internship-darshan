package com.zs.ecom.Models;


import com.zs.ecom.utils.DbConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Orders {

    int orderId;
    int productId;
    int quantity;
    float total;
    int userAddressId;

    String mobileNumber;
    Date orderDate;

    Connection connection = DbConnection.getConnection();
    private LocalDate localDate;

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id");
        productId = scanner.nextInt();
        System.out.println("Enter quantity");
        quantity = scanner.nextInt();
        System.out.println("Enter user address id");
        userAddressId = scanner.nextInt();
    }

    public void inputOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter phone number");
        mobileNumber = scanner.nextLine();
        System.out.println("Enter date");
        localDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter user address id");
        userAddressId = scanner.nextInt();
    }

    public void addOrders() throws SQLException {
        inputOrder();
        String insertQuery = "insert into orders(mobile_number,order_date,user_address_id,total) values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setString(1, mobileNumber);
        statement.setObject(2, localDate);
        statement.setInt(3, userAddressId);
        statement.setInt(4, 0);
        statement.executeUpdate();
    }


    public void addOrder() throws SQLException {
        input();
        String query = "select quantity,price from product where product_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        int q = 0;
        float p = 0;
        while (resultSet.next()) {
            q = resultSet.getInt("quantity");
            p = resultSet.getFloat("price");
        }
        if (q <= quantity) {
            System.out.println("Quantity is less than specified one");
            return;
        }

        total = p * quantity;
        PreparedStatement st = connection.prepareStatement("INSERT INTO orders (product_id, quantity,total,user_address_id) VALUES (?, ?, ?, ?)");
        st.setInt(1, productId);
        st.setInt(2, quantity);
        st.setFloat(3, total);
        st.setInt(4, userAddressId);
        st.executeUpdate();

        String queryUpdate = "update product set quantity=quantity-" + quantity + " where product_id=?";
        PreparedStatement statement1 = connection.prepareStatement(queryUpdate);
        statement1.setInt(1, productId);
        statement1.executeUpdate();

    }

    public void readOrder() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id");
        int orderId = scanner.nextInt();
        String query = "select p.product_name,op.quantity,p.price from orders o join order_product op on o.order_id=op.order_id join product p on p.product_id=op.product_id where o.order_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, orderId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String productName = resultSet.getString("product_name");
            quantity = resultSet.getInt("quantity");
            float price = resultSet.getFloat("price");
            System.out.println(productName + " " + quantity + " " + price);
        }
    }

    public void deleteOrder() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id");
        orderId = scanner.nextInt();
        String query = "delete from orders where order_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, orderId);
        statement.executeUpdate();
    }

    public void lastXOrders() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter phone number");
        String mobile = scanner.nextLine();
        System.out.println("Number of orders to view");
        int number = scanner.nextInt();
        String query = "select * from orders where mobile_number=? limit ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobile);
        statement.setInt(2, number);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String mobileNumber = resultSet.getString("mobile_number");
            total = resultSet.getFloat("total");
            String orderDate = resultSet.getString("order_date");
            System.out.println(mobileNumber + " " + orderDate + " " + total);
        }
    }

    public void topXProducts() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter phone number");
        String mobile = scanner.nextLine();
        System.out.println("Enter number");
        int number = scanner.nextInt();
        String query = "select p.product_name,count(*) from orders o join order_product op on o.order_id=op.order_id join product p on op.product_id=p.product_id where o.mobile_number=? group by p.product_name limit ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobile);
        statement.setInt(2, number);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String productName = resultSet.getString("product_name");
            String count = resultSet.getString("count");
            System.out.println(productName + " " + count);
        }
    }

    public void numberOfSaleOnPincode() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of days");
        int days = scanner.nextInt();
        String query = "select count(*),a.pincode from orders o join users_address u on o.user_address_id=u.user_address_id join address a on a.address_id=u.address_id where o.order_date::date + ? <= CURRENT_DATE group by a.pincode";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, days);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String pincode = resultSet.getString("pincode");
            String count = resultSet.getString("count");
            System.out.println(pincode + " " + count);
        }
    }

//    select p.product_name,p.product_name,o.quantity,o.total from orders o, users_address u, product p where u.user_address_id=o.user_address_id and mobile_number='9408548950' and o.product_id=p.product_id order by o.order_id desc limit ?
//    select count(*),o.product_id,p.product_name from orders o, users_address u, product p where u.user_address_id=o.user_address_id and u.mobile_number='9408548950' and p.product_id=o.product_id group by o.product_id,p.product_name order by count(*) desc;
//    select count(*) from orders o join users_address u on o.user_address_id=u.user_address_id join address a on a.address_id=u.address_id where a.pincode='0';


}
