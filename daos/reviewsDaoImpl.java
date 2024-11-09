package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import entities.movies;
import entities.reviews;

public class reviewsDaoImpl extends Dao implements reviewsDao {
	private PreparedStatement stmtAddReview;
	private PreparedStatement stmtEditReview;
	private PreparedStatement stmtFindReview;
	private PreparedStatement stmtDeleteReview;
	private PreparedStatement stmtDisplayReview;
	private PreparedStatement stmtDisplayMyReviews;
	private PreparedStatement stmtShareReview;
	private PreparedStatement stmtSharedWithMe;


	public reviewsDaoImpl() throws Exception {
		String sqlAddReview = "INSERT INTO reviews(id , movie_id, review ,rating ,user_id,modified  ) VALUES(?, ?, ?,?,?,?)";
		stmtAddReview = con.prepareStatement(sqlAddReview);
		String sqlEditReview = "update reviews set movie_id=?,review=?,user_id=? , rating=? ,modified=? where id=?";
		stmtEditReview = con.prepareStatement(sqlEditReview);
		
		String sqlFindReview = "select * from reviews where id=?";
		stmtFindReview = con.prepareStatement(sqlFindReview);
		
		String sqlDeleteReview = "delete from reviews where id=?";
		stmtDeleteReview = con.prepareStatement(sqlDeleteReview);
		
		String sqlDisplayReview = "select * from reviews";
		stmtDisplayReview = con.prepareStatement(sqlDisplayReview);
		
		String sqlDisplayMyReviews = "select * from reviews where user_id=?";
		stmtDisplayMyReviews = con.prepareStatement(sqlDisplayMyReviews);
		
		String sqlShareReview = "INSERT INTO shares (user_id, review_id) VALUES (?, ?)";
		stmtShareReview = con.prepareStatement(sqlShareReview);
		
		
		
		String sqlSharedWithMe = "SELECT r.*\r\n"
				+ "		FROM shares s\r\n"
				+ "		JOIN reviews r ON s.review_id = r.id\r\n"
				+ "		WHERE s.user_id = ?;";
		stmtSharedWithMe = con.prepareStatement(sqlSharedWithMe);
	}
	public void close() throws Exception {
		stmtAddReview.close();
		stmtEditReview.close();
		stmtFindReview.close();
		stmtDeleteReview.close();
		stmtDisplayReview.close();
		stmtDisplayMyReviews.close();
		stmtShareReview.close();
		stmtSharedWithMe.close();

		super.close();
	}

	@Override
	public boolean addReview(reviews r) throws Exception {
		stmtAddReview.setInt(1, r.getId());
		stmtAddReview.setInt(2, r.getMovieId());
		
		stmtAddReview.setString(3, r.getReview());
		stmtAddReview.setInt(4, r.getRating());
		stmtAddReview.setInt(5, r.getUserId());
		stmtAddReview.setTimestamp(6, r.getModified());

		
		return stmtAddReview.executeUpdate() == 1;

	}
	
	public boolean editReview(reviews r) throws Exception{
	 		stmtEditReview.setInt(1, r.getMovieId());
	 		stmtEditReview.setString(2, r.getReview());
	 		stmtEditReview.setInt(3, r.getUserId());

	 		stmtEditReview.setInt(4, r.getRating());
	 		stmtEditReview.setTimestamp(5, r.getModified());

	 		stmtEditReview.setInt(6, r.getId());

	 		return stmtEditReview.executeUpdate() == 1;
	    }
	
	public reviews findReview(int id) throws Exception {
		stmtFindReview.setInt(1, id);
		try(ResultSet rs = stmtFindReview.executeQuery()) {
			if(rs.next()) {
				id = rs.getInt("id");
				int movie_id = rs.getInt("movie_id");
				int rating = rs.getInt("rating");
				int user_id = rs.getInt("user_id");
				String review_text = rs.getString("review");
				Timestamp ts = Timestamp.from(Instant.now());

				reviews r = new reviews(id, movie_id, review_text, rating, user_id,ts);
				return r;
			}
		} // rs.close();
		return null;
	} 
	 
	public boolean deleteReview(int id) throws Exception {
		stmtDeleteReview.setInt(1, id);
		return stmtDeleteReview.executeUpdate() == 1;
	}
	
	public List<reviews> displayAll() throws Exception {
		List<reviews> list = new ArrayList<reviews>();
		try(ResultSet rs = stmtDisplayReview.executeQuery()) {
			while(rs.next()) {
				int id = rs.getInt("id");

				int movie_id = rs.getInt("movie_id");
				int rating = rs.getInt("rating");
				int user_id = rs.getInt("user_id");
				String review_text = rs.getString("review");
				Timestamp ts = Timestamp.from(Instant.now());

				reviews r = new reviews(id, movie_id, review_text, rating, user_id,ts);
				list.add(r);
			}
		} // rs.close();
		return list;
	}
	
	 public List<reviews> displayMyReviews(int user_id) throws Exception{
    	 List<reviews> list = new ArrayList<reviews>();
       stmtDisplayMyReviews.setInt(1, user_id);

    	 try(ResultSet rs = stmtDisplayMyReviews.executeQuery()){
    		 while(rs.next()) {
 				 int id = rs.getInt("id");
    			 int movie_id = rs.getInt("movie_id");
 				String review_text = rs.getString("review");
 				int rating = rs.getInt("rating");
 				int user_id1 = rs.getInt("user_id");
				Timestamp ts = Timestamp.from(Instant.now());

				reviews r = new reviews(id, movie_id, review_text, rating, user_id1,ts);
 				list.add(r);
    		 }
    	 } 
    	 return list;
}
	 
		
	public void shareReviewMain(int reviewId, List<Integer> userIds,int reviewOwnerId) throws Exception {
   shareReviewRecursive(reviewId, userIds, 0,reviewOwnerId);
}
	
 public void shareReviewRecursive(int reviewId, List<Integer> userIds, int index,int reviewOwnerId) throws Exception {
	 if (index >= userIds.size()) {
	        return; 
	    }

	    int userId = userIds.get(index);

	    if (userId == reviewOwnerId) {
	        System.out.println("Cannot share review " + reviewId + " with user " + userId + " (the review owner).");
	    } else {
	        stmtShareReview.setInt(1, userId);
	        stmtShareReview.setInt(2, reviewId);
	        stmtShareReview.executeUpdate();
	        System.out.println("Shared review " + reviewId + " with user " + userId);
	    }

	    shareReviewRecursive(reviewId, userIds, index + 1, reviewOwnerId);
}
 
 public List<reviews> sharedWithMe(int user_id) throws Exception {
	   

     stmtSharedWithMe.setInt(1, user_id);

     List<reviews> list = new ArrayList<reviews>();
		try(ResultSet rs = stmtSharedWithMe.executeQuery()) {
			while(rs.next()) {
				int id = rs.getInt("id");

				int movie_id = rs.getInt("movie_id");
				int rating = rs.getInt("rating");
				int user_id1 = rs.getInt("user_id");
				String review_text = rs.getString("review");
				Timestamp ts = Timestamp.from(Instant.now());

				reviews r = new reviews(id, movie_id, review_text, rating, user_id1,ts);
				list.add(r);
			}
		} // rs.close();
		return list;
	}
}
