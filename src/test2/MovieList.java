package test2;

import java.util.List;

public class MovieList {
	private List<Movie> movieList;
	
	public MovieList(List<Movie> movies) {
		this.movieList=movies;
	}

	public int size() {
		return movieList.size();
	}

	public Movie get(int i) {
		return movieList.get(i);
	}

	public void add(Movie movie) {
		movieList.add(movie);
	}

	public void remove(int i) {
		movieList.remove(i);
		
	}

	public boolean isEmpty() {
		return movieList.isEmpty();
	}
}
