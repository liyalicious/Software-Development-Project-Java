import java.util.Comparator;

/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework3 
 * Lab Section: 100 
 * Other collaborators: /
 */
public class ComparatorTime implements Comparator<Playable> {

	private int totalTime1;
	private int totalTime2;
	
	@Override
	public int compare(Playable o1, Playable o2) {

		if (o1.getPlayTimeSeconds() > o2.getPlayTimeSeconds()) return +1;
		if (o1.getPlayTimeSeconds() < o2.getPlayTimeSeconds()) return -1;
		return 0;
	}

}
