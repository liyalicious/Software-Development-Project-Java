/* Name: Liya Xu
 * Email ID: lx2hy
 * Assignment: Homework 0
 * Lab Section: 100
 * Sources: Textbook
 */

import java.util.ArrayList;

public class BookClub {

	// field
	private static ArrayList<Book> common = new ArrayList<Book>();

	// method that returns the a list of the Books that both Person a and Person
	// b have read
	public static ArrayList<Book> commonBooks(Person a, Person b) {

		for (int i = 0; i < a.getRead().size(); i++) {
			for (int j = 0; j < b.getRead().size(); j++) {
				if (a.getRead().get(i).equals(b.getRead().get(j))) {
					common.add(a.getRead().get(i));
				}
			}
		}

		return common;
	}

	// method that returns a measure of how similar the reading preferences of two readers
	// are as a number between 0 and 1.
	public static double similarity(Person a, Person b) {
		if (a.getRead().size() > b.getRead().size()) {
			return (double) common.size() / b.getRead().size();
		} else {
			return (double) common.size() / a.getRead().size();
		}
	}

	// Test
	// public static void main(String[] args) {
	// Person Mike = new Person("Mike", 12345);
	// Person Luke = new Person("Luke", 23456);
	//
	// Book one = new Book("Harry Potter I", "JK Rowling");
	// Book two = new Book("Harry Potter II", "JK Rowling");
	// Book three = new Book("Harry Potter III", "JK Rowling");
	// Book four = new Book("Narnia", "C.S Lewis");
	// Book five = new Book("On The Road", "Someone");
	//
	// Mike.addBook(one);
	// Mike.addBook(three);
	// Mike.addBook(five);
	//
	//
	// Luke.addBook(one);
	// Luke.addBook(two);
	// Luke.addBook(three);
	// Luke.addBook(four);
	//
	// // Mike.forgetBook(one);
	//
	// System.out.println(Luke.addBook(four));
	//
	// System.out.println(commonBooks(Mike, Luke));
	// System.out.println(similarity(Mike, Luke));
	//
	// }

}
