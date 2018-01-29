package ctrl;

import java.io.IOException;
import java.io.Writer;
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
	private int counter1,counter2=0;
	private Double oldPrin=null;
	private Double oldPeriod=null;
	
	private static final String PRINCIPAL = "principal";
    private static final String INTEREST= "interest";
    private static final String PERIOD = "period";
    private static final String GRACE_INTEREST = "graceInterest";
    private static final String MONTHLY_PAYMENT = "monthlyPayment";
    private static final String ERRROR_MESSAGE = "errorMessage";
    
    private String errorMessage = "";
    private boolean errorOccured=false;
    private boolean firstTime=true;
    private Double totalPrincipal;
    private double graceInterest;
	
    String startPage="/UI.jspx";
	String resultPage="/Result.jspx";
    
	private Loan loan;
	private double principal;
	private double interest;
	private double period;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		loan = new Loan();
	}
    
    private void displayError() {
    	this.getServletContext().setAttribute(ERRROR_MESSAGE, errorMessage);
    }
    
    private void updateValues(HttpServletRequest request) {
    	
		
    	this.getServletContext().setAttribute(INTEREST, interest);
		this.getServletContext().setAttribute(PERIOD, period);
		this.getServletContext().setAttribute(PRINCIPAL, principal);
		this.getServletContext().setAttribute(MONTHLY_PAYMENT, totalPrincipal);
		this.getServletContext().setAttribute(GRACE_INTEREST, graceInterest);
    }
    
    private boolean graceCheckedOff(HttpServletRequest request) {
    	String g= request.getParameter("gracePeriod");
    	boolean graceCheck;
    	
    	if(g==null) {
    		graceCheck=false;
    	} else {
    		graceCheck=true;
    	}
    	return graceCheck;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		paymentCalc(request, response);
		updateValues(request);
		checkDispatch(request, response);
		firstTime=true;
	}
//			request.setAttribute(PRINCIPAL,df.format(totalPrincipal));
//			request.setAttribute(INTEREST,df.format(graceInterest));
//			request.getRequestDispatcher(resultPage).forward(request,response);
//		//	request.getRequestDispatcher(resultPage).forward(request,response);
	
	private void paymentCalc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String principalInput = request.getParameter("principal");
		String periodInput = request.getParameter("period");
		String interestInput = request.getParameter("interest");		

		double totalPrincipalVal = 0.0;
		double totalInterest = 0.0;
		double fixedInterest = 0.0;
		int n=0;
		if(!checkInput(principalInput, interestInput, periodInput)) {
			errorOccured=false;
			principal = Double.parseDouble(principalInput);
			period = Double.parseDouble(periodInput);
			interest= Double.parseDouble(interestInput);
			
			double  fixedInterest1 = Double.parseDouble(this.getServletContext().getInitParameter("fixedInterest"));
			double gracePeriod = Double.parseDouble(this.getServletContext().getInitParameter("gracePeriod"));

			try{
				graceInterest = loan.computeGraceInterest(principal, gracePeriod, interest, fixedInterest1, graceCheckedOff(request), period);
				totalPrincipal = loan.computePayment(principal, period, interest, fixedInterest1, graceCheckedOff(request), graceInterest, gracePeriod);
			}
			catch(Exception e){
				errorOccured= true;
				//
				errorMessage= "";
				if(principal<0) {
					errorMessage=errorMessage + "Principal value cannot be negative. \n";
				}
				if(period<0) {
					errorMessage= errorMessage+ "Period value cannot be negative. \n";
				}
				if(interest<0) {
					errorMessage= errorMessage+ "Interest value cannot be negative.\n ";
				}
				
				displayError();
				//errorOccured=false;
			}
		}
		else if(!firstTime) {
			errorMessage = "What the fuck";
			errorOccured = true;
			displayError();
		}
		
	}
		
	
	private boolean checkInput(String dPrincipal, String dInterest, String dPeriod) {
		if (dPrincipal == null || dInterest == null || dPeriod == null) {
			return true;
		} else {
			try {
				Double.parseDouble(dPrincipal);
				Double.parseDouble(dPeriod);
				Double.parseDouble(dInterest);
			} catch (Exception e) {
				return true;
			}
		}
		return false;
	}
	
	private void checkDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String target = startPage;
		String submitParameter = request.getParameter("Submit");
		String restartParameter = request.getParameter("restart");

		if (restartParameter != null && restartParameter.equals("Recompute")) {
			firstTime = true;
			errorMessage = "";
			displayError();
		} else if (submitParameter != null && submitParameter.equals("submit")) {
			if (!errorOccured) {
				target = resultPage;
			}
		}
		try {
			request.getRequestDispatcher(target).forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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