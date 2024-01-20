import java.util.ArrayList;

import javax.print.attribute.standard.PrinterInfo;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*a
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{	
		for (int i = 0; i < contents.size(); i++) //iterates over the contents playlist
		{
			int index = (i + 1);  //adds one to index because when printing you start at 1 and not 0
			System.out.print(index + ". "); // prints the current chapter number followed by a string period
			contents.get(i).printInfo(); // gets the content (songs &/or books) from the arraylist and prints the information 
			System.out.println(); // prints a blank like to match the txt file
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for (int i = 0; i < contents.size(); i++) //iterates over the contents playlist
		{	
			System.out.println(); // prints empty line to mtch the txt file
			contents.get(i).play(); // gets the content (songs &/or books) from the arraylist and calls super class to play
		}

	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		for (int i = 0; i < contents.size(); i++) //iterates over the contents playlist
		{
			if (index < i) //if the index is less than the current integer
			{
				contents.get(index); // retrieve the content from the current index
			}
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other) // overrides the built in equals from class object
	{
		Playlist otherA = (Playlist) other; //creates a varible from class audiobook to the other parameter
		if (title.equals(otherA.title)) //checks if the current title is equal to the other objects, title
		{
			return true; // if it is return true
		}
		return false; // if it is not return true
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index) 
	{
		if (!contains(index)) return; //checks if the arraylist contains contains the index
		// if contents does not contain the index
		contents.remove(index-1); //remove the index -1 from contents, we use -1 to convert position a zero based index
	}	
}
