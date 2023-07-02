package onlineshopping;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AgentProduct {
    private static final String DB_URL = "jdbc:mysql://localhost:3308/rvvn";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456789";

    public void productdetails() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Agent Product Actions:");
            System.out.println("1. Buy products");
            System.out.println("2. Sell Products");
            System.out.println("0. Logout");
            System.out.println("___________________________");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    sellProducts();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter productID: ");
        String productID = sc.nextLine();
        System.out.print("Enter productname: ");
        String productname = sc.nextLine();
        System.out.print("Enter productprice: ");
        int productprice = sc.nextInt();
        System.out.print("Enter product quantity: ");
        double productquantity = sc.nextDouble();

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            String query = "INSERT INTO productdetails (productID,productname,productprice,productquantity) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, productID);
            ps.setString(2, productname);
            ps.setInt(3,productprice);
            ps.setDouble(4, productquantity);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

public void sellProducts() {
    	    Connection con = null;
    	    PreparedStatement ps = null;
    	    ResultSet rs = null;
    	    Scanner sc = new Scanner(System.in);

    	    try {
    	        con = getConnection();

    	        System.out.print("Enter Product ID: ");
    	        int productId = sc.nextInt();

    	        System.out.print("Enter Quantity: ");
    	        int quantity = sc.nextInt();

    	        String query = "SELECT productprice,productquantity FROM productdetails WHERE productID = ?";
    	        ps = con.prepareStatement(query);
    	        ps.setInt(1, productId);

    	        rs = ps.executeQuery();

    	        if (rs.next()) {
    	        	int price = rs.getInt("productprice");
    	            int availableQuantity = rs.getInt("productquantity");
    	            if (quantity <= availableQuantity) {
    	            	int totalPrice = price * quantity;
    	                System.out.println("Product is available. Selling " + quantity + " units.");
    	                System.out.println("Total price is "+totalPrice);
    	                System.out.println("Products sold successfully!");
    	                System.out.println("______________________________");
    	            } else {
    	                System.out.println("Insufficient quantity. Available quantity: " + availableQuantity);
    	                System.out.println("______________________________");
    	            }
    	        } else {
    	            System.out.println("Product not found.");
    	            System.out.println("___________________________________");
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        try {
    	            if (rs != null) {
    	                rs.close();
    	            }
    	            if (ps != null) {
    	                ps.close();
    	            }
    	            if (con != null) {
    	                con.close();
    	            }
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
    	}

    }