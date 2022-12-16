package rs.ac.uns.ftn.db.jdbc.zadaci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;

public class Zadatak02 {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		try {
			listWorkersNumber();			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}
	
	private static void listWorkersNumber() throws SQLException {
		String query = "SELECT COUNT(mbr) FROM radproj WHERE spr = ? AND brc > ?";
		
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			System.out.println("SPR: ");
			preparedStatement.setInt(1, Integer.parseInt(sc.nextLine()));
			System.out.println("BRC: ");
			preparedStatement.setInt(2, Integer.parseInt(sc.nextLine()));
			
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				
				if (!resultSet.isBeforeFirst()) {
					System.out.println("No data found!");
				} else {
					resultSet.next();
					System.out.printf("%-4s\n", "BROJ RADNIKA");
					System.out.printf("%-4d", resultSet.getInt(1));
				}
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} 
	}
}
