package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
	Scanner sc = new Scanner(System.in);
	MovieList movieList = new MovieList();
	
	
	public void input() {
		while(true) {
			System.out.println("1:영화 등록하기");
			System.out.println("2:영화 목록보기");
			System.out.println("3:영화 삭제하기");
			System.out.println("b:메인 메뉴로 이동");
			System.out.print("메뉴를 선택하세요: ");
			
			String menu=sc.nextLine();
			
			if(menu.equals("b")) {
				System.out.println("메인 메뉴로 이동합니다.");
				break;
			}
			switch(menu) {
			case "1":
				System.out.print("제목: ");
				String title = sc.nextLine();
				System.out.print("장르: ");
				String content = sc.nextLine();
				long number = (long)(Math.random()*10000000);
				
				movieList.add(new Movie(title,content,number));
				System.out.println(">> 등록되었습니다.");
				break;
			case "2":
				if(movieList.isEmpty()) {
					System.out.println("등록된 영화가 없습니다.");
				}else {
					System.out.println("-----영화목록------");
					for(int i=0;i<movieList.size();i++) {
						Movie movie = movieList.get(i);
						System.out.println((i+1)+". "+movie.getNumber()+", "+movie.getTitle()+", "+movie.getContent());
					}
				}
				break;
			case "3":
				System.out.println("-----영화목록------");
				for(int i=0;i<movieList.size();i++) {
					Movie movie = movieList.get(i);
					System.out.println((i+1)+". "+movie.getNumber()+", "+movie.getTitle()+", "+movie.getContent());
				}
				System.out.print("삭제할 영화를 선택하세요: ");
				int a = Integer.parseInt(sc.nextLine());
				if(a >=1 && a<=movieList.size()) {
					movieList.remove(a-1);
					System.out.println(">> 삭제되었습니다.");
				}
				break;
			}
		}
	}
}