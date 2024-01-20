import javax.sound.sampled.spi.AudioFileReader;

public class Song extends AudioContent // implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
		super(title, year, id, type, audioFile, length);  // intializes first compents from the super class audio content
		this.artist = artist;							 //intializes remaining instance variables from the parametres
		this.composer = composer;						// uses "this" to help differentiate the receiving variable
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		super.printInfo(); //calls superclass audio content prints the information from superclass
		System.out.println("Artist: " + artist + ", Composer: " + composer + ", Genre: " + genre);
		 //prints remaining information that adds on to the song subclass
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		this.setAudioFile(lyrics); //sets a audiofile to lyrics using "this" to show that it is the current variable
		super.play(); // calling play from super class
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{	
		//creates a varible from class song to the other parameter
		Song otherA = (Song) other;
		//checks if the super class equals the other variable as well as the computers and artists
		if (super.equals(otherA) && composer.equals(otherA.composer) && artist.equals(otherA.artist))
		{
			return true; //returns true if yes
		}
		return false; // else return false
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{	
		// checks if the the first letter using charat of song is less than the character at the length of the other object
		if (super.getTitle().charAt(0) < other.getTitle().charAt(getLength())) 
		{
			return -1;
		}
		//checks if the index is equal to the index of the length of the other object
		else if (super.getTitle().charAt(0) == other.getTitle().charAt(getLength())) 
		{
			return 0; //no changes if it is
		}
		// else the first letter is greater than the charat the other object so 
		// that case return 1
			return 1;
	}
}