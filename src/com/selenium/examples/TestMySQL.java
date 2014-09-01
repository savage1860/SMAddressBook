package com.selenium.examples;

import java.util.Formatter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.mysql.jdbc.Driver.*; 

public class TestMySQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String PrepearedQuery = "SELECT T01.Code, T01.Name, T01.Continent FROM WORLD.COUNTRY T01 WHERE T01.Code LIKE 'U%'";
    	Formatter fmt;
        String fmtString = "%-5s%2s%-40s%2s%-20s";
    	
    	try {
    		// Load MySQL driver
    		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    		
    		// Connect to the database
            Connection conn;
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","admin123");
    		
    		// Query the test table 
            Statement stmt = conn.createStatement(); 
            ResultSet rset = stmt.executeQuery(PrepearedQuery);
            
            // Print the result set out 
            System.out.println();
            fmt = new Formatter();
            fmt.format(fmtString, "CODE", " ", "NAME", " ", "CONTINENT");
            System.out.println(fmt);
            while (rset.next())
            {
               //System.out.print(rset.getString(1).trim() + "  ");
               //System.out.println(rset.getString(2));
               fmt = new Formatter();
               //fmtString = "%-5s%2s%-40s%2s%-20s";
               //fmtString = "%3s%2s%.2s%" + (4-rset.getString(2).trim().length()) + "s%.20s%" + (22-rset.getString(3).trim().length()) + "s%.25s";
               fmt.format(fmtString, rset.getString(1).trim(), " ", rset.getString(2).trim(), " ", rset.getString(3).trim());
               System.out.println(fmt);
            }

            //close the result set, statement, and the connection
            rset.close();
            stmt.close();
            conn.close();
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

}
