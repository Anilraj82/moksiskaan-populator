package edu.university.pathwaycommons.PathwayCommons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is responsible for populating database.
 * First, it connects to database and creates table if not exists.
 * Then it reads the extracted files. The reaction type is converted into numerical entity
 * and inserted into database.
 */

public class dataUpload{
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		File folder = new File(System.getProperty("user.home") + "/eclipse/SeleniumDownloads");
		File[] listOfFiles = folder.listFiles();

		Connection conn = null;
		Statement st = null;
		try {
			Class.forName("org.postgresql.Driver");
			// connection to database, port no used here is 5433
			// username is "postgres" and password is "password"
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "password");
			st = conn.createStatement();
			// removes table if exists
			String sqll = "DROP TABLE IF EXISTS moksiskaan";
			st.executeUpdate(sqll);
			System.out.println("Table dropped.");

			// create new table
			String sql = "CREATE TABLE moksiskaan (gene1 VARCHAR NOT NULL, reaction_type VARCHAR NOT NULL, gene2 VARCHAR NOT NULL)";
			st.executeUpdate(sql);
			System.out.println("Table created.");
			} 
		catch (Exception e) {
			// TODO: handle exception but here just print the exception to debug it
			System.out.println(e);
			}

			for (int i = 0; i < listOfFiles.length; i++) {
				String fileName = listOfFiles[i].getName();
				if (listOfFiles[i].isFile() & fileName.endsWith(".txt")) {
					// reads files for genes and reaction type
					File input = new File(System.getProperty("user.home") + "/eclipse/SeleniumDownloads/" + fileName);
					Scanner scan = new Scanner(input);
					
					// Skips the first line since it contains only header info
					scan.nextLine();
					String regex = "(\\t)+";

					HashSet<String> set = new HashSet<String>();

					while (scan.hasNext()) {
						String codeInt = "";
						// splits the text according to tab
						String[] row = scan.nextLine().split(regex);

						codeInt = updateCode(row[1]);
						set.add((row[0] + ' ' + codeInt + ' ' + row[2]));
					}
					
					Iterator<String> itr = set.iterator();
					while (itr.hasNext()) {
						// Now add into database
						
						String[] items = itr.next().split(" ");
						//create the command that uploads the data
						String sq = String.format("INSERT INTO moksiskaan (Gene1, reaction_type, gene2) VALUES('%s', '%s', '%s')", items[0],items[1], items[2]);
						st.executeUpdate(sq);
					}
					scan.close();
				}
			}
			
			for (File file : listOfFiles) {
				if (file.isFile()) {
					System.out.println("Inserting data from " + file.getName());
				}
			}
			
			System.out.println("Data inserted successfully.");
			st.close();
			conn.close();
		}
	
		/**
		 * 
		 * @param row
		 * @param codeInt
		 * @return
		 * Converts each reaction type to numerical entities
		 * These numerical entities are just for prototyping
		 */
		public static String updateCode(String row) {
			// converts reaction types to entity
			String codeInt = "";
			if (row.equals("interacts-with")) {
				codeInt = "101";
			} else if (row.equals("controls-expression-of")) {
				codeInt = "102";
			} else if (row.equals("controls-state-change-of")) {
				codeInt = "103";
			} else if (row.equals("controls-phosphorylation-of")) {
				codeInt = "104";
			} else if (row.equals("neighbor-of")) {
				codeInt = "105";
			} else if (row.equals("chemical-affects")) {
				codeInt = "106";
			} else if (row.equals("consumption-controlled-by")) {
				codeInt = "107";
			} else if (row.equals("used-to-produce")) {
				codeInt = "108";
			} else if (row.equals("controls-transport-of-chemical")) {
				codeInt = "109";
			} else if (row.equals("reacts-with")) {
				codeInt = "110";
			} else if (row.equals("controls-production-of")) {
				codeInt = "111";
			} else if (row.equals("catalysis-precedes")) {
				codeInt = "112";
			} else if (row.equals("controls-transport-of")) {
				codeInt = "113";
			} else {
				// For in-complex-with reaction types
				codeInt = "114";
			}
			
			return codeInt;
		}
	}