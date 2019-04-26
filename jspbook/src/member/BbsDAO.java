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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BbsDAO {
	private static final Logger LOG = LoggerFactory.getLogger(MemberDAO.class);
    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "javapass";
    private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
    private Connection conn;
	
    public BbsDAO() {
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
    	List<BbsMember> bmList = selectJoinAll(0);
    	try {
			FileWriter fw = new FileWriter("C:/Temp/BbsList.csv");
			String head = "번호,제목,이름,최종수정일,내용\r\n";
			sb.append(head);
			fw.write(head);
			LOG.trace(head);
			for (BbsMember bm : bmList) {
				String line = bm.getId() + "," + bm.getTitle() + "," 
						+ bm.getName() + "," + bm.getDate() + "," + bm.getContent()+"\r\n";
				if(line.contains("<br>")) {
					line = line.replaceAll("<br>", "\r\n");
				}
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

    public void createBbsTable() {
    	LOG.debug("");
    	String query = "create table if not exists bbs (" + 
    			"  id int unsigned not null auto_increment," + 
    			"  memberId int unsigned not null," + 
    			"  title varchar(50) not null," + 
    			"  date datetime not null default current_timestamp," + 
    			"  content varchar(400)," + 
    			"  primary key(id)," + 
    			"  foreign key(memberId) references member(id)" + 
    			") default charset=utf8;";
    	PreparedStatement pStmt = null;
    	try {
			pStmt = conn.prepareStatement(query);
			
			pStmt.execute();
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
		String query = "select count(*) from bbs;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
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
    
	public BbsDTO selectOne(int id) {
		LOG.debug("");
		String query = "select * from bbs where id=?;";
		PreparedStatement pStmt = null;
		BbsDTO bDto = new BbsDTO();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {				
				bDto.setId(rs.getInt(1));
				bDto.setMemberId(rs.getInt(2));
				bDto.setTitle(rs.getString(3));
				bDto.setDate(rs.getString(4));
				bDto.setContent(rs.getString(5));
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
		return bDto;
	}
	
	public void updateBbs(BbsDTO bDto) {
		LOG.debug("");
		PreparedStatement pStmt = null;
		//String date = getCurrentDBTime();
		String query = "update bbs set title=?, date=now(), content=? where id=?;";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, bDto.getTitle());
			//pStmt.setString(2, date);
			pStmt.setString(2, bDto.getContent());
			pStmt.setInt(3, bDto.getId());
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
	
	public void insertBbs(BbsDTO bDto) {
		LOG.debug("");
		PreparedStatement pStmt = null;
		String query = "insert into bbs (memberId, title, content) values(?, ?, ?);";
		pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, bDto.getMemberId());
			pStmt.setString(2, bDto.getTitle());
			pStmt.setString(3, bDto.getContent());		
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
	
	public void deleteBbs(int id) {
		LOG.debug("");
		String query = "delete from bbs where id=?;";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
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
	
	public BbsMember ViewData(int id) {
		LOG.debug("");
		String query = "select bbs.id, bbs.title, member.name, bbs.date, bbs.content from bbs " + 
				"inner join member on bbs.memberId=member.id where bbs.id=?;";;
		PreparedStatement pStmt = null;
		BbsMember bmDto = new BbsMember();
		int result = -1;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {				
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4).substring(0, 16));
				bmDto.setContent(rs.getString(5));
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
		return bmDto;
	}
	
	public List<BbsMember> selectJoinAll(int page) {
		LOG.debug("");
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select bbs.id, bbs.title, member.name, bbs.date, bbs.content from bbs " + 
					"inner join member on bbs.memberId=member.id order by bbs.id desc;";
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select bbs.id, bbs.title, member.name, bbs.date, bbs.content from bbs " + 
					"inner join member on bbs.memberId=member.id order by bbs.id desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<BbsMember> bmList = new ArrayList<BbsMember>();
		try {
			pStmt = conn.prepareStatement(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {	
				BbsMember bmDto = new BbsMember();
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4));
				bmDto.setContent(rs.getString(5));
				bmList.add(bmDto);
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
		return bmList;
	}
	
	public String getCurrentDBTime() {
		LOG.debug("");
		String query = "select now();";
		PreparedStatement pStmt = null;
		String ts = null;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			LOG.trace(query);
			while (rs.next()) {
				ts = rs.getString(1);
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
		return ts;
	}
	
	public void close() {
		LOG.debug("");
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception se1) { LOG.trace(se1.getMessage()); }
		LOG.debug("");
	}
}
