import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc 
 * Assignment Name: Homework4 
 * Lab Section: 100 
 * Other collaborators: /
 * 
 */
public class BlatherIter implements Iterator<String> {
	private int count;
	private String key;
	private int num;
	private Blatherer blatherer;
	/**
	 * new string
	 */
	String v = "";

	/**
	 * Constructor of BlatherIter
	 * @param b
	 * @param s
	 * @param n
	 */
	public BlatherIter(Blatherer b, String s, int n) {
		count = 0;
		key = s;
		num = n;
		blatherer = b;
	}

	/**
	 * True if the number of words generated is not yet the given number, and
	 * the current key is in the Blatherer, and the collection of values for the
	 * current key is not empty.
	 */
	public boolean hasNext() {
		return ((count < num) && blatherer.nGrams().containsKey(key)
				&& !blatherer.nGrams().get(key).isEmpty() && !blatherer.nGrams().get(key).contains(""));
	}

	/**
	 * return a random element of the current key's collection, and advance the
	 * key to reflect what is returned. If the iteration is over, throw a
	 * NoSuchElementException instead.
	 */
	public String next() {
		if (hasNext()) {
			ArrayList<String> values = (ArrayList<String>) (blatherer.nGrams()
					.get(key));
			String[] splitKey = key.split("~");
			String temp = "";
			for (int i = 1; i < splitKey.length; i++) {
				temp += splitKey[i] + "~";
			}
			String random = values.get((int) (Math.random() * (values.size())));
			key = temp + random;
			count++;
			v += random + " ";

			return random;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * throw an UnsupportedOperationException
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Increase the number of words left to generate by the given number.
	 * @param n
	 */
	public void moreWords(int n) {
		this.num += n;
	}
}
