import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework3 
 * Lab Section: 100 
 * Other collaborators: /
 *
 */
public class MediaPlayer {

	private Map<PlayList, String> mp3 = new TreeMap<PlayList, String>();
	private PlayList defaultList = new PlayList();
	
	/**
	 * Method to reverse the key and the value for a map
	 * @param m map
	 * @return reversed map
	 */
	public static Map<String, Set<PlayList>> reverseMap(Map<PlayList, String> m) {
		Map<String, Set<PlayList>> reverse = new TreeMap<String, Set<PlayList>>();
		Set<PlayList> keys = m.keySet();
		
		for (PlayList s : keys) {
			if (!reverse.containsKey(m.get(s))) {
				Set<PlayList> playables = new TreeSet<PlayList>();
				playables.add(s);
				reverse.put(m.get(s), playables);
			}
			
			else {
				Set<PlayList> playables = reverse.get(m.get(s));
				playables.add(s);
				reverse.put(m.get(s), playables);
			}
		}
		
		return reverse;
	}

	/**
	 * method that serves as the user interface.
	 * The users can choose the function that they want.
	 */
	public void start() {
		Scanner keyboard = new Scanner(System.in);


		while (true) {
			System.out.println("What do you want to do? Type in the number");
			System.out
					.println("1. Create a new Playlist. \n2. Add a file to an existing Playlist. \n3. Add one MP3 file by name to an existing Playlist. \n4. Add a Playlist to an existing Playlist. \n5. Play all media from an existing Playlist. \n6. Print out the detail of an existing Playlist. \n7. Play a MP3 file. \n8. Print just titles and names of all existing media. \n9. Print the names of all Playlists. \n10. Quit");
			int input = keyboard.nextInt();
			switch (input) {
				case 1: 
					System.out.println("What do you want to name the Playlist");
					String playlistName = keyboard.nextLine();
					PlayList newPlayList = new PlayList(playlistName);
					store(newPlayList);
					break;
					
				case 2:
					System.out.println("What Playlist would you like to load the media into?");
					String playlistName2 = keyboard.nextLine();
					if (mp3.containsValue(playlistName2)) {
						System.out.println("What file are you loading?");
						String file = keyboard.nextLine();
						PlayList temp = (PlayList) (reverseMap(mp3).get(playlistName2).toArray()[0]);
						temp.loadMedia(file);
					}
					break;
					
				case 3:
					System.out.println("What is the filename of the song do you want to load?");
					String songLoad = keyboard.nextLine();
					System.out.println("To what playlist would you like to add this song?");
					String playlistName3 = keyboard.nextLine();
					((PlayList) (reverseMap(mp3).get(playlistName3).toArray()[0])).loadMedia(songLoad);
					break;
					
				case 4: 
					System.out.println("What playlist would you like to add?");
					String playAdd = keyboard.nextLine();
					System.out.println("What playlist would you like to add " + playAdd + " to?");
					String playtoAdd = keyboard.nextLine();
					if (!playAdd.equals(playtoAdd)) {
						((PlayList) (reverseMap(mp3).get(playAdd).toArray()[0])).loadMedia(playtoAdd);
					}
					else {
						System.out.println("I'm sorry, but you can't add a playlist to itself");
					}
					break;
					
				case 5:
					System.out.println("What playlist would you like to play?"); 
					String list = keyboard.nextLine();
					System.out.println("If you would like to only play a number of seconds of each song, type in that nubmer(not applicable for videos). If not, press 0");
					int numSec = keyboard.nextInt();
					PlayList temp = ((PlayList) (reverseMap(mp3).get(list).toArray()[0]));
					for (int i = 0; i < reverseMap(mp3).get(list).size(); i++) {
						if (numSec > 0) {
							temp.play(numSec);
						}
						
						else {
							temp.play();
						}
					}
					break;
					
				case 6:
					System.out.println("What playlist would you like to print the details of?");
					String playListPrint = keyboard.next();
					reverseMap(mp3).get(playListPrint).toArray()[0].toString();
					break;
					
				case 7:
					PlayList tempPlay = new PlayList();
					System.out.println("What Mp3 file would you like to play (please input file name)");
					String file = keyboard.next();
					tempPlay.loadMedia(file);
					System.out.println("If you would like to play the song for a certain number of seconds, type in the number. Otherwise, press 0");
					int seconds = keyboard.nextInt();
					if (seconds > 0) {
						tempPlay.play(seconds);
					}
					else {
						tempPlay.play();
					}
					
					tempPlay.removeSong(0);
					break;
					
				case 8: 
					reverseMap(mp3).get("playlist").toString();
					break;
					
				case 9:
					Set<PlayList> set = mp3.keySet();
					for (PlayList p : set) {
						p.toString();
					}
					break;
					
				case 10:
					System.exit(1);
			}
		}
	}

	/**
	 * method that stores the default playlist and other playlists
	 * 
	 * @param list
	 */
	public void store(PlayList list) {
		mp3.put(defaultList, "playlist");

		if (!mp3.containsKey(list)) {
			mp3.put(list, list.getName());
		}

	}

	/**
	 * Method to return the default playlist
	 * @return PlayList
	 */
	public PlayList getDefaultPlayList() {
		return defaultList;
	}

	/**
	 * Method to get a list of the playlists
	 * @return Set<PlayList>
	 */
	public Set<PlayList> getPlayLists() {
		Set<PlayList> pl = mp3.keySet();
		return pl;
	}

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MediaPlayer player = new MediaPlayer();

		player.start();
	}

}
