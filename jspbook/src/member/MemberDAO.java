package member;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MemberDAO {
	private static final Logger LOG = LoggerFactory.getLogger(MemberDAO.class);
	public static final int ID_PASSWORD_MATCH = 1;
	public static final int ID_DOES_NOT_EXIST = 2;
	public static final int PASSWORD_IS_WRONG = 3;
	public static final int DATABASE_ERROR = -1;
	Connection conn;
    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "javapass";
    private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
  
    public MemberDAO() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");	
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		LOG.info("DB Connect ERROR");
		}
    }
    
    public String prepareDownload() {
    	LOG.debug("");
    	StringBuffer sb = new StringBuffer();
    	List<MemberDTO> mList = selectJoinAll(0);
    	
    	try {
			FileWriter fw = new FileWriter("C:/Temp/MemberList.csv");
			String head = "아이디,이름,생년월일,주소\r\n";
			sb.append(head);
			fw.write(head);
			LOG.trace(head);
			for (MemberDTO mDto : mList) {
				String line = mDto.getId() + "," + mDto.getName() + "," 
						+ mDto.getBirthday() + "," + mDto.getAddress() + "\r\n";
				sb.append(line);
				fw.write(line);
				LOG.trace(line);
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			LOG.trace(e.getMessage());
		} 
    	LOG.debug("");
    	return sb.toString();
    }
    
	public int verifyIdPassword(int id, String password) {
		LOG.debug("");
		//System.out.println("verifyIdPassword(): " + id + ", " + password);
		String query = "select hashed from member where id=?;";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String hashedPassword = "";
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {	
				hashedPassword = rs.getString(1);
				LOG.trace(hashedPassword);
				if (BCrypt.checkpw(password, hashedPassword))
					return ID_PASSWORD_MATCH;
				else
					return PASSWORD_IS_WRONG;
			}
			return ID_DOES_NOT_EXIST;
		} catch (Exception e) {
			LOG.trace(e.getMessage());
		} finally {
			try {
				rs.close();
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				LOG.trace(se.getMessage());
			}
		}
		LOG.debug("");
		return DATABASE_ERROR;
	}
    
    public void insertMember(MemberDTO member) {
    	LOG.debug("");
    	String query = "insert into member(password, name, birthday, address, hashed) values (?, ?, ?, ?, ?);";
    	PreparedStatement pStmt = null;
    	try {
    		String hashedPassword = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
    		LOG.debug(hashedPassword);
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, "*");
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getBirthday());
			pStmt.setString(4, member.getAddress());
			pStmt.setString(5, hashedPassword);
			
			pStmt.executeUpdate();
			LOG.trace(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				LOG.trace(se.getMessage());
			}
		}
    	LOG.debug("");
    }
    
    public void updateMember(MemberDTO member) {
    	LOG.debug("");
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
			LOG.trace(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				LOG.trace(se.getMessage());
			}
		}
    	LOG.debug("");
    }
    
    public void deleteMember(int memberId) {
    	LOG.debug("");
    	String query = "delete from member where id=?;";
    	PreparedStatement pStmt = null;
    	try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, memberId);
			
			pStmt.executeUpdate();
			LOG.trace(query);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.trace(e.getMessage());
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				LOG.trace(se.getMessage());
			}
		}
    	LOG.debug("");
    }
    
    public int getCount() {
    	LOG.debug("");
		String query = "select count(*) from member;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {				
				count = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.trace(e.getMessage());
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				LOG.trace(se.getMessage());
			}
		}
		LOG.debug("");
		return count;
	}
    
	public List<MemberDTO> selectJoinAll(int page) {
		LOG.debug("");
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select id, name, birthday, address from member order by id desc;"; 
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select id, name, birthday, address from member order by id desc limit ?, 10;";  
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<MemberDTO> mList = new ArrayList<MemberDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			LOG.trace(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				MemberDTO mDto = new MemberDTO();
				mDto.setId(rs.getInt(1));
				mDto.setName(rs.getString(2));
				mDto.setBirthday(rs.getString(3));
				mDto.setAddress(rs.getString(4));
				mList.add(mDto);
				LOG.trace(query);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.trace(e.getMessage());
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				LOG.trace(se.getMessage());
			}
		}
		LOG.debug("");
		return mList;
	}
    
    public MemberDTO recentId() {
    	LOG.debug("");
    	String sql = "select * from member order by id desc limit 1;";
    	LOG.trace(sql);
    	MemberDTO mDto = selectOne(sql);
    	LOG.debug("");
    	return mDto;
    }
    
    public MemberDTO searchById(int memberId) {
    	LOG.debug("");
    	String sql = "select * from member where id=" + memberId + ";";
    	MemberDTO mDto = selectOne(sql);
    	LOG.debug("");
    	return mDto;
    }
    
    public MemberDTO searchByName(String memberName) {
    	LOG.debug("");
    	String sql = "select * from member where name like '" + memberName + "';";
    	MemberDTO mDto = selectOne(sql);
    	LOG.debug("");
    	return mDto;
    }

    public MemberDTO selectOne(String query) {
    	LOG.debug("");
    	PreparedStatement pStmt = null;
    	MemberDTO member = new MemberDTO();
    	try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {
				member.setId(rs.getInt(1));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setBirthday(rs.getString(4));
				member.setAddress(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.trace(e.getMessage());
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				LOG.trace(se.getMessage());
			}
		}
    	LOG.debug("");
    	return member;
    }
    
    public List<MemberDTO> selectAll() {
    	LOG.debug("");
    	String query = "select * from member;";
    	PreparedStatement pStmt = null;
    	List<MemberDTO> memberList = new ArrayList<MemberDTO>();
    	try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getInt(1));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setBirthday(rs.getString(4));
				member.setAddress(rs.getString(5));
				memberList.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.trace(e.getMessage());
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
				LOG.trace(se.getMessage());
			}
		}
    	LOG.debug("");
    	return memberList;
    }
    
    public void close() {
    	LOG.debug("");
    	try {
			if (conn != null && !conn.isClosed()) 
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.trace(e.getMessage());
		}
    	LOG.debug("");
    }
}
