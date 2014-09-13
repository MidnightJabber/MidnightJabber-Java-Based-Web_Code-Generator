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

public class allFunctions implements Serializable
{

	// Movie Variables
	ArrayList<String> movieNames = new ArrayList<String>();
	ArrayList<Integer> releaseDates = new ArrayList<Integer>();
	ArrayList<String> imageURLs = new ArrayList<String>();
	ArrayList<String> imageHREFs = new ArrayList<String>();

	// Awards Variables
	ArrayList<String> awards = new ArrayList<String>();
	ArrayList<String> IMDBawardsPageURLs = new ArrayList<String>();
	ArrayList<awardBank> awardsBank = new ArrayList<awardBank>();

	// Author Variables
	ArrayList<String> authorsBank = new ArrayList<String>();
	ArrayList<String> authorItem = new ArrayList<String>();

	
	//Random Variables
	String movieWhosAwardsWereJustGenerated;
	
	public void saveFile() throws IOException
	{

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
		oos.writeObject(authorsBank);
		oos.writeObject(authorItem);
		oos.close();

	}

	public void deleteMovie() throws IOException
	{

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAMovie();

		String temp;
		int terminal, i, n, m = 0;

		if(idx.size() == 1)
		{
			i = idx.get(0);
		}

		else if(idx.size() == 0)
		{
			char tmp;
			System.out.println("No Movie Found of that Name. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if(tmp == 'y')
			{
				deleteMovie();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return;
			}
			else
			{
				return;
			}

		}

		else
		{
			do
			{
				if(m == 1)
				{
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			}
			while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out.println("\n\n ------------Movie Selected to Be Deleted------------");
		System.out.println("\n 1. Movie Name: " + movieNames.get(i));
		System.out.println("\n 2. Release Date: " + releaseDates.get(i));
		System.out.println("\n 3. Author: " + authorItem.get(i));
		System.out.println("\n 4. Image URL: " + imageURLs.get(i));
		System.out.println("\n 5. Image HREF: " + imageHREFs.get(i));
		System.out.println("\n 6. IMDB Awards URL: " + IMDBawardsPageURLs.get(i));

		System.out.println("\nAre you sure you want to delete this movie? (y/n)");
		temp = br.readLine();

		if(temp.equalsIgnoreCase("y"))
		{
			movieNames.remove(i);
			releaseDates.remove(i);
			authorItem.remove(i);
			imageURLs.remove(i);
			imageHREFs.remove(i);
			IMDBawardsPageURLs.remove(i);
		}

		else
		{
			System.out.println("\nSkipped Deletion!\n");
		}

	}

	public ArrayList<Integer> findAMovie() throws IOException
	{
		System.out.println("                                      Searching For a Movie                                      ");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		String searchString, subString;
		int flg = 0, len, i, j, k, cntr = 0;
		char ch;

		System.out.println("Enter The Movie Name: \n");
		searchString = br.readLine();

		len = searchString.length();

		for(i = 0; i < movieNames.size(); i++)
		{

			for(k = 0, j = len; j <= movieNames.get(i).length(); k++, j++)
			{
				subString = movieNames.get(i).substring(k, j);

				if(searchString.equalsIgnoreCase(subString))
				{
					cntr = cntr + 1;
					indexes.add(i);

					System.out.print("\n" + cntr + ") ");
					System.out.println("\n 1. Movie Name: " + movieNames.get(i));
					System.out.println("\n 2. Release Date: " + releaseDates.get(i));
					System.out.println("\n 3. Author: " + authorItem.get(i));
					System.out.println("\n 4. Image URL: " + imageURLs.get(i));
					System.out.println("\n 5. Image HREF: " + imageHREFs.get(i));
					System.out.println("\n 6. IMDB Awards URL: " + IMDBawardsPageURLs.get(i));

					flg = 1;

					break;
				}
			}

		}
		if(flg == 0 || movieNames.isEmpty())
		{
			System.out.println("Movie names is empty: " + movieNames.isEmpty());
			System.out.println("flg= " + flg);
			System.out.println("\n Sorry, Couldn't Find A Movie..");
		}

		System.out.println("\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return indexes;
		}

	}

	public void editMovie() throws IOException
	{

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAMovie();

		String temp;
		int terminal, i, n = 0, m = 0;

		if(idx.size() == 1)
		{
			i = idx.get(0);
		}

		else if(idx.size() == 0)
		{
			char tmp;
			System.out.println("No Movie Found of that Name. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if(tmp == 'y')
			{
				editMovie();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return;
			}
			else
			{
				return;
			}

		}

		else
		{

			do
			{
				if(m == 1)
				{
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			}
			while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out.println("\n\n ------------Movie Selected to Be Edited------------");
		System.out.println("\n 1. Movie Name: " + movieNames.get(i));
		System.out.println("\n 2. Release Date: " + releaseDates.get(i));
		System.out.println("\n 3. Author: " + authorItem.get(i));
		System.out.println("\n 4. Image URL: " + imageURLs.get(i));
		System.out.println("\n 5. Image HREF: " + imageHREFs.get(i));
		System.out.println("\n 6. IMDB Awards URL: " + IMDBawardsPageURLs.get(i));
		System.out.println("\n 7. Exit");

		System.out.println("\n What do you want to edit?\n");
		n = Integer.parseInt(br.readLine());

		if(n == 1)
		{
			System.out.println("\nEnter new Name: ");
			temp = br.readLine();
			movieNames.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else if(n == 2)
		{
			System.out.println("\nEnter new Release Date: ");
			temp = br.readLine();
			releaseDates.set(i, Integer.parseInt(temp));
			System.out.println("\nSuccessfully Edited!");
		}

		else if(n == 3)
		{
			System.out.println("\nEnter new Author name: ");
			temp = br.readLine();
			authorItem.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}
		else if(n == 4)
		{
			System.out.println("\nEnter new Image URL: ");
			temp = br.readLine();
			imageURLs.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else if(n == 5)
		{
			System.out.println("\nEnter new HREF: ");
			temp = br.readLine();
			imageHREFs.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else if(n == 6)
		{
			System.out.println("\nEnter new IMDB URL: ");
			temp = br.readLine();
			IMDBawardsPageURLs.set(i, temp);
			System.out.println("\nSuccessfully Edited!");
		}

		else
		{

		}

	}

	@SuppressWarnings("unchecked")
	public void openFile() throws IOException, ClassNotFoundException
	{

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
		authorsBank = (ArrayList<String>) in.readObject();
		authorItem = (ArrayList<String>) in.readObject();

		in.close();
		fileIn.close();

	}

	public void getMovie() throws IOException
	{

		String year, name, date, imageURL, imageHREF, imdbURL, author;
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

		System.out.println("\nEnter the Name of this articles Author:  ");
		author = br.readLine();
		authorItem.add(author);

		System.out.println("\nEnter the URL of the Movie's Poster:  ");
		imageURL = br.readLine();
		imageURLs.add(imageURL);

		System.out.println("\nEnter the Destination URL of the Movie's Poster and Heading:  ");
		imageHREF = br.readLine();
		imageHREFs.add(imageHREF);

		System.out.println("\nEnter the URL of the IMDB Awards Page:  ");
		imdbURL = br.readLine();
		IMDBawardsPageURLs.add(imdbURL);

		System.out.println("\nYou have entered: \n");
		System.out.println("Movie:  " + name);
		System.out.println("Date:  " + date);
		System.out.println("Auhtor:  " + author);
		System.out.println("Poster URL:  " + imageURL);
		System.out.println("HREF:  " + imageHREF);
		System.out.println("IMDB URL:  " + imdbURL);

	}

	public void sortListsByReleaseDates()
	{
		int i, j, tmp;
		String temp;

		for(i = 0; i < releaseDates.size(); i++)
		{
			for(j = 0; j < releaseDates.size() - 1; j++)
			{
				if(releaseDates.get(j) < releaseDates.get(j + 1))
				{
					tmp = releaseDates.get(j);
					releaseDates.set(j, releaseDates.get(j + 1));
					releaseDates.set(j + 1, tmp);

					temp = movieNames.get(j);
					movieNames.set(j, movieNames.get(j + 1));
					movieNames.set(j + 1, temp);

					temp = authorItem.get(j);
					authorItem.set(j, authorItem.get(j + 1));
					authorItem.set(j + 1, temp);

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

	public void sortListsAlphabetically()
	{
		int i, j, tmp;
		String temp;

		for(i = 0; i < releaseDates.size(); i++)
		{
			for(j = 0; j < releaseDates.size() - 1; j++)
			{
				if(movieNames.get(j).compareTo(movieNames.get(j + 1)) > 0)
				{
					tmp = releaseDates.get(j);
					releaseDates.set(j, releaseDates.get(j + 1));
					releaseDates.set(j + 1, tmp);

					temp = movieNames.get(j);
					movieNames.set(j, movieNames.get(j + 1));
					movieNames.set(j + 1, temp);

					temp = authorItem.get(j);
					authorItem.set(j, authorItem.get(j + 1));
					authorItem.set(j + 1, temp);

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

	public int findLargest(ArrayList<String> arr)
	{
		int len, i, largest;

		len = arr.size();
		largest = arr.get(0).length();

		for(i = 1; i < len; i++)
		{
			if(arr.get(i).length() > largest)
			{
				largest = arr.get(i).length();
			}
		}

		return largest;

	}

	public void displayLists() throws IOException
	{
		System.out.println("\n");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		int i, largestNameSize, largestAuthorNameSize, largestURLSize, largestHREFSize, j;

		largestNameSize = findLargest(movieNames);
		largestAuthorNameSize = findLargest(authorItem);
		largestURLSize = findLargest(imageURLs);
		largestHREFSize = findLargest(imageHREFs);

		for(i = 0; i < movieNames.size(); i++)
		{
			System.out.print((i + 1) + ". ");

			System.out.print(movieNames.get(i));
			for(j = 0; j <= largestNameSize - movieNames.get(i).length(); j++)
			{
				System.out.print(" ");
			}

			System.out.print(releaseDates.get(i) + " ");

			System.out.print(authorItem.get(i));
			for(j = 0; j <= largestAuthorNameSize - authorItem.get(i).length(); j++)
			{
				System.out.print(" ");
			}

			System.out.print(imageURLs.get(i));
			for(j = 0; j <= largestURLSize - imageURLs.get(i).length(); j++)
			{
				System.out.print(" ");
			}

			System.out.print(imageHREFs.get(i));
			for(j = 0; j <= largestHREFSize - imageHREFs.get(i).length(); j++)
			{
				System.out.print(" ");
			}

			System.out.print(IMDBawardsPageURLs.get(i));

			System.out.println();
		}

		char ch;

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
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

		for(z = 0; z < numberOfDivisions; z++)
		{

			if(flg == 0)
			{
				flg = 1;
				TextIO.putln("");
				TextIO.putln("if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for(i = ((z + 1) * 25); i < (((z + 1) * 25) + 25) && i < movieNames.size(); i = i + 5)
				{

					/* Image Area */
					for(j = i; j < i + 5 && j < (((z + 1) * 25) + 25) && j < movieNames.size(); j++)
					{
						if(j % 5 == 0)
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for(k = i; k < i + 5 && k < (((z + 1) * 25) + 25) && k < movieNames.size(); k++)
					{
						if(k % 5 == 0)
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

						}

						else
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

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

			if(flg == 1)
			{

				TextIO.putln("");
				TextIO.putln("else if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for(i = ((z + 1) * 25); i < (((z + 1) * 25) + 25) && i < movieNames.size(); i = i + 5)
				{

					/* Image Area */
					for(j = i; j < i + 5 && j < (((z + 1) * 25) + 25) && j < movieNames.size(); j++)
					{
						if(j % 5 == 0)
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for(k = i; k < i + 5 && k < (((z + 1) * 25) + 25) && k < movieNames.size(); k++)
					{
						if(k % 5 == 0)
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

						}

						else
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

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

		for(z = 0; z < 25 && z < movieNames.size(); z = z + 5)
		{

			/* Image Area */
			TextIO.putln("<!--Image Area-->");
			for(j = z; j < z + 5 && j < movieNames.size(); j++)
			{
				if(j % 5 == 0)
				{
					TextIO.putln("<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>");
					TextIO.putln("");
				}

				else
				{
					TextIO.putln("<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>");
					TextIO.putln("");
				}
			}

			TextIO.putln("<br>");
			TextIO.putln("");

			/* Text Area */
			TextIO.putln("<!--Text Area-->");
			TextIO.putln("<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">");
			for(k = z; k < z + 5 && k < movieNames.size(); k++)
			{
				if(k % 5 == 0)
				{
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}

				else
				{
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}
			}

			TextIO.putln("</font>");

		}

		TextIO.putln("</body>");
		TextIO.putln("</html>");
		TextIO.putln("");

	}

	public void saveAlphabeticalCodetoFile() throws IOException
	{
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

		for(z = 0; z < numberOfDivisions; z++)
		{

			if(flg == 0)
			{
				flg = 1;
				TextIO.putln("");
				TextIO.putln("if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for(i = ((z + 1) * 25); i < (((z + 1) * 25) + 25) && i < movieNames.size(); i = i + 5)
				{

					/* Image Area */
					for(j = i; j < i + 5 && j < (((z + 1) * 25) + 25) && j < movieNames.size(); j++)
					{
						if(j % 5 == 0)
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for(k = i; k < i + 5 && k < (((z + 1) * 25) + 25) && k < movieNames.size(); k++)
					{
						if(k % 5 == 0)
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

						}

						else
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

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

			if(flg == 1)
			{

				TextIO.putln("");
				TextIO.putln("else if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for(i = ((z + 1) * 25); i < (((z + 1) * 25) + 25) && i < movieNames.size(); i = i + 5)
				{

					/* Image Area */
					for(j = i; j < i + 5 && j < (((z + 1) * 25) + 25) && j < movieNames.size(); j++)
					{
						if(j % 5 == 0)
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else
						{
							str += "<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for(k = i; k < i + 5 && k < (((z + 1) * 25) + 25) && k < movieNames.size(); k++)
					{
						if(k % 5 == 0)
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

						}

						else
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>";

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

		for(z = 0; z < 25 && z < movieNames.size(); z = z + 5)
		{

			/* Image Area */
			TextIO.putln("<!--Image Area-->");
			for(j = z; j < z + 5 && j < movieNames.size(); j++)
			{
				if(j % 5 == 0)
				{
					TextIO.putln("<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>");
					TextIO.putln("");
				}

				else
				{
					TextIO.putln("<a href=\"" + imageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + imageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>");
					TextIO.putln("");
				}
			}

			TextIO.putln("<br>");
			TextIO.putln("");

			/* Text Area */
			TextIO.putln("<!--Text Area-->");
			TextIO.putln("<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">");
			for(k = z; k < z + 5 && k < movieNames.size(); k++)
			{
				if(k % 5 == 0)
				{
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}

				else
				{
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + imageHREFs.get(k) + "\"><font color=\"black\">" + movieNames.get(k) + "</font></a></div>");
					TextIO.putln("");
				}
			}

			TextIO.putln("</font>");

		}

		TextIO.putln("</body>");
		TextIO.putln("</html>");
		TextIO.putln("");

	}

	public void retrieveAwards() throws IOException
	{
		awards.clear();
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

		if(idx.size() == 1)
		{
			i = idx.get(0);
		}

		else if(idx.size() == 0)
		{
			char tmp;
			System.out.println("No Movie Found of that Name. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if(tmp == 'y')
			{
				retrieveAwards();
				return;
			}
			else
			{
				return;
			}

		}

		else
		{
			do
			{
				if(m == 1)
				{
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			}
			while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		movieWhosAwardsWereJustGenerated=movieNames.get(i);
		
		String arr[]=movieWhosAwardsWereJustGenerated.split(" ");
		
		for(int x=0;x<(arr.length-1);x++)
		{
			if(x==0)
			{
				movieWhosAwardsWereJustGenerated=arr[x];
			}
			
			else
			{
				movieWhosAwardsWereJustGenerated=movieWhosAwardsWereJustGenerated+arr[x];
			}
		}
		
		System.out.println("\n\n ------------Movie Selected to generate Awards For------------");
		System.out.println("\n 1. Movie Name: " + movieNames.get(i));
		System.out.println("\n 2. Release Date: " + releaseDates.get(i));
		System.out.println("\n 3. Image URL: " + imageURLs.get(i));
		System.out.println("\n 4. Image HREF: " + imageHREFs.get(i));
		System.out.println("\n 5. IMDB Awards URL: " + IMDBawardsPageURLs.get(i));

		System.out.println("\n\n Are you sure this is the movie you want to retrieve awards for? (y/n)");
		temp = (char) br.read();

		if(temp == 'y')
		{
			Document doc = Jsoup.connect(IMDBawardsPageURLs.get(i)).get();

			Elements years = doc.getElementsByClass("event_year");
			noOfYears = years.size();

			Elements tables = doc.getElementsByTag("table"); // gets all the
																// <table>
																// tag from the
																// html
																// code
			noOfTables = tables.size() - 1;

			for(i = 0; i < noOfYears; i++)
			{
				year.add(years.get(i).text().toString());
			}

			// Declaring Element variables
			Element individualTable, individualTD, individualPerson;

			// Declaring Elements variables
			Elements tdsOfIndividualTable, tmp, personsofTD;

			for(i = 0; i < noOfTables; i++)
			{

				awardYear = year.get(i);// assigning award year

				individualTable = tables.get(i);

				tdsOfIndividualTable = individualTable.getElementsByTag("td");

				noOfTDs = tdsOfIndividualTable.size();

				for(j = 0; j < noOfTDs; j++)
				{

					individualTD = tdsOfIndividualTable.get(j);

					if(individualTD.hasClass("title_award_outcome"))
					{

						tmp = individualTD.getElementsByTag("b");
						awardStatus = tmp.get(0).text().toString();// assigning
																	// award
																	// status

						tmp = individualTD.getElementsByTag("span");
						awardName = tmp.get(0).text().toString();
					}

					if(individualTD.hasClass("award_description"))
					{

						personsofTD = individualTD.getElementsByTag("a");
						noOfPersons = personsofTD.size();

						for(k = 0; k < noOfPersons; k++)
						{
							individualPerson = personsofTD.get(k);

							if(k == 0)
							{
								if(!(individualPerson.text().equals("More")))
								{
									awardPerson = individualPerson.text();
								}
								
							}

							else
							{
								if(!(individualPerson.text().equals("More")))
								{
									awardPerson = awardPerson + "|" + individualPerson.text();
								}
								
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

						finalString = awardYear + ";" + awardName + ";" + awardDescription + ";" + awardPerson + ";" + awardStatus;

						awards.add(finalString);
					}

				}
			}
		}

	}

	public void displayAwardsList() throws IOException
	{
		int i;
		char ch;

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		if(awards.isEmpty() != true)
		{
			System.out.println("\n\n------------------Awards List------------------\n\n");
			System.out.println("\nNo of awards: " + awards.size());

			for(i = 0; i < awards.size(); i++)
			{
				System.out.println("\n" + (i + 1) + ") " + awards.get(i));
			}
		}

		else
		{
			System.out.println("\nAwards List is currently empty. Please retrieve an items awards first!\n");
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return;
		}

	}

	public void updateAwardBank() throws IOException
	{

		/**
		 * Desc: Used to add an Award to MidnightJabber Database (Local to the
		 * .java program not a SQL DAtabase) It also adds the object(award) to
		 * the Array List Bank. Note: The Award Name that you enter should be
		 * exactly how IMDB Awards are named on their site
		 */

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		char ch;
		System.out.println("\n\n\n");

		System.out.print("Enter the Name of the Award: ");
		String name = br.readLine();
		System.out.print("\nEnter the Image URL of the Award: ");
		String link = br.readLine();

		awardBank obj = new awardBank(name, link);
		awardsBank.add(obj);

		int size = awardsBank.size(); // Store the Size of the current Award
										// Bank Array List
		System.out.println("Award " + obj.getName() + " successfully added to the Award bank.");
		System.out.println("Total Awards in the Bank = " + size);

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return;
		}
	}

	public void deleteFromAwardBank() throws IOException
	{

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

		if(choice == 1)
		{
			for(int i = 0; i < awardsBank.size(); i++)
			{
				System.out.println((i + 1) + ") " + awardsBank.get(i).getName());
			}

			System.out.print("\nChoose the Number to Delete: ");
			number = Integer.parseInt(br.readLine());

			System.out.println("Are you sure you want to delete " + awardsBank.get(number - 1)
																				.getName() + "? (y/n)");
			answer = (char) br.read();
			if(answer == 'y')
			{
				awardsBank.remove(number - 1);
				System.out.println("\nAward has been successfully deleted from the Bank!");
			}
			else
			{
				System.out.println("\nAward has NOT been deleted from the Bank!");
			}

		}

		else if(choice == 2)
		{
			ArrayList<Integer> idx = new ArrayList<Integer>();

			idx = findAnAward();

			String temp;
			int terminal, i, n, m = 0;

			if(idx.size() == 1)
			{
				i = idx.get(0);
			}

			else if(idx.size() == 0)
			{
				char tmp;
				System.out.println("No Award Found of that Name. Do you want to try again? (y/n)");
				tmp = (char) br.read();
				if(tmp == 'y')
				{
					deleteFromAwardBank();
					return;
				}
				else
				{
					return;
				}

			}

			else
			{
				do
				{
					if(m == 1)
					{
						System.out.println("\nInavalid Choice, Enter Again: \n");
					}

					System.out.println("Enter a choice: \n");
					n = Integer.parseInt(br.readLine());
					m = 1;
				}
				while (n > idx.size() || n < 0);

				i = idx.get(n - 1);
			}

			System.out.println("\n\n ------------Award Selected to Be Deleted------------");
			System.out.println("\n 1. Award Name: " + awardsBank.get(i)
																.getName());

			System.out.println("\nAre you sure you want to delete this award? (y/n)");
			temp = br.readLine();

			if(temp.equalsIgnoreCase("y"))
			{
				awardsBank.remove(i);
				System.out.println("\nAward has been successfully deleted from the Bank!");
			}

			else
			{
				System.out.println("\nAward has NOT been deleted from the Bank!\n");
			}

		}
		else
		{
			System.out.println("Invalid Choice!");
			return;
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return;
		}

	}

	public void editAwardBank()throws IOException
	{
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
		String temporaryInput;//Edit nmae and link
		
		System.out.println("Instuctions to Delete:");
		System.out.println("Press 1 to Choose from the List/Award Bank");
		System.out.println("Press 2 to Search by Name");
		choice = Integer.parseInt(br.readLine());

		if(choice == 1)
		{
			for(int i = 0; i < awardsBank.size(); i++)
			{
				System.out.println((i + 1) + ") " + awardsBank.get(i).getName());
			}

			System.out.print("\nChoose the Number to Delete: ");
			number = Integer.parseInt(br.readLine());

			System.out.println("Are you sure you want to delete " + awardsBank.get(number - 1)
																				.getName() + "? (y/n)");
			answer = (char) br.read();
			if(answer == 'y')
			{
				awardsBank.remove(number - 1);
				System.out.println("\nAward has been successfully deleted from the Bank!");
			}
			else
			{
				System.out.println("\nAward has NOT been deleted from the Bank!");
			}

		}

		else if(choice == 2)
		{
			ArrayList<Integer> idx = new ArrayList<Integer>();

			idx = findAnAward();

			String temp;
			int terminal, i, n, m = 0;

			if(idx.size() == 1)
			{
				i = idx.get(0);
			}

			else if(idx.size() == 0)
			{
				char tmp;
				System.out.println("No Award Found of that Name. Do you want to try again? (y/n)");
				tmp = (char) br.read();
				if(tmp == 'y')
				{
					deleteFromAwardBank();
					return;
				}
				else
				{
					return;
				}

			}

			else
			{
				do
				{
					if(m == 1)
					{
						System.out.println("\nInavalid Choice, Enter Again: \n");
					}

					System.out.println("Enter a choice: \n");
					n = Integer.parseInt(br.readLine());
					m = 1;
				}
				while (n > idx.size() || n < 0);

				i = idx.get(n - 1);
			}

			System.out.println("\n\n ------------Award Selected to Be Edited------------");
			System.out.println("\n 1. Award Name: " + awardsBank.get(i)
																.getName());

			System.out.println("\nAre you sure you want to delete this award? (y/n)");
			temp = br.readLine();

			if(temp.equalsIgnoreCase("y"))
			{
				System.out.println("\nEnter New Name: ");
				temporaryInput=br.readLine();
				awardsBank.get(i).setName(temporaryInput);
				
				System.out.println("\nEnter New Image Url: ");
				temporaryInput=br.readLine();
				awardsBank.get(i).setLink(temporaryInput);
				
				
				System.out.println("\nAward has been successfully edited in the Bank!");
			}

			else
			{
				System.out.println("\nAward has NOT been edited in the Bank!\n");
			}

		}
		else
		{
			System.out.println("Invalid Choice!");
			return;
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return;
		}

}

	public void displayAwardBankList() throws IOException
	{

		char ch;
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		System.out.println("\n\n\n ---------------------------------------Award Bank----------------------------------------\n\n");
		for(int i = 0; i < awardsBank.size(); i++)
		{
			System.out.println((i + 1) + ") " + awardsBank.get(i).getName());
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return;
		}

	}

	public void generateAwardCode()
	{
		
		

		String fileName = "./AwardCodeFiles/movieAwardCode_"+movieWhosAwardsWereJustGenerated+".txt";
		TextIO.writeFile(fileName);

		TextIO.putln("<font face=\"Yanone Kaffeesatz Regular\" size=\"6\">Awards & Nominations:</font>");
		TextIO.putln("<br><br>");

		TextIO.putln();

		TextIO.putln("<!DOCTYPE html>");
		TextIO.putln("<html>");
		TextIO.putln("<head>");
		TextIO.putln("<style>");

		TextIO.putln();

		TextIO.putln("/* http://ianlunn.github.io/Hover/# ( for hover effects by Ian Lunn) */");
		TextIO.putln("/* Hover effects are on his Github. Each effect has a class in the hover.css file */");
		TextIO.putln("/* GITHUB LINK: https://github.com/IanLunn/Hover */");

		TextIO.putln("/* GROW CODE */");
		TextIO.putln(".grow {");
		TextIO.putln("display: inline-block;");
		TextIO.putln("-webkit-transition-duration: 0.1s;");
		TextIO.putln("transition-duration: 0.1s;");
		TextIO.putln("-webkit-transition-property: transform;");
		TextIO.putln("transition-property: transform;");
		TextIO.putln("-webkit-transform: translateZ(0);");
		TextIO.putln("transform: translateZ(0);");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("/* GROW HOVER CODE */");
		TextIO.putln(".grow:hover, .grow:focus, .grow:active");
		TextIO.putln("{");
		TextIO.putln("-webkit-transform:  translateZ(0) scale(1.1);");
		TextIO.putln("transform: translateZ(0) scale(1.1);");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("/** Description: Creates Text Box on Hover. The Decoration/Colors can be edited");
		TextIO.putln("* Author: Code taken online (unknown)");
		TextIO.putln("*/");

		TextIO.putln(".tab");
		TextIO.putln("{");
		TextIO.putln("float: left;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln(".tabcontent{");
		TextIO.putln("margin-top:1px;");
		TextIO.putln("margin-left: -10px;");
		TextIO.putln("position: absolute;");
		TextIO.putln("padding:0px 0 0 10px;");
		TextIO.putln("background: rgba(0,0,0,0.9);");
		TextIO.putln("/* Safari 4-5, Chrome 1-9 */");
		TextIO.putln("background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#1947A4), to(#4A7EBD));");
		TextIO.putln("/* Safari 5.1, Chrome 10+ */");
		TextIO.putln("background: -webkit-linear-gradient(top, #1947A4, #4A7EBD);");
		TextIO.putln("/* Firefox 3.6+ */");
		TextIO.putln("background: -moz-linear-gradient(top, #020202, #4A4A4A);");
		TextIO.putln("/* IE 10 */");
		TextIO.putln("background: -ms-linear-gradient(top, #020202, #4A4A4A);");
		TextIO.putln("/* Opera 11.10+ */");
		TextIO.putln("background: -o-linear-gradient(top, #020202, #4A4A4A);");
		TextIO.putln("/*  border:1px solid #000; */");
		TextIO.putln("border-radius: 12px ;");
		TextIO.putln("height:150px;");
		TextIO.putln("width: 250px;");
		TextIO.putln("display:none;");
		TextIO.putln("transition: all 1.2s;");
		TextIO.putln("box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.6);");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln(".tab:hover > .tabcontent");
		TextIO.putln("{");
		TextIO.putln("display: block;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("/** Description: Gap Animation code between rows of awards");
		TextIO.putln("*Functionality/Usage: <div class=\"gap\"> (includes a row of awards) </div>");
		TextIO.putln("* Authors: Vishrut,Sukrit (www.midnightjabber.com)");
		TextIO.putln("*/");

		TextIO.putln("");

		TextIO.putln(".gap:hover");
		TextIO.putln("{");
		TextIO.putln("");
		TextIO.putln("margin-bottom:180px;");
		TextIO.putln("transition: all 0.5s;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln(".lastgap:hover");
		TextIO.putln("{");
		TextIO.putln("margin-bottom:205px;");
		TextIO.putln("transition: all 0.5s;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("/* For Testing Purposes */");
		TextIO.putln(".solid_border {");
		TextIO.putln("border-style: dashed;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln(".scrollbar");
		TextIO.putln("{");
		TextIO.putln("margin-top:5px;");
		TextIO.putln("height: 140px;");
		TextIO.putln("width: 249px;");
		TextIO.putln("overflow-y: auto;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln(".force-overflow");
		TextIO.putln("{");
		TextIO.putln("overflow-y: auto;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("/*");
		TextIO.putln("*  STYLE 1");
		TextIO.putln("*/");

		TextIO.putln();
		TextIO.putln("#style-1::-webkit-scrollbar-track");
		TextIO.putln("{");
		TextIO.putln("-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);");
		TextIO.putln("border-radius: 12px;");
		TextIO.putln("background: -webkit-linear-gradient(top, #1947A4, #4A7EBD);");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("#style-1::-webkit-scrollbar");
		TextIO.putln("{");
		TextIO.putln("border-radius: 12px;");
		TextIO.putln("width: 8px;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("#style-1::-webkit-scrollbar-thumb");
		TextIO.putln("{");
		TextIO.putln("border-radius: 12px;");
		TextIO.putln("-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.5);");
		TextIO.putln("background-color: #555;");
		TextIO.putln("}");

		TextIO.putln("");

		TextIO.putln("</style>");
		TextIO.putln("</head>");

		TextIO.putln("");

		/*-----------------------------------------------------BODY TIME----------------------------------------------------*/
		// Now we are in the Body guys. Time to own this logic!
		TextIO.putln("<body>");

		TextIO.putln("");

		String splitAwards[]; // Splits all information of an award into
								// different elements
		String splitPpl[];

		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> duplicateAwardsList = new ArrayList<String>();

		String tempAwardName = "", tempAwardYear = "", tempAwardDesc = "", tempAwardPpl = "", tempAwardOutcome = "";
		String randomTemporaryString;
		int counter = -1;

		int totalAwardMatches = 0, var, offset; // VAR and OFFSet
		String previousYear = "", previousAward = "";
		boolean oneWayFlag, open;

		// This loop is used to gather the total number of awards
		// Holy shit we did not think of a better way
		for(int i = 0; i < awardsBank.size(); i++)
		{

			// Loop to check if we have a IMDB Match of Award with our Award
			// Bank
			for(int j = 0; j < awards.size(); j++)
			{

				// Hit a match!
				if(awardsBank.get(i).getName()
								.equals(awards.get(j).split(";")[1]))
				{

					totalAwardMatches++; // To count the number of matches
					break;

				}

			}
		}

		int randomFlag;

		for(int i = 0; i < awardsBank.size(); i++)
		{

			oneWayFlag = false;

			duplicateAwardsList.clear(); // Used to clear the list for next use

			// Loop to check if we have a IMDB Match of Award with our Award
			// Bank
			for(int j = 0; j < awards.size(); j++)
			{

				// Hit a match!
				if(awardsBank.get(i).getName()
								.equals(awards.get(j).split(";")[1]))
				{
					duplicateAwardsList.add(awards.get(j));

					if(!oneWayFlag)
					{
						counter++; // To count the number of matches
						oneWayFlag = true;

						var = totalAwardMatches % 4;
						offset = totalAwardMatches - var;

						if(offset == totalAwardMatches)
						{
							offset = offset - 4;
						}

						if(counter == -1)
						{
						}

						else if(counter != offset && (counter + 1 % 4 == 0))
						{
							String str = "Counter: " + Integer.toString(counter) + "  Offset: " + Integer.toString(offset);

							TextIO.putln("<!--" + str + "-->");

							TextIO.putln("</div>");
						}

						else if(counter == offset && offset != 0)
						{
							TextIO.putln("</div>");
							TextIO.putln("<br>");
							TextIO.putln("<br>");
							TextIO.putln("<br>");
							TextIO.putln("<div class=\"lastgap\">");
						}

						else if(counter == offset && offset == 0)
						{
							TextIO.putln("<div class=\"lastgap\">");
						}

						else if(counter % 4 == 0 && counter != offset)
						{
							TextIO.putln("<div class=\"gap\">");
						}

						else if(counter == (totalAwardMatches - 1))
						{
							randomFlag = 0;
						}

					}
				}

			}

			open = false;

			for(int j = 0; j < duplicateAwardsList.size(); j++)
			{

				splitAwards = duplicateAwardsList.get(j).split(";");
				randomTemporaryString = splitAwards[3];
				splitPpl = randomTemporaryString.split("\\|");

				for(int m = 0; m < splitPpl.length; m++)
				{

					if(m == 0)
					{
						tempAwardPpl = splitPpl[m];
					}

					else
					{
						tempAwardPpl = tempAwardPpl + ", " + splitPpl[m];
					}
				}

				previousYear = tempAwardYear;
				previousAward = tempAwardName;
				tempAwardYear = splitAwards[0];
				tempAwardName = splitAwards[1];
				tempAwardDesc = splitAwards[2];
				tempAwardOutcome = splitAwards[4];

				if(!(tempAwardName.equalsIgnoreCase(previousAward)))
				{

					TextIO.putln("<!-- *****************************" + tempAwardName + "," + tempAwardYear + " *************************** -->");

					if(counter % 4 == 0)
					{
						TextIO.putln("<div class=\"tab\" style=\" margin-left: 10px;\">");

					}

					else if(counter % 4 == 1)
					{
						TextIO.putln("<div class=\"tab\" style=\" margin-left: 35px;\">");

					}

					else if(counter % 4 == 2)
					{
						TextIO.putln("<div class=\"tab\" style=\" margin-left: 50px;\">");

					}

					else if(counter % 4 == 3)
					{
						TextIO.putln("<div class=\"tab\" style=\" margin-left: 60px;\">");

					}

					TextIO.putln("<a href=\"http://midnightjabber.com/legend/\"><img class =\" grow \" src=\"" + awardsBank.get(i)
																															.getLink() + "\" style=\"height: 50px; width: auto;\" title=\"\"></a>");
					TextIO.putln("<div class=\"tabcontent\">");
					TextIO.putln("<div class=\"scrollbar\" id=\"style-1\">");
					TextIO.putln("<div class=\"force-overflow\">");
					TextIO.putln("<font size=\"4\" color=\"#F7AD07\"><b><u>" + tempAwardName.toUpperCase() + " " + tempAwardYear + "</u></b></font>");

					TextIO.putln();
					TextIO.putln("<br>");

				}

				if((tempAwardName.equalsIgnoreCase(previousAward)) && !(tempAwardYear.equals(previousYear)))
				{

					TextIO.putln("<font size=\"4\" color=\"#F7AD07\"><b><u>" + tempAwardName.toUpperCase() + " " + tempAwardYear + "</u></b></font>");

					TextIO.putln();
					TextIO.putln("<br>");

				}

				if(tempAwardOutcome.equalsIgnoreCase("Won"))
					TextIO.putln("<img src=\"http://midnightjabber.com/wp-content/uploads/2014/07/Award-Won.png\" style=\"height: 12px; width: auto;\" title=\"Won!\"><font size=\"3\"color=\"white\" style=\"margin-left:5px;\"><b><u>" + tempAwardDesc + "</u></b></font>");
				else
					TextIO.putln("<img src=\"http://midnightjabber.com/wp-content/uploads/2014/07/Award-Lose.png\" style=\"height: 12px; width: auto;\" title=\"Nominated\"><font size=\"3\"color=\"white\" style=\"margin-left:5px;\"><b><u>" + tempAwardDesc + "</u></b></font>");

				TextIO.putln("<br>");
				TextIO.putln("<font size=\"2\" color=\"white\">" + tempAwardPpl + "</font>");

				if(j != (duplicateAwardsList.size() - 1))
				{
					TextIO.putln("<br>");
					TextIO.putln("<br>");

				}
				else
				{
					TextIO.putln();
					TextIO.putln("</div>");
					TextIO.putln("</div>");
					TextIO.putln("</div>");
					TextIO.putln("</div>");
					TextIO.putln();
					TextIO.putln();
				}

			}

		}
		
		if((counter + 1 % 4 == 0) || counter == (totalAwardMatches - 1))
		{
			String str = "Counter: " + Integer.toString(counter) + "  Offset: " + Integer.toString(totalAwardMatches);

			TextIO.putln("<!--" + str + "-->");

			TextIO.putln("</div>");
		}

		TextIO.putln("</body>");
		TextIO.putln("</html>");

	}// Function Close
	
	public ArrayList<Integer> findAnAward() throws IOException
	{
		System.out.println("                                      Searching For an Award                                    ");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		String searchString, subString;
		int flg = 0, len, i, j, k, cntr = 0;
		char ch;

		System.out.println("Enter The Award Name: \n");
		searchString = br.readLine();

		len = searchString.length();

		for(i = 0; i < awardsBank.size(); i++)
		{

			for(k = 0, j = len; j <= awardsBank.get(i).getName().length(); k++, j++)
			{
				subString = awardsBank.get(i).getName().substring(k, j);

				if(searchString.equalsIgnoreCase(subString))
				{
					cntr = cntr + 1;
					indexes.add(i);

					System.out.print("\n" + cntr + ") ");
					System.out.println("\n  1. Award Name: " + awardsBank.get(i)
																			.getName());

					flg = 1;

					break;
				}
			}

		}
		if(flg == 0 || awardsBank.isEmpty())
		{
			System.out.println("\n Sorry, Couldn't Find An Award..");
		}

		System.out.println("\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return indexes;
		}

	}

	public void addNewAuthor() throws IOException
	{
		System.out.println("\n");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		String name;

		System.out.println("Enter the name of the new Author: ");
		name = br.readLine();

		authorsBank.add(name);
	}

	public ArrayList<Integer> findAnAuthor() throws IOException
	{
		System.out.println("                                      Searching For an Author                                      ");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		String searchString, subString;
		int flg = 0, len, i, j, k, cntr = 0;
		char ch;

		System.out.println("Enter The Name of the Author: \n");
		searchString = br.readLine();

		len = searchString.length();

		for(i = 0; i < authorsBank.size(); i++)
		{

			for(k = 0, j = len; j <= authorsBank.get(i).length(); k++, j++)
			{
				subString = authorsBank.get(i).substring(k, j);

				if(searchString.equalsIgnoreCase(subString))
				{
					cntr = cntr + 1;
					indexes.add(i);

					System.out.print("\n" + cntr + ") " + authorsBank.get(i));

					flg = 1;

					break;
				}
			}

		}
		if(flg == 0 || authorsBank.isEmpty())
		{
			System.out.println("\n Sorry, Couldn't Find The Author You Were Looking For");
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return indexes;
		}

	}

	public ArrayList<Integer> findAnAuthorsWorks() throws IOException
	{

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAnAuthor();

		String temp;
		int terminal, i, n = 0, m = 0;

		if(idx.size() == 1)
		{
			i = idx.get(0);
		}

		else if(idx.size() == 0)
		{
			char tmp;
			System.out.println("No Author with that Name Found. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if(tmp == 'y')
			{
				findAnAuthorsWorks();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return null;
			}
			else
			{
				return null;
			}

		}

		else
		{

			do
			{
				if(m == 1)
				{
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			}
			while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		ArrayList<Integer> indexes = new ArrayList<Integer>();

		String searchString, subString;
		int flg = 0, len, j, k, cntr = 0;
		char ch;

		searchString = authorsBank.get(i);

		len = searchString.length();

		for(i = 0; i < authorItem.size(); i++)
		{

			for(k = 0, j = len; j <= authorItem.get(i).length(); k++, j++)
			{
				subString = authorItem.get(i).substring(k, j);

				if(searchString.equalsIgnoreCase(subString))
				{
					cntr = cntr + 1;
					indexes.add(i);

					System.out.print("\n" + cntr + ") ");
					System.out.println("\n  1. Movie Name: " + movieNames.get(i));
					System.out.println("\n  2. Release Date: " + releaseDates.get(i));
					System.out.println("\n  3. Image URL: " + imageURLs.get(i));
					System.out.println("\n  4. Image HREF: " + imageHREFs.get(i));
					System.out.println("\n  5. IMDB Awards URL: " + IMDBawardsPageURLs.get(i));
					flg = 1;

					break;
				}
			}

		}
		if(flg == 0 || authorItem.isEmpty())
		{
			System.out.println("\n Sorry, Couldn't Find The Author You Were Looking For");
		}

		System.out.println("\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return indexes;
		}

	}

	public void editAuthorBank() throws NumberFormatException, IOException
	{
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAnAuthor();

		char ch;

		String temp;
		int terminal, i, n = 0, m = 0;

		if(idx.size() == 1)
		{
			i = idx.get(0);
		}

		else if(idx.size() == 0)
		{
			char tmp;
			System.out.println("No Author with that Name Found. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if(tmp == 'y')
			{
				editAuthorBank();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return;
			}
			else
			{
				return;
			}

		}

		else
		{

			do
			{
				if(m == 1)
				{
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			}
			while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out.println("\n\n ------------Author Selected to Be Edited------------");
		System.out.println("\n Author Name: " + authorsBank.get(i));

		System.out.println("\n\n Are you Sure You want to edit this Author? (y/n)");

		ch = (char) br.read();

		if(ch == 'y')
		{
			System.out.println("\nEnter New Author Name: ");
			temp = br.readLine();

			authorsBank.set(i, temp);
		}

		else
		{
			return;
		}
	}

	public void deleteAuthor() throws IOException
	{
		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		ArrayList<Integer> idx = new ArrayList<Integer>();

		idx = findAnAuthor();

		char ch;

		int i, n = 0, m = 0;

		if(idx.size() == 1)
		{
			i = idx.get(0);
		}

		else if(idx.size() == 0)
		{
			char tmp;
			System.out.println("No Author with that Name Found. Do you want to try again? (y/n)");
			tmp = (char) br.read();
			if(tmp == 'y')
			{
				editAuthorBank();

				/*
				 * 
				 * 
				 * Remember to change the above function call to the function
				 * you're copying this code to
				 */
				return;
			}
			else
			{
				return;
			}

		}

		else
		{

			do
			{
				if(m == 1)
				{
					System.out.println("\nInavalid Choice, Enter Again: \n");
				}

				System.out.println("Enter a choice: \n");
				n = Integer.parseInt(br.readLine());
				m = 1;
			}
			while (n > idx.size() || n < 0);

			i = idx.get(n - 1);
		}

		System.out.println("\n\n ------------Author Selected to Be Deleted------------");
		System.out.println("\n Author Name: " + authorsBank.get(i));

		System.out.println("\n\n Are you Sure You want to DELETE this Author? (y/n)");

		ch = (char) br.read();

		if(ch == 'y')
		{
			authorsBank.remove(i);
		}

		else
		{
			return;
		}
	}

	public void displayAuthorBank() throws IOException
	{
		System.out.println("------------------------- Author Bank -------------------------");

		InputStreamReader rd = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(rd);

		int i;
		char ch;

		for(i = 0; i < authorsBank.size(); i++)
		{
			System.out.println("\n" + (i + 1) + ") " + authorsBank.get(i));
		}

		System.out.println("\n\nPress 0 Key to Continue...\n");
		while (true)
		{
			ch = (char) br.read();
			if(ch == '0')
				return;
		}
	}

	public void generateAuthorsCode() throws IOException
	{
		ArrayList<Integer> authorsWorks = new ArrayList<Integer>();

		ArrayList<String> authorsWorksImageURLs = new ArrayList<String>();
		ArrayList<String> authorsWorksImageHREFs = new ArrayList<String>();
		ArrayList<String> authorsMovies = new ArrayList<String>();

		String authorName;
		String arr[];

		int i;

		authorsWorks = findAnAuthorsWorks();

		authorName = authorItem.get(authorsWorks.get(0));

		arr = authorName.split(" ");

		for(i = 0; i < arr.length; i++)
		{
			if(i == 0)
			{
				authorName = arr[i];
			}
			else
			{
				authorName = authorName + arr[i];
			}
		}

		for(i = 0; i < authorsWorks.size(); i++)
		{
			authorsMovies.add(movieNames.get(authorsWorks.get(i)));
			authorsWorksImageURLs.add(imageURLs.get(authorsWorks.get(i)));
			authorsWorksImageHREFs.add(imageHREFs.get(authorsWorks.get(i)));
		}

		// Code generation
		i = 0;

		int numberOfDivisions;
		int j = 0, k = 0;

		String fileName = "./AuthorPageFile/"+authorName + "sPage.txt";
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
		numberOfDivisions = ((authorsMovies.size()) / 25);

		TextIO.putln("if(y >= contentHeight && cntr<" + numberOfDivisions + ")"); // checking
																					// scroll
																					// height
		TextIO.putln("{ \n");
		TextIO.putln("// Ajax call to get more dynamic data goes here \n");

		// inside first if

		// now going for content==1,2 etc.

		for(z = 0; z < numberOfDivisions; z++)
		{

			if(flg == 0)
			{
				flg = 1;
				TextIO.putln("");
				TextIO.putln("if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for(i = ((z + 1) * 25); i < (((z + 1) * 25) + 25) && i < authorsMovies.size(); i = i + 5)
				{

					/* Image Area */
					for(j = i; j < i + 5 && j < (((z + 1) * 25) + 25) && j < authorsMovies.size(); j++)
					{
						if(j % 5 == 0)
						{
							str += "<a href=\"" + authorsWorksImageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + authorsWorksImageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else
						{
							str += "<a href=\"" + authorsWorksImageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + authorsWorksImageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for(k = i; k < i + 5 && k < (((z + 1) * 25) + 25) && k < authorsMovies.size(); k++)
					{
						if(k % 5 == 0)
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + authorsWorksImageHREFs.get(k) + "\"><font color=\"black\">" + authorsMovies.get(k) + "</font></a></div>";

						}

						else
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + authorsWorksImageHREFs.get(k) + "\"><font color=\"black\">" + authorsMovies.get(k) + "</font></a></div>";

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

			if(flg == 1)
			{

				TextIO.putln("");
				TextIO.putln("else if(content == " + (z + 1) + ")");
				TextIO.putln("{ ");

				TextIO.putln("cntr++; ");

				for(i = ((z + 1) * 25); i < (((z + 1) * 25) + 25) && i < authorsMovies.size(); i = i + 5)
				{

					/* Image Area */
					for(j = i; j < i + 5 && j < (((z + 1) * 25) + 25) && j < authorsMovies.size(); j++)
					{
						if(j % 5 == 0)
						{
							str += "<a href=\"" + authorsWorksImageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + authorsWorksImageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>";

						}

						else
						{
							str += "<a href=\"" + authorsWorksImageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + authorsWorksImageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>";

						}
					}

					TextIO.putln("");

					str += "<br><br>";

					/* Text Area */
					str += "<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">";
					for(k = i; k < i + 5 && k < (((z + 1) * 25) + 25) && k < authorsMovies.size(); k++)
					{
						if(k % 5 == 0)
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + authorsWorksImageHREFs.get(k) + "\"><font color=\"black\">" + authorsMovies.get(k) + "</font></a></div>";

						}

						else
						{
							str += "<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + authorsWorksImageHREFs.get(k) + "\"><font color=\"black\">" + authorsMovies.get(k) + "</font></a></div>";

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

		for(z = 0; z < 25 && z < authorsMovies.size(); z = z + 5)
		{

			/* Image Area */
			TextIO.putln("<!--Image Area-->");
			for(j = z; j < z + 5 && j < authorsMovies.size(); j++)
			{
				if(j % 5 == 0)
				{
					TextIO.putln("<a href=\"" + authorsWorksImageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + authorsWorksImageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 5px;\" ></a>");
					TextIO.putln("");
				}

				else
				{
					TextIO.putln("<a href=\"" + authorsWorksImageHREFs.get(j) + "\"><img class = \"grow glow\" src=\"" + authorsWorksImageURLs.get(j) + "\" width=215px height=auto title=\"\" style=\"margin-left: 26px;\" ></a>");
					TextIO.putln("");
				}
			}

			TextIO.putln("<br>");
			TextIO.putln("");

			/* Text Area */
			TextIO.putln("<!--Text Area-->");
			TextIO.putln("<font face=\"Yanone Kaffeesatz Regular\" color=\"black\" size=\"6\">");
			for(k = z; k < z + 5 && k < authorsMovies.size(); k++)
			{
				if(k % 5 == 0)
				{
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 10px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + authorsWorksImageHREFs.get(k) + "\"><font color=\"black\">" + authorsMovies.get(k) + "</font></a></div>");
					TextIO.putln("");
				}

				else
				{
					TextIO.putln("<div style=\"width:215px; height:150px; margin-left: 30px; margin-top:15px; float: left; position:relative;\" ><a href=\"" + authorsWorksImageHREFs.get(k) + "\"><font color=\"black\">" + authorsMovies.get(k) + "</font></a></div>");
					TextIO.putln("");
				}
			}

			TextIO.putln("</font>");

		}

		TextIO.putln("</body>");
		TextIO.putln("</html>");
		TextIO.putln("");

	}

	
}
