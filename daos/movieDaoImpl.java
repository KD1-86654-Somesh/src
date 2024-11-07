package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.movies;

public class movieDaoImpl extends Dao implements movieDao {
	private PreparedStatement stmtFindAll;

	public movieDaoImpl() throws Exception {
		String sqlFindAll = "SELECT * FROM movies";
        stmtFindAll = con.prepareStatement(sqlFindAll);

	}

	public void close() throws Exception {
        try {
            if (stmtFindAll != null) stmtFindAll.close();

        } finally {
            super.close();
        }
    }
	@Override
	public List<movies> findAll() throws Exception {
		List<movies> list = new ArrayList<movies>();
		try(ResultSet rs = stmtFindAll.executeQuery()) {
			while(rs.next()) {
				String title = rs.getString("title");
				Date release = rs.getDate("rel_date");
				movies c = new movies(title,release);
				list.add(c);
			}
		} // rs.close();
		return list;
	}

}
