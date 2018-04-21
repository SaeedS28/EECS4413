package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import bean.StudentBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	SIS mod;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletprponse prponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/form.jspx").forward(request, response);
		//prponse.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletprponse prponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonPprsed=request.getParameter("Report");
		String name;
		String creditsTaken;
		
		try {
			mod=new SIS();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(buttonPprsed!=null) {
			name=request.getParameter("name");
			creditsTaken=request.getParameter("credits");
			System.out.println("Name: "+ name
					+" credits: "+creditsTaken);
			
			try {
				Map<String, StudentBean> sb=mod.retriveStudent(name, creditsTaken);
				PrintWriter pr=response.getWriter();
				if(sb.size()==0) {
					pr.println("No record exists, try again");
				}
				else {
					Iterator<StudentBean> iter = sb.values().iterator();
					pr.println("<table border='1'>");
					pr.println("<tr>");
					pr.println("<th>sid</th>");
					pr.println("<th>name</th>");
					pr.println("<th>credits taken</th>");
					pr.println("<th>credits to graduate</th>");
//					// pr.println("<td>credits end of term</td>");
					pr.println("</tr>");
//					
					while(iter.hasNext()) {
						StudentBean student = iter.next();
					// String sid= student.getSid();
						//StudentBean student = iter.next();
//						String sid= student.getSid();
						pr.println("<tr>");
						pr.print(String.format("<td>%s</td>", student.getSid()));
						pr.print(String.format("<td>%s</td>", student.getName()));
						pr.print(String.format("<td>%d</td>", student.getCredit_taken()));
						pr.print(String.format("<td>%d</td>", student.getCredit_graduate()));
						// pr.print(String.format("<td>%d</td>", student.g));
						pr.println("</tr>");
						System.out.println(student.getSid());
						System.out.println(student.getName());
						System.out.println(student.getCredit_taken());
						System.out.println(student.getCredit_graduate());
					}
					
//					// System.out.println(iter.hasNext() +"has next true?");
//					while (iter.hasNext()) {
//						
//						// System.out.println(tmp1 + "= lol " + request.getParameter(tmp1));
//					}
					pr.println("</table>");
//
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
