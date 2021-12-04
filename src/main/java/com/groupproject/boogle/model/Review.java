package com.groupproject.boogle.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;
	
	@Column(length = 50)
	private String reviewTitle;
	
	@Column(length = 250)
	private String reviewDes;
	
	private byte rating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", updatable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "isbn13")
	private Book book;

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewDes() {
		return reviewDes;
	}

	public void setReviewDes(String reviewDes) {
		this.reviewDes = reviewDes;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, rating, reviewDes, reviewId, reviewTitle, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(book, other.book) && rating == other.rating && Objects.equals(reviewDes, other.reviewDes)
				&& Objects.equals(reviewId, other.reviewId) && Objects.equals(reviewTitle, other.reviewTitle)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", reviewTitle=" + reviewTitle + ", reviewDes=" + reviewDes
				+ ", rating=" + rating + ", user=" + user + ", book=" + book + "]";
	}

}
