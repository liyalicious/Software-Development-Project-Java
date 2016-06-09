import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Liya Xu lx2hy
 * Assignment Name: Homework1
 * Lab Section: 100
 * Other collaborators: /
 */
public class PlayList {
	/**
	 * fields
	 */
	private String name; // contains the name of the playlist
	private ArrayList<Song> songList = new ArrayList<Song>(); // ArrayList of
	// songs that
	// make up the
	// play list

	/**
	 * constructor with empty play list named "Untitled"
	 * 
	 */
	public PlayList() {
		name = "Untitled"; 
	}

	/**
	 * constructor with empty play list
	 * @param newName
	 */
	public PlayList(String newName) {
		this.name = newName; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ArrayList<Song> songList) {
		this.songList = songList;
	}


	/**
	 *
	 * loadSong method that loads songs from file
	 * @param fileName the name of the file with extension
	 * @return true if the song is loaded 
	 */
	public boolean loadSongs(String fileName) {
		File theFile = new File(fileName);
		Scanner fileScnr = null;
		try {
			fileScnr = new Scanner(theFile);
			while (fileScnr.hasNextLine()) {

				String songName = fileScnr.nextLine().trim();
				String artist = fileScnr.nextLine().trim();
				String duration = fileScnr.nextLine().trim();
				fileScnr.nextLine();

				String[] splitLine = duration.split(":");
				int mins = Integer.parseInt(splitLine[0]);
				int secs = Integer.parseInt(splitLine[1]);

				if (secs >= 60) {
					secs = secs % 60;
					mins += secs / 60;
				}

				Song newSong = new Song(artist, songName, mins, secs);
				songList.add(newSong);
			}

		} catch (Exception e) {
			System.err
			.println("error reading File fileName in method loadSong");
			System.exit(1);
			return false; 
		}

		return true; 
	}
	
	/**
	 * method that removes all songs
	 * @return true if all the songs are cleared
	 */
	public boolean clear() {
		songList.clear();
		return true; 
	}

	/**
	 * method that adds a song to the end of the play list
	 * @param s the song that will be added
	 * @return true if the song is added
	 */
	public boolean addSong(Song s) {
		songList.add(s);
		return true; 
	}

	/**
	 * method that removes Song at index from the list and returns it
	 * @param index the index of the song in the list
	 * @return the song that will be removed
	 */
	public Song removeSong(int index) {
		try 
		{
			Song s = songList.remove(index); 
			return s; 
		
		}		
		catch (Exception e) 
		{
			System.err.println("the index given is not valid /n in removeSong(int index)");
			return null; 
		}
		
	}

	/**
	 * method that removes Song s from the list and returns it
	 * @param s the song that will be removed and returned
	 * @return the song that will be removed
	 */
	public Song removeSong(Song s) {
		boolean remove = false; 
		
		while (songList.contains(s)) {
			songList.remove(s);
			remove = true; 
		}

		if (remove) {
			return s; 
		} 
		else 
		{
			return null;
		}
		
	}
	
	/**
	 * method that returns the song at the appropriate index
	 * @param index the index of the song in the list
	 * @return the song that will be got
	 */
	public Song getSong(int index) {
		try 
		{
			Song s = songList.get(index);
			return s; 
		
		}		
		catch (Exception e) 
		{
			System.err.println("the index given is not valid /n in getSong(int index)");
			return null; 
		}
		  
	}

	/**
	 * method that sorts the class's song list by artist first, then by title if
	 * the artists are equal, then shortest first if both artist and title are
	 * equal
	 */
	public void sortByArtist() {

		Collections.sort(songList); 
	}


	/**
	 * plays the play list by calling play() on each Song in the play list in order
	 */
	public void play() {

		for (Song s : songList) {
			s.play(); 
		}
	}

	/**
	 * returns the number of songs in the play list
	 * @return number of songs
	 */
	public int size() {
		return songList.size(); 
	}
	
	/**
	 * returns the total time the play list will take
	 * @return total time in the correct format
	 * @throws ParseException
	 */
	public String totalPlayTime() throws ParseException {
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		String output = "";
		for (int i = 0; i < songList.size(); i++) {
			seconds += songList.get(i).getSeconds();
			minutes += songList.get(i).getMinutes();
		}

		int temp = minutes * 60 + seconds ;
		hours = temp / 3600;
		minutes = (temp - 3600 * hours) / 60;
		seconds = (temp - 3600 * hours) % 60;

		String newString = "";

		if (hours != 0) {
			output = hours + ":" + minutes + ":" + seconds;
			Date date = new SimpleDateFormat("HH:mm:ss").parse(output);
			newString = new SimpleDateFormat("HH:mm:ss").format(date);
		} else {
			output = minutes + ":" + seconds;
			Date date = new SimpleDateFormat("mm:ss").parse(output);
			newString = new SimpleDateFormat("mm:ss").format(date);
		}

		System.out.println(newString); 


		return newString; 
	}


	/**
	 * returns the total time the play list will take as the number of seconds
	 * @return play time in seconds
	 */
	public int getPlayTimeSeconds() {
		int minutes = 0;
		int seconds = 0;

		for (int i = 0; i < songList.size(); i++) {
			seconds += songList.get(i).getSeconds();
			minutes += songList.get(i).getMinutes();
		}

		return minutes * 60 + seconds; 

	}

//	public static void main(String[] args) throws ParseException{
//
//		
//	Song s1 = new Song("title","artist",12,12);
//	Song s2 = new Song(s1);
//	Song s3 = new Song("","");
//	
//	
//	
//	System.out.println(s2.equals(s2));
		
		
/*		
		PlayList newPlaylist = new PlayList();

		newPlaylist.loadSongs("Test.txt");

		newPlaylist.totalPlayTime();

		System.out.println(newPlaylist.getPlayTimeSeconds());
		
		newPlaylist.sortByArtist();
		System.out.println(newPlaylist.getSongList().toString());
		
		newPlaylist.play();
*/
//	}

}
