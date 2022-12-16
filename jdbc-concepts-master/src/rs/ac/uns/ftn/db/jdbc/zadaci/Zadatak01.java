package rs.ac.uns.ftn.db.jdbc.zadaci;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;

public class Zadatak01 {
	public static void main(String[] args) {
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(generateQuery());) {
			
			System.out.printf("%-4s %-8s %-8s\n", "MBR", "IME", "PREZIME");
			
			// iterating through resultSet
			while (resultSet.next()) {
				int mbr = resultSet.getInt(1);
				String ime = resultSet.getString(2);
				String prezime = resultSet.getString(3);
				
				System.out.printf("%-4d %-8.8s %-8.8s\n", mbr, ime, prezime);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String generateQuery() {
		String query = "SELECT mbr, ime, prz FROM radnik\n"
				+ "WHERE mbr IN (\n"
				+ " SELECT mbr FROM radproj\n"
				+ "	WHERE spr = 10\n"
				+ ") AND mbr NOT IN (\n"
				+ "	SELECT mbr FROM radproj\n"
				+ "	WHERE spr = 30\n"
				+ ")";
		
		return query;
	}
}
