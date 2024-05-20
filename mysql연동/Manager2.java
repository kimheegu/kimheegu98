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
		System.out.println("** ȸ�� �Խ��� ���� **");
		System.exit(0);
	}
	public void input() throws Exception {
		while(true) {
			System.out.println("1:��ȭ ����ϱ�");
			System.out.println("2:��ȭ ��Ϻ���");
			System.out.println("3:��ȭ �����ϱ�");
			System.out.println("b:���� �޴��� �̵�");
			System.out.print("�޴��� �����ϼ���: ");
			String menu = sc.nextLine();
			
			if(menu.equals("b")) {
				System.out.println("���� �޴��� ���ư��ϴ�.");
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
		System.out.print("������ ��ȭ�� �Է����ּ���: ");
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
		System.out.print("����: ");
		String title = sc.nextLine();
		movie.setTitle(title);
		System.out.print("�帣: ");
		String content = sc.nextLine();
		movie.setContent(content);
		System.out.print("��ȭ��ȣ: ");
		String num = sc.nextLine();
		movie.setNum(num);
		System.out.println("-------------------------------------------------------");
		System.out.println("����Ͻðڽ��ϱ�?:1.Ok | 2.Cancel");
		System.out.print("�޴�����: ");
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
		System.out.println("[��ȭ ���]");
		System.out.println("-------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n","����","����","�帣","��ȭ��ȣ");
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
