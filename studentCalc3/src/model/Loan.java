package model;

public class Loan {
	
	public double computePayment(double principal,double period, double interest, double fixedInterest, boolean grace, 
			double graceInterest,double gracePeriod) throws Exception{
		
		double monthlyPayments;
		double totalInterest = interest+ fixedInterest;
		monthlyPayments = ((0.01 * totalInterest) / 12) * principal	/ (1 - Math.pow(1 + ((0.01 * totalInterest) / 12), (-1) * period)); 
		
		if(grace) {
			monthlyPayments = monthlyPayments + (graceInterest/gracePeriod); 
		}
		return monthlyPayments;
	}
	
	public static double computeGraceInterest(double principal, double gracePeriod, double interest,
			double fixedInterest, boolean grace) throws Exception {
		double graceInterest;
		double totalInterest = interest+fixedInterest;
		if(grace) {
			graceInterest=principal*((totalInterest*0.01)/12)*gracePeriod;
		} else {
			graceInterest=0;
		}
		
		if (principal < 0 || gracePeriod < 0 || interest < 0) {
			throw new Exception();

		}
		return graceInterest;
	}

}
