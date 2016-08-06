package pl.bastus.bookapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class BookDTO {

	@NotBlank
	private String title;

	@NotBlank
	@Pattern(regexp="[1-2][0-9]{3}")
	private String date;
	
	@NotNull
	private Float price;
	
	@NotBlank
	private String author;

	public String getTitle() {
		return title;
	}

	@SuppressWarnings("unused")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	@SuppressWarnings("unused")
	public void setDate(String date) {
		this.date = date;
	}

	public Float getPrice() {
		return price;
	}

	@SuppressWarnings("unused")
	public void setPrice(Float price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	@SuppressWarnings("unused")
	public void setAuthor(String author) {
		this.author = author;
	}

}
