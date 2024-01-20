import java.net.NetPermission;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.print.attribute.standard.PrinterInfo;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	AudioContentStore contentStore = new AudioContentStore();
  	private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg; //method will be used in myaudioui
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks  	= new ArrayList<AudioBook>(); ;
		playlists       = new ArrayList<Playlist>();
	   podcasts		= new ArrayList<Podcast>(); ;
	}

	public void download(AudioContent content)
	{ 
		// checks if the type is a song or not
		// enters if statement if it a song
		if (content.getType().equals(Song.TYPENAME)){
		{
			//iterates over songs arraylist
			for(int i = 0;i < songs.size(); i++)
			{
			if(songs.get(i).equals(content))//chskcs if song sis equal to content object 
			{
				//throws already downloaed excpetion
				throw new AlreadyDownloaded("Song "+ content.getTitle() + " already downloaded");
			}
				}
		}
		// else adds the song to the content
		songs.add((Song)content);  
		// prints statement when it is downloaded to let user know
		System.out.println("SONG "+ content.getTitle() +" Added to Library");
		}
		// checks if content is of type audiobook
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			// iterates over audiobook arraylist
			for(int i=0;i < audiobooks.size();i++)
			{ 
				//chskcs if song sis equal to content object
				if(audiobooks.get(i).equals(content))
				{ 
					//throws already downloaded exception
					throw new AlreadyDownloaded("Audiobook "+content.getTitle()+" already downloaded"); 
				}
			}
			audiobooks.add((AudioBook)content); 
			System.out.println("AUDIOBOOK "+ content.getTitle() +" Added to Library");
		}
	}
	// download method for artist and genre with content instance
	public void downloadAG(AudioContent content)
	{
		// if type is a song
		if(content.getType() == Song.TYPENAME)
		{ 
			//iterate over songs arraylist
			for (int i = 0; i < songs.size(); i++) {
				//create a new song variable and cast it to the song object
				Song song = songs.get(i);
				// if song is equal to content
				if (song.equals(content)) 
				{
					// throw already downloaed exception
					throw new AlreadyDownloaded("Song " + content.getTitle() + " already downloaded");
				}
			}
			// else add the song to the contents
			songs.add((Song) content);
		}
			
		// else if check if the type is an audiobook
		else if(content.getType() == AudioBook.TYPENAME){ 
			//iterate over the audiobook arraylist
			for (int i = 0; i < audiobooks.size(); i++)
			{
				//create a new book variable and cast it to the audiobook object
				AudioBook book = audiobooks.get(i);
				// if book is equal to content
				if (book.equals(content)) {
					// throw already downloaed exception
					throw new AlreadyDownloaded("Audiobook " + content.getTitle() + " already downloaded");
				}
			}
			//else it is not downloaded so add the book to the contents
			audiobooks.add((AudioBook) content);
		}
	}
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++) //iterates over songs arraylist starting at 0
		{
			int index = i + 1;  //adds one to index because when printing you start at 1 and not 0
			System.out.print("" + index + ". "); //prints the index and a period
			songs.get(i).printInfo();			//followed by the information (songs) from songs arraylist
			System.out.println();				//print line to follow the txtfile format
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++) //iterates over songs arraylist starting at 0
		{
			int index = i + 1; //adds one to index because when printing you start at 1 and not 0
			System.out.print("" + index + ". "); //prints the index and a period
			audiobooks.get(i).printInfo();	//followed by the information (audiobook) from audiobook arraylist
			System.out.println();	//print line to follow the txtfile format
		}
	}
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i+1;
			System.out.print(index + ". ");
			podcasts.get(i).printInfo();
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++) //iterate over the playlists arraylist
		{
			int index = i + 1; //adds one to index because when printing you start at 1 and not 0
			System.out.print("" + index + ". " + playlists.get(i).getTitle()); //prints songtitle of playlist arraylist at the index with the string .
			System.out.println();	//print line to follow the txtfile format
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names

		ArrayList<String> artists = new ArrayList<String>(); //creates new arraylist to add the artists from the song
		for (int i = 0; i < songs.size(); i++) //iterate over song arraylist
		{	
			String currentSong = songs.get(i).getArtist(); //creates string current song tht is the current artist at index i from songs arraylist
			if (!artists.contains(currentSong)) //if the artists name is not in the currentsong arraylist
			{
				artists.add(currentSong); //add the artist name to the array list
				
			}
		}	
		for (int i = 0; artists.size() > i; i++)
		{
			System.out.println((i+1) + ". " + artists.get(i)); //prints i+1 with the period because you dont want string to start at 0
			//also pints the name of the artist from the newly created arraylist
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index) 
	{
		if ( (index) > songs.size()) //edge case checks of if index is out of index
		{
			errorMsg = "Please Enter A Valid Input"; //creates errormsg to be read in myaudioui
			System.out.println(errorMsg);
			return false;
		}
		if ((index) < 1) //edge case
			{
				Song deleteSong = songs.get(index); //initialize the song that is to be deleted
				songs.remove(deleteSong); // delete a song from the playlist songs
				return false;
			}
			Song deleteSong = songs.get(index); //initialize the song that is to be deleted
			songs.remove(deleteSong); // delete a song from the playlist songs
		for (int i = 0; i < playlists.size(); i++) //iterate over the playlist arrayList
		{	
			Playlist currentPlaylist = playlists.get(i); //initialize the current song chosen from class Playlist
			if (currentPlaylist.getContent().contains(deleteSong)) // if the current playlist chosen contains the song that should be deleted
			{
				currentPlaylist.getContent().remove(currentPlaylist.getContent().indexOf(deleteSong));
				//then remove the current song from the information at the current index
				return true;
			}
		}
		return false;
	}

  //Sort songs in library by year
	public void sortSongsByYear()
	{
		//sorts a ist of song objects in ascending order 
		//order is based on the release year given by songyearcomparator information
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> //implents the compartor created in method about
	// implements Comparator
	{
		public int compare(Song song, Song otherSong)
		{
			//checks if the year of the song is less than the index of the length of the other song
			if (song.getYear() < otherSong.getYear())
			{
				//return the -1 to go back
				return -1;
			}
			//checks if the year of the song is equal to the index of the length of the other object
			else if (song.getYear() == otherSong.getYear())
			{
				//return 0 for no changes to happen
				return 0;
			}
		// else the song year is greater than the other song object 
		// that case return 1
				return 1;
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		//sorts a list of song objects in ascending order 
		//order is based on the length of the songs given by SongLengthComparator information
		Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song> //implements the comparator song
	{
		public int compare(Song song, Song otherSong)
		{
			//checks if the length of the song is less than the length of the other song
			if (song.getLength() < otherSong.getLength())
			{
				return -1; //return -1
			}
			else if (song.getLength() == otherSong.getLength())
			{
				// if the lengths of the song are equivalent than return 0
				return 0; 
			}
		// else the song length is greater than the other song object 
		// that case return 1
				return 1;
		}
	}

	// Sort songs by title 
		// Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
	public void sortSongsByName()
	{
		//sorts a list of song objects in ascending order 
		//order is based on the first letter of the given title by SongNameCapartor information
		Collections.sort(songs, new SongNameCapartor());
	}
	private class SongNameCapartor implements Comparator<Song> //implements the comparator song
	{
		public int compare(Song song, Song otherSong)
		{
			//checks if the first letter of the title (using charat(0)) 
			//of the song is less than the first letter of the title of the other song
			if (song.getTitle().charAt(0) < otherSong.getTitle().charAt(0))
			{
				return -1;
			}
			else if (song.getTitle().charAt(0) == otherSong.getTitle().charAt(0))
			{
				// if the first letter of the song are equivalent than return 0
				return 0;
			}
		// else the letter at char index is greater than the other song object 
		// that case return 1
				return 1;
		}
	}
	/*
	 * Play Content
	 */
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{
			throw new NotFound("Song Not Found");

		}
		//else place the index using method play() from superclass audiocontent
		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		if (index < 1 || index > podcasts.size())
		{
			errorMsg = "Podcast Not Found";
			return false;
		}
		Podcast podcast = podcasts.get(index-1);
		if (season < 1 || season > podcast.getSeasons().size())
		{
			errorMsg = "Season Not Found";
			return false;
		}
		
		if (index < 1 || index > podcast.getSeasons().get(season-1).episodeTitles.size())
		{
			errorMsg = "Episode Not Found";
			return false;
		}
		podcast.setSeason(season-1);
		podcast.setEpisode(episode-1);
		podcast.play();
		return true;

	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		if (index < 1 || index > podcasts.size())
		{
			errorMsg = "Podcast Not Found";
			return false;
		}
		Podcast podcast = podcasts.get(index-1);
		podcast.printSeasonEpisodes(season);
		return true;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	//now  a void method because of exception handing
	public void playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size()) //edge case checks if index is out of bounds
		{
			// throw not found exception
			throw new NotFound("Audiobook Not Found");
		}
		if (chapter < 1 || chapter > audiobooks.get(index-1).getNumberOfChapters())  //edge case 
		{
			//thows new notfound exception
			throw new NotFound("Chapter Not Found");
		}
		else
		{
			audiobooks.get(index-1).selectChapter(chapter); //gets the current chapter, uses index-1 so get the first chapter at position 0
			audiobooks.get(index-1).play(); //uses .play from superclass audiocontent	
		}
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	//now  a void method because of exception handling
	public void printAudioBookTOC(int index)
	{
		for (int i = 0; i < audiobooks.size(); i++) //iterates over the audiobook arraylist
		{
			if (index < 1 || index > audiobooks.size()) //checks if index is less than 1 or out of range being greater than the audiobooks length
			{
				// not found exception is thrown
				throw new NotFound("Chapter Not Found");					// return the error message
			}
		}
		// else 
		audiobooks.get(index-1).printTOC(); // the index is within range so retrieve the chapter and table of contents

	}

  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	//now  a void method because of exception handing
	public void makePlaylist(String title)
	{
		Playlist newPlaylist = new Playlist(title); // creates a new playlist object 

		for (int i = 0; i < playlists.size(); i++) //iterate over playlists
		{
			if (playlists.get(i).getTitle().equals(newPlaylist.getTitle())) //checks if the playlist is already created in the playlist arraylist
			{
				//throws exception that the playlist has already been made
				throw new AlreadyExists("Playlist Already Exists");
			}
		}
		//else the playlist has not been created yet in the arraylist playlists
		playlists.add(newPlaylist); // add the new created playlist to the playlist arraylist
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	//now  a void method because of exception handing
	public void printPlaylist(String title)
	{
		for (int i =0; i < playlists.size(); i++) //iterates over playlists arraylist
		{
			if (playlists.get(i).getTitle().equals(title)) //checks if there exist an arraylist in the arraylist, playlists
			{
				playlists.get(i).printContents(); //if true then retrive the contents from the playlist for it to be printed

			}
		}
		//else playlist titles are not found and notfound excpetion is called
		throw new NotFound("Playlist Not Found");
	}
	
	// Play all content in a playlist
	//now  a void method because of exception handing
	public void playPlaylist(String playlistTitle) //1
	{
		Playlist newPlaylist = null; //create playlist in the playlist to null

		for (int i = 0; i < playlists.size(); i++) //iterate over the playlists arraylist
		{
			if (playlistTitle.equals(playlists.get(i).getTitle())) // if the playlistis equal to the current title in the arraylist

			{
				newPlaylist = playlists.get(i);	//sets new playlist to the current playlist at index i
				newPlaylist.playAll();			//call playall method

			}
	
		}
		// else song is not equal to the current title retrieved from method
		// in place of error message a excpetion is called
		throw new NotFound("Playlist Not Found");

	}
	
	// Play a specific song/audiobook in a playlist
	//now  a void method because of exception handing
	public void playPlaylist(String playlistTitle, int indexInPL) //2
	{
		Playlist newPlaylist = null;	//create playlist in the playlist to null
		for (int i = 0; i< playlists.size(); i++) //iterate over the playlists arraylist
		{
			if (playlists.get(i).getTitle().equals(playlistTitle))  // if the specific song or audiobook in the arraylist by using equals method simialr to method above
			{
				newPlaylist = playlists.get(i); //sets new playlist to the current playlist at index i
			}
		}
		if (newPlaylist == null) //edge case
		{
			// not found exception is called
			throw new NotFound("Playlist Not Found");
		}
		//creates two new objects
		Song newSong;
		AudioBook newBook;

		if (indexInPL < 1 || indexInPL > newPlaylist.getContent().size()) //edge case that checks if there is no given index or
		// the index is larger than the newplaylists size
		{
			//edge case exception
			throw new NotFound("Index Not Found");
		}
		else
		{
			if (newPlaylist.getContent().get( indexInPL- 1).getType().equals("SONG")) //gets content, index and the type and if the type is equal to "song"
			{
				//create new song object with the given information for song
				newSong = (Song) newPlaylist.getContent().get(indexInPL-1);
				//call play from super class and return the given information initialized in method .play
				newSong.play();
			}
			if (newPlaylist.getContent().get( indexInPL- 1).getType().equals("AUDIOBOOK"))
			//gets content, index and the type and if the type is equal to "audiobook"
			{
				//create new audiobook object with the given information for audiobook
				newBook = (AudioBook) newPlaylist.getContent().get(indexInPL-1);
				//call play from super class and return the given information initialized in method .play
				newBook.play();
			}

		}
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		Playlist newPlaylist = null; //create playlist in the playlist to null
		for (int i = 0; i <playlists.size();i++) //iterates over playlist
		{
			if (playlists.get(i).getTitle().equals(playlistTitle)) // checks if playlist title equal to the title from playlists arraylist at index i
			{
				 //sets new playlist to the current playlist at index i
				newPlaylist = playlists.get(i);
			}
		}

		if (newPlaylist == null) //edge case
		{
			// exception for an edge case
			throw new NotFound("Playlist Not Found");
		}

		if (type.equalsIgnoreCase("song")) //edge case
		{
			if (index < 1 || index > songs.size()) //that checks if there is no given index or is larger than the songs size aka length
			{
				// edge case exception
				throw new NotFound("Playlist Not Found");
			}
			else
			{	
				// retieve the infromation from the newplaylist and add the song at index -1
				// we do index - 1 because the arraylist starts at 0
				newPlaylist.getContent().add(songs.get(index-1));
			}
		}
			if (type.equalsIgnoreCase("audiobook")) // edge case exception
			{
				//that checks if there is no given index or is larger than the audiobooks size
				if (index < 1 || index > audiobooks.size())
				{
					throw new NotFound("Audiobook Not Found");
				}
				else
				{
				// retieve the infromation from the newplaylist and add the audiobook at index -1
				// we do index - 1 because the arraylist starts at 0
					newPlaylist.getContent().add(audiobooks.get(index-1));
				}
			}
		
		// else no exception is called 
		errorMsg = "Please enter a valid input";
	}


  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		for (int i =0; i < playlists.size(); i++) //iterateover playlists arraylist
		{
			if (playlists.get(i).getTitle().equals(title))	// if a song title is in the playlist araylist 
				{
					playlists.get(i).deleteContent(index); //delete the content at the given index which will be the song
					return true;	//return true
				}
		}
		//no song was found so create error message
		errorMsg = "Playlist not found";
		return false;
	}
	//alreadydownloaded exception extends runtime so it inherits from runtime
	public class AlreadyDownloaded extends RuntimeException{
		//creates alreadydownloaded instance created
		public AlreadyDownloaded() {}

		public AlreadyDownloaded(String message){
			super(message);
		}
	}
	//notfound exception extends runtime so it inherits from runtime
	public class NotFound extends RuntimeException{
		//creates notfound instance created
		public NotFound() {}

		public NotFound(String message){
			super(message);
		}
	}
	//alreadyexists exception extends runtime so it inherits from runtime
	public class AlreadyExists extends RuntimeException{
		//creates alreadyexists instance created
		public AlreadyExists() {}

		public AlreadyExists(String message){
			super(message);
		}
	}

	
	
}

//  HELLO