package ctrl;


import java.io.IOException;
//import java.io.Writer;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Loan;
/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns={"/Start","/Start/*", "/StartUp","/StartUp/*"})

public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Loan loan;
	
	private static final String PRINCIPAL = "principal";
    private static final String INTEREST = "interest";
    private static final String PERIOD = "period";
    
    private static final String GRACE= "graceInterest";
	private static final String MP = "monthlyPayment";
	private static final String ERROR = "errorMessage";

	private boolean error=false;
	private String errorMessage="";
	private boolean ft=true;
	
    private String principal;
	private String period;
	private String rate;
    
//    private Double totalPrincipal;
	private String startPage="/UI.jspx";
	private String resultPage="/Result.jspx";
    
	private double monthlyPayment=0;
	private double graceInterest=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	loan= new Loan();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		calculateValues(request);
		setAllAttributes(request);
		checkDispatch(request, response);
		ft=true;
		
	}

	private void calculateValues(HttpServletRequest request) {
		String localPrincipal = request.getParameter("principal");
		String localPeriod = request.getParameter("period");
		String localInterest = request.getParameter("interest");
		
		error=correctInputType(localPrincipal, localPeriod, localInterest);
	
		principal = localPrincipal;
		period= localPeriod;
		rate=localInterest;
		
		if(!error) {
			double dPrincipal= Double.parseDouble(localPrincipal);
			double dInterest= Double.parseDouble(localInterest);
			double dPeriod= Double.parseDouble(localPeriod);
			double fixedInterest=Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
			double gracePeriod = Double.parseDouble(this.getServletContext().getInitParameter("gracePeriod"));
		
			try {
				graceInterest =loan.computeGraceInterest(dPrincipal, gracePeriod, dInterest, fixedInterest, graceCheckedOff(request));
				monthlyPayment = loan.computePayment(dPrincipal, dPeriod, dInterest, graceInterest, gracePeriod, fixedInterest, graceCheckedOff(request));
			}catch(Exception e) {
				errorMessage="you fucked up the parameters";
				error=true;
				setErrorMessage();
			}
		} else {
			if(!ft) {
				errorMessage="Are you suffering from amnesia?"
						+ " Because you have already done this!!";
				setErrorMessage();
			}
		}
	}
	
	private void setErrorMessage() {
		this.getServletContext().setAttribute(ERROR, errorMessage);
	}
	
	private boolean graceCheckedOff(HttpServletRequest request) {
		String graceVal = request.getParameter("gracePeriod");
		boolean grace = false;

		if (graceVal == null) {
			grace = false;
		} else {
			grace = true;
		}
		return grace;
	}

	private void checkDispatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String submit = request.getParameter("Submit");
		String restart = request.getParameter("restart");
		
		String redirect=startPage;
		
		if(restart!=null && restart.equals("Recompute")) {
			ft=true;
			errorMessage="";
			setErrorMessage();
		} else if (submit != null && submit.equals("submit")) {
			if(!error) {
				redirect=resultPage;
			}
		} 
		request.getRequestDispatcher(redirect).forward(request, response);
	}
	
	private void setAllAttributes(HttpServletRequest request) {
		this.getServletContext().setAttribute(MP, monthlyPayment);
		this.getServletContext().setAttribute(GRACE, graceInterest);
		
		this.getServletContext().setAttribute(PRINCIPAL, principal);
		this.getServletContext().setAttribute(INTEREST, rate);
		this.getServletContext().setAttribute(PERIOD, period);
	}
	
	private boolean correctInputType(String localPrincipal, String localPeriod, String localInterest) {
		if (localPrincipal == null || localInterest == null || localPeriod == null) {
			return true;
		} else {
			try {
				Double.parseDouble(localPrincipal);
				Double.parseDouble(localPeriod);
				Double.parseDouble(localInterest);
			} catch (Exception e) {
				return true;
			}
		return false;
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