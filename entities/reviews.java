package entities;

public class reviews {
	private int id ;
	private int movieId ;
	private String review ;
	private int rating ;
	private int userId;
	
	public reviews() {
		// TODO Auto-generated constructor stub
	}

	public reviews(int id, int movieId, String review, int rating, int userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.review = review;
		this.rating = rating;
		this.userId = userId;
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

	@Override
	public String toString() {
		return "reviews [id=" + id + ", movieId=" + movieId + ", review=" + review + ", rating=" + rating + ", userId="
				+ userId + "]";
	}
	
	
	
	

}
