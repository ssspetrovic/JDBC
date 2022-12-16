package rs.ac.uns.ftn.db.jdbc.prepared_statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import rs.ac.uns.ftn.db.jdbc.connection.ConnectionUtil_Basic;

public class Example04_DDL_DML_QL {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		createTableFazeProjekta();

		handleMenu();

		sc.close();
	}

	private static void handleMenu() {
		String response = null;

		do {
			System.out.println("\nChoose an option:");
			System.out.println("1 - Select all rows from ");
			System.out.println("2 - Select row from faze_projekta by spr and sfp");
			System.out.println("3 - Insert row into faze_projekta");
			System.out.println("4 - Modify existing row from faze_projekta");
			System.out.println("5 - Delete existing row from faze_projekta");
			System.out.println("6 - Drop table faze_projekta");
			System.out.println("X - Exit");

			response = sc.nextLine();
			try {
				switch (response) {
				case "1":
					selectAll();
					break;
				case "2":
					selectBySprAndSfp();
					break;
				case "3":
					insertRow();
					break;
				case "4":
					modifyRow();
					break;
				case "5":
					deleteRow();
					break;
				case "6":
					dropTableFazeProjekta();
					break;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} while (!response.equalsIgnoreCase("X"));
	}

	private static void selectAll() throws SQLException {
		String query = "SELECT spr, sfp, rukfp, nafp, datp FROM faze_projekta";

		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			// checks if the result set is empty
			if (!resultSet.isBeforeFirst()) {
				System.out.println("No data found!");
			} else {
				System.out.printf("%-4s %-4s %-5s %-10s %-20s\n", "SPR", "SFP", "RUKFP", "NAFP", "DATP");

				while (resultSet.next()) {
					System.out.printf("%-4s %-4s %-5s %-10s %-20s\n", resultSet.getString(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
				}
			}
		}
	}

	private static void selectBySprAndSfp() throws SQLException {
		String query = "SELECT spr, sfp, rukfp, nafp, datp FROM faze_projekta WHERE spr = ? AND sfp = ?";

		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			System.out.println("SPR: ");
			preparedStatement.setInt(1, Integer.parseInt(sc.nextLine()));
			System.out.println("SFP: ");
			preparedStatement.setInt(2, Integer.parseInt(sc.nextLine()));

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (!resultSet.isBeforeFirst()) {
					System.out.println("No rows selected!");
				} else {
					System.out.printf("%-4s %-4s %-5s %-10s %-20s\n", "SPR", "SFP", "RUKFP", "NAFP", "DATP");

					while (resultSet.next()) {
						System.out.printf("%-4s %-4s %-5s %-10s %-20s\n", resultSet.getString(1),
								resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
								resultSet.getString(5));
					}
				}
			}
		}
	}

	private static void insertRow() throws SQLException {
		String command = "INSERT INTO faze_projekta (spr, sfp, rukfp, nafp, datp)\n"
				+ "VALUES (?, ?, ?, ?, to_date(?, 'dd.MM.yyyy.'))";

		System.out.println("SPR: ");
		String spr = sc.nextLine();
		System.out.println("SFP: ");
		String sfp = sc.nextLine();
		System.out.println("RUKFP: ");
		String rukfp = sc.nextLine();
		System.out.println("NAFP: ");
		String nafp = sc.nextLine();
		System.out.println("DATP: ");
		String datp = sc.nextLine();

		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);) {
			preparedStatement.setInt(1, Integer.parseInt(spr));
			preparedStatement.setInt(2, Integer.parseInt(sfp));
			preparedStatement.setInt(3, Integer.parseInt(rukfp));
			preparedStatement.setString(4, nafp);
			preparedStatement.setString(5, datp);

			preparedStatement.executeUpdate();

			System.out.println("Row successfully inserted!");
		}
	}

	private static void modifyRow() throws SQLException {
		String command = "UPDATE faze_projekta SET rukfp = ?, nafp = ?, datp = to_date(?, 'dd.MM.yyyy.') WHERE spr = ? AND sfp = ?";

		System.out.println("SPR: ");
		String spr = sc.nextLine();
		System.out.println("SFP");
		String sfp = sc.nextLine();
		System.out.println("RUKFP: ");
		String rukfp = sc.nextLine();
		System.out.println("NAFP: ");
		String nafp = sc.nextLine();
		System.out.println("DATP: ");
		String datp = sc.nextLine();

		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);) {

			preparedStatement.setInt(1, Integer.parseInt(rukfp));
			preparedStatement.setString(2, nafp);
			preparedStatement.setString(3, datp);
			preparedStatement.setInt(4, Integer.parseInt(spr));
			preparedStatement.setInt(5, Integer.parseInt(sfp));

			System.out.printf("%d row(s) affected by update!", preparedStatement.executeUpdate());
		}
	}
	
	private static void deleteRow() throws SQLException {
		String command = "DELETE FROM faze_projekta WHERE spr = ? AND sfp = ?";

		System.out.println("SPR: ");
		String spr = sc.nextLine();
		System.out.println("SFP: ");
		String sfp = sc.nextLine();

		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);) {

			preparedStatement.setInt(1, Integer.parseInt(spr));
			preparedStatement.setInt(2, Integer.parseInt(sfp));

			System.out.printf("%d row(s) affected by delete!", preparedStatement.executeUpdate());
		}
	}


	private static final boolean executeSimple(String command) throws SQLException {
		try (Connection connection = ConnectionUtil_Basic.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(command);) {
			return preparedStatement.execute(command);
		}
	}

	private static void createTableFazeProjekta() {
		String command = "CREATE TABLE faze_projekta(\n" + "spr INT," + "sfp INT," + "rukfp int," + "nafp VARCHAR2(10),"
				+ "datp DATE," + "CONSTRAINT FP_PK PRIMARY KEY (spr, sfp),"
				+ "CONSTRAINT FP_FK FOREIGN KEY (rukfp) REFERENCES radnik(mbr)" + ")";

		try {
			executeSimple(command);
			System.out.println("Table faze_projekta succesfully created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void dropTableFazeProjekta() throws SQLException {
		String command = "DROP TABLE faze_projekta";

		try {
			executeSimple(command);
			System.out.println("Table faze_projekta succesfully dropped!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
