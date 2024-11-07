package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


	public reviewsDaoImpl() throws Exception {
		String sqlAddReview = "INSERT INTO reviews(id , movie_id, review ,rating ,user_id  ) VALUES(?, ?, ?,?,?)";
		stmtAddReview = con.prepareStatement(sqlAddReview);
		String sqlEditReview = "update reviews set movie_id=?,review=?,user_id=? , rating=? where id=?";
		stmtEditReview = con.prepareStatement(sqlEditReview);
		
		String sqlFindReview = "select * from reviews where id=?";
		stmtFindReview = con.prepareStatement(sqlFindReview);
		
		String sqlDeleteReview = "delete from reviews where id=?";
		stmtDeleteReview = con.prepareStatement(sqlDeleteReview);
		
		String sqlDisplayReview = "select * from reviews";
		stmtDisplayReview = con.prepareStatement(sqlDisplayReview);
		
		String sqlDisplayMyReviews = "select * from reviews where user_id=?";
		stmtDisplayMyReviews = con.prepareStatement(sqlDisplayMyReviews);
	}
	public void close() throws Exception {
		stmtAddReview.close();
		stmtEditReview.close();
		stmtFindReview.close();
		stmtDeleteReview.close();

		super.close();
	}

	@Override
	public boolean addReview(reviews r) throws Exception {
		stmtAddReview.setInt(1, r.getId());
		stmtAddReview.setInt(2, r.getMovieId());
		
		stmtAddReview.setString(3, r.getReview());
		stmtAddReview.setInt(4, r.getRating());

		stmtAddReview.setInt(5, r.getUserId());
		
		return stmtAddReview.executeUpdate() == 1;

	}
	
	public boolean editReview(reviews r) throws Exception{
	 		stmtEditReview.setInt(1, r.getMovieId());
	 		stmtEditReview.setString(2, r.getReview());
	 		stmtEditReview.setInt(3, r.getUserId());

	 		stmtEditReview.setInt(4, r.getRating());
	 		stmtEditReview.setInt(5, r.getId());

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
			
				reviews r = new reviews(id, movie_id, review_text, rating, user_id);
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
				reviews r = new reviews(id, movie_id, review_text, rating, user_id);
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
				reviews r = new reviews(id, movie_id, review_text, rating, user_id1);
 				list.add(r);
    		 }
    	 } 
    	 return list;

}
}
