import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class allFunctions implements Serializable {

	ArrayList<String> movieNames = new ArrayList<String>();
	ArrayList<Integer> releaseDates = new ArrayList<Integer>();
	ArrayList<String> imageURLs = new ArrayList<String>();
	ArrayList<String> imageHREFs = new ArrayList<String>();
	ArrayList<String> awards = new ArrayList<String>();
	ArrayList<String> IMDBawardsPageURLs = new ArrayList<String>();
	ArrayList<awardBank> awardsBank = new ArrayList<awardBank>();

	public void saveFile() throws IOException {

		String fileName = "moviesPage.ser";

		// Serializing to file
		FileOutputStream fout = new FileOutputStream(fileName, false);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(movieNames);
		oos.writeObject(releaseDates);
		oos.writeObject(imageURLs);
		oos.writeObject(imageHREFs);
		oos.writeObject(IMDBawardsPageURLs);
		oos.writeObject(awardsBank);
		oos.close();

	}

	public void deleteMovie() throws IOException {

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAMovie();

		String temp;
		int terminal, i, n, m = 0;

		if (idx.size() == 1) {
			i = idx.get(0);
		}

		else if (idx.size() == 0) {
			char tmp;
			System.out
					.println("No Movie Found of that Name. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if (tmp == 'y') {
				deleteMovie();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return;
			} else {
				return;
			}

		}

		else {
			do {
				if (m == 1) {
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			} while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out
				.println("\n\n ------------Movie Selected to Be Deleted------------");
		System.out.println("\n 1. Movie Name: " + movieNames.get(i));
		System.out.println("\n 2. Release Date: " + releaseDates.get(i));
		System.out.println("\n 3. Image URL: " + imageURLs.get(i));
		System.out.println("\n 4. Image HREF: " + imageHREFs.get(i));
		System.out.println("\n 5. IMDB Awards URL: "
				+ IMDBawardsPageURLs.get(i));

		System.out
				.println("\nAre you sure you want to delete this movie? (y/n)");
		temp = br.readLine();

		if (temp.equalsIgnoreCase("y")) {
			movieNames.remove(i);
			releaseDates.remove(i);
			imageURLs.remove(i);
			imageHREFs.remove(i);
			IMDBawardsPageURLs.remove(i);
		}

		else {
			System.out.println("\nSkipped Deletion!\n");
		}

	}

	public ArrayList<Integer> findAMovie() throws IOException {
		System.out
				.println("                                      Searching For a Movie                                      ");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		String searchString, subString;
		int flg = 0, len, i, j, k, cntr = 0;
		char ch;

		System.out.println("Enter The Movie Name: \n");
		searchString = br.readLine();

		len = searchString.length();

		for (i = 0; i < movieNames.size(); i++) {

			for (k = 0, j = len; j <= movieNames.get(i).length(); k++, j++) {
				subString = movieNames.get(i).substring(k, j);

				if (searchString.equalsIgnoreCase(subString)) {
					cntr = cntr + 1;
					indexes.add(i);

					System.out.print("\n" + cntr + ") ");
					System.out.println("\n  1. Movie Name: "
							+ movieNames.get(i));
					System.out.println("\n  2. Release Date: "
							+ releaseDates.get(i));
					System.out.println("\n  3. Image URL: " + imageURLs.get(i));
					System.out.println("\n  4. Image HREF: "
							+ imageHREFs.get(i));
					System.out.println("\n  5. IMDB Awards URL: "
							+ IMDBawardsPageURLs.get(i));

					flg = 1;

					break;
				}
			}

		}
		if (flg == 0 || movieNames.isEmpty()) {
			System.out.println("\n Sorry, Couldn't Find A Movie..");
		}

		System.out.println("\nPress 0 Key to Continue...\n");
		while (true) {
			ch = (char) br.read();
			if (ch == '0')
				return indexes;
		}

	}

	public void editMovie() throws IOException {

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAMovie();

		String temp;
		int terminal, i, n = 0, m = 0;

		if (idx.size() == 1) {
			i = idx.get(0);
		}

		else if (idx.size() == 0) {
			char tmp;
			System.out
					.println("No Movie Found of that Name. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if (tmp == 'y') {
				editMovie();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return;
			} else {
				return;
			}

		}

		else {

			do {
				if (m == 1) {
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			} while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out
				.println("\n\n ------------Movie Selected to Be Edited------------");
		System.out.println("\n 1. Movie Name: " + movieNames.get(i));
		System.out.println("\n 2. Release Date: " + releaseDates.get(i));
		System.out.println("\n 3. Image URL: " + imageURLs.get(i));
		System.out.println("\n 4. Image HREF: " + imageHREFs.get(i));
		System.out.println("\n 5. IMDB Awards URL: "
				+ IMDBawardsPageURLs.get(i));
		System.out.println("\n 6. Exit");

		System.out.println("\n What do you want to edit?\n");
		n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println("\nEnter new Name: ");
			temp = br.readLine();
			movieNames.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else if (n == 2) {
			System.out.println("\nEnter new Release Date: ");
			temp = br.readLine();
			releaseDates.set(i, Integer.parseInt(temp));
			System.out.println("\nSuccessfully Edited!");
		}

		else if (n == 3) {
			System.out.println("\nEnter new Image URL: ");
			temp = br.readLine();
			imageURLs.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		} else if (n == 4) {
			System.out.println("\nEnter new HREF: ");
			temp = br.readLine();
			imageHREFs.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else if (n == 5) {
			System.out.println("\nEnter new IMDB URL: ");
			temp = br.readLine();
			IMDBawardsPageURLs.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else {

		}

	}

	@SuppressWarnings("unchecked")
	public void openFile() throws IOException, ClassNotFoundException{

		String fileName = "moviesPage.ser";

		// Serializing from file

		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		
		movieNames = (ArrayList<String>) in.readObject();
		releaseDates = (ArrayList<Integer>) in.readObject();
		imageURLs = (ArrayList<String>) in.readObject();
		imageHREFs = (ArrayList<String>) in.readObject();
		IMDBawardsPageURLs = (ArrayList<String>) in.readObject();
		awardsBank = (ArrayList<awardBank>) in.readObject();

		in.close();
		fileIn.close();

	}

	public void getMovie() throws IOException {

		String year, name, date, imageURL, imageHREF, imdbURL;
		int intyear;

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		System.out.println("Enter the name of the movie:  ");
		name = br.readLine();

		System.out.println("\nEnter the Date of Realese of the movie:  ");
		date = br.readLine();
		releaseDates.add(Integer.parseInt(date));

		/* Getting year from the date */
		intyear = (Integer.parseInt(date)) / 10000;

		year = intyear + ""; // Converting year(integer) to year(String)
		name = name + " (" + year + ")"; // Adding year to the name

		movieNames.add(name); // Finally adding the moviename

		System.out.println("\nEnter the URL of the Movie's Poster:  ");
		imageURL = br.readLine();
		imageURLs.add(imageURL);

		System.out
				.println("\nEnter the Destination URL of the Movie's Poster and Heading:  ");
		imageHREF = br.readLine();
		imageHREFs.add(imageHREF);

		System.out.println("\nEnter the URL of the IMDB Awards Page:  ");
		imdbURL = br.readLine();
		IMDBawardsPageURLs.add(imdbURL);

		System.out.println("\nYou have entered: \n");
		System.out.println("Movie:  " + name);
		System.out.println("Date:  " + date);
		System.out.println("Poster URL:  " + imageURL);
		System.out.println("HREF:  " + imageHREF);
		System.out.println("IMDB URL:  " + imdbURL);

	}

	public void sortListsByReleaseDates() {
		int i, j, tmp;
		String temp;

		for (i = 0; i < releaseDates.size(); i++) {
			for (j = 0; j < releaseDates.size() - 1; j++) {
				if (releaseDates.get(j) < releaseDates.get(j + 1)) {
					tmp = releaseDates.get(j);
					releaseDates.set(j, releaseDates.get(j + 1));
					releaseDates.set(j + 1, tmp);

					temp = movieNames.get(j);
					movieNames.set(j, movieNames.get(j + 1));
					movieNames.set(j + 1, temp);

					temp = imageURLs.get(j);
					imageURLs.set(j, imageURLs.get(j + 1));
					imageURLs.set(j + 1, temp);

					temp = imageHREFs.get(j);
					imageHREFs.set(j, imageHREFs.get(j + 1));
					imageHREFs.set(j + 1, temp);

					temp = IMDBawardsPageURLs.get(j);
					IMDBawardsPageURLs.set(j, IMDBawardsPageURLs.get(j + 1));
					IMDBawardsPageURLs.set(j + 1, temp);

				}
			}
		}
	}

	public void sortListsAlphabetically() {
		int i, j, tmp;
		String temp;

		for (i = 0; i < releaseDates.size(); i++) {
			for (j = 0; j < releaseDates.size() - 1; j++) {
				if (movieNames.get(j).compareTo(movieNames.get(j + 1)) > 0) {
					tmp = releaseDates.get(j);
					releaseDates.set(j, releaseDates.get(j + 1));
					releaseDates.set(j + 1, tmp);

					temp = movieNames.get(j);
					movieNames.set(j, movieNames.get(j + 1));
					movieNames.set(j + 1, temp);

					temp = imageURLs.get(j);
					imageURLs.set(j, imageURLs.get(j + 1));
					imageURLs.set(j + 1, temp);

					temp = imageHREFs.get(j);
					imageHREFs.set(j, imageHREFs.get(j + 1));
					imageHREFs.set(j + 1, temp);

					temp = IMDBawardsPageURLs.get(j);
					IMDBawardsPageURLs.set(j, IMDBawardsPageURLs.get(j + 1));
					IMDBawardsPageURLs.set(j + 1, temp);

				}
			}
		}
	}

	public int findLargest(ArrayList<String> arr) {
		int len, i, largest;

		len = arr.size();
		largest = arr.get(0).length();

		for (i = 1; i < len; i++) {
			if (arr.get(i).length() > largest) {
				largest = arr.get(i).length();
			}
		}

		return largest;

	}

	public void displayLists() throws IOException{
		
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);
		
		
		System.out.println("\n");

		int i, largestNameSize, largestURLSize, largestHREFSize, j;

		largestNameSize = findLargest(movieNames);
		largestURLSize = findLargest(imageURLs);
		largestHREFSize = findLargest(imageHREFs);

		for (i = 0; i < movieNames.size(); i++) {
			System.out.print((i + 1) + ". ");

			System.out.print(movieNames.get(i));
			for (j = 0; j <= largestNameSize - movieNames.get(i).length(); j++) {
				System.out.print(" ");
			}

			System.out.print(releaseDates.get(i) + " ");

			System.out.print(imageURLs.get(i));
			for (j = 0; j <= largestURLSize - imageURLs.get(i).length(); j++) {
				System.out.print(" ");
			}

			System.out.print(imageHREFs.get(i));
			for (j = 0; j <= largestHREFSize - imageHREFs.get(i).length(); j++) {
				System.out.print(" ");
			}

			System.out.print(IMDBawardsPageURLs.get(i));

			System.out.println();
		}

		System.out.println("\n\n");
		
		char ch;
		
		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true) {
			ch = (char)br.read();
			if (ch == '0')
				return;
		}

	}

	public void saveCodetoFile() throws IOException

	{
		String code = "";
		int i, numberOfDivisions;
		int j = 0, k = 0;

		String fileName = "moviesPage.txt";
		TextIO.writeFile(fileName);

		// ****************************************************
		TextIO.putln("<!DOCTYPE html>");
		TextIO.putln("<html>");
		TextIO.putln("<head>");

		TextIO.putln("");

		// Start of JavaScript
		TextIO.putln("<script type=\"text/javascript\">");
		TextIO.putln("");
		TextIO.putln("var cntr = 0;");

		TextIO.putln("function yHandler()");
		TextIO.putln("{");

		TextIO.putln("");
		TextIO.putln("// Watch video for line by line explanation of the code");
		TextIO.putln("// http://www.youtube.com/watch?v=eziREnZPml4");
		TextIO.putln("\n var wrap = document.getElementById('wrap');");
		TextIO.putln("var str = ''; "); // String Variable

		TextIO.putln("var contentHeight = wrap.offsetHeight;");
		TextIO.putln("var yOffset = window.pageYOffset; ");
		TextIO.putln("var y = yOffset + window.innerHeight;");
		TextIO.putln("var content = 1;");

		String str = "";
		int z, flg = 0;
		numberOfDivisions = ((movieNames.size()) / 25);

		TextIO.putln("if(y >= contentHeight && cntr<" + numberOfDivisions + ")"); // checking
		// scroll
		// height
		TextIO.putln("{ \n");
		TextIO.putln("// Ajax call to get more dynamic data goes here \n");

		// inside first if

		// now going for content==1,2 etc.

		for (z = 0; z < numberOfDivisions; z++) {

			if (flg == 0) {
				flg = 1;
				TextIO.putln("");
				TextIO.putln("if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for (i = ((z + 1) * 25); i < (((z + 1) * 25) + 25)
						&& i < movieNames.size(); i = i + 5) {

					/* Image Area */
					for (j = i; j < i + 5 && j < (((z + 1) * 25) + 25)
							&& j < movieNames.size(); j++) {
						if (j % 5 == 0) {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for (k = i; k < i + 5 && k < (((z + 1) * 25) + 25)
							&& k < movieNames.size(); k++) {
						if (k % 5 == 0) {
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}

						else {
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}
					}

					str += "</font>";

				}

				TextIO.putln("\nstr='" + str + "'");

				str = "";

				TextIO.putln("wrap.innerHTML += '<div class=\"newData\">'+str+'</div>';");
				TextIO.putln("content++;");
				TextIO.putln("}");

			}

			if (flg == 1) {

				TextIO.putln("");
				TextIO.putln("else if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for (i = ((z + 1) * 25); i < (((z + 1) * 25) + 25)
						&& i < movieNames.size(); i = i + 5) {

					/* Image Area */
					for (j = i; j < i + 5 && j < (((z + 1) * 25) + 25)
							&& j < movieNames.size(); j++) {
						if (j % 5 == 0) {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for (k = i; k < i + 5 && k < (((z + 1) * 25) + 25)
							&& k < movieNames.size(); k++) {
						if (k % 5 == 0) {
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}

						else {
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}
					}

					str += "</font>";

				}

				TextIO.putln("\nstr='" + str + "'");

				str = "";

				TextIO.putln("wrap.innerHTML += '<div class=\"newData\">'+str+'</div>';");
				TextIO.putln("content++;");
				TextIO.putln("}");
			}

		}// End of numberOfDivisions For Loop

		TextIO.putln("}");// end of first if ~ contentHeight

		TextIO.putln("");
		TextIO.putln("var status = document.getElementById('status');");
		TextIO.putln("status.innerHTML = contentHeight+\" | \"+y+\" | cntr=\"+cntr;");
		TextIO.putln("}");// End of yHandler
		TextIO.putln("window.onscroll = yHandler;");
		TextIO.putln("");
		TextIO.putln("</script>");

		TextIO.putln("<style type=\"text/css\">");
		TextIO.putln("");

		/* Class Grow */
		TextIO.putln("/*Class Grow*/");
		TextIO.putln(".grow {");
		TextIO.putln("display: inline-block;");
		TextIO.putln("-webkit-transition-duration: 0.5s;");
		TextIO.putln("transition-duration: 0.5s;");
		TextIO.putln("-webkit-transition-property: transform;");
		TextIO.putln("transition-property: transform;");
		TextIO.putln("-webkit-transform: translateZ(0);");
		TextIO.putln("transform: translateZ(0);");
		TextIO.putln("box-shadow: 0 0 1px rgba(0, 0, 0, 0);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Grow

		/* Grow Hover Code */
		TextIO.putln("/*Grow Hover Code*/");
		TextIO.putln(".grow:hover, .grow:focus, .grow:active {");
		TextIO.putln("-webkit-transform: scale(1.1);");
		TextIO.putln("transform: scale(1.1);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Grow Hover

		/* Class Glow */
		TextIO.putln("/*Class Glow*/");
		TextIO.putln(".glow {");
		TextIO.putln("display: inline-block;");
		TextIO.putln("-webkit-transition-duration: 0.5s;");
		TextIO.putln("transition-duration: 0.5s;");
		TextIO.putln("-webkit-transition-property: box-shadow;");
		TextIO.putln("transition-property: box-shadow;");
		TextIO.putln("-webkit-transform: translateZ(0);");
		TextIO.putln("transform: translateZ(0);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Glow

		/* Glow Hover Code */
		TextIO.putln("/*Glow Hover Code*/");
		TextIO.putln(".glow:hover, .glow:focus, .glow:active {");
		TextIO.putln(" box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.6);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Glow Hover

		TextIO.putln("div#status{position:fixed; font-size:24px;}");
		TextIO.putln("div#wrap{width:1400px; margin:0px auto;}");

		TextIO.putln("div.newData{ background:#FFF; margin:10px 0px;}");
		TextIO.putln("</style>");
		TextIO.putln("</head>");
		TextIO.putln("<body>");

		TextIO.putln("<div id=\"status\">0 | 0</div>");
		TextIO.putln("<div id=\"wrap\">");

		for (z = 0; z < 25 && z < movieNames.size(); z = z + 5) {

			/* Image Area */
			TextIO.putln("<!--Image Area-->");
			for (j = z; j < z + 5 && j < movieNames.size(); j++) {
				if (j % 5 == 0) {
					TextIO.putln("<a href=\""
							+ imageHREFs.get(j)
							+ "\"><img class = \"grow glow\" src=\""
							+ imageURLs.get(j)
							+ "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>");
					TextIO.putln("");
				}

				else {
					TextIO.putln("<a href=\""
							+ imageHREFs.get(j)
							+ "\"><img class = \"grow glow\" src=\""
							+ imageURLs.get(j)
							+ "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>");
					TextIO.putln("");
				}
			}

			TextIO.putln("<br>");
			TextIO.putln("");

			/* Text Area */
			TextIO.putln("<!--Text Area-->");
			TextIO.putln("<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">");
			for (k = z; k < z + 5 && k < movieNames.size(); k++) {
				if (k % 5 == 0) {
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\""
							+ imageHREFs.get(k)
							+ "\"><font color=\"black\">"
							+ movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}

				else {
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\""
							+ imageHREFs.get(k)
							+ "\"><font color=\"black\">"
							+ movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}
			}

			TextIO.putln("</font>");

		}

		TextIO.putln("</body>");
		TextIO.putln("</html>");
		TextIO.putln("");

	}

	public void saveAlphabeticalCodetoFile() throws IOException {
		String code = "";
		int i, numberOfDivisions;
		int j = 0, k = 0;

		String fileName = "moviesPageAlphabetical.txt";
		TextIO.writeFile(fileName);

		// ****************************************************
		TextIO.putln("<!DOCTYPE html>");
		TextIO.putln("<html>");
		TextIO.putln("<head>");

		TextIO.putln("");

		// Start of JavaScript
		TextIO.putln("<script type=\"text/javascript\">");
		TextIO.putln("");
		TextIO.putln("var cntr = 0;");

		TextIO.putln("function yHandler()");
		TextIO.putln("{");

		TextIO.putln("");
		TextIO.putln("// Watch video for line by line explanation of the code");
		TextIO.putln("// http://www.youtube.com/watch?v=eziREnZPml4");
		TextIO.putln("\n var wrap = document.getElementById('wrap');");
		TextIO.putln("var str = ''; "); // String Variable

		TextIO.putln("var contentHeight = wrap.offsetHeight;");
		TextIO.putln("var yOffset = window.pageYOffset; ");
		TextIO.putln("var y = yOffset + window.innerHeight;");
		TextIO.putln("var content = 1;");

		String str = "";
		int z, flg = 0;
		numberOfDivisions = ((movieNames.size()) / 25);

		TextIO.putln("if(y >= contentHeight && cntr<" + numberOfDivisions + ")"); // checking
		// scroll
		// height
		TextIO.putln("{ \n");
		TextIO.putln("// Ajax call to get more dynamic data goes here \n");

		// inside first if

		// now going for content==1,2 etc.

		for (z = 0; z < numberOfDivisions; z++) {

			if (flg == 0) {
				flg = 1;
				TextIO.putln("");
				TextIO.putln("if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for (i = ((z + 1) * 25); i < (((z + 1) * 25) + 25)
						&& i < movieNames.size(); i = i + 5) {

					/* Image Area */
					for (j = i; j < i + 5 && j < (((z + 1) * 25) + 25)
							&& j < movieNames.size(); j++) {
						if (j % 5 == 0) {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for (k = i; k < i + 5 && k < (((z + 1) * 25) + 25)
							&& k < movieNames.size(); k++) {
						if (k % 5 == 0) {
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}

						else {
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}
					}

					str += "</font>";

				}

				TextIO.putln("\nstr='" + str + "'");

				str = "";

				TextIO.putln("wrap.innerHTML += '<div class=\"newData\">'+str+'</div>';");
				TextIO.putln("content++;");
				TextIO.putln("}");

			}

			if (flg == 1) {

				TextIO.putln("");
				TextIO.putln("else if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for (i = ((z + 1) * 25); i < (((z + 1) * 25) + 25)
						&& i < movieNames.size(); i = i + 5) {

					/* Image Area */
					for (j = i; j < i + 5 && j < (((z + 1) * 25) + 25)
							&& j < movieNames.size(); j++) {
						if (j % 5 == 0) {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else {
							str += "<a href=\""
									+ imageHREFs.get(j)
									+ "\"><img class = \"grow glow\" src=\""
									+ imageURLs.get(j)
									+ "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for (k = i; k < i + 5 && k < (((z + 1) * 25) + 25)
							&& k < movieNames.size(); k++) {
						if (k % 5 == 0) {
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}

						else {
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\""
									+ imageHREFs.get(k)
									+ "\"><font color=\"black\">"
									+ movieNames.get(k) + "</font></a></div>";

						}
					}

					str += "</font>";

				}

				TextIO.putln("\nstr='" + str + "'");

				str = "";

				TextIO.putln("wrap.innerHTML += '<div class=\"newData\">'+str+'</div>';");
				TextIO.putln("content++;");
				TextIO.putln("}");
			}

		}// End of numberOfDivisions For Loop

		TextIO.putln("}");// end of first if ~ contentHeight

		TextIO.putln("");
		TextIO.putln("var status = document.getElementById('status');");
		TextIO.putln("status.innerHTML = contentHeight+\" | \"+y+\" | cntr=\"+cntr;");
		TextIO.putln("}");// End of yHandler
		TextIO.putln("window.onscroll = yHandler;");
		TextIO.putln("");
		TextIO.putln("</script>");

		TextIO.putln("<style type=\"text/css\">");
		TextIO.putln("");

		/* Class Grow */
		TextIO.putln("/*Class Grow*/");
		TextIO.putln(".grow {");
		TextIO.putln("display: inline-block;");
		TextIO.putln("-webkit-transition-duration: 0.5s;");
		TextIO.putln("transition-duration: 0.5s;");
		TextIO.putln("-webkit-transition-property: transform;");
		TextIO.putln("transition-property: transform;");
		TextIO.putln("-webkit-transform: translateZ(0);");
		TextIO.putln("transform: translateZ(0);");
		TextIO.putln("box-shadow: 0 0 1px rgba(0, 0, 0, 0);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Grow

		/* Grow Hover Code */
		TextIO.putln("/*Grow Hover Code*/");
		TextIO.putln(".grow:hover, .grow:focus, .grow:active {");
		TextIO.putln("-webkit-transform: scale(1.1);");
		TextIO.putln("transform: scale(1.1);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Grow Hover

		/* Class Glow */
		TextIO.putln("/*Class Glow*/");
		TextIO.putln(".glow {");
		TextIO.putln("display: inline-block;");
		TextIO.putln("-webkit-transition-duration: 0.5s;");
		TextIO.putln("transition-duration: 0.5s;");
		TextIO.putln("-webkit-transition-property: box-shadow;");
		TextIO.putln("transition-property: box-shadow;");
		TextIO.putln("-webkit-transform: translateZ(0);");
		TextIO.putln("transform: translateZ(0);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Glow

		/* Glow Hover Code */
		TextIO.putln("/*Glow Hover Code*/");
		TextIO.putln(".glow:hover, .glow:focus, .glow:active {");
		TextIO.putln(" box-shadow: 10px 10px 20px rgba(0, 0, 0, 0.6);");
		TextIO.putln("}");
		TextIO.putln("");
		// End of Glow Hover

		TextIO.putln("div#status{position:fixed; font-size:24px;}");
		TextIO.putln("div#wrap{width:1400px; margin:0px auto;}");

		TextIO.putln("div.newData{ background:#FFF; margin:10px 0px;}");
		TextIO.putln("</style>");
		TextIO.putln("</head>");
		TextIO.putln("<body>");

		TextIO.putln("<div id=\"status\">0 | 0</div>");
		TextIO.putln("<div id=\"wrap\">");

		for (z = 0; z < 25 && z < movieNames.size(); z = z + 5) {

			/* Image Area */
			TextIO.putln("<!--Image Area-->");
			for (j = z; j < z + 5 && j < movieNames.size(); j++) {
				if (j % 5 == 0) {
					TextIO.putln("<a href=\""
							+ imageHREFs.get(j)
							+ "\"><img class = \"grow glow\" src=\""
							+ imageURLs.get(j)
							+ "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>");
					TextIO.putln("");
				}

				else {
					TextIO.putln("<a href=\""
							+ imageHREFs.get(j)
							+ "\"><img class = \"grow glow\" src=\""
							+ imageURLs.get(j)
							+ "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>");
					TextIO.putln("");
				}
			}

			TextIO.putln("<br>");
			TextIO.putln("");

			/* Text Area */
			TextIO.putln("<!--Text Area-->");
			TextIO.putln("<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">");
			for (k = z; k < z + 5 && k < movieNames.size(); k++) {
				if (k % 5 == 0) {
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\""
							+ imageHREFs.get(k)
							+ "\"><font color=\"black\">"
							+ movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}

				else {
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\""
							+ imageHREFs.get(k)
							+ "\"><font color=\"black\">"
							+ movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}
			}

			TextIO.putln("</font>");

		}

		TextIO.putln("</body>");
		TextIO.putln("</html>");
		TextIO.putln("");

	}

	public void retrieveAwards() throws IOException {
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAMovie();

		// Declaring htmlParsing Variables
		ArrayList<String> year = new ArrayList<String>();
		ArrayList<String> people = new ArrayList<String>();
		int i, j, len1, len2, noOfYears, noOfTables, noOfTDs;
		String awardStatus = "", awardName = "", awardYear, awardDescription, awardPerson = null, finalString;

		// Declaring variables of this function
		char temp;
		int k, n, m = 0, noOfPersons;

		if (idx.size() == 1) {
			i = idx.get(0);
		}

		else if (idx.size() == 0) {
			char tmp;
			System.out
					.println("No Movie Found of that Name. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if (tmp == 'y') {
				retrieveAwards();
				return;
			} else {
				return;
			}

		}

		else {
			do {
				if (m == 1) {
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			} while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out
				.println("\n\n ------------Movie Selected to generate Awards For------------");
		System.out.println("\n 1. Movie Name: " + movieNames.get(i));
		System.out.println("\n 2. Release Date: " + releaseDates.get(i));
		System.out.println("\n 3. Image URL: " + imageURLs.get(i));
		System.out.println("\n 4. Image HREF: " + imageHREFs.get(i));
		System.out.println("\n 5. IMDB Awards URL: "
				+ IMDBawardsPageURLs.get(i));

		System.out
				.println("\n\n Are you sure this is the movie you want to retrieve awards for? (y/n)");
		temp = (char) br.read();

		if (temp == 'y') {
			Document doc = Jsoup.connect(IMDBawardsPageURLs.get(i)).get();

			Elements years = doc.getElementsByClass("event_year");
			noOfYears = years.size();

			Elements tables = doc.getElementsByTag("table"); // gets all the
																// <table>
			// tag from the html
			// code
			noOfTables = tables.size() - 1;

			for (i = 0; i < noOfYears; i++) {
				year.add(years.get(i).text().toString());
			}

			// Declaring Element variables
			Element individualTable, individualTD, individualPerson;

			// Declaring Elements variables
			Elements tdsOfIndividualTable, tmp, personsofTD;

			for (i = 0; i < noOfTables; i++) {

				awardYear = year.get(i);// assigning award year

				individualTable = tables.get(i);

				tdsOfIndividualTable = individualTable.getElementsByTag("td");

				noOfTDs = tdsOfIndividualTable.size();

				for (j = 0; j < noOfTDs; j++) {

					individualTD = tdsOfIndividualTable.get(j);

					if (individualTD.hasClass("title_award_outcome")) {

						tmp = individualTD.getElementsByTag("b");
						awardStatus = tmp.get(0).text().toString();// assigning
																	// award
																	// status

						tmp = individualTD.getElementsByTag("span");
						awardName = tmp.get(0).text().toString();
					}

					if (individualTD.hasClass("award_description")) {

						personsofTD = individualTD.getElementsByTag("a");
						noOfPersons = personsofTD.size();

						for (k = 0; k < noOfPersons; k++) {
							individualPerson = personsofTD.get(k);

							if (k == 0) {
								awardPerson = individualPerson.text();
							}

							else {
								awardPerson = awardPerson + "|"
										+ individualPerson.text();
							}
						}

						len2 = awardPerson.length();

						awardDescription = individualTD.ownText().toString();
						len1 = awardDescription.length();

						/*
						 * if(awardDescription.contains(awardPerson))
						 * 
						 * { awardDescription=awardDescription.substring(0,
						 * (len1-len2)); }
						 */

						finalString = awardYear + ";" + awardName + ";"
								+ awardDescription + ";" + awardPerson + ";"
								+ awardStatus;

						awards.add(finalString);
					}

				}
			}
		}

		System.out.println("\nAwards Retrieved!\n");
	}

	public void displayAwardsList() throws IOException{
		
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);
		
		
		int i;
		if (awards.isEmpty() != true) {
			System.out
					.println("\n\n------------------Awards List------------------\n\n");

			for (i = 0; i < awards.size(); i++) {
				System.out.println("\n" + (i + 1) + ") " + awards.get(i));
			}
		}

		else {
			System.out
					.println("\nAwards List is currently empty. Please retrieve an items awards first!\n");
		}

		System.out.println("\n\n");
			
		char ch;
		
		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true) {
			ch = (char)br.read();
			if (ch == '0')
				return;
		}
	}

	public void updateAwardBank() throws IOException {

		/**
		 * Desc: Used to add an Award to MidnightJabber Database (Local to the
		 * .java program not a SQL DAtabase) It also adds the object(award) to
		 * the Array List Bank. Note: The Award Name that you enter should be
		 * exactly how IMDB Awards are named on their site
		 */

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		System.out.println("\n\n\n");

		System.out.print("Enter the Name of the Award: ");
		String name = br.readLine();
		System.out.print("\nEnter the Image URL of the Award: ");
		String link = br.readLine();

		awardBank obj = new awardBank(name, link);
		awardsBank.add(obj);

		int size = awardsBank.size(); // Store the Size of the current Award
										// Bank Array List
		System.out.println("Award " + obj.getName()
				+ " successfully added to the Award bank.");
		System.out.println("Total Awards in the Bank = " + size);
		
		System.out.println("\n\nPress 0 Key to Continue...\n");
		char ch;
		while (true) {
			ch = (char) br.read();
			if (ch == '0')
				return;
		}
	}

	public void deleteFromAwardBank() throws IOException {

		/**
		 * Desc: Used to delete an Award to Midnightjabber Database (Local to
		 * the .java program not a SQL DAtabase) It also adds the object(award)
		 * to the Array List Bank. Note: The Award Name that you enter should be
		 * exactly how IMDB Awards are named on their site
		 */

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		System.out.println("\n\n\n");

		int choice; // 1 to choose the number and 2 to search
		int number; // Display order number of awards
		char answer, ch; // Yes and No Answer
		System.out.println("Instuctions to Delete:");
		System.out.println("Press 1 to Choose from the List/Award Bank");
		System.out.println("Press 2 to Search by Name");
		choice = Integer.parseInt(br.readLine());

		if (choice == 1) {
			for (int i = 0; i < awardsBank.size(); i++) {
				System.out
						.println((i + 1) + ") " + awardsBank.get(i).getName());
			}

			System.out.print("\nChoose the Number to Delete: ");
			number = Integer.parseInt(br.readLine());

			System.out.println("Are you sure you want to delete "
					+ awardsBank.get(number - 1).getName() + "? (y/n)");
			answer = (char) br.read();
			if (answer == 'y') {
				awardsBank.remove(number - 1);
				System.out
						.println("\nAward has been successfully deleted from the Bank!");
			} else {
				System.out
						.println("\nAward has NOT been deleted from the Bank!");
			}

		}

		else if (choice == 2) {
			ArrayList<Integer> idx = new ArrayList<Integer>();

			idx = findAnAward();

			String temp;
			int terminal, i, n, m = 0;

			if (idx.size() == 1) {
				i = idx.get(0);
			}

			else if (idx.size() == 0) {
				char tmp;
				System.out
						.println("No Award Found of that Name. Do you want to try again? (y/n)");
				tmp = (char) br.read();
				if (tmp == 'y') {
					deleteFromAwardBank();
					return;
				} else {
					return;
				}

			}

			else {
				do {
					if (m == 1) {
						System.out
								.println("\nInavalid Choice, Enter Again: \n");
					}

					System.out.println("Enter a choice: \n");
					n = Integer.parseInt(br.readLine());
					m = 1;
				} while (n > idx.size() || n < 0);

				i = idx.get(n - 1);
			}

			System.out
					.println("\n\n ------------Award Selected to Be Deleted------------");
			System.out.println("\n 1. Award Name: "
					+ awardsBank.get(i).getName());

			System.out
					.println("\nAre you sure you want to delete this award? (y/n)");
			temp = br.readLine();

			if (temp.equalsIgnoreCase("y")) {
				awardsBank.remove(i);
				System.out
						.println("\nAward has been successfully deleted from the Bank!");
			}

			else {
				System.out
						.println("\nAward has NOT been deleted from the Bank!\n");
			}

		} else {
			System.out.println("Invalid Choice!");
			return;
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true) {
			ch = (char) br.read();
			if (ch == '0')
				return;
		}

	}

	public void displayAwardBankList() throws IOException {

		char ch;
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		System.out
				.println("\n\n\n ---------------------------------------Award Bank----------------------------------------\n\n");
		for (int i = 0; i < awardsBank.size(); i++) {
			System.out.println((i + 1) + ") " + awardsBank.get(i).getName());
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true) {
			ch = (char) br.read();
			if (ch == '0')
				return;
		}

	}

	public ArrayList<Integer> findAnAward() throws IOException {
		System.out
				.println("                                      Searching For an Award                                    ");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		String searchString, subString;
		int flg = 0, len, i, j, k, cntr = 0;
		char ch;

		System.out.println("Enter The Award Name: \n");
		searchString = br.readLine();

		len = searchString.length();

		for (i = 0; i < awardsBank.size(); i++) {

			for (k = 0, j = len; j <= awardsBank.get(i).getName().length(); k++, j++) {
				subString = awardsBank.get(i).getName().substring(k, j);

				if (searchString.equalsIgnoreCase(subString)) {
					cntr = cntr + 1;
					indexes.add(i);

					System.out.print("\n" + cntr + ") ");
					System.out.println("\n  1. Award Name: "
							+ awardsBank.get(i).getName());

					flg = 1;

					break;
				}
			}

		}
		if (flg == 0 || awardsBank.isEmpty()) {
			System.out.println("\n Sorry, Couldn't Find An Award..");
		}

		System.out.println("\nPress 0 Key to Continue...\n");
		while (true) {
			ch = (char) br.read();
			if (ch == '0')
				return indexes;
		}

	}

}
