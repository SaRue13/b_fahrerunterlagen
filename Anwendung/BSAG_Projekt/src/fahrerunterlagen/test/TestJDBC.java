package fahrerunterlagen.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String jdbcUrl = "jdbc:postgresql://localhost:5432/fahrerunterlagen";
		String user = "postgres";
		String pass = "secret?passwort";
		
		try {
			System.out.println("Connecting to" + jdbcUrl);
			
			Connection myCon = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
