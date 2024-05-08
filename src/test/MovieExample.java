package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieExample {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
        String chain=null;
        String check = null;
        int num = 0;
        MovieList movieList = new MovieList();
       
        String password="admin1234";
		while (true) { 
            System.out.println("1:영화 예매하기");
            System.out.println("2:영화 확인하기");
            System.out.println("3:영화 취소하기");
            System.out.println("4:관리자 메뉴로 이동");
            System.out.println("q:종료");
            System.out.println();
            System.out.print("메뉴를 선택하세요: ");

            String menu = sc.nextLine();

            if (menu.equals("q")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            
            switch(menu) {
            case "1":
            	for(int i=0;i<movieList.size();i++) {
            		Movie movie = movieList.get(i);
            		System.out.println((i+1)+".["+movie.getNumber()+"]:"+movie.getTitle()+"("+movie.getContent()+")");
            	}
                System.out.print("예매할 영화의 번호를 입력하세요: ");
                
                check = sc.nextLine();
                int check1=Integer.parseInt(check);
                if(check1 >= 0 && check1<movieList.size()) {
                	Movie selectMovie = movieList.get(check1);
                	num = (int)(Math.random()*100000000);
                	System.out.print("좌석을 선택하세요(예:E-9): ");
                	chain = sc.nextLine();
                	System.out.println(">> 예매가 완료되었습니다.");
                	System.out.println(">> 발급번호: "+num);
                	check=String.valueOf(selectMovie.getNumber());
                }else {
                	System.out.println("예매할 영화의 번호를 다시 입력해주세요.");
                }
                break;
            case "2":
            	if(num != 0) {
            		System.out.print("발급번호를 입력하세요: ");
            		String a = sc.nextLine();
            		int num1 = Integer.parseInt(a);
            		for(int i=0;i<movieList.size();i++) {
            			Movie movie = movieList.get(i);
            		if(num == num1 && String.valueOf(movie.getNumber()).equals(check) ) {
            			System.out.println(">> [확인완료] 영화:"+movie.getTitle()+", 좌석:"+chain);

            		}
            		}
            		System.out.println("잘못된 발급번호 입니다.");
            	}
            	else {
            		System.out.println("예매된 영화가 없습니다.");
            	}
            	break;
            case "3":
            	if(num != 0) {
            		System.out.print("발급번호를 입력하세요: ");
            		String a = sc.nextLine();
            		int num1 = Integer.parseInt(a);
            		for(int i=0;i<movieList.size();i++) {
            			Movie movie = movieList.get(i);
                		if(num == num1 && String.valueOf(movie.getNumber()).equals(check) ) {
                			System.out.println(">> [취소완료] 영화:"+movie.getTitle()+", 좌석:"+chain);
                			num=0;
                			chain=null;
                		}else {
                			System.out.println("잘못된 발급번호 입니다.");
            		}
            		}
            	}else {
            		System.out.println("취소할 영화가 없습니다.");
            	}
            	break;
            case "4":
            	System.out.print("관리자 비밀번호를 입력하세요: ");
            	String password1 = sc.nextLine();
            	if(password.equals(password1)) {
            	Manager mg = new Manager();
            	mg.input();
            	}else {
            		System.out.println("관리자 비밀번호를 잘못 입력하셨습니다.");
            	}
            }
		}
	}
}
