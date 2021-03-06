package member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class MemberProc
 */
@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(MemberDAO.class);
       
    public MemberProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("");
/*		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("doGet(): " + uri + ", " + conPath + ", " + command);
*/		doAction(request, response);
		LOG.debug("");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("");
/*		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("doPost(): " + uri + ", " + conPath + ", " + command);
*/		doAction(request, response);
		LOG.debug("");
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("");
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher rd = null;
		int id = 0;
		String password = null;
		String name = null;
		String birthday = null;
		String address = null;
		String message = null;
		String url = null;
		int curPage = 1;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		List<String> pageList = new ArrayList<String>();
		
		switch(action) {
		case "list": LOG.debug("");
			if (!request.getParameter("page").equals("")) {
				curPage = Integer.parseInt(request.getParameter("page"));
				LOG.trace("");
			}
			mDao = new MemberDAO();
			int count = mDao.getCount();
			if (count == 0)			// 데이터가 없을 때 대비
				count = 1;
			int pageNo = (int)Math.ceil(count/10.0);
			if (curPage > pageNo)	// 경계선에 걸렸을 때 대비
				curPage--;
			session.setAttribute("currentMemberPage", curPage);
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			String page = null;
			page = "<a href=#>&laquo;</a>&nbsp;";
			pageList.add(page);
			for (int i=1; i<=pageNo; i++) {
				page = "&nbsp;<a href=memberProcServlet?action=list&page=" + i + ">" + i + "</a>&nbsp;";
				pageList.add(page);
				LOG.trace("");
			}
			page = "&nbsp;<a href=#>&raquo;</a>";
			pageList.add(page);
			
			List<MemberDTO> mList = mDao.selectJoinAll(curPage);
			request.setAttribute("memberList", mList);
			request.setAttribute("memberPageList", pageList);
			rd = request.getRequestDispatcher("loginMain.jsp");
	        rd.forward(request, response);
	        LOG.trace("");
			break;
		
		case "update": LOG.debug("");		// 수정 버튼 클릭 시
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
				LOG.trace("");
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 수정 권한이 없습니다.";
				url = "memberProcServlet?action=list&page="+curPage;
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				LOG.trace(url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			mDao = new MemberDAO();
			member = mDao.searchById(id);
			mDao.close();
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
	        rd.forward(request, response);
	        LOG.trace("");
	        break;
	        
		case "delete": LOG.debug("");		// 삭제 버튼 클릭 시
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
				LOG.trace("");
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 삭제 권한이 없습니다.";
				url = "memberProcServlet?action=list&page="+curPage;
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				LOG.trace(url);
				break;
			}
			mDao = new MemberDAO();
			mDao.deleteMember(id);
			mDao.close();
			//response.sendRedirect("loginMain.jsp");
			message = "id = " + id + " 이/가 삭제되었습니다.";
			url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			LOG.trace(url);
			
		case "login": LOG.debug("");		// login 할 때
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			password = request.getParameter("password");
			mDao = new MemberDAO();
			int result = mDao.verifyIdPassword(id, password);
			String errorMessage = null;
			switch (result) {
			case MemberDAO.ID_PASSWORD_MATCH:
				break;
			case MemberDAO.ID_DOES_NOT_EXIST:
				errorMessage = "ID가 없음"; break;
			case MemberDAO.PASSWORD_IS_WRONG:
				errorMessage = "패스워드가 틀렸음"; break;
			case MemberDAO.DATABASE_ERROR:
				errorMessage = "DB 오류";
			}
			
			if (result == MemberDAO.ID_PASSWORD_MATCH) {
				member = mDao.searchById(id);
				session.setAttribute("memberId", id);
				session.setAttribute("memberName", member.getName());
				System.out.println("세션 ID: " + (Integer)session.getAttribute("memberId"));
				response.sendRedirect("memberProcServlet?action=list&page=1");
				LOG.trace("");
			} else {
				request.setAttribute("message", errorMessage);
				request.setAttribute("url", "login.jsp");
				rd = request.getRequestDispatcher("alertMsg.jsp");
		        rd.forward(request, response);
		        LOG.trace("");
			}
			mDao.close();
			break;
		
		case "logout": LOG.debug("");			// 로그아웃할 때
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			response.sendRedirect("login.jsp");
			LOG.trace("");
			break;
			
		case "register": LOG.debug("");		// 회원 등록할 때
			password = request.getParameter("password");
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			member = new MemberDTO(password, name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.insertMember(member);
			member = mDao.recentId();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberName", name);
			
			message = "귀하의 아이디는 " + member.getId() + " 입니다.";
			url = "memberProcServlet?action=list&page="+curPage;
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			LOG.trace("");
			mDao.close();
			break;
			
		case "execute": LOG.debug("");			// 회원 수정정보 입력 후 실행할 때
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			
			member = new MemberDTO(id, "*", name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.updateMember(member);
			mDao.close();
			
			message = "다음과 같이 수정하였습니다.\\n" + member.toString();
			url = "memberProcServlet?action=list&page="+curPage;
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
	        LOG.trace("");
			//response.sendRedirect("loginMain.jsp");
			break;
			
		default:
		}
		LOG.debug("");
	}
}