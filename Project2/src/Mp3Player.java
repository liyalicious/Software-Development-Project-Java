
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework2 
 * Lab Section: 100 
 * Other collaborators: /
 */
public class Mp3Player {

	private ArrayList<PlayList> mp3 = new ArrayList<PlayList>();
	private PlayList defaultList = new PlayList();
	
	/**
	 * method that serves as the user interface
	 */
	public void start() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What file do you want to load?");
		String input = keyboard.nextLine();
		
		defaultList.loadSongs(input);
		defaultList.toString();
		
		for (int i = 0; i < defaultList.size(); i++) {
			defaultList.getPlayable(i).play(5);
		}
	}
	
	/**
	 * method that stores the default playlist and other playlists
	 * @param list
	 */
	public void store(PlayList list) {
		mp3.add(defaultList);
		
		if (!mp3.contains(list)) {
			mp3.add(list);
		}
		
	}
	
	public PlayList getDefaultPlayList() {
		return defaultList; 
	}
	
	public ArrayList<PlayList> getPlayLists() {
		return mp3;
	}

	/**
	 * main method
	 * @param args 
	 */
	public static void main(String[] args) {
		Mp3Player player = new Mp3Player();

		player.start();
	}

}
