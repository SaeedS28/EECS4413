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

@WebServlet(urlPatterns={"/Start","/Startup","/Startup/*","/Start/*"})
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletprponse
	 *      prponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/form.jspx").forward(request, response);
		if(request.getParameter("p")==null) {
			System.out.println("No p detected");
		}
		else {
			System.out.println("p detected as: "+request.getParameter("p"));
		}
		// prponse.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletprponse
	 *      prponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String buttonPprsed = request.getParameter("Report");
		String genXML = request.getParameter("GenerateXML");
		String name;
		String creditsTaken;
		PrintWriter pr = response.getWriter();

		try {
			mod = new SIS();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (buttonPprsed != null) {
			name = request.getParameter("name");
			creditsTaken = request.getParameter("credits");
			System.out.println("Name: " + name + " credits: " + creditsTaken);

			try {
				Map<String, StudentBean> sb = mod.retriveStudent(name, creditsTaken);

				if (sb.size() == 0) {
					pr.println("No record exists, try again");
				} else {
					Iterator<StudentBean> iter = sb.values().iterator();
					pr.println("<table border='1'>");
					pr.println("<tr>");
					pr.println("<th>Id</th>");
					pr.println("<th>Name</th>");
					pr.println("<th>Credits taken</th>");
					pr.println("<th>Credits to graduate</th>");
					pr.println("<th>Credits end of term</th>");
					pr.println("</tr>");

					while (iter.hasNext()) {
						StudentBean student = iter.next();
						pr.println("<tr>");
						pr.print(String.format("<td>%s</td>", student.getSid()));
						pr.print(String.format("<td>%s</td>", student.getName()));
						pr.print(String.format("<td>%d</td>", student.getCredit_taken()));
						pr.print(String.format("<td>%d</td>", student.getCredit_graduate()));
						pr.print(String.format("<td>%d</td>", student.getCredit_taken() + student.getCredit_taking()));
						pr.println("</tr>");
						System.out.print(student.getSid() + "\t");
						System.out.print(student.getName() + "\t");
						System.out.print(student.getCredit_taken() + "\t");
						System.out.print(student.getCredit_graduate() + "\t");
						System.out.println(student.getCredit_taken() + student.getCredit_taking());
					}
					pr.println("</table>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (genXML != null) {
			// pr.println("You pressed the Generate XML button");
			name = request.getParameter("name");
			creditsTaken = request.getParameter("credits");
			String f = "export/" + request.getSession().getId() + ".xml";
			System.out.println(f);
			String filename = this.getServletContext().getRealPath("/" + f);
			System.out.println(filename);
			request.setAttribute("link", f);
			request.setAttribute("anchor", filename);

			try {
				mod.export(name, creditsTaken, filename);
				request.getRequestDispatcher("/Done.jspx").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
