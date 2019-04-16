package jspbook.ch03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MemberServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name, email, phone;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		name = request.getParameter("name");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		
		MemberBean member = new MemberBean();
		
		member.setEmail(email); member.setName(name); member.setPhone(phone);
		
		out.println("<html>");
		out.println("<head><title>회원 ID 발급</title></head>");
		out.println("<body><center>");
		out.println("<h2>ID</h2>");
		out.println("<hr>");
		out.println("회원 가입을 축하 드립니다!!");
		out.println("<br>");
		out.println("Name : " + name + "Email : " + email + "Phone : " + phone);
		out.println("<br>");
		out.println("<hr>");
		out.println("회원의 ID는 다음과 같습니다 : " + member.getId());
		out.println("</body></html>");
	}

}
