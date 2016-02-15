/**
 * 
 */
package com.betterit.kaligia;

/**
 * @author V135012
 *
 */

import com.oceanoptics.omnidriver.api.wrapper.Wrapper;
import com.oceanoptics.omnidriver.features.buffer.DataBuffer;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class SpectraAcquisitionMaya {
	
	
	// spectrometer parameter
	double integrationTime; // in second
	int restTime;
	int scanToAverage;
	int darkCurrentCorrectFlag;
	int nonlinearityCorrectFlag;
	int boxcarWidth;
	int spectrometerIndex;
	int acquisitionMode;
	Wrapper wrapper;
	DataBuffer bufferCtrl;

	double[] spectrum;
	double[] wavelength;

	SpectraAcquisitionMaya(int i, double j, int t, int k, int l, int m, int n, int o, Wrapper p) {
		acquisitionMode = i;
		integrationTime = j;
		restTime = t;
		scanToAverage = k;
		darkCurrentCorrectFlag = l;
		nonlinearityCorrectFlag = m;
		boxcarWidth = n;
		spectrometerIndex = o;
		wrapper = p;
		
	}

	public void setParameters() {
		// wrapper.setExternalTriggerMode(spectrometerIndex, 0);
		wrapper.setExternalTriggerMode(spectrometerIndex,acquisitionMode);
		wrapper.setIntegrationTime(spectrometerIndex, (int)(integrationTime * 1000000));
		wrapper.setScansToAverage(spectrometerIndex, scanToAverage);
		wrapper.setCorrectForElectricalDark(spectrometerIndex, darkCurrentCorrectFlag);
		wrapper.setCorrectForDetectorNonlinearity(spectrometerIndex, nonlinearityCorrectFlag);
		wrapper.setBoxcarWidth(spectrometerIndex, boxcarWidth);
	}


	public void getSpectra() {

		spectrum = wrapper.getSpectrum(spectrometerIndex);
		wavelength = wrapper.getWavelengths(spectrometerIndex);
		
		try {
			TimeUnit.SECONDS.sleep(restTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public double[] returnSpectra() {

		return spectrum;
	}

	public double[] returnWavelength() {

		return wavelength;
	}

}
