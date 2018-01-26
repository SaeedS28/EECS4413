package model;

public class Loan {
	
	public double computePayment(double principal, double period, double interest, double graceInterest, double gracePeriod, double fixedInterest, boolean grace) throws Exception{
		
		double totalInterest= interest+fixedInterest;
		double mp;
		double base = ((0.01 * totalInterest) / 12) * principal
				/ (1 - Math.pow(1 + ((0.01 * totalInterest) / 12), (-1) * period));
		
		if(grace) {
			mp=base+(graceInterest/gracePeriod);
			return mp;
		}
		mp=base;
		return mp;
	}
	
	public double computeGraceInterest(double principal, double gracePeriod, double interest, double fixedInterest, boolean grace) throws Exception {
		
		double graceInterestCalc;
		double totalInterest = interest+fixedInterest;
		
		if (grace) {
			graceInterestCalc = principal*((totalInterest*0.01)/12)*gracePeriod;
		} else {
			graceInterestCalc = 0;
		}

		return graceInterestCalc;
	}
}
