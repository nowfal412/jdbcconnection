package onlineshopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }


        Connection con = null;

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/rvvn", "root", "123456789");

        } catch (SQLException e) {

            e.printStackTrace();

        }
        if (con !=null)
        	System.out.println("connection success");
        else
        	System.out.println("try again");

	}

}
