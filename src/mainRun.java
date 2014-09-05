import java.io.*;
import java.sql.Savepoint;
import java.util.ArrayList;

public class mainRun
{

	public static void displayMenu()
	{
		System.out.println();
		System.out.println("<Movie Functions>			<File Functions>				<Awards Functions>");
		System.out.println();
		System.out.println("1. Add a Movie				5. Open file					13. Retrieve Awards List");
		System.out.println("2. Find a Movie				6. Save Lists					14. Display Retrieved Awards List");
		System.out.println("3. Edit a Movie				7. Display Entire Movie Lists					");
		System.out.println("4. Delete a Movie									15. Add Award to Award Bank");
		System.out.println("					8. Sort the Lists (By Release Dates)		16. Edit Award in Award Bank");
		System.out.println("					9. Save Code as TXT / Generate Code		17. Delete Award From Award Bank");
		System.out.println("											18. Display Award Bank");
		System.out.println("					10. Sort Alphabeticaly				19. Generate Awards Code (of last retrieved awards)");
		System.out.println("					11. Generate Alphabetical Code			");
		System.out.println("					12. Do all the saving and generating	");
			
		
		System.out.println("_____________________________________________________________________________________________________________________________________________");
		System.out.println("");
		System.out.println("<Author Functions>");
		System.out.println("");
		System.out.println("20. Find An Author");
		System.out.println("21. Find An Authors Works");
		System.out.println("");
		System.out.println("22. Generate Authors Code ");
		System.out.println("");
		System.out.println("23. Add New Auhtor To Author Bank");
		System.out.println("24. Edit Author Bank");
		System.out.println("25. Delete Author (from Bank)");
		System.out.println("26. Display Author Bank");
		
		
		

		
		System.out.println("\n\n");
		System.out.println("\n0. Quit\n\n");
	}

	public static void main(String args[]) throws IOException,
			ClassNotFoundException, InterruptedException
	{

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		allFunctions obj = new allFunctions();

		int n;
		ArrayList<Integer> useless = new ArrayList<Integer>();

		// automatically opening file
		obj.openFile();

		do
		{

			displayMenu();
			n = Integer.parseInt(br.readLine());

			switch (n)
			{
				case 0:
					System.out.println("Are you sure you want to exit? (y/n)");
					char ans = (char) br.read();
					if(ans == 'y')
					{
						exit(0);
					}
					break;

				case 1:
					obj.getMovie();
					obj.saveFile();
					System.out.println("\nAddition has been saved to (.SER) file!\n");
					Thread.sleep(3000);
					break;

				case 2:
					useless = obj.findAMovie();
					break;

				case 3:
					obj.editMovie();
					obj.saveFile();
					System.out.println("\nChanges have been saved!\n");
					Thread.sleep(3000);
					break;

				case 4:
					obj.deleteMovie();
					obj.saveFile();
					System.out.println("\nChanges have been saved!\n");
					Thread.sleep(3000);
					break;

				case 5:
					obj.openFile();
					System.out.println("File Opened!\n");
					Thread.sleep(3000);
					break;

				case 6:
					obj.saveFile();
					System.out.println("\nFile Has been saved!\n");
					Thread.sleep(3000);
					break;

				case 7:
					obj.displayLists();
					break;

				case 8:
					obj.sortListsByReleaseDates();
					obj.saveFile();
					System.out.println("List has been Sorted and Saved!\n");
					Thread.sleep(3000);
					break;

				case 9:
					obj.saveCodetoFile();
					System.out.println("Saved To File!\n");
					Thread.sleep(3000);
					break;

				case 10:
					obj.sortListsAlphabetically();
					System.out.println("List has been Sorted but NOT Saved!\n");
					Thread.sleep(3000);
					break;

				case 11:
					obj.saveAlphabeticalCodetoFile();
					System.out.println("Saved To File!\n");
					Thread.sleep(3000);
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
					System.out.println("\nAwards Retrieved!\n");
					Thread.sleep(3000);
					break;

				case 14:
					obj.displayAwardsList();
					break;

				case 15:
					obj.updateAwardBank();
					obj.saveFile();
					break;

				case 16:
					obj.editAwardBank();
					obj.saveFile();
					break;	
					
				case 17:
					obj.deleteFromAwardBank();
					obj.saveFile();
					break;

				case 18:
					obj.displayAwardBankList();
					break;

				case 19: 
					obj.generateAwardCode();
					break;	
					
				case 20:
					obj.findAnAuthor();
					break;

				case 21:
					obj.findAnAuthorsWorks();
					break;

				case 22:
					obj.generateAuthorsCode();
					obj.saveFile();
					break;

				case 23:
					obj.addNewAuthor();
					obj.saveFile();
					break;	
					
				case 24:
					obj.editAuthorBank();
					obj.saveFile();
					break;
					
				case 25:
					obj.deleteAuthor();
					obj.saveFile();
					break;

				case 26:
					obj.displayAuthorBank();
					break;

				

				default:
					System.out.println("Wrong Option");
					break;
			}

		}
		while (n != 0);

	}

	private static void exit(int i)
	{
		// TODO Auto-generated method stub

	}
}
