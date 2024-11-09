package entities;

import java.sql.Timestamp;

public class reviews {
	private int id ;
	private int movieId ;
	private String review ;
	private int rating ;
	private int userId;
	private Timestamp modified;
	public reviews(int id, int movieId, String review, int rating, int userId, Timestamp modified) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.review = review;
		this.rating = rating;
		this.userId = userId;
		this.modified = modified;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getModified() {
		return modified;
	}
	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	@Override
	public String toString() {
		return "reviews [id=" + id + ", movieId=" + movieId + ", review=" + review + ", rating=" + rating + ", userId="
				+ userId + ", modified=" + modified + "]";
	}
	
	
	
	
	
	

}
