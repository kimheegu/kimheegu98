package test;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
	private List<Movie> movieList;
	
	public MovieList() {
		movieList=new ArrayList();
    	movieList.add(new Movie("어벤져스", "판타지", (long)(Math.random()*100000000)));
    	movieList.add(new Movie("컨저링", "호러", (long)(Math.random()*100000000)));
    	movieList.add(new Movie("러브 엑츄얼리", "로맨스", (long)(Math.random()*100000000)));
	}

	public void add(Movie movie) {
		movieList.add(movie);
		
	}

	public boolean isEmpty() {
		return movieList.isEmpty();
	}

	public int size() {
		return movieList.size();
	}

	public Movie get(int i) {
		return movieList.get(i);
	}

	public void remove(int i) {
		movieList.remove(i);
		
	}
}
