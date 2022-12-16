package rs.ac.uns.ftn.db.jdbc.zadaci;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;

public class Zadatak03 {

	public static void main(String[] args) {
		try {			
			printBosses();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void printBosses() throws SQLException {
		String query = "SELECT r1.ime || ' ' || r1.prz ime_prz, COUNT(r2.mbr), r1.plt, r1.mbr FROM radnik r1\n"
				+ "LEFT OUTER JOIN radnik r2 ON r1.mbr = r2.sef\n"
				+ "WHERE r2.sef IS NOT NULL\n"
				+ "GROUP BY r1.ime, r1.prz, r1.plt, r1.mbr";
		
		int sef = -1;
		
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			if (!resultSet.isBeforeFirst()) {
				System.out.println("no rows selected!");
			} else {
				while (resultSet.next()) {
					System.out.printf("%-15s, %-4d, %-10d\n", resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3));
					sef = resultSet.getInt(4);
					try {
						printWorkers(connection, sef);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private static void printWorkers(Connection connection, int sef) throws SQLException {
		String query = "SELECT r1.ime || ' ' || r1.prz as ime_prz_2, r1.plt FROM radnik r1, radnik r2\n"
				+ "WHERE r1.sef = r2.mbr AND r1.sef = " + sef;
		
		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			if (!resultSet.isBeforeFirst()) {
				System.out.println("no rows selected!");
			} else {
				while (resultSet.next()) {
					System.out.printf("\t%-25s, %-10d\n", resultSet.getString(1), resultSet.getInt(2));
				}
			}
		}
	}
}
