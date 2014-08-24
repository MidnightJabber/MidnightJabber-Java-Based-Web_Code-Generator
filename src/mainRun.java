import java.io.*;
import java.sql.Savepoint;
import java.util.ArrayList;

public class mainRun {

	public static void displayMenu() {
		System.out.println();
		System.out
				.println("                                     <Movie Functions>");
		System.out.println("1. Add a Movie");
		System.out.println("2. Find a Movie");
		System.out.println("3. Edit a Movie");
		System.out.println("4. Delete a Movie");

		System.out.println("\n\n");

		System.out
				.println("                                     <File Functions>");
		System.out.println("5. Open file");
		System.out.println("6. Save Lists");
		System.out.println("7. Display Entire Movie Lists");
		System.out.println();
		System.out.println("8. Sort the Lists (By Release Dates)");
		System.out.println("9. Save Code as TXT / Generate Code");
		System.out.println();
		System.out.println("10. Sort Alphabeticaly");
		System.out.println("11. Generate Alphabetical Code");
		System.out.println();
		System.out.println("12. Do all the saving and generating.");

		System.out.println("\n\n");

		System.out
				.println("                                     <Awards Functions>");
		System.out.println("13. Retrieve Awards List");
		System.out.println("14. Display Awards List");
		System.out.println();
		System.out.println("15. Add Award to Award Bank");
		System.out.println("16. Delete Award from Award Bank");
		System.out.println("17. Display Award Bank");

		System.out.println("\n0. Quit\n\n");
	}

	public static void main(String args[]) throws IOException,
			ClassNotFoundException {

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		allFunctions obj = new allFunctions();

		int n;
		ArrayList<Integer> useless = new ArrayList<Integer>();

		// automatically opening file
		obj.openFile();
	
		do {

			displayMenu();
			n = Integer.parseInt(br.readLine());

			switch (n) {
			case 0:
				System.out.println("Are you sure you want to exit? (y/n)");
				char ans = (char) br.read();
				if (ans == 'y') {
					exit(0);
				}
				break;

			case 1:
				obj.getMovie();
				obj.saveFile();
				System.out
						.println("\nAddition has been saved to (.SER) file!\n");
				break;

			case 2:
				useless = obj.findAMovie();
				break;

			case 3:
				obj.editMovie();
				obj.saveFile();
				System.out.println("\nChanges have been saved!\n");
				break;

			case 4:
				obj.deleteMovie();
				obj.saveFile();
				System.out.println("\nChanges have been saved!\n");
				break;

			case 5:
				obj.openFile();
				System.out.println("File Opened!\n");
				break;

			case 6:
				obj.saveFile();
				System.out.println("\nFile Has been saved!\n");
				break;

			case 7:
				obj.displayLists();
				break;

			case 8:
				obj.sortListsByReleaseDates();
				obj.saveFile();
				System.out.println("List has been Sorted and Saved!\n");
				break;

			case 9:
				obj.saveCodetoFile();
				System.out.println("Saved To File!\n");
				break;

			case 10:
				obj.sortListsAlphabetically();
				System.out.println("List has been Sorted but NOT Saved!\n");
				break;

			case 11:
				obj.saveAlphabeticalCodetoFile();
				System.out.println("Saved To File!\n");
				break;

			case 12:
				obj.sortListsByReleaseDates();
				obj.saveFile();
				obj.saveCodetoFile();
				obj.sortListsAlphabetically();
				obj.saveAlphabeticalCodetoFile();
				break;

			case 13:
				obj.retrieveAwards();
				break;

			case 14:
				obj.displayAwardsList();
				break;

			case 15:
				obj.updateAwardBank();
				obj.saveFile();
				break;

			case 16:
				obj.deleteFromAwardBank();
				obj.saveFile();
				break;

			case 17:
				obj.displayAwardBankList();

				break;

			default:
				System.out.println("Wrong Option");
				break;
			}

		} while (n != 0);

	}

	private static void exit(int i) {
		// TODO Auto-generated method stub

	}
}