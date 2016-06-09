import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

/**
* @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework3 
 * Lab Section: 100 
 * Other collaborators: /
 */

public class PlayList implements Playable {
	/**
	 * fields
	 */
	private String name = "playlist"; // contains the name of the playlist
	private ArrayList<Playable> playableList = new ArrayList<Playable>(); // ArrayList

	/**
	 * constructor with empty play list named "playlist"
	 * 
	 */
	public PlayList() {
		name = "untitled";
	}

	/**
	 * constructor with empty play list
	 * 
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

	public ArrayList<Playable> getPlayableList() {
		return playableList;
	}

	public void setPlayableList(ArrayList<Playable> playableList) {
		this.playableList = playableList;
	}

	/**
	 * equals method for playlist
	 */
	public boolean equals(Object o) {
		boolean retVal = false;

		if (o instanceof PlayList) {
			PlayList other = (PlayList) o;
			retVal = this.getName().equals(((PlayList) o).getName());
		}

		return retVal;
	}

	/**
	 * 
	 * loadMedia method that loads all the playable media from file
	 * 
	 * @param fileName
	 *            the name of the file with extension
	 * @return true if the media is loaded
	 */
	public boolean loadMedia(String fileName) {
		File theFile = new File(fileName);
		Scanner fileScnr = null;

		try {
			fileScnr = new Scanner(theFile);
		} catch (Exception e) {
			System.out
					.println("error reading File fileName in method loadSong");
			return false;
		}

		int count = 0;
		String songName = "";
		String artist = "";
		String duration = "";
		String file = "";
		
		while (fileScnr.hasNext()) {
			String temp = "";
			temp = fileScnr.nextLine().split("//")[0].trim();
		
			if (!temp.isEmpty()) {
				if (count == 0) {
					songName = temp;
					count++;
				} else if (count == 1) {
					artist = temp;
					count++;
				} else if (count == 2) {
					duration = temp;
					count++;
				} else {
					file = temp;

					if (count == 3) {

						count = 0;

						String[] splitLine = duration.split(":");

						int mins = 0;

						try {
							mins = Integer.parseInt(splitLine[0]);
						} catch (NumberFormatException e) {
							System.out.println("Invalid Time Input");
						}

						int secs = 0;

						if (splitLine.length > 1) {
							try {
								secs = Integer.parseInt(splitLine[1]);
							} catch (NumberFormatException e) {
								System.out.println("Invalid Time Input");
							}
						} else {
							System.out.println("Invalid Time Input");
						}

						if (secs >= 60) {
							mins += secs / 60;
							secs = secs % 60;
						}

						if (file.length() >= 8) {
							System.out.println("check");
							if (file.substring(0, 8).equalsIgnoreCase(
									"youtube:")) {
								Video vid = new Video(artist, songName, mins,
										secs, "http://"
												+ file.substring(8,
														file.length()));
								addPlayable(vid);
							} else {
								if (!(new File(file).exists())) {
									return false;
								}

								Song newSong = new Song(artist, songName, mins,
										secs, file);
								addPlayable(newSong);
							}
						}

						else {
							if (!(new File(file).exists())) {
								return false;
							}

							Song newSong = new Song(artist, songName, mins,
									secs, file);
							addPlayable(newSong);
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	


	/**
	 * method that removes all media
	 * 
	 * @return true if all medias are cleared
	 */
	public boolean clear() {
		playableList.clear();
		return true;
	}

	/**
	 * method that adds a song to the end of the play list
	 * 
	 * @param s
	 *            the song that will be added
	 * @return true if the song is added
	 */
	public boolean addSong(Song s) {
		playableList.add(s);
		return true;
	}

	/**
	 * method that removes Song at index from the list and returns it
	 * 
	 * @param index
	 *            the index of the song in the list
	 * @return the song that will be removed
	 */
	public Playable removeSong(int index) {
		try {
			Playable s = playableList.remove(index);
			return s;

		} catch (Exception e) {
			System.err
					.println("the index given is not valid /n in removeSong(int index)");
			return null;
		}

	}

	/**
	 * method that removes Song s from the list and returns it
	 * 
	 * @param s
	 *            the song that will be removed and returned
	 * @return the song that will be removed
	 */
	public Playable removeSong(Song s) {
		boolean remove = false;

		while (playableList.contains(s)) {
			playableList.remove(s);
			remove = true;
		}

		if (remove) {
			return s;
		} else {
			return null;
		}

	}

	/**
	 * method that returns the song at the appropriate index
	 * 
	 * @param index
	 *            the index of the song in the list
	 * @return the song that will be got
	 */
	public Playable getPlayable(int index) {
		try {
			Playable s = playableList.get(index);
			return s;

		} catch (Exception e) {
			System.err
					.println("the index given is not valid /n in getPlayable(int index)");
			return null;
		}

	}

	/**
	 * add the playlist pl to the playablelist
	 * 
	 * @param pl
	 * @return boolean
	 */
	public boolean addPlayList(PlayList pl) {
		boolean duplicate = false;
		boolean added = false;
		for (int i = 0; i < playableList.size(); i++) {
			if (playableList.get(i).equals(pl)) {
				duplicate = true;
			}
		}
		
		if (duplicate == true) {
			added = false;
		}
		
		else {
			playableList.add(pl);
			added = true;
		}
		
		return added;
	}

	/**
	 * method that sorts the class's song list by artist first, then by title if
	 * the artists are equal, then shortest first if both artist and title are
	 * equal
	 */
	public void sortByName() {

		Collections.sort(playableList, new ComparatorPlayList());
	}

	/**
	 * sort the playablelist by time
	 */
	public void sortByTime() {
		Collections.sort(playableList, new ComparatorTime());
	}

	/**
	 * plays the play list by calling play() on each Song in the play list in
	 * order
	 */
	public void play() {

		for (Playable s : playableList) {
			s.play();
		}
	}

	/**
	 * returns the number of songs in the play list
	 * 
	 * @return number of songs
	 */
	public int size() {
		return playableList.size();
	}

	/**
	 * returns the total time the play list will take as the number of seconds
	 * 
	 * @return play time in seconds
	 */
	public int getPlayTimeSeconds() {
		int seconds = 0;

		for (int i = 0; i < playableList.size(); i++) {
			seconds += playableList.get(i).getPlayTimeSeconds();
		}

		return seconds;

	}

	/**
	 * Adds a Playable to the playableList
	 * @param p Playable
	 * @return boolean
	 */
	public boolean addPlayable(Playable p) {
		playableList.add(p);
		return true;
	}

	/**
	 * toString method that print out the names in the playable list
	 */
	public String toString() {
		String listName = "";
		for (int i = 0; i < playableList.size(); i++) {
			listName += playableList.get(i).toString() + "\n";
		}

		return name + "\n" + listName;
	}

	@Override
	public void play(double seconds) {
		for (int i = 0; i < playableList.size(); i++) {
			playableList.get(i).play();
		}
	}
	
//	public static void main(String[] args) throws ParseException{
//		PlayList p = new PlayList();
//		p.loadMedia("TestPlan.txt");
//	}
}
