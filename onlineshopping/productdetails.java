package onlineshopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class productdetails {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome to the Login App");
            System.out.println("1. Admin Login");
            System.out.println("2. Agent Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    agentLogin();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/rvvn", "root", "123456789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void adminLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Admin Username: ");
        String username = sc.next();
        System.out.print("Admin Password: ");
        String password = sc.next();
        
        Connection con = getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM rvvn WHERE username=? AND password=?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    System.out.println("Admin login successful.");
                    System.out.println("______________________________");
                    // Perform admin actions
                    performAdminActions();
                } else {
                    System.out.println("Invalid admin username or password. Please try again.");
                }
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Check the connection");
        }
    }

    public static void agentLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Agent Username: ");
        String username = sc.next();
        System.out.print("Agent Password: ");
        String password = sc.next();

        Connection con = getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM rvvn WHERE username=? AND password=?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    System.out.println("Agent login successful.");
                    System.out.println("_________________________");
                    // Perform agent actions
                    performAgentActions();
                } else {
                    System.out.println("Invalid agent username or password. Please try again.");
                    System.out.println("______________________________");
                }
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("Check the connection");
        }

    }

    public static void performAdminActions() {
        // Admin actions implementation
        System.out.println("Performing admin actions...");
        AdminProductActions apa = new AdminProductActions();
        apa.productdetails();
        apa.addProduct();
        apa.viewProducts();
        
    }

    public static void performAgentActions() {
        // Agent actions implementation
        System.out.println("Performing agent actions...");
        AgentProduct ag = new AgentProduct();
        ag.productdetails();
        ag.addProduct();
        ag.sellProducts();
        
    }
}