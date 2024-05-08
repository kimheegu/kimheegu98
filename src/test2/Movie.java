package test2;

public class Movie{
	private String title;
	private String content;
	private long number;
	
	public Movie(String title,String content,long number) {
		this.title=title;
		this.content=content;
		this.number=number;
	}
	public long getNumber() {
		return number;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
}
