/**
 * 
 */
package com.betterit.kaligia;

/**
 * @author V135012
 *
 */
public class PolyValue {
	
	double coef[];
	double xaxis[];
	double fitData[];
	int power;

	/**
	 * @param coef
	 * @param xaxis
	 * @param fitData
	 */
	public PolyValue(double[] coef, double[] xaxis, int size, int power) {
		this.coef = coef;
		this.xaxis = xaxis;
		this.fitData = new double[size];
		this.power = power;
	}

	/**
	 * @return the coef
	 */
	public double[] getCoef() {
		return coef;
	}

	/**
	 * @param coef the coef to set
	 */
	public void setCoef(double[] coef) {
		this.coef = coef;
	}

	/**
	 * @return the xaxis
	 */
	public double[] getXaxis() {
		return xaxis;
	}

	/**
	 * @param xaxis the xaxis to set
	 */
	public void setXaxis(double[] xaxis) {
		this.xaxis = xaxis;
	}

	/**
	 * @return the fitData
	 */
	public double[] getFitData() {
		return fitData;
	}

	/**
	 * @param fitData the fitData to set
	 */
	public void setFitData(double[] fitData) {
		this.fitData = fitData;
	}
	
	public double[] polyEval() {
		for(int i = 0; i < xaxis.length; i++) {
			fitData[i] = 0.0;
			for(int j=0; j<power; j++) {
				if(j==0) {
					fitData[i] += coef[j];
				} else {
					fitData[i] += coef[j]*Math.pow(xaxis[i], j);
				}
			}
		}
		return fitData;
	}
}
