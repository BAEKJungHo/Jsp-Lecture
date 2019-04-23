package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.MemberDAO;
import member.MemberDTO;

public class BbsDAO {
	
	private Connection conn;
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
	

	// Constructor : JDBC 드라이버를 로딩 & DB Connection 구하기
	public BbsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// INSERT QUERY - 글쓰기
	public void insertBbs(BbsDTO bbs) { 
		String query = "insert into bbs(memberId, title, date, content) values (?, ?, ?, ?);";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, bbs.getMemberId());
			pStmt.setString(2, bbs.getTitle());
			pStmt.setString(3, bbs.getDate());
			pStmt.setString(4, bbs.getContent());

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
	public void deleteBbs(int id) { 
		String query = "delete from bbs where id=?;";
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
	
	// UPDATE QUERY - 수정 
	public void updateBbs(BbsDTO bbs) { 
		String query = "update bbs set title=?, date=?, content=? where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, bbs.getTitle());
			pStmt.setString(2, bbs.getDate());
			pStmt.setString(3, bbs.getContent());
			pStmt.setInt(4, bbs.getId());
			
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
	
	// selectCondition
	public List<BbsDTO> selectCondition(String query) {
		PreparedStatement pStmt = null;
		List<BbsDTO> bbsList = new ArrayList<BbsDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO bbs = new BbsDTO();
				bbs.setId(rs.getInt(1)); 
				bbs.setMemberId(rs.getInt(2)); 
				bbs.setName(rs.getString(3)); 
				bbs.setTitle(rs.getString(4)); 
				bbs.setDate(rs.getString(5)); 
				bbs.setContent(rs.getString(6));

				bbsList.add(bbs);
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
		return bbsList;
	}
	
	// selectOne
	// selectOne - id로 상세조회
	public BbsDTO selectOne(int id) {
		String query = "select * from bbs where id=?;";
		PreparedStatement pStmt = null;
		BbsDTO bbs = new BbsDTO();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1,  id);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				bbs.setId(rs.getInt(1)); 
				bbs.setMemberId(rs.getInt(2)); 
				bbs.setTitle(rs.getString(3)); 
				bbs.setDate(rs.getString(4)); 
				bbs.setContent(rs.getString(5));
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
		return bbs;
	}
	
	// 전체조회
	// select - 조회
	public List<BbsDTO> selectAll() {
		String sql ="select b.id as 아이디, m.id as 멤버아이디, m.name as 이름, b.title as 제목, b.date as 수정일, b.content as 내용"
				+ " from bbs as b " + 
				"inner join member as m " + 
				"on b.memberid = m.id " +
				"order by date desc";
		List<BbsDTO> bbsList = selectCondition(sql);
		return bbsList;
	}
	
	// ID로 상세조회
	// 제목으로 상세조회
	public BbsDTO selectId(int id) {
		String sql ="select m.name as 이름, b.title as 제목, b.date as 수정일, b.content as 내용"
				+ " from bbs as b " + 
				"inner join member as m " + 
				"on b.memberid = m.id " +
				"where b.id=? " +
				"order by date desc";
		PreparedStatement pStmt = null;
		BbsDTO bbs = new BbsDTO();
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,  id);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				bbs.setName(rs.getString(1)); 
				bbs.setTitle(rs.getString(2)); 
				bbs.setDate(rs.getString(3)); 
				bbs.setContent(rs.getString(4));
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
		return bbs;
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
