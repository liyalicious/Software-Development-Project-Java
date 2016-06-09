/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework3 
 * Lab Section: 100 
 * Other collaborators: /
 */
public interface Playable {

	/**
	 * plays the Playable object
	 */
	public void play();
	
	/**
	 * Plays however many seconds are passed by the argument
	 * @param seconds
	 */
	public void play(double seconds);
	
	/**
	 * Returns name of the Playable object
	 * @return String
	 */
	public String getName();
	
	/** 
	 * Returns the total seconds in the Playable object
	 * @return int
	 */
	public int getPlayTimeSeconds();
}
