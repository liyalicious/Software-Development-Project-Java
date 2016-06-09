/**
 * @author Liya Xu lx2hy
 * Assignment Name: Homework1
 * Lab Section: 100
 * Other collaborators: /
 */
public class Song implements Comparable<Song> {

	/**
	 * fields
	 */
	private String artist; // the artist performing the song
	private String title; // the title of the song
	private int minutes; // number of min in length
	private int seconds; // number of seconds of length of the song (aleways less than 60)


	/**
	 * constructor
	 * @param artist
	 * @param title
	 */
	public Song(String artist, String title) {
		this.artist = artist;
		this.title = title;
	}

	/**
	 * constructor
	 * @param artist
	 * @param title
	 * @param minutes
	 * @param seconds
	 */
	public Song(String artist, String title, int minutes, int seconds) {
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
	}

	/**
	 * constructor
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
	 * @return true if equals, false if not
	 */
	public boolean equals(Object o) {
		boolean retVal = false;

		if (o instanceof Song) {
			Song other = (Song) o;
			retVal = (this.getArtist().equals(other.getArtist())
					&& this.getTitle().equals(other.getTitle())
					&& this.getMinutes() == other.getMinutes() && this
					.getSeconds() == other.getSeconds()); 
		}
		
		return retVal; 
	}

	/**
	 * toString method
	 * @return a string of title and artist
	 */
	public String toString() { 
		return "{Song: title=" + title + " artist=" + artist + "}"; 
	}

	/**
	 * play method
	 */
	public void play() { 
		System.out.printf("Playing Song: artist=%-20s title=%s\n", artist,
				title); 
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

	/**
	 * seconds setter
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

}
