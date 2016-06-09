
import java.util.Comparator;

/**
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc
 * Assignment Name: Homework2 
 * Lab Section: 100 
 * Other collaborators: /
 */
public class ComparatorPlayList implements Comparator<Playable> { 


	@Override
	public int compare(Playable o1, Playable o2) {
			
		return o1.getName().compareTo(o2.getName());
	}

}
