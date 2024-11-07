package daos;

import java.util.List;

import entities.movies;


public interface movieDao extends AutoCloseable {
	List<movies> findAll() throws Exception;
	

}
