/**
 * 
 */
package com.betterit.kaligia;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.oceanoptics.omnidriver.api.wrapper.Wrapper;
import com.oceanoptics.omnidriver.features.buffer.DataBuffer;

/**
 * @author Ben
 *
 */
public class SpectraAcquisition {
	
		// spectrometer parameter
		int integrationTime; // in second
		int restTime;
		int scanToAverage;
		int darkCurrentCorrectFlag;
		int nonlinearityCorrectFlag;
		int boxcarWidth;
		int spectrometerIndex;
		Wrapper wrapper;
		DataBuffer bufferCtrl;

		double[] spectrum;
		double[] wavelength;
		

		public SpectraAcquisition(int i, int j, int k, int l, int m, int n, int o, Wrapper p, DataBuffer q) {
			integrationTime = i;
			restTime = j;
			scanToAverage = k;
			darkCurrentCorrectFlag = l;
			nonlinearityCorrectFlag = m;
			boxcarWidth = n;
			spectrometerIndex = o;
			wrapper = p;
			bufferCtrl = q;
		}

		public void setParameters() {
			// wrapper.setExternalTriggerMode(spectrometerIndex, 0);
			wrapper.setIntegrationTime(spectrometerIndex, integrationTime * 1000000);
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
			spectrum = wrapper.getSpectrum(spectrometerIndex); // is it needed
																// everytime?
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
