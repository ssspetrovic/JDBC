package rs.ac.uns.ftn.db.jdbc.zadaci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;

public class Zadatak04 {
	
	public static void main(String[] args) {	
		try {
			addHireWorker();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void addHireWorker() throws SQLException {

		String command = "INSERT INTO radnik (mbr, ime, prz, plt, sef, pre, god)\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, to_date(?, 'dd.MM.yyyy.'))";
		
		String query = "SELECT spr FROM PROJEKAT\n"
				+ "WHERE spr <= ALL(SELECT spr FROM projekat)";
	
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			
			connection.setAutoCommit(false);
			
			if (!resultSet.isBeforeFirst()) {
				System.out.println("Failed to found expected project.");
			} else {
				Scanner sc = new Scanner(System.in);
				
				// inserting worker
				System.out.println("Uneti podatke za radnika:");
				System.out.println("MBR: ");
				int mbr = Integer.parseInt(sc.nextLine());
				preparedStatement.setInt(1, mbr);
				System.out.println("IME: ");
				preparedStatement.setString(2, sc.nextLine());
				System.out.println("PRZ: ");
				preparedStatement.setString(3, sc.nextLine());
				System.out.println("PLT: ");
				preparedStatement.setInt(4, Integer.parseInt(sc.nextLine()));
				System.out.println("SEF: ");
				preparedStatement.setInt(5, Integer.parseInt(sc.nextLine()));
				System.out.println("PRE: ");
				preparedStatement.setInt(6, Integer.parseInt(sc.nextLine()));
				System.out.println("GOD: ");
				preparedStatement.setString(7, sc.nextLine());
				
				preparedStatement.execute();
				System.out.println("Worker successfully added!");
				
				// hiring worker
				resultSet.next();
				int spr = resultSet.getInt(1);
				int brc = 0;
				
				String hireWorker = "INSERT INTO radproj (spr, mbr, brc)\n"
						+ "VALUES (" + spr + ", " + mbr + ", " + brc + ")";
				
				try (Statement statement2 = connection.createStatement()) {
					statement2.execute(hireWorker);
					System.out.println("Worker successfully hired!");
				}
				
				// commit / rollback
				String response = null;
				System.out.println("\nCommit or rollback?");
				System.out.println("1 - Commit");
				System.out.println("2 - Rollback");

				response = sc.nextLine();
				sc.close();
				
				switch (response) {
				case "1":
					connection.commit();
					System.out.println("Commit successful!");
					break;
				case "2":
					connection.rollback();
					System.out.println("Rollback successful!");
				default:
					System.out.println("Option not matched! Rolling back changes...");
					connection.rollback();
					System.out.println("Rollback successful!");
				}
			}
		}
	}
}
