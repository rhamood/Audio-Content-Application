import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
		try{

			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				// intialize from and start indices
				int fromIndex = 0;
				int toIndex = 0;
				//prints the message
				System.out.print("Store Content #: ");
				// if the user inters an integer
				if (scanner.hasNextInt())
				{
					// set the int to fromidex
					fromIndex = scanner.nextInt(); 
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				//prints the second message
				System.out.print("To Store Content #: ");
				// if the user inters an integer
				if (scanner.hasNextInt())
				{
					// sents toindex to the second int enterd
					toIndex = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// iterares over the range from the first num entered to the second num entered
				for(int i = fromIndex;i <= toIndex;i++){
					// sets content to the audiocontent object
					AudioContent content = store.getContent(i);
					if (content == null)
					{
						System.out.println("Content not Found"); //edge case
					}
					else
					try
					{
						//tries to download the content from the library
						mylibrary.download(content);
					}
					// if cannot then enter the runtime exception
					catch(RuntimeException e)
					{
						System.out.println(e.getMessage());
					}
				}
				

			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0; //initial index to 0 user input will change the index

				System.out.print("Song Number #: "); //prints the message
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					index = scanner.nextInt(); // sets the variable to the user input of an integer
					scanner.nextLine(); //goes to next line because nextint does not check next line
				}
				// Print error message if the song doesn't exist in the library
				mylibrary.playSong(index); 
				
				
			}

			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					index = scanner.nextInt(); // sets the variable to the user input of an integer
					scanner.nextLine();// "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// Print error message if the book doesn't exist in the library
				mylibrary.printAudioBookTOC(index); 
				
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;
				int chapter = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					index = scanner.nextInt(); //if user inputs an integer
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				System.out.print("Chapter: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					chapter = scanner.nextInt(); // sets the variable to the user input of an integer
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}

				mylibrary.playAudioBook(index, chapter); //checks if method return true or false
		
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				int index = 0;
				int season = 0;
				
				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				if (!mylibrary.printPodcastEpisodes(index, season))
					System.out.println(mylibrary.getErrorMessage());	
			
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				int index = 0;

				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				int season = 0;
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				int episode = 0;
				System.out.print("Episode: ");
				if (scanner.hasNextInt())
				{
					episode = scanner.nextInt();
					scanner.nextLine();
				}
				if (!mylibrary.playPodcast(index, season, episode))
					System.out.println(mylibrary.getErrorMessage());	
			
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String playlistTitle = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine()) //if user enter a string
				{
					playlistTitle = scanner.nextLine(); //sets the string to the given variable
				}
				
				mylibrary.playPlaylist(playlistTitle);
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				// initializes the contructor varbales 
				int indexInPL = 0;
				String playlistTitle = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					playlistTitle = scanner.nextLine(); //sets the string to the given variable
				}
				System.out.print("Content Number: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					indexInPL = scanner.nextInt(); // sets the variable to the user input of an integer
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				mylibrary.playPlaylist(playlistTitle,indexInPL);
	
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				// initializes variables
				int index = 0;
				System.out.print("Library Song #: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					index = scanner.nextInt(); //sets index to the input given by user
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}

				mylibrary.deleteSong(index-1); 

			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();  // sets the variable to the user input of an string
				}
				mylibrary.makePlaylist(title); //checks if the return of the method was true or false
				
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine(); // sets the variable to the user input of an integer
				}
				mylibrary.printPlaylist(title); //checks if the return of the method was true or false
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				// this method has three scanners because it goes through all the parametres and user input
				// initializes given variables from method
				String playlistTitle = "";
				String type = "";
				int index = 0;

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine()) //checks if user input a string
				{
					playlistTitle = scanner.nextLine(); //sets the user input to the given variable
				}

				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				if (scanner.hasNextLine()) //checks if user input a string
				{
					type = scanner.nextLine();  //sets the user input to the given variable
				}

				System.out.print("Library Content #: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					index = scanner.nextInt();  //sets the user input to the given integer
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				mylibrary.addContentToPlaylist(type,index,playlistTitle);
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				// initialiizing variable from method in library
				int index = 0;
				String title = ""; 
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine()) //checks if user has input a string
				{
					title = scanner.nextLine(); //sets the parameter to the given string givn by user
				}

				System.out.print("Playlist Content: ");
				if (scanner.hasNextInt()) //if user inputs an integer
				{
					index = scanner.nextInt(); //sets varibale to user input
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				mylibrary.delContentFromPlaylist(index, title); //checks if the return was true or false of the method
				// this method has two scanners because it goes through all the parametres and user input
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}
			else if(action.equalsIgnoreCase("SEARCH"))
			{
				//intializes title to an empty string
				String title = "";
				// prints out message
				System.out.print("Title: ");
				//sets title to the string entered by user
				title = scanner.nextLine();
				// runs search map
				store.search(title);
			}

			else if(action.equalsIgnoreCase("SEARCHA"))
			{
				String artist = ""; // intiializing string
				System.out.print("Artist: ");
				artist = scanner.nextLine();
				ArrayList<Integer> index = store.searchA(artist);
				// iterates over index arraylist created
				for(int i=0; i < index.size(); i++)
				{
					System.out.print(index.get(i)+1 + ". ");
					store.getContent(index.get(i)+1).printInfo();
					//prints info from super class and the index + 1
					// because the index starts at 0
					System.out.println("\n");
				}
			}

			else if(action.equalsIgnoreCase("SEARCHG"))
			{
				String genre = ""; //intialing genre to empty string
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				genre = scanner.nextLine();
				ArrayList<Integer> index = store.searchG(Song.Genre.valueOf(genre));
				// iterates over index arraylist that was created
				for(int i = 0;i < index.size(); i++)
				{
					System.out.print(index.get(i)+1 + ". ");
					store.getContent(index.get(i)+1).printInfo();
					System.out.println("\n");
				}
			}

			else if(action.equalsIgnoreCase("DOWNLOADA"))
			{
				String artist = "";
				// prints message
				System.out.print("Artist Name: ");
				artist = scanner.nextLine();

				ArrayList<Integer> index = store.searchA(artist);
				// iterates over index arrraylist
				for (int i = 0; i < index.size(); i++) {
					int j = index.get(i);
					//creates content variable from object audiocontent
					AudioContent content = store.getContent(j + 1);
					try 
					{
						mylibrary.downloadAG(content);
					} 
					catch (RuntimeException exception)
					{
						System.out.println(exception.getMessage());
					}
				}
			}

			else if(action.equalsIgnoreCase("DOWNLOADG"))
			{
				System.out.print("Genre: ");
				String genre = scanner.nextLine();

				ArrayList<Integer> index = store.searchG(Song.Genre.valueOf(genre));
				// iterates over index arraylist made
				for (int i = 0; i < index.size(); i++) {
					// set j to be the number taken from index
					int j = index.get(i);
					//creates content variable from object audiocontent
					AudioContent content = store.getContent(j + 1);
					// try block passes variable content as parameter
					try 
					{
						mylibrary.downloadAG(content);
					} 
					catch (RuntimeException e) 
					{
						// prints exception message
						System.out.println(e.getMessage());
					}
				}
			}

		}
		// runtime error is called
		catch(RuntimeException e)
		{
			//sends the messgae when the exception is called
			System.out.println(e.getMessage());
		}
			System.out.print("\n>");
		}
	}
}