package entities;

import java.sql.Date;

public class movies {
	private String title;
	private Date release;
	
	public movies() {
		// TODO Auto-generated constructor stub
	}

	public movies(String title, Date release) {
		super();
		this.title = title;
		this.release = release;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	@Override
	public String toString() {
		return "movies [title=" + title + ", release=" + release + "]";
	}
	
	

}
