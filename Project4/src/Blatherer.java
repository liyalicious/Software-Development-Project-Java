import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author Liya Xu lx2hy, Tyler Crawford tjc4jc 
 * Assignment Name: Homework4 
 * Lab Section: 100 
 * Other collaborators: /
 * 
 */
public class Blatherer implements Iterable<String> {
	/**
	 * a non-text character that is used to connect words together
	 */
	public static final char GRAM_DELIMITER = '~';
	private Map<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();
	private int num;

	/**
	 * Constructor for Blatherer class
	 * 
	 * @param n
	 */
	public Blatherer(int n) {
		num = n;
	}

	/**
	 * the method that adds the contents of the Scanner to the map of n-grams.
	 * 
	 * @param s
	 */
	public void feed(Scanner s) {
		ArrayList<String> stringList = new ArrayList<String>();
		String grams = "";
		ArrayList<String> value = new ArrayList<String>();
//		ArrayList<String> store = new ArrayList<String>();
//		String stored = "";
		int count1 = 0;
		while (s.hasNext()) {
			stringList.add(s.next());
		}
		stringList.add("");

		for (int i = 0; i < stringList.size() - (num - 1); i++) {
			grams = "";
			for (int j = i; j < i + (num - 1); j++) {
				grams += stringList.get(j) + GRAM_DELIMITER;
			}

			if (!map.containsKey(grams.substring(0, grams.length() - 1))) {
				value.add(stringList.get(i + (num - 1)));
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(value.get(count1));
				map.put(grams.substring(0, grams.length() - 1), temp);
				count1++;
			} else {
				ArrayList<String> temp = map.get(grams.substring(0,
						grams.length() - 1));
				temp.add(stringList.get(i + (num - 1)));
				map.remove(grams.substring(0, grams.length() - 1));
				map.put(grams.substring(0, grams.length() - 1), temp);
			}
		}
	}

	/**
	 * a method that removes all dead-ends from the Blatherer's map of n-grams.
	 */
	public void removeDeadEnds() {
		while (deadEndsLeft()) {

		}

		// System.out.println(map.toString());
	}

	/**
	 * a helper method that compares two array of strings to see if the nth
	 * element of the first array is equal to the (n+1)th element of the second
	 * array.
	 * 
	 * @param s
	 * @param t
	 * @return boolean
	 */
	public boolean compareKey(String[] s, String[] t) {
		for (int i = 0; i < s.length - 1; i++) {
			if (s[i].equals(t[i + 1])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * a helper method to check if a String value inside the map is equals to
	 * the given String
	 * 
	 * @param s
	 * @param t
	 * @return boolean
	 */
	public boolean containsValue(String s, String t) {
		ArrayList<String> values = map.get(s);
		for (String v : values) {
			if (t.equals(v)) {
				return true;
			}
		}

		return false;
	}

	private boolean deadEndsLeft() {

		String temp2 = "";
		boolean t = false;
		for (String key : new ArrayList<String>(map.keySet())) {
			if (map.get(key).size() == 0 || map.get(key).get(0).equals("")) {
				String[] temp = key.split("~");
				temp2 = temp[temp.length - 1];
				map.remove(key);
				for (String key2 : new ArrayList<String>(map.keySet())) {
					if (containsValue(key2, temp2)
							&& compareKey(temp, key2.split("~"))) {
						ArrayList<String> val = map.get(key2);
						val.remove(temp2);
						map.remove(key2);
						map.put(key2, val);
						t = true;
						break;
					}
				}
			}
		}
		return t;
	}

	/**
	 * method that returns an unmodifiable map of key to Collections of Strings.
	 * 
	 * @return a map collection
	 */
	public Map<String, ? extends Collection<String>> nGrams() {
		return Collections.unmodifiableMap(this.map);
	}

	/**
	 * a helper method that would generate a new key out of the given key by
	 * remove the first word and add the value of that key to the end
	 * 
	 * @param key
	 * @return String of a new key
	 */
	public String newKey(String key) {
		String[] s = key.split("~");
		String a = "";
		for (int i = 1; i < s.length; i++) {
			a += s[i] + "~";
		}

		return a + map.get(key).get(0);
	}

	/**
	 * method that returns the run length of each key in the current map.
	 * 
	 * @return map with values of run lengths
	 */
	public Map<String, Integer> runLengths() {
		Map<String, Integer> runLengthMap = new TreeMap<String, Integer>();
		String key2 = "";
		for (String key : new ArrayList<String>(map.keySet())) {
			if (map.get(key).get(0).equals("") || map.get(key).size() > 1) {
				runLengthMap.put(key, 0);
			} else {
				key2 = newKey(key);
				int count = 1;
				while (!map.get(key2).get(0).equals("")
						&& map.get(key2).size() == 1) {
					count++;
					key2 = newKey(key2);
					if (count == 100) {
						break;
					}
				}
				runLengthMap.put(key, count);
			}
		}
		return runLengthMap;
	}

	/**
	 * method that removes certain key that has the run length larger than the
	 * given value
	 * 
	 * @param i
	 */
	public void removeRunsLongerThan(int i) {
		for (String key : new ArrayList<String>(runLengths().keySet())) {
			if (runLengths().get(key) > i) {
				map.remove(key);
			}
		}
	}

	/**
	 * method that returns a key whose collection of values has the most entries
	 * 
	 * @return String of most common key
	 */
	public String mostCommonKey() {
		String max = (String) map.keySet().toArray()[0];
		for (String key : map.keySet()) {
			if (map.get(key).size() > map.get(max).size()) {
				max = key;
			}
		}
		return max;
	}

	/**
	 * Returns an iterator that will generate the specified number of words
	 * (unless it hits a dead-end first), starting at the given key.
	 * 
	 * @param n
	 * @param s
	 * @return BlatherIter
	 */
	public BlatherIter iterator(int n, String s) {
		return new BlatherIter(this, s, n);
	}

	/**
	 * Returns an iterator that will generate the specified number of words
	 * (unless it hits a dead-end first), starting at the mostCommonKey
	 * 
	 * @param n
	 * @return BlatherIter
	 */
	public BlatherIter iterator(int n) {
		return new BlatherIter(this, mostCommonKey(), n);
	}

	/**
	 * Returns an iterator that will generate 100 words, starting at the
	 * mostCommonKey
	 */
	public BlatherIter iterator() {
		return new BlatherIter(this, mostCommonKey(), 100);
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner("I am Sam Sam I am That Sam I am that Sam I am I do not like");
		Blatherer b = new Blatherer(4);
		b.feed(scanner);
		b.removeDeadEnds();
		BlatherIter bI = b.iterator(105);
		bI.moreWords(10);
		int count = 1;
		while (bI.hasNext()) {
			System.out.println(bI.next());
			System.out.println(count);
			count++;
		}
	}
}
