import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.fitting.*;
import org.apache.commons.math3.stat.*;
//import org.postgresql.*;

public class FLremovalMain {
	public static void main(String[] args) {
		//////////////////////////
		//The values in following 4 lines should be user input
		int startPos = 200;
		int endPos = 1000;
		int totalNumPixel = 1044;
		double threshhold = 0.0001;
		/////////////////////////
		int numPixel = endPos - startPos;
		double wavelength[] = new double[totalNumPixel];
		double photonCount[] = new double[totalNumPixel];
		double SpecNoBk[] = new double[numPixel];
		double ThisSpectrum[] = new double[numPixel];
		double ThisXaxis[] = new double[numPixel];
		double Po[] = new double[numPixel];
		double Re[] = new double[numPixel];
		double P[] = new double[6];
		double Re2[] = new double[numPixel - 1];
		double mySUM[] = new double[numPixel];
		int ind[];
		double DEV;
		double prevDEV;
		Connection connection = null;
		Statement stmt = null;
		String pattern = "##.##";

		
  try{
   Scanner in = new Scanner(new FileReader(args[0]));
   int i = 0;
   while(in.hasNextDouble()){
    wavelength[i] = in.nextDouble();
    photonCount[i] = in.nextDouble();
    ++i;
   }
  } catch(FileNotFoundException e){
   e.printStackTrace();
  }
  
  
		ThisSpectrum = Arrays.copyOfRange(photonCount, startPos, endPos);
		ThisXaxis = Arrays.copyOfRange(wavelength, startPos, endPos);
		final WeightedObservedPoints obs = new WeightedObservedPoints();

		for (int i = 0; i < numPixel; i++) {
			obs.add(ThisXaxis[i], ThisSpectrum[i]);
		}

		final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(5);
		P = fitter.fit(obs.toList());
		Polyval pVal = new Polyval(P, ThisXaxis, numPixel);
		Po = pVal.evl();

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
			final PolynomialCurveFitter fitter1 = PolynomialCurveFitter.create(5);
			P = fitter1.fit(obs1.toList());
			Polyval pVal1 = new Polyval(P, ThisXaxisKeep, indKeepLength);
			PoKeep = pVal1.evl();

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
		Polyval pVal2 = new Polyval(P, ThisXaxis, numPixel);
		double FLbk[] = pVal2.evl();
		for (int i = 0; i < ThisXaxis.length; i++) {
			SpecNoBk[i] = ThisSpectrum[i] - FLbk[i];
		}
		
		//the write-to-file part is only for testing purpose, ThisXaxis and SpecNoBk are two outputs
		try {
			FileWriter fr = new FileWriter(args[1]);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter out = new PrintWriter(br);
			DecimalFormat df = new DecimalFormat(pattern);
			for (int j = 0; j < ThisXaxis.length; j++) {
				if (Double.toString(wavelength[j]) != null)
					out.write(ThisXaxis[j] + "\t" + SpecNoBk[j]);
				out.write("\r\n");
			}
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}
