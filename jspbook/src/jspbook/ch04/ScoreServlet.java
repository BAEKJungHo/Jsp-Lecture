package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScoreServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int kor, eng, math;
		int sum;
		double avg;
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		kor = Integer.parseInt(request.getParameter("kor"));
		eng = Integer.parseInt(request.getParameter("eng"));
		math = Integer.parseInt(request.getParameter("math"));
		
		Score score = new Score();
		sum = score.sum(kor, eng, math);
		avg = score.avg(kor, eng, math);
		
		out.println("<html>");
		out.println("<head><title>점수 계산</title></head>");
		out.println("<body><center>");
		out.println("<h2>계산 결과</h2>");
		out.println("<hr>");
		out.println("합계 : " + sum);
		out.println("평균 : " + avg);
		out.println("</body></html>");
	}

}
