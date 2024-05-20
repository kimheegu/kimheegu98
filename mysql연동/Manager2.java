package test2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Manager2 {
	private Scanner sc = new Scanner(System.in);
	private Connection conn;
	
	public Manager2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletex?useUnicode=true&characterEncoding=utf8",
					"root","1234");
		}catch(Exception e) {
			e.printStackTrace();
			exit();
		}
	}
	private void exit() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
			}
		}
		System.out.println("** 회원 게시판 종료 **");
		System.exit(0);
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
				create();
				break;
			case "2":
				list();
				break;
			case "3":
				list();
				delete();
				break;
			}
		}
	}
	private void delete() {
		Movie2 movie = new Movie2();
		System.out.print("삭제할 영화를 입력해주세요: ");
		String del = sc.nextLine();
		try {
			String sql = ""+"DELETE FROM movielist WHERE num=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, del);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			exit();
		}
		list();
	}
	private void create() {
		Movie2 movie = new Movie2();
		System.out.print("제목: ");
		String title = sc.nextLine();
		movie.setTitle(title);
		System.out.print("장르: ");
		String content = sc.nextLine();
		movie.setContent(content);
		System.out.print("영화번호: ");
		String num = sc.nextLine();
		movie.setNum(num);
		System.out.println("-------------------------------------------------------");
		System.out.println("등록하시겠습니까?:1.Ok | 2.Cancel");
		System.out.print("메뉴선택: ");
		String menuNo = sc.nextLine();
		if(menuNo.equals("1")) {
			try {
				String sql=""+
							"INSERT INTO movielist (title,content,num)"+
							"VALUES(?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, movie.getTitle());
				pstmt.setString(2, movie.getContent());
				pstmt.setString(3, movie.getNum());
				pstmt.executeUpdate();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				exit();
			}
		}
		list();
	}
	private void list() {
		System.out.println();
		System.out.println("[영화 목록]");
		System.out.println("-------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n","순서","제목","장르","영화번호");
		System.out.println("-------------------------------------------------------");
		
		try {
			String sql="SELECT id,title,content,num FROM movielist";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Movie2 movie = new Movie2();
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String num = rs.getString("num");
				movie.setId(id);
				movie.setTitle(title);
				movie.setContent(content);
				movie.setNum(num);
				System.out.printf("%-6s%-12s%-16s%-40s\n",movie.getId(),
						movie.getTitle(),movie.getContent(),movie.getNum());
				System.out.println("------------------------------------------------------");
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
			exit();
		}
		
	}
}
