package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher dispatcher = null;
		String action = request.getParameter("action");
		String strId = request.getParameter("id");
		System.out.println(action + ", " + strId);
		switch(action) {
		case "update":
			mDao = new MemberDAO();
			member = mDao.selectOne(Integer.parseInt(strId));
			mDao.close();
			request.setAttribute("member", member);
			dispatcher = request.getRequestDispatcher("update.jsp");
			dispatcher.forward(request, response);
			break;
		case "delete":
			mDao = new MemberDAO();
			mDao.deleteMember(Integer.parseInt(strId));
			mDao.close();
			String message = "id = " + Integer.parseInt(strId) + "가 삭제되었습니다.";
			String url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			dispatcher = request.getRequestDispatcher("alertMsg.jsp");
			dispatcher.forward(request, response);
			// response.sendRedirect("loginMain.jsp");
			break;
		default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
