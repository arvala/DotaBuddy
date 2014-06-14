
import java.sql.*;
public class DotaBuddy {

	private static Connection kanta = null;
	
	public static Connection alusta() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			kanta = DriverManager.getConnection("jdbc:postgresql:Dota2", "postgres", "admin");
		}
		catch (Exception e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
		return kanta;
	}
		
	public static void sulje() {
		try {
			kanta.close();
		}
		catch (SQLException e) {
			System.out.println("Virhe tietokantakerroksessa: " + e);
		}
	}
	
}
