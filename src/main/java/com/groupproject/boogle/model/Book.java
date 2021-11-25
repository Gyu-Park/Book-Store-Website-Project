package com.groupproject.boogle.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@Column(nullable = false, length = 20)
    private String isbn13;
	
	@Column(nullable = false, length = 20)
	private String isbn10;
	
	@Column(nullable = false, length = 200)
	private String title;
	
	@Column(nullable = false, length = 40)
	private String alias;
	
	@Column(nullable = false, length = 70)
	private String author;
	
	@Column(nullable = false, length = 45)
	private String author_et_alia;
	
	@Column(nullable = false, length = 40)
	private String publisher;
	
	@Column(nullable = false, length = 20)
	private String publication_year;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private int number_on_hand;
	
	@Column(nullable = false, length = 225)
	private String image;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "categories_books",
			joinColumns = @JoinColumn(name = "books_isbn13"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> category;

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor_et_alia() {
		return author_et_alia;
	}

	public void setAuthor_et_alia(String author_et_alia) {
		this.author_et_alia = author_et_alia;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(String publication_year) {
		this.publication_year = publication_year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getNumber_on_hand() {
		return number_on_hand;
	}

	public void setNumber_on_hand(int number_on_hand) {
		this.number_on_hand = number_on_hand;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, author, author_et_alia, category, image, isbn10, isbn13, number_on_hand, price,
				publication_year, publisher, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(alias, other.alias) && Objects.equals(author, other.author)
				&& Objects.equals(author_et_alia, other.author_et_alia) && Objects.equals(category, other.category)
				&& Objects.equals(image, other.image) && Objects.equals(isbn10, other.isbn10)
				&& Objects.equals(isbn13, other.isbn13) && number_on_hand == other.number_on_hand
				&& Objects.equals(price, other.price) && Objects.equals(publication_year, other.publication_year)
				&& Objects.equals(publisher, other.publisher) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [isbn13=" + isbn13 + ", isbn10=" + isbn10 + ", title=" + title + ", alias=" + alias + ", author="
				+ author + ", author_et_alia=" + author_et_alia + ", publisher=" + publisher + ", publication_year="
				+ publication_year + ", price=" + price + ", number_on_hand=" + number_on_hand + ", image=" + image
				+ ", category=" + category + "]";
	}
	
}
