package daos;

import java.util.List;

import entities.movies;
import entities.reviews;

public interface reviewsDao extends AutoCloseable {
	boolean addReview(reviews r) throws Exception;
	boolean editReview(reviews r) throws Exception;
    reviews findReview(int id) throws Exception;
    boolean deleteReview(int id) throws Exception;
	List<reviews> displayAll() throws Exception;
	List<reviews> displayMyReviews(int id) throws Exception;
	public void shareReviewMain(int reviewId, List<Integer> userIds,int reviewOwnerId) throws Exception;
	void shareReviewRecursive(int reviewId, List<Integer> userIds, int index,int reviewOwnerId) throws Exception;
	List<reviews> sharedWithMe(int user_id) throws Exception;
	
}