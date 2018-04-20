package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    public void init(){
    	try {
			mod=new SIS();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/form.jspx").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buttonPressed=request.getParameter("Report");
		String name;
		String creditsTaken;
		
		if(buttonPressed!=null) {
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
