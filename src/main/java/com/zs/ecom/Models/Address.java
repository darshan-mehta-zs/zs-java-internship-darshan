package main.java.com.zs.ecom.Models;


import main.java.com.zs.ecom.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Address {

    String addressLine1;
    String pincode;

    public static Connection connection = DbConnection.getConnection();

    public void input() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter address line 1");
        addressLine1 = scanner.nextLine();
        System.out.println("Enter pincode");
        pincode = scanner.nextLine();

    }

    public void addAddress() throws SQLException {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mobile Number");
        String mobile = scanner.nextLine();
        if (new Users().userExists(mobile)) {
            input();
            PreparedStatement st = connection.prepareStatement("INSERT INTO address (address_line_1, pincode) VALUES (?, ?)");
            st.setString(1, addressLine1);
            st.setString(2, pincode);
            st.executeUpdate();
            st.close();
            Connection connection2 = DbConnection.getConnection();
            PreparedStatement st2 = connection2.prepareStatement("insert into users_address(mobile_number,address_id) values(?,?)");
            st2.setString(1, mobile);
            st2.setInt(2, lastId());
            st2.executeUpdate();
            st2.close();
        }

    }

    public int lastId() throws SQLException {
        String query = "select address_id from address order by address_id desc limit 1";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }


    public void readAddress() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mobile Number");
        String mobile = scanner.nextLine();
        if (!new Users().userExists(mobile)) {
            System.out.println("Does not exists");
            return;
        }
        String query = "select address.address_line_1,address.pincode from address join users_address on address.address_id = users_address.address_id where users_address.mobile_number=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, mobile);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            addressLine1 = resultSet.getString("address_line_1");
            pincode = resultSet.getString("pincode");
            System.out.println(addressLine1 + " " + pincode);
        }

    }

    public void deleteAddress() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter address id");
        int addressId = scanner.nextInt();
        String query = "delete from address where address_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, addressId);
        statement.executeUpdate();
    }

    public void updateAddress() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter address id");
        int addressId = scanner.nextInt();
        input();
        String query = "update address set address_line_1=?, pincode=? where address_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, addressLine1);
        statement.setString(2, pincode);
        statement.setInt(3, addressId);
        statement.executeUpdate();

    }

}
