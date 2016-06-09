import java.net.URI;
import java.awt.Desktop;
import java.lang.Thread;

/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework3 
 * Lab Section: 100 
 * Other collaborators: /
 */
public class Video implements Playable {
	
	private String videoName;
	private int minutes;
	private int seconds;
	private String user;
	private String title;
	
	// BLOCK_ADJUSTMENT used to increase time we block when playing a song to allow for
	//	time it takes to get video to start up in browser.  Adjust for your system if needed.
	private static final int BLOCK_ADJUSTMENT = 3; // units are seconds
   
	/**
	 * Constructor for Video
	 * @param user Username
	 * @param title Title of the video
	 * @param min Minutes of video
	 * @param sec Seconds of video
	 * @param videoName Video web address
	 */
	public Video(String user, String title, int min, int sec, String videoName)
	{
		this.user = user;
		this.title = title;
		this.minutes = min;
		this.seconds = sec;
		this.videoName = videoName;  // must in this form: http://www.youtube.com/embed/FzRH3iTQPrk
		
		if (!videoName.toLowerCase().startsWith("http://www.youtube.com/embed/")) {
			System.out.println("* Constructor given videoName "
				+ videoName + " which is not the proper form.");
			System.out.println("* This video will almost certainly not play.");
		}
	}


	/**
	 * Plays the video
	 */
	public void play() {
		this.play( this.minutes * 60 + this.seconds );
	}


	/**
	 * Plays however many seconds of a video that the user specifies
	 */
	public void play(double s) {
		try {
			Desktop.getDesktop().browse( new URI( videoName + "?autoplay=1") );
			Thread.sleep((int) (1000 * (seconds + BLOCK_ADJUSTMENT))); // block for length of song
		} catch (Exception e) {
			System.out.println("* Error: " + e + " when playing YouTube video "
				+ videoName );
		}
	}


	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args)
	{
		Video v1 = new Video("jimvwmoss", "The Sneezing Baby Panda", 0, 17,
			"http://www.youtube.com/embed/4hpEnLtqUDg");
		System.out.println("* Playing video for 10 seconds.");
		v1.play(10);

		Video v2 = new Video("jimvwmoss", "The Sneezing Baby Panda", 0, 17,
			"http://www.youtube.com/embed/FzRH3iTQPrk");
		System.out.println("* Playing video for full length.");
		v2.play();
		
		System.out.println("* Should be done when this prints.");

	}

	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Video) {
			Video v = (Video) o;
			if (this.getName().equals(v.getName()) && this.getPlayTimeSeconds() == v.getPlayTimeSeconds() && this.getVideoName().equals(v.getVideoName()) && this.getUser().equals(v.getUser())) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String getName() {
		return title;
	}
	
	public String getVideoName() {
		return videoName;
	}
	
	public String getUser() {
		return user;
	}


	@Override
	public int getPlayTimeSeconds() {
		return minutes * 60 + seconds;
	}
	
	@Override
	public String toString() {
		return "{Video: title=" + title + " User=" + user + " minutes = "
				+ minutes + " seconds = " + seconds + "Video Name: " + videoName + "}";
	}
}