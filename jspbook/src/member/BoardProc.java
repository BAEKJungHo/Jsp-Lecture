package member;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/boardServlet")
public class BoardProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("doGet(): " + uri + ", " + conPath + ", " + command);
	*/	doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("doPost(): " + uri + ", " + conPath + ", " + command);
	*/	doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDAO bDao = new BbsDAO();
		BbsDTO bbs = null;
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher rd = null;
		SimpleDateFormat format1 = null;
		Date time = null;
		String time1 = null;
		int id = 0;
		int memId = 0;
		String message = null;
		String url = null;
		List<BbsDTO> list = bDao.selectAll();
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("action :" + action);
		
		switch(action) {
		case "update":		// 수정 버튼 클릭 시
			//bDao = new BbsDAO();
			// List<BbsDTO> list = bDao.selectAll();
				for(BbsDTO bb : list) {
					memId = bb.getMemberId();
				}
				
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (memId != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 수정 권한이 없습니다.";
				url = "board_list.jsp";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			
			// bDao = new BbsDAO();
			bbs = bDao.selectOne(id);
			bDao.close();
			request.setAttribute("bbs", bbs);
			rd = request.getRequestDispatcher("board_update.jsp");
	        rd.forward(request, response);
	        break;
	        
		case "delete":		// 삭제 버튼 클릭 시
			//bDao = new BbsDAO();
			// List<BbsDTO> list = bDao.selectAll();
				for(BbsDTO bb : list) {
					memId = bb.getMemberId();
				}
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (memId != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 삭제 권한이 없습니다.";
				url = "board_list.jsp";
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			//bDao = new BbsDAO();
			bDao.deleteBbs(id);
			bDao.close();
			
			message = "id = " + id + " 가 작성한 내용이 삭제되었습니다.";
			url = "board_list.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		case "write":		// write 할 때
			id = (Integer)session.getAttribute("memberId");
			System.out.print("id : " +  id);
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			// 날짜 변환
			format1 = new SimpleDateFormat ("yy-MM-dd HH:mm");
			time = new Date();
			time1 = format1.format(time);
		
			// 글쓰기
			//bDao = new BbsDAO();
			bDao.insertBbs(new BbsDTO(id, title, time1, contents));
			
			message = "게시물 작성이 완료되었습니다.";
			url = "board_list.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
		
		case "execute":			// 게시물 수정정보 입력 후 실행할 때
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			title = request.getParameter("title");
			contents = request.getParameter("contents");
			
			format1 = new SimpleDateFormat ("yy-MM-dd HH:mm");
			time = new Date();
			time1 = format1.format(time).substring(0, 16);
			
			bbs = bDao.selectOne(id);
			
			bbs.setTitle(title);
			bbs.setContent(contents);
			bbs.setDate(time1);
			// System.out.println(bbs.toString());
			
			bDao = new BbsDAO();
			bDao.updateBbs(bbs);
			bDao.close();
			
			message = "다음과 같이 수정하였습니다.\\n" + bbs.toStrOne();
			request.setAttribute("message", message);
			request.setAttribute("url", "board_list.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
			break;
		
		default:
		}
	}
}