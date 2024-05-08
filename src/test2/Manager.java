package test2;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Manager {
	Scanner sc = new Scanner(System.in);
	MovieList movieList;
	String a =null;
	String b = null;
	public Manager(MovieList movieList) {
		this.movieList=movieList;
	}

	public void input() throws Exception {
		while(true) {
			System.out.println("1:영화 등록하기");
			System.out.println("2:영화 목록보기");
			System.out.println("3:영화 삭제하기");
			System.out.println("b:메인 메뉴로 이동");
			System.out.print("메뉴를 선택하세요: ");
			String menu = sc.nextLine();
			
			if(menu.equals("b")) {
				System.out.println("메인 메뉴로 돌아갑니다.");
				break;
			}
			switch(menu) {
			case "1":
				File file1 = new File("C:/Temp/file1.txt");
				FileWriter fw2 = new FileWriter(file1,false);
				System.out.print("제목: ");
				String title = sc.nextLine();
				System.out.print("장르: ");
				String content = sc.nextLine();
				long num = (long)(Math.random()*1000000000);
				movieList.add(new Movie(title,content,num));
				System.out.println(">>등록되었습니다.");
				File file = new File("C:/Temp/file1.txt");
				FileWriter fw = new FileWriter(file,true);
				for(int i=0;i<movieList.size();i++) {
					Movie movie = movieList.get(i);
					a= movie.getTitle();
					b = movie.getContent();
					fw.write(a+"(");
					fw.write(b+")"+"\n");
				}
				fw.flush();
				fw.close();
				break;
			case "2":
				if(!movieList.isEmpty()) {
				for(int i=0;i<movieList.size();i++){
					Movie movie = movieList.get(i);
					System.out.println((i+1)+".["+movie.getNumber()+"]:"+movie.getTitle()+"("+movie.getContent()+")");
				}
				}else {
					System.out.println("등록된 영화가 없습니다.");
				}
				break;
			case "3":
				File file3 = new File("C:/Temp/file1.txt");
				FileWriter fw3 = new FileWriter(file3,false);
				if(!movieList.isEmpty()) {
				for(int i=0;i<movieList.size();i++){
					Movie movie = movieList.get(i);
					System.out.println((i+1)+".["+movie.getNumber()+"]:"+movie.getTitle()+"("+movie.getContent()+")");
				}
				System.out.print("삭제할 영화를 선택하세요(예:1,2,3,4): ");
				String del = sc.nextLine();
				int del2 = Integer.parseInt(del);
				if(del2 >= 1 && del2<=movieList.size()) {
					movieList.remove(del2-1);
					System.out.println(">> 삭제되었습니다.");
					File file2 = new File("C:/Temp/file1.txt");
					FileWriter fw1 = new FileWriter(file2,true);
					for(int i=0;i<movieList.size();i++) {
						Movie movie = movieList.get(i);
						a= movie.getTitle();
						b = movie.getContent();
						fw1.write(a+"(");
						fw1.write(b+")"+"\n");
					}
					fw1.flush();
					fw1.close();
				}else {
					System.out.println("삭제할 영화를 다시 선택해주세요.");
				}
				}else {
					System.out.println("등록된 영화가 없습니다.");
				}
				break;
			}
		}
	}
}
