/* Name: Liya Xu
 * Email ID: lx2hy
 * Assignment: Homework 0
 * Lab Section: 100
 * Sources: Textbook
 */

public class Book {

	// fields
	private final String title;
	private final String author;

	// constructor that takes title and author as parameters
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}

	// Accessors and Mutators
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	// the equals method to compare if two books are the same
	public boolean equals(Object o) {

		boolean retVal = false;

		if (o instanceof Book) {
			Book other = (Book) o;
			retVal = ((this.getTitle() == other.getTitle()) && (this
					.getAuthor() == other.getAuthor()));
		}

		return retVal;
	}

	// toString method that print the book title and author
	public String toString() {
		return "Book title: " + title + ", Author: " + author;
	}

}
