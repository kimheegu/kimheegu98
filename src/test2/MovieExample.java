package test2;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieExample {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("어벤져스","판타지",(long)(Math.random()*1000000000)));
		movies.add(new Movie("컨저링","호러",(long)(Math.random()*1000000000)));
		movies.add(new Movie("러브 엑츄얼리","로맨스",(long)(Math.random()*1000000000)));
		MovieList movieList = new MovieList(movies);
		Manager mg = new Manager(movieList);
		int num=0;
		String chain=null;
		Movie selectMv=null;
		String pw = "kimheegu";
		
		while(true) {
			System.out.println("1:영화 예매하기");
			System.out.println("2:예매 확인하기");
			System.out.println("3:예매 취소하기");
			System.out.println("4:관리자 메뉴로 이동");
			System.out.println("q:종료");
			System.out.print("메뉴를 선택하세요: ");
			String menu = sc.nextLine();
			
			if(menu.equals("q")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			switch(menu) {
			case "1":
				if(!movieList.isEmpty()) {
				for(int i=0;i<movies.size();i++){
					Movie movie = movies.get(i);
					System.out.println((i+1)+".["+movie.getNumber()+"]:"+movie.getTitle()+"("+movie.getContent()+")");
				}
					System.out.print("예매할 영화를 선택하세요(예:1,2,3,4): ");
					String mv = sc.nextLine();
					int mv2 = Integer.parseInt(mv);
					selectMv = movies.get(mv2-1);
					if(mv2 >= 1 && mv2<movies.size()+1) {
						System.out.print("좌석을 선택하세요(예:E-9): ");
						chain = sc.nextLine();
						System.out.println(">> 예매가 완료되었습니다.");
						num = (int)(Math.random()*100000);
						System.out.println(">> 발급번호: "+num);
					}else {
						System.out.println("영화를 다시 선택해주세요.");
					}
				}else {
					System.out.println("등록된 영화가 없습니다.");
				}
					break;
			case "2":
				if(num != 0) {
					System.out.print("발급번호를 입력하세요: ");
					String check = sc.nextLine();
					int check2=Integer.parseInt(check);
					if(check2 == num) {
						System.out.println("[확인 완료] 영화:"+selectMv.getTitle()+", 좌석:"+chain);
					}else {
						System.out.println("발급번호가 잘못 입력되었습니다.");
					}
				}else {
					System.out.println("예매된 영화가 없습니다.");
				}
				break;
			case "3":
				if(num != 0) {
				System.out.print("발급번호를 입력하세요: ");
				String check = sc.nextLine();
				int check2 = Integer.parseInt(check);
				if(check2 == num) {
					System.out.println("[취소 완료] 영화:"+selectMv.getTitle()+", 좌석:"+chain+"의 예매가 취소되었습니다.");
					num = 0;
					chain = null;
				}else {
					System.out.println("발급번호가 잘못 입력되었습니다.");
				}
				}else {
					System.out.println("예매된 영화가 없습니다.");
				}
				break;
			case "4":
				System.out.print("관리자 비밀번호를 입력하세요: ");
				String check = sc.nextLine();
				if(pw.equals(check)) {
					mg.input();
				}else {
					System.out.println("비밀번호를 다시 입력해 주세요.");
				}
			}
		}
	}
}


