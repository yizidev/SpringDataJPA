package me.wonana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        String url="jdbc:postgresql://localhost:5432/springdata";
        String username = "wona";
        String password = "pass";
        
        try(Connection con = DriverManager.getConnection(url, username, password)){
        	System.out.println("Connection created: " + con);
        	String sql = "CREATE TABLE ACCOUNT (id int, username varchar(255), password varchar(255))";
        	String sql2 = "INSERT INTO ACCOUNT VALUES(1, 'wona', 'pass');";
        	try(PreparedStatement statement = con.prepareStatement(sql2)){
        		statement.execute();
        	}
        }
    }
}
