package pl.bastus.bookapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetUserDetailsUsingPS {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // read user entered data
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter email id:");
        String id = scanner.nextLine();
        System.out.println("User id=" + id);
        System.out.println("Please enter password to get details:");
        String pwd = scanner.nextLine();
        System.out.println("User password=" + pwd);
        printUserData(id, pwd);
    }

    private static void printUserData(String id, String pwd) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select name, country, password from Users where email = ? and password = ?";
        try {
            con = DatabaseConnect.connectDatabase();
            ps = con.prepareStatement(query);

            // set the parameter, prepareStatement uses wildcards and setString for, suprise, prepare statement
            ps.setString(1, id);
            ps.setString(2, pwd);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Name=" + rs.getString("name") + ",country="
                        + rs.getString("country") + ",password="
                        + rs.getString("password"));
            }
        } finally {
            if(rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }

    }
}