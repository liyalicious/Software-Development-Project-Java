/* Name: Liya Xu
 * Email ID: lx2hy
 * Assignment: Homework 0
 * Lab Section: 100
 * Sources: Textbook
 */

import java.util.ArrayList;

public class Person {

	// fields
	private String name;
	private int id;
	private ArrayList<Book> read = new ArrayList<Book>();

	// constructor that takes the name and id as parameters
	public Person(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	// accessors and mutators
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Book> getRead() {
		return read;
	}

	public void setRead(ArrayList<Book> read) {
		this.read = read;
	}

	// method that add a book to the read book list if it's not in it
	public boolean addBook(Book b) {
		if (!read.contains(b)) {
			read.add(b);
			return true;
		}

		return false;
	}

	// method that tests if the person has read the book or not
	public boolean hasRead(Book b) {
		if (this.read.contains(b)) {
			return true;
		}

		return false;
	}

	// method that remove a book from the read book list
	public boolean forgetBook(Book b) {
		if (this.read.contains(b)) {
			read.remove(b);
			return true;
		}

		return false;
	}

	// method that returns the number of books a person has read
	public int numBooksRead() {
		return this.read.size();
	}

	// method that compares if the two people are the same person
	public boolean equals(Object o) {
		boolean retVal = false;

		if (o instanceof Person) {
			Person other = (Person) o;
			retVal = this.getId() == other.getId();
		}

		return retVal;
	}

	// the toString method that print the person's name, id, and read books
	public String toString() {
		return "Name: " + name + ", ID: " + id + ", Read: " + read;
	}

}
