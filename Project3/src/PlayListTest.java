import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework3 
 * Lab Section: 100 
 * Other collaborators: /
 */
public class PlayListTest {

	@Test
	public void testAddPlayList() {
		PlayList pl = new PlayList();
		PlayList testList = new PlayList("Test1");
		pl.addPlayList(testList);
		
		assertEquals("testAdd", "Test1", pl.getPlayable(0).getName());
	}
	
	@Test
	public void testAddPlayList1() {
		PlayList pl = new PlayList();
		PlayList testList = new PlayList("Test1");
		
		assertEquals("returns true", true, pl.addPlayList(testList));
	}
	
	@Test
	public void testAddPlayListDup() {
		PlayList list = new PlayList("duplicate");
		PlayList testList = new PlayList();
		testList.addPlayList(list);
		
		assertEquals("testDup", false, testList.addPlayList(list));
	}
	
	@Test
	public void testAddPlayable() {
		PlayList pl = new PlayList();
		Playable p = new PlayList("Test1");
		pl.addPlayable(p);
		
		assertEquals("testAdd", "Test1", pl.getPlayable(0).getName());
	}
	
	@Test
	public void testAddPlayable1() {
		PlayList pl = new PlayList();
		PlayList p = new PlayList("Test 1");
		
		assertEquals("returns true", true, pl.addPlayable(p));
	}
	
	@Test
	public void testLoadMediaSong() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("no complications", "Dancing Queen", test.getPlayable(2).getName());
	}
	
	@Test
	public void testLoadMediaSong1() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("comments", "Dancing Queen", test.getPlayable(3).getName());
	}
	
	@Test
	public void testLoadMediaSong2() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		Song song = (Song)test.getPlayable(4);
		
		assertEquals("incorrect time - mintues", 5, song.getMinutes());
	}
	
	@Test
	public void testLoadMediaSong3() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		Song song = (Song)test.getPlayable(4);
		
		assertEquals("incorrect time - seconds", 39, song.getSeconds());
	}
	
	@Test
	public void testLoadMediaSong4() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		Song song = (Song)test.getPlayable(5);
		
		assertEquals("spaces", "Dancing Queen", song.getName());
		assertEquals("spaces", "Abba", song.getArtist());
		assertEquals("spaces", 4, song.getMinutes());
		assertEquals("spaces", 13, song.getSeconds());
	}
	
	@Test
	public void testLoadMediaSong5() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		Song song = (Song)test.getPlayable(5);
		
		assertEquals("spaces and comments", "Dancing Queen", song.getName());
		assertEquals("spaces and comments", "Abba", song.getArtist());
		assertEquals("spaces and comments", 4, song.getMinutes());
		assertEquals("spaces and comments", 13, song.getSeconds());
	}
	
	@Test
	public void testLoadMediaSong6() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("making sure loadMedia stores the right class", "class Song", test.getPlayable(3).getClass().toString());
	}
	
	@Test
	public void testLoadMediaVideo() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("no complications", "Call Me Maybe - Polka Style", test.getPlayable(0).getName());
	}
	
	
	@Test
	public void testLoadMediaVideo1() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("comments", "Call You Maybe - Polka Style", test.getPlayable(1).getName());
	}
	
	@Test
	public void testLoadMediaVideo3() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("trickier comments", "OFFICIAL UVA Harlem Shake", test.getPlayable(7).getName());
	}
	
	@Test
	public void testLoadMediaVideo4() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		Video vid = (Video)test.getPlayable(7);
		
		assertEquals("web address", "http://www.youtube.com/embed/JEdlIwGBNzw", vid.getVideoName());
	}
	
	
	@Test
	public void testLoadMediaVideo5() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("youtube capitalization", "class Video", test.getPlayable(8).getClass().toString());
	}
	
	@Test
	public void testLoadMediaVideo6() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("youtube capitalization", "class Video", test.getPlayable(8).getClass().toString());
	}
	
	@Test
	public void testLoadMediaVideo7() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("youtube capitalization", "class Video", test.getPlayable(8).getClass().toString());
	}
	
	@Test
	public void testLoadMediaVideo8() {
		PlayList test = new PlayList();
		test.loadMedia("VideoTest.txt");
		
		assertEquals("youtube capitalization", "class Video", test.getPlayable(8).getClass().toString());
	}
}
