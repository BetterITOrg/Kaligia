/**
 * 
 */
package com.betterit.kaligia;

import java.util.Arrays;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author V135012
 *
 */
public class FLRemoval {
	

	double wavelength[];
	double photonCount[];
	
	//////////////////////////
	//The values in following 4 lines should be user input
	int startPos;
	int endPos;
	double threshhold;
	int power;
	/////////////////////////
	
	int numPixel;
	double SpecNoBk[];
	double ThisSpectrum[];
	double ThisXaxis[];
	double Po[];
	double Re[];
	double P[];
	double Re2[];
	double mySUM[];
	int ind[];
	double DEV;
	double prevDEV;
	
	/**
	 * @param startPos
	 * @param endPos
	 * @param totalNumPixel
	 * @param threshhold
	 * @param power
	 */
	public FLRemoval(double wavelength[], double photonCount[], int startPos, int endPos, double threshhold, int power) {
		this.wavelength = wavelength;
		this.photonCount = photonCount;
		this.startPos = startPos;
		this.endPos = endPos;
		this.threshhold = threshhold;
		this.power = power;
		this.numPixel = endPos - startPos;
		this.SpecNoBk = new double[numPixel];
		this.ThisSpectrum = new double[numPixel];
		this.ThisXaxis = new double[numPixel];
		this.Po = new double[numPixel];
		this.Re = new double[numPixel];
		this.P = new double[power];
		this.Re2 = new double[numPixel - 1];
		this.mySUM = new double[numPixel];
	}
	
	public void removeFL() {
		
		ThisSpectrum = Arrays.copyOfRange(photonCount, startPos, endPos);
		ThisXaxis = Arrays.copyOfRange(wavelength, startPos, endPos);
		final WeightedObservedPoints obs = new WeightedObservedPoints();

		for (int i = 0; i < numPixel; i++) {
			obs.add(ThisXaxis[i], ThisSpectrum[i]);
		}

		final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(5);
		P = fitter.fit(obs.toList());
		PolyValue pVal = new PolyValue(P, ThisXaxis, numPixel, power);
		Po = pVal.polyEval();

		for (int i = 0; i < numPixel; i++) {
			Re[i] = ThisSpectrum[i] - Po[i];
		}

		for (int i = 0; i < numPixel - 1; i++) {
			Re2[i] = Re[i + 1] - Re[i];
		}

		DEV = Math.sqrt(StatUtils.populationVariance(Re2, 0, Re2.length));
		for (int i = 0; i < numPixel; i++) {
			mySUM[i] = Po[i] + DEV;
		}

		int jj = 0; // jj is the length of points to be removed
		for (int i = 0; i < numPixel; i++) {
			if (ThisSpectrum[i] > mySUM[i]) {
				jj++;
				;
			}
		}
		ind = new int[jj];

		int jjj = 0;
		for (int i = 0; i < numPixel; i++) {
			if (ThisSpectrum[i] > mySUM[i]) {
				ind[jjj] = i;
				jjj++;
			}
		}

		int indKeepLength = numPixel - ind.length;
		int indKeep[] = new int[indKeepLength];
		int k = 0;
		for (int i = 0; i < numPixel; i++) {
			if (!ArrayUtils.contains(ind, i)) {
				indKeep[k] = i;
				k++;
			}
		}
		double ThisSpectrumKeep[] = new double[indKeepLength];
		double ThisXaxisKeep[] = new double[indKeepLength];
		double PoKeep[] = new double[indKeepLength];
		double ReKeep[] = new double[indKeepLength];
		double Re2Keep[] = new double[indKeepLength - 1];
		double mySUMKeep[] = new double[indKeepLength];

		for (int i = 0; i < indKeepLength; i++) {
			ThisSpectrumKeep[i] = ThisSpectrum[indKeep[i]];
			ThisXaxisKeep[i] = ThisXaxis[indKeep[i]];
		}

		prevDEV = DEV;

		// at the point, ThisSpectrum and ThisXaxis should have reduced size
		final WeightedObservedPoints obs1 = new WeightedObservedPoints();

		for (int i = 0; i < indKeepLength; i++) {
			obs1.add(ThisXaxisKeep[i], ThisSpectrumKeep[i]);
		}

		while (true) {
			final PolynomialCurveFitter fitter1 = PolynomialCurveFitter.create(power-1);
			P = fitter1.fit(obs1.toList());
			PolyValue pVal1 = new PolyValue(P, ThisXaxisKeep, indKeepLength, power);
			PoKeep = pVal1.polyEval();

			for (int i = 0; i < indKeepLength; i++) {
				ReKeep[i] = ThisSpectrumKeep[i] - PoKeep[i];
			}

			for (int i = 0; i < indKeepLength - 1; i++) {
				Re2Keep[i] = ReKeep[i + 1] - ReKeep[i];
			}
			
			DEV = Math.sqrt(StatUtils.populationVariance(Re2Keep, 0, Re2Keep.length));

			for (int i = 0; i < indKeepLength; i++) {
				mySUMKeep[i] = PoKeep[i] + DEV;
			}

			for (int i = 0; i < indKeepLength; i++) {
				if (ThisSpectrumKeep[i] > mySUMKeep[i])
					ThisSpectrumKeep[i] = mySUMKeep[i];
			}
			if ((Math.abs(DEV - prevDEV) / DEV) < threshhold)
				break;
			prevDEV = DEV;
			
			obs1.clear();
			for (int i = 0; i < indKeepLength; i++) {
				obs1.add(ThisXaxisKeep[i], ThisSpectrumKeep[i]);
			}

		}
		
		PolyValue pVal2 = new PolyValue(P, ThisXaxis, numPixel, power);
		double FLbk[] = pVal2.polyEval();
		for (int i = 0; i < ThisXaxis.length; i++) {
			SpecNoBk[i] = ThisSpectrum[i] - FLbk[i];
		}
	}

	/**
	 * @return the specNoBk
	 */
	public double[] getSpecNoBk() {
		return SpecNoBk;
	}

	/**
	 * @return the thisXaxis
	 */
	public double[] getThisXaxis() {
		return ThisXaxis;
	}

	/**
	 * @param wavelength the wavelength to set
	 */
	public void setWavelength(double[] wavelength) {
		this.wavelength = wavelength;
	}

	/**
	 * @param photonCount the photonCount to set
	 */
	public void setPhotonCount(double[] photonCount) {
		this.photonCount = photonCount;
	}	

}
