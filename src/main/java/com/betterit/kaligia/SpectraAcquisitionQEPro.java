package com.betterit.kaligia;

import com.oceanoptics.omnidriver.api.wrapper.Wrapper;
import com.oceanoptics.omnidriver.features.buffer.DataBuffer;
import java.io.*;

public class SpectraAcquisitionQEPro {

	// spectrometer parameter
	double integrationTime; // in second
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

	SpectraAcquisitionQEPro(int i, double j, int k, int l, int m, int n, int o, Wrapper p, DataBuffer q) {
		acquisitionMode = i;
		integrationTime = j;
		scanToAverage = k;
		darkCurrentCorrectFlag = l;
		nonlinearityCorrectFlag = m;
		boxcarWidth = n;
		spectrometerIndex = o;
		wrapper = p;
		bufferCtrl = q;
	}

	public void setParameters() {
		
		wrapper.setExternalTriggerMode(spectrometerIndex,acquisitionMode);
		wrapper.setIntegrationTime(spectrometerIndex, (int)(integrationTime * 1000000));
		wrapper.setScansToAverage(spectrometerIndex, scanToAverage);
		wrapper.setCorrectForElectricalDark(spectrometerIndex, darkCurrentCorrectFlag);
		wrapper.setCorrectForDetectorNonlinearity(spectrometerIndex, nonlinearityCorrectFlag);
		wrapper.setBoxcarWidth(spectrometerIndex, boxcarWidth);
	}

	public void setBuffer() {
		try {
			bufferCtrl.setBufferCapacity(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getSpectra() {

		try {
			bufferCtrl.abortAcquisition();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferCtrl.clearBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferCtrl.startAcquisition();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		spectrum = wrapper.getSpectrum(spectrometerIndex);
		wavelength = wrapper.getWavelengths(spectrometerIndex);


	}

	public double[] returnSpectra() {

		return spectrum;
	}

	public double[] returnWavelength() {

		return wavelength;
	}

}
