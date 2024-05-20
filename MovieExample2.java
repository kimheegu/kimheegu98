package test2;


import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieExample2 {
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn;
	public MovieExample2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletex?useUnicode=true&characterEncoding=utf8",
					"root","1234");
		}catch(Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	private static void exit() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
			}
		}
		System.out.println("** 회원 게시판 종료 **");
		System.exit(0);
	}


	public static void main(String[] args) throws Exception {
		MovieExample2 movieexam = new MovieExample2();
		Manager2 mg = new Manager2();
		String data=null;
		String selectMvTitle=null;
		String pw = "1";
		
		while(true) {
			System.out.println("1:영화 예매하기");
			System.out.println("2:예매 확인하기");
			System.out.println("3:예매 취소하기");
			System.out.println("4:영화 리뷰하기");
			System.out.println("5:관리자 메뉴로 이동");
			System.out.println("q:종료");
			System.out.print("메뉴를 선택하세요: ");
			String menu = sc.nextLine();
			
			if(menu.equals("q")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			switch(menu) {
			case "1":
				Movie2 movie = new Movie2();
				System.out.println("[영화 목록]");
				System.out.println("-------------------------------------------------------");
				System.out.printf("%-6s%-12s%-12s%-16s\n","순번","제목","장르","영화번호");
				System.out.println("-------------------------------------------------------");
				try {
					String sql = "SELECT id,title,content,num FROM movielist";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						int id = rs.getInt("id");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String num = rs.getString("num");
						movie.setId(id);
						movie.setTitle(title);
						movie.setContent(content);
						movie.setNum(num);
						System.out.printf("%-6s%-12s%-12s%-16s\n",movie.getId(),
								movie.getTitle(),movie.getContent(),movie.getNum());
						System.out.println("-------------------------------------------------------");
						}
					System.out.print("예매할 영화번호를 고르세요: ");
					String mv = sc.nextLine();
					if(mv.equals(movie.getNum())) {
						System.out.print("좌석을 선택하세요(예:E-9): ");
						String chain = sc.nextLine();
						movie.setChain(chain);
						int numm = (int)(Math.random()*100000);
						movie.setNumm(numm);
						try {
							String sql1 = ""+"INSERT INTO rev(title,content,num,chain,numm)"+
											"VALUES(?,?,?,?,?)";
							PreparedStatement pstmt1 = conn.prepareStatement(sql1);
							pstmt1.setString(1, movie.getTitle());
							pstmt1.setString(2, movie.getContent());
							pstmt1.setString(3, movie.getNum());
							pstmt1.setString(4, movie.getChain());
							pstmt1.setInt(5, numm);
							pstmt1.executeUpdate();
							pstmt1.close();
							pstmt.close();
						}catch(Exception e) {
							e.printStackTrace();
							exit();
						}
					}
						}catch(SQLException e) {
							e.printStackTrace();
							exit();
						}
					break;
			case "2":
				Movie2 movie1 = new Movie2();
				System.out.println("[예매 목록]");
				System.out.println("-------------------------------------------------------");
				System.out.printf("%-6s%-12s%-12s%-16s%-16s\n","제목","장르","영화번호","좌석","발급번호");
				System.out.println("-------------------------------------------------------");
				try {
					String sql = "SELECT title,content,num,chain,numm FROM rev";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						String chain = rs.getString("chain");
						String title = rs.getString("title");
						String content = rs.getString("content");
						String num = rs.getString("num");
						int numm = rs.getInt("numm");
						movie1.setChain(chain);
						movie1.setTitle(title);
						movie1.setContent(content);
						movie1.setNum(num);
						movie1.setNumm(numm);
						System.out.printf("%-6s%-12s%-12s%-16s%-16s\n",movie1.getTitle(),
								movie1.getContent(),movie1.getNum(),movie1.getChain(),movie1.getNumm());
						System.out.println("-------------------------------------------------------");
						}
					rs.close();
					pstmt.close();
				}catch(Exception e) {
							e.printStackTrace();
							exit();
						}
				break;
			case "3":
				delete();
				break;
			case "4":
				break;
			case "5":
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

	private static void delete() {
		Movie2 movie1 = new Movie2();
		System.out.println("[예매 목록]");
		System.out.println("-------------------------------------------------------");
		System.out.printf("%-6s%-12s%-12s%-16s%-16s\n","제목","장르","영화번호","좌석","발급번호");
		System.out.println("-------------------------------------------------------");
		try {
			String sql = "SELECT title,content,num,chain,numm FROM rev";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String chain = rs.getString("chain");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String num = rs.getString("num");
				int numm = rs.getInt("numm");
				movie1.setChain(chain);
				movie1.setTitle(title);
				movie1.setContent(content);
				movie1.setNum(num);
				movie1.setNumm(numm);
				System.out.printf("%-6s%-12s%-16s%-10s%-20s\n",movie1.getTitle(),
						movie1.getContent(),movie1.getNum(),movie1.getChain(),movie1.getNumm());
				System.out.println("-------------------------------------------------------");
				}
			rs.close();
			pstmt.close();
		}catch(Exception e) {
					e.printStackTrace();
					exit();
				}
		System.out.print("취소할 영화의 발급번호를 선택하세요: ");
		String num1 = sc.nextLine();
		int num2 = Integer.parseInt(num1);
		try {
			String sql1 = ""+"DELETE FROM rev WHERE numm=?";
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num2);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			exit();
		}
		
	}

	private void list() {
		System.out.println("[영화 목록]");
		System.out.println("-------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n","순번","제목","장르","영화번호");
		System.out.println("-------------------------------------------------------");
		try {
			String sql = "SELECT id,title,content,num FROM movielist";
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
				}
				}catch(SQLException e) {
					e.printStackTrace();
					exit();
				}
	}
}


