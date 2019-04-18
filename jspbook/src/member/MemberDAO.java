package member;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	// 로그인 처리를 위한 상수 선언
	public static final int ID_PASSWORD_MATCH = 1; // 아이디, 패스워드가 올바르면 1의값
	public static final int ID_DOES_NOT_EXIST = 2; // 아이디가 존재하지 않으면 2의 값
	public static final int PASSWORD_IS_WRONG = 3; // 패스워드가 틀리면 3의 값
	public static final int DATABASE_ERROR = -1; // DB Error 발생 시 -1의 음수값
	
	private Connection conn;
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
	

	// Constructor : JDBC 드라이버를 로딩 & DB Connection 구하기
	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 처리를 위한 메소드
	public int verifyIdPassword(int id, String password) {
		System.out.println("verifyIdPassword(): " + id + "," + password);
		String query = "select password from member where id=?;";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String dbPassword = "";
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				dbPassword = rs.getString(1);
				if(dbPassword.equals(password))
					return ID_PASSWORD_MATCH;
				else 
					return PASSWORD_IS_WRONG;
			}
			return ID_DOES_NOT_EXIST;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return DATABASE_ERROR;
	}
	
	// INSERT QUERY - 회원가입
	public void insertMember(MemberDTO member) { 
		String query = "insert into member values (?, ?, ?, ?, ?);";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, member.getId());
			pStmt.setString(2, member.getPassword());
			pStmt.setString(3, member.getName());
			pStmt.setString(4, member.getBirthday());
			pStmt.setString(5, member.getAddress());

			pStmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	// UPDATE QUERY - 변경
	public void updateMember(MemberDTO member) { 
		String query = "update member set password=?, name=?, birthday=?, address=? where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, member.getPassword());
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getBirthday());
			pStmt.setString(4, member.getAddress());
			pStmt.setInt(5, member.getId());
			
			pStmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	// DELETE QUERY - 삭제
	public void deleteMember(int id) { 
		String query = "delete from member where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			
			pStmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	// selectOne
	public MemberDTO selectOne(int id) {
		String query = "select * from member where id=?;";
		PreparedStatement pStmt = null;
		MemberDTO member = new MemberDTO();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1,  id);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				member.setId(rs.getInt(1)); 
				member.setPassword(rs.getString(2)); 
				member.setName(rs.getString(3)); 
				member.setBirthday(rs.getString(4)); 
				member.setAddress(rs.getString(5));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return member;
	}
	
	// selectCondition
	public List<MemberDTO> selectCondition(String query) {
		PreparedStatement pStmt = null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getInt(1)); 
				member.setPassword(rs.getString(2)); 
				member.setName(rs.getString(3)); 
				member.setBirthday(rs.getString(4)); 
				member.setAddress(rs.getString(5));

				memberList.add(member);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return memberList;
	}

	// 이름으로 검색 - 동명이인 존재 가능성 
	public List<MemberDTO> selectName(String name) {
		String sql ="select * from member where name like '" + name + "';";
		List<MemberDTO> memberList = selectCondition(sql);
		return memberList;
	}
	
	// select - 조회 - 최근순서(내림차순)
	public List<MemberDTO> selectAll() {
		String sql ="select * from member;";
		List<MemberDTO> memberList = selectCondition(sql);
		return memberList;
	}
	
	// 로그인 검증(ID, PASSWORD)
	public boolean checkLogin(int id, String password) {
		MemberDTO member = new MemberDTO();
		MemberDAO mDao = new MemberDAO();
		member = mDao.selectOne(id); // 해당 id에 속하는 컬럼값 들을 member객체에 저장
		
		// member객체로 Getter()를 사용하여 ID와 PASSWORD값 얻어와서 비교하기
		if((member.getId() == id) && (member.getPassword().equals(password))) {
			//System.out.println("로그인 성공!" + " ID : " + member.getId());
			return true;
		} else {
			//System.out.println("로그인 실패!");
			return false;
		}
	}
	// Connection Close
 	public void close() {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
