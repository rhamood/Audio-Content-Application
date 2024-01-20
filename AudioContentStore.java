import java.util.ArrayList;
import java.io.IOException;
//importing maps and exception to be used
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Iterator;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{		
	//initializing contents and maps
	private ArrayList<AudioContent> contents;
	private Map<String, Integer> titleMap;
	private Map<String, ArrayList<Integer> > artistMap;
	private Map<Song.Genre, ArrayList<Integer>> genreMap; 
	
	public AudioContentStore()
	{
		// contents = new ArrayList<AudioContent>();
		// initializing hashmaps
		titleMap = new HashMap<String, Integer>(); // searching for title
		artistMap = new HashMap<String, ArrayList<Integer>>(); // searching for artist
		genreMap = new HashMap<Song.Genre, ArrayList<Integer> >(); // searching for genre
		
		// try to read the content
		try
		{
			contents = readContent();
		}
		//catch the input/output exception
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

	// iterating over contents contents arraylist
	for(int i=0; i< contents.size(); i++)
	{
		//maps title name to i which is the key
		titleMap.put(contents.get(i).getTitle(), i);
	}

	// iterating over contents contents arraylist
	for(int i=0; i < contents.size(); i++)
	{
		//creates arraylist that holds the indices of the song and audiobooks
		ArrayList<Integer> number = new ArrayList<Integer>();
		// if the current i item is of type "song" 
		if(contents.get(i).getType().equalsIgnoreCase("SONG")){
			//create a new song variable and cast it to the song object
			Song song = (Song)contents.get(i);
			// if the artistMap contains the artist of the song object.
			if(artistMap.containsKey(song.getArtist()))
			{
				//maps song name to the key, i
				number = artistMap.get(song.getArtist());
				//add i to the arraylist, number
				number.add(i);
			}
			else
			{
				//add i to the arraylist, number
				number.add(i);
				// Add a mapp to the artistMap HashMap with the author as the key
				artistMap.put(song.getArtist(), number);
			}
		}
		// if the current i item is of type "audiobook" 
		else if(contents.get(i).getType().equalsIgnoreCase("AUDIOBOOK"))
		{
			//create a new book variable and cast it to the audiobook object
			AudioBook book = (AudioBook)contents.get(i);
			// if the artistMap contains the author of the audiobook object.
			if(artistMap.containsKey(book.getAuthor()))
			{
				//maps book name to the key, i
				number = artistMap.get(book.getAuthor());
				//add the index, i to the number arraylist
				number.add(i);
			}
			else
			{
				//add the index, i to the number arraylist
				number.add(i);
				artistMap.put(book.getAuthor(), number);
			}
		}
	}

	// iterating over contents contents arraylist
	for(int i=0; i < contents.size(); i++)
	{
		//creates arraylist that holds the indices the genre of the songs
		ArrayList<Integer> number2 = new ArrayList<Integer>();
		// if the current i item is of type "song" 
		if(contents.get(i).getType().equalsIgnoreCase("SONG"))
		{
			//create a new song variable and cast it to the song object
			Song song = (Song)contents.get(i);
			Song.Genre[] genres = Song.Genre.values();
			// iterating over genres in contents array
			for (int j = 0; j < genres.length; j++) 
			{
				Song.Genre genre = genres[j];
				//checks if the song genre is equal to the user genre
				if (song.getGenre() == genre)
				{
				//checks if it mapped
				if (genreMap.containsKey(genre))
				{
					//initializes number2 to the retrieved genre from the hashmap
				number2 = genreMap.get(genre);
				}
				//add the index, i to the number2 arraylist
				number2.add(i);
				genreMap.put(genre, number2);
				}
			}

		}
}				
}

// creates private method
private ArrayList<AudioContent> readContent() throws IOException
{
	//create content arraylist
	ArrayList<AudioContent> contents = new ArrayList<AudioContent>();
	//create object file called file from store.txt
	File file = new File("store.txt");
	//scanner reads from file
	Scanner in = new Scanner(file); 
	//file the store.txt has new line
	while(in.hasNextLine())
	{
		String type = in.nextLine();
		// if string type is equal to the string song
		// then it is a song and go into this if statement
		if(type.equalsIgnoreCase("SONG")){
			//intialize based on the information given by the instructions
			String id = in.nextLine();
			String title = in.nextLine();
			//use parseint to convert string to int to read line
			int year = Integer.parseInt(in.nextLine());
			int length = Integer.parseInt(in.nextLine());
			String artist = in.nextLine();
			String composer = in.nextLine();
			// String genre = in.nextLine();
			Song.Genre genre = Song.Genre.valueOf(in.nextLine());
			int numLyrics = Integer.parseInt(in.nextLine());

			String lyrics = "";
			// interating over num of lyrics
			for(int i = 0; i < numLyrics;i++)
			{
				// add the lyrics to the lyric string
				lyrics += in.nextLine() + "\n";
			}
			//create a new song variable and cast it to the song object
			Song song = new Song(title, year, id, type, lyrics, length, artist, composer, genre, lyrics);
			//add the new song to contents
			contents.add(song);	
		}
		// else if string type is equal to the string audiobook
		// then it is a audiobook so enter the if statement
		else if(type.equalsIgnoreCase("AUDIOBOOK")){
			// follow the format given by the instructions and
			// from the store textfile
			String id = in.nextLine();
			String title = in.nextLine();
			int year = Integer.parseInt(in.nextLine());
			int length = Integer.parseInt(in.nextLine());
			String author = in.nextLine();
			String narrator = in.nextLine();
			int numOfChapters = Integer.parseInt(in.nextLine());
			// create two arraylist because audiobook object states them as arraylists
			ArrayList<String> chapterTitles = new ArrayList<>();
			ArrayList<String> chapters = new ArrayList<>();
			//iterate over the number of chapter a books has
			for(int i=0;i < numOfChapters; i++)
			{
				// add the current chapter to the chapter titles
				chapterTitles.add(in.nextLine());
			}
			// iterates of the number of chapters
			for(int i=0;i < numOfChapters; i++)
			{
				int numOfLines = in.nextInt();
				in.nextLine();
				String lines = ""; 
				for(int j=0;j<numOfLines;j++){
					lines += in.nextLine() + "\n";
				}
				chapters.add(lines);
			}
			AudioBook audioBook = new AudioBook(title, year, id, type, "", length, author, narrator, chapterTitles, chapters);
			contents.add(audioBook);
		}
	}
	return contents;
}
	
	
	public AudioContent getContent(int index)
	{
		if (index < 1 || index > contents.size())
		{
			return null;
		}
		return contents.get(index-1);
	}
	
	public void listAll()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	// creates method that'll return map with the key and value
	public Map<String, Integer> getByTitle() {
		//returns titlemap
		return titleMap;
	}

	// creates method that'll return map with the key and arraylist value
	public Map<String, ArrayList<Integer>> getByartist() {
		return artistMap;
	}

	// creates method that'll return map with the key and value
	public Map<Song.Genre, ArrayList<Integer>> getByGenre() {
		return genreMap;
	}
	//creates a public method with parameter title
	public void search(String title)
	{
		//if titlemap contains the key title
		if(titleMap.containsKey(title))
		{
			// initialize iterator that iterates over title map
			Iterator<Map.Entry<String, Integer>> itr = titleMap.entrySet().iterator();
			//while iterate has more thingto go over
			while (itr.hasNext()) 
			{
				//assigns map to the enter variable
				Map.Entry<String, Integer> enter = itr.next();
				//retrieves the key from the enter variable
				String key = enter.getKey();
				// if the key is equal to the title
				if (key.equals(title)) 
				{
					//assignmnet the number to vlaue
					int num = enter.getValue();
					//prints the index with the title
					System.out.print((num + 1) + ". ");
					contents.get(num).printInfo();
					return;
				}
			}
		}
		// throws matching error
		throw new NoMatchesException("No matches for "+ title);	
	}
	public ArrayList<Integer> searchA(String artist)
	{
		// initialize iterator that iterates over artist map
		ArrayList<Integer> artists = new ArrayList<>();
		if(artistMap.containsKey(artist)){
			Iterator<Map.Entry<String, ArrayList<Integer>>> itr = artistMap.entrySet().iterator();
			//while iterate has more thingto go over
			while (itr.hasNext())
			 {
				Map.Entry<String, ArrayList<Integer>> entry = itr.next();
				//retrieves the key from the enter variable
				String key = entry.getKey();
				//if key is equal to artist 
				if (key.equals(artist)) {
					// assignmets artist to the enter value
					artists = entry.getValue();
					//breaks from while loop
					break;
				}
			}
		}
		else
		{
			// throws matches error
			throw new NoMatchesException("No matches for "+ artist);
		}
		return artists;
	}

	//creates a public method with parameter genre
	public ArrayList<Integer> searchG(Song.Genre genre)
	{
		// initialize iterator that iterates over genre map
		ArrayList<Integer> genres = new ArrayList<>();
		if (genreMap.containsKey(genre)) {
			Iterator<Song.Genre> itr = genreMap.keySet().iterator();
			//while there is a string to iterate over 
			while (itr.hasNext()) {
				Song.Genre key = itr.next();
				//if key is equal to the genre 
				if (key == genre) {
					//assign genre to the key
					genres = genreMap.get(key);
				}
			}
		}
		else
		{	// throws matches error
			throw new NoMatchesException("No matches for "+ genre);
		}
		return genres;
	}

	//nomatches exception extends runtime so it inherits from runtime
	public class NoMatchesException extends RuntimeException
	{
		//creates nomarches instance
		public NoMatchesException() {}
		public NoMatchesException(String message)
		{
			//sends the messgae when the exception is called
			super(message);
		}
	}	
}
