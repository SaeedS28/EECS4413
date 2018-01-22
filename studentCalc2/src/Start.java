
import java.io.IOException;
import java.io.Writer;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns={"/Start","/Start/*", "/StartUp","/StartUp/*"})

public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int counter1,counter2=0;
	private Double oldPrin=null;
	private Double oldPeriod=null;
	
	private static final String PRINCIPAL = "principal";
    private static final String INTEREST_RATE = "interest";
//    private static final String PERIOD_FORM = "period";
    private static final String  TOTAL_VALUE = "totalvalue";
    private Double totalPrincipal;
	String startPage="/UI.jspx";
	String resultPage="/Result.jspx";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Writer resOut = response.getWriter();//this is fine

		//double principal=Double.parseDouble(this.getServletContext().getInitParameter("principle"));
		String principle=request.getParameter("principal");
		String period=request.getParameter("period");
		String rate=request.getParameter("interest");
	//	resOut.write("Principle: " + principle+"\tPeriod: "+period+"\tRate: "+rate+"\n");
		double ratee;
		double totalInterest;
		double graceInterest=0;
		
		if (request.getParameter("Submit")==null) {
		   request.getRequestDispatcher(startPage).forward(request,response);//here he uses request???
		  
		}
		else{
			if(request.getParameter("gracePeriod")==null){
//				resOut.write("The checkbox is not pressed! FUCK!!!");
				
				double prin;
				double per;
				
				if (principle != null) {
					prin = Double.parseDouble(principle);
					oldPrin = prin;
					counter1 = 1;
				} else if (counter1 == 0) {
					prin = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
					oldPrin = prin;
					// counter1=0;
				} else {
					prin = oldPrin;
				}
				if (period != null) {
					per = Double.parseDouble(period);
					oldPeriod = per;
					counter2 = 1;
				} else if (counter2 == 0) {
					per = Double.parseDouble(this.getServletContext().getInitParameter("period"));
					oldPeriod = per;
				} else {
					per = oldPeriod;
				}

				resOut.write("Period: " + per + "<html>&emsp;</html>");
				if (rate != null) {
					ratee = Double.parseDouble(rate);
				} else {
					ratee = Double.parseDouble(this.getServletContext().getInitParameter("interest"));
				}
				 totalInterest = ratee
						+ Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));

				totalPrincipal = ((0.01 * totalInterest) / 12) * prin
						/ (1 - Math.pow(1 + ((0.01 * totalInterest) / 12), (-1) * per));

			}
			else{
	//			resOut.write("The checkbox is pressed! FUCK!!!");
				double prin;
				double per;
				if (principle != null) {
					prin = Double.parseDouble(principle);
					oldPrin = prin;
					counter1 = 1;
				} else if (counter1 == 0) {
					prin = Double.parseDouble(this.getServletContext().getInitParameter("principal"));
					oldPrin = prin;
					// counter1=0;
				} else {
					prin = oldPrin;
				}
				if (period != null) {
					per = Double.parseDouble(period);
					oldPeriod = per;
					counter2 = 1;
				} else if (counter2 == 0) {
					per = Double.parseDouble(this.getServletContext().getInitParameter("period"));
					oldPeriod = per;
				} else {
					per = oldPeriod;
				}
				
				double gracePeriod = Double.parseDouble(this.getServletContext().getInitParameter("gracePeriod"));
				
				resOut.write("Period: " + per + "<html>&emsp;</html>");
				if (rate != null) {
					ratee = Double.parseDouble(rate);
				} else {
					ratee = Double.parseDouble(this.getServletContext().getInitParameter("interest"));
				}
				totalInterest = ratee
						+ Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
				
				
				double monthlyPayments = ((0.01 * totalInterest) / 12) * prin
						/ (1 - Math.pow(1 + ((0.01 * totalInterest) / 12), (-1) * per));

				// df.setRoundingMode(RoundingMode.CEILING);

				graceInterest =prin+((totalInterest)/12)*gracePeriod;
				
				totalPrincipal= monthlyPayments+(graceInterest/gracePeriod);
			}
			DecimalFormat df = new DecimalFormat("#.####");
			df.setMaximumFractionDigits(2);
			request.setAttribute(PRINCIPAL,df.format(totalPrincipal));
			request.setAttribute(INTEREST_RATE,df.format(graceInterest));
			request.getRequestDispatcher(resultPage).forward(request,response);
		//	request.getRequestDispatcher(resultPage).forward(request,response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}