package jspbook.ch03;

public class CalcBean {
	private int num1, num2;
	private String operator="";
	private int result;
	
	public void calculate() {
		switch(operator) {
		case "+":
			result = num1 + num2; break;
		case "-":
			result = num1 - num2; break;
		case "*":
			result = num1 * num2; break;
		case "/":
			result = num1 / num2; break;
		}
	}

	// 사용자가 입력한 JSP의 form에서 입력받아온다.
	// 따라서 매개변수는 String 타입 이어야 한다.
	// 그리고 int로 형변환 해주어야 한다.
	public void setNum1(String num1) {
		this.num1 = Integer.parseInt(num1);
	}
	
	public void setNm2(String num2) {
		this.num2 = Integer.parseInt(num2);
	}
	
	public void setOperator(String operator) {
		System.err.println("## " + operator);
		this.operator = operator;
	}
	
	public int getResult() {
		return result;
	}
}
