import java.util.ArrayList;
import java.util.GregorianCalendar;

/*  
 * Propiedades:
 * 		Title: Cadena, Consultable, Modificable
 * 		Langage: Cadena, Consultable, Modificable
 * 		Category: Cadena, Consultable, Modificable 
 * 		Author:  ArrayList de cadenas, Consultable, Modificable
 * 		Year:  Entero, Consultable, Modificable
 * 		Price:  Double, Consultable, Modificable
 * 		
 * 
 * */
public class Book {

	private String title;
	private String language;
	private String category;
	private ArrayList<String> authors;
	private int year;
	private double price;
	
	
	public Book() {
		title="";
		language="";
		category="";
		authors=new ArrayList<String>();
		year=0;
		price=0;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public ArrayList<String> getAuthors() {
		return authors;
	}


	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String toString() {
		String cadena= title+","+language+","+category+","+authors+","+year+","+price;
		return cadena;
	}
	
	
}
