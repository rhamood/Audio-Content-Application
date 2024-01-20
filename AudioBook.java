import java.util.ArrayList;

public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{ 
		// Initialize additional AudioBook instance variables. 
		super(title, year, id, type, audioFile, length);
		this.author = author;							
		this.narrator = narrator;						
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
	}	
	
	public String getType()
	{
		return TYPENAME;
	}

  	// Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Author: " + author + " Narrator: " + narrator); 
		//prints remaining information that adds on to the audiobook subclass like the author and narrator
	}
	
    // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		//sets a audiofile to the current chapter by getting the currentchapter title by using the get method get
		// also gets the current chapter after using the get method as chapters is an arraylist
		this.setAudioFile(chapterTitles.get(currentChapter) + "\n" + chapters.get(currentChapter));
		// calls the play() method in the super class audiocontent
		super.play();
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	public void printTOC()
	{	
		for (int i = 0; i < chapterTitles.size(); i++) //iterates over chaptertitles
		{
			int index = i +1; //adds one to index because when printing you start at 1 and not 0
			System.out.println("Chapter: " + index + ". " + chapterTitles.get(i)); // prints the chapter num and the chapter title using get
			System.out.println();
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		//creates a varible from class audiobook to the other parameter
		AudioBook otherA = (AudioBook) other;
		//checks if the super class equals the other narrators and authord
		if (author.equals(otherA.author) && narrator.equals(otherA.narrator))
		{
			return true; 
		}
		return false;
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}