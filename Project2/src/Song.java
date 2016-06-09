import edu.virginia.cs2110.Mp3FilePlayer;

/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework2 
 * Lab Section: 100 
 * Other collaborators: /
 */
public class Song implements Comparable<Song>, Playable {

	/**
	 * fields
	 */
	private String artist; // the artist performing the song
	private String title; // the title of the song
	private int minutes; // number of min in length
	private int seconds; // number of seconds of length of the song (aleways
							// less than 60)
	private String fileName;

	/**
	 * constructor
	 * 
	 * @param artist
	 * @param title
	 */
	public Song(String artist, String title, String fileName) {
		this.artist = artist;
		this.title = title;
		this.fileName = fileName;
	}

	/**
	 * constructor
	 * 
	 * @param artist
	 * @param title
	 * @param minutes
	 * @param seconds
	 */
	public Song(String artist, String title, int minutes, int seconds,
			String fileName) {
		this.artist = artist;
		this.title = title;
		if (seconds >= 60) {
			int temp = seconds;
			this.minutes = minutes + temp / 60;
			this.seconds = temp % 60;
		} else {
			this.minutes = minutes;
			this.seconds = seconds;
		}
		this.fileName = fileName;
	}

	/**
	 * constructor
	 * 
	 * @param s
	 */
	public Song(Song s) {
		this.artist = s.getArtist();
		this.title = s.getTitle();
		this.minutes = s.getMinutes();
		this.seconds = s.getSeconds();
	}

	/**
	 * equals method, a song is equal if all four fields are equal
	 * 
	 * @return true if equals, false if not
	 */
	public boolean equals(Object o) {
		boolean retVal = false;

		if (o instanceof Song) {
			Song other = (Song) o;
			retVal = (this.getArtist().equals(other.getArtist())
					&& this.getTitle().equals(other.getTitle())
					&& this.getMinutes() == other.getMinutes()
					&& this.getSeconds() == other.getSeconds() && this.fileName
					.equals(other.fileName));
		}

		return retVal;
	}

	/**
	 * toString method
	 * 
	 * @return a string of title and artist
	 */
	public String toString() {
		return "{Song: title=" + title + " artist=" + artist + " minutes = "
				+ minutes + " seconds = " + seconds + "}";
	}

	/**
	 * play method
	 */
	public void play() {
		Mp3FilePlayer mp3 = new Mp3FilePlayer(fileName);
		mp3.playAndBlock();
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public String getFileName() {
		return fileName;
	}

	/**
	 * seconds setter
	 * 
	 * @param seconds
	 */
	public void setSeconds(int seconds) {
		if (seconds < 60) {
			this.seconds = seconds;
		} else {
			int temp = seconds;
			this.seconds = temp % 60;
			this.minutes = minutes + temp / 60;
		}
	}

	@Override
	public int compareTo(Song o) {

		int temp = this.getArtist().compareTo(o.getArtist());

		if (temp == 0) {
			temp = this.getTitle().compareTo(o.getTitle());

			if (temp == 0) {
				if (this.getMinutes() == o.getMinutes()) {
					temp = 0;
				} else if (this.getMinutes() > o.getMinutes()) {
					temp = 1;
				} else {
					temp = -1;
				}

				if (temp == 0) {
					if (o.getSeconds() == this.getSeconds()) {
						temp = 0;
					} else if (this.getSeconds() > o.getSeconds()) {
						temp = 1;
					} else {
						temp = -1;
					}
				}
			}
		}

		return temp;
	}

	@Override
	public void play(double s) {
		Mp3FilePlayer mp3 = new Mp3FilePlayer(fileName);
		mp3.playAndBlock(s);
	}

	@Override
	public String getName() {
		return title;
	}

	@Override
	public int getPlayTimeSeconds() {
		return 60 * minutes + seconds;
	}

}
