package com.betterit.kaligia;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.labjack.LJUD;
import com.oceanoptics.omnidriver.api.wrapper.Wrapper;
import com.oceanoptics.omnidriver.features.buffer.DataBuffer;
import com.sun.jna.ptr.IntByReference;

/**
 * 
 */

/**
 * @author Kaide Johar
 *
 */
public class TestRun {
	
	private static final Logger log = LoggerFactory.getLogger(TestRun.class);
	
	private Integer seg_run_id;
	private Integer integrationTime;
	private Integer restTime;
	private Integer scanToAverage;
	private Integer darkCurrentCorrectFlag;
	private Integer nonlinearityCorrectFlag;
	private Integer boxcarWidth;
	private Integer spectrometerIndex;
	private double[] spectra;
	private double[] wavelength;
	private String spectrometerType;			//QEPro or MAYA
	private String labjackType = "U6";			//labjack type
	private String status;
	private int acquisitionM = 0;
	
	// laser output power
	private double laserPowV = 0.8; // must be in [0 1.2]V

	// lab jack parameters
	private int intHandle = 0;
	private int portNumber = 0;		//FIO0
	private int portNumberLaser = 1;//FIO1
	private int portNumberLaserIntensity = 0; //DAC0
	
	public TestRun (
			Integer seg_run_id,
			Integer integrationTime, 
			Integer restTime,
			Integer scanToAverage,
			Integer darkCurrent,
			Integer nonLinear,
			Integer boxcarWidth,
			Integer spectrometerIndex,
			String spectrometerType
			) {
		this.seg_run_id = seg_run_id;
		this.integrationTime = integrationTime;
		this.restTime = restTime;
		this.scanToAverage = scanToAverage;
		this.darkCurrentCorrectFlag = darkCurrent;
		this.nonlinearityCorrectFlag = nonLinear;
		this.boxcarWidth = boxcarWidth;
		this.spectrometerIndex = spectrometerIndex;
		this.spectrometerType = spectrometerType;
	}

	
	
	public int doTestRun() {
		
		log.info("integrationTime:" + integrationTime);
		log.info("restTime:" + restTime);
		log.info("scanToAverage:" + scanToAverage);
		log.info("darkCurrentCorrectFlag:" + darkCurrentCorrectFlag);
		log.info("nonlinearityCorrectFlag:" + nonlinearityCorrectFlag);
		log.info("boxcarWidth:" + boxcarWidth);
		log.info("spectrometerIndex:" + spectrometerIndex);

		
		//Initialize Device
		
		Wrapper wrapper_t;
		DataBuffer bufferCtrl_t = null;
		LaserControl lsControl;
		TTLControl ctrlTTL;
		
		try {
	
		//initialize spectrometer	
		wrapper_t = new Wrapper();
		wrapper_t.openAllSpectrometers();
		bufferCtrl_t = wrapper_t.getFeatureControllerDataBuffer(spectrometerIndex);
		
		// initialize lab jack
		IntByReference refHandle = new IntByReference(0);
		
		if (labjackType.equals("U6")){
			LJUD.openLabJack(LJUD.Constants.dtU6, LJUD.Constants.ctUSB, "1", 1, refHandle);
		} else {
			LJUD.openLabJack(LJUD.Constants.dtU3, LJUD.Constants.ctUSB, "1", 1, refHandle);
		}
		
		intHandle = refHandle.getValue();
		LJUD.ePut(intHandle, LJUD.Constants.ioPIN_CONFIGURATION_RESET, 0, 0, 0);
				
		// start TTL control thread
		ctrlTTL = new TTLControl("set high TTL", intHandle, portNumber);
		Thread newTTLc = new Thread(ctrlTTL);
		newTTLc.start();
		
		// set laser power
		lsControl = new LaserControl(intHandle, portNumberLaser);
		lsControl.setTTLSwitchLow();
		// lsControl.setLaserPower(laserPowV, portNumberLaserIntensity); //works on new OEM laser unit
	
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Device Initialization Failed: " + e.getMessage());
			status = "Device Initialization Failed.";
			return 100;
		}
		
		switch (spectrometerType) {
		// QEPro
		case "QEPro":
			
			SpectraAcquisitionQEPro singleMeasurement = new SpectraAcquisitionQEPro(acquisitionM, integrationTime, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t, bufferCtrl_t);
			
			singleMeasurement.setParameters();
			singleMeasurement.setBuffer();
			
			lsControl.setLaserPower(laserPowV, portNumberLaserIntensity);
			lsControl.setTTLSwitchHigh();
			
			singleMeasurement.getSpectra();
			spectra = singleMeasurement.returnSpectra();
			wavelength = singleMeasurement.returnWavelength();
			
			lsControl.setTTLSwitchLow();
			
			try {
				TimeUnit.MILLISECONDS.sleep((int) (restTime * 1000));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			//taking reflectance measurements
			/*
			SpectraAcquisitionQEPro singleMeasurement = new SpectraAcquisitionQEPro(acquisitionM, integrationTRef,
					scanToAvg, darkCurrentCorr, nonlinearityCorr, boxcarW, spectrometerInd, wrapper_t,
					bufferCtrl_t);
			singleMeasurement.setParameters();
			singleMeasurement.setBuffer();
			lsControl.setTTLSwitchHigh();
			singleMeasurement.getSpectra();
			spectraMRef = singleMeasurement.returnSpectra();
			wavelengthMRef = singleMeasurement.returnWavelength();
			lsControl.setTTLSwitchLow();
			*/
			break;

		case "MAYA":
			// MAYA
			// test run, and make sure the acquisition mode is properly set
			SpectraAcquisitionMaya singleMeasurementMaya_t = new SpectraAcquisitionMaya(acquisitionM, 0.01, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t);

			singleMeasurementMaya_t.setParameters();
			singleMeasurementMaya_t.getSpectra();

			SpectraAcquisitionMaya singleMeasurementMaya = new SpectraAcquisitionMaya(acquisitionM, integrationTime, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t);

			singleMeasurementMaya.setParameters();
			
			lsControl.setLaserPower(laserPowV, portNumberLaserIntensity);
			lsControl.setTTLSwitchHigh();
			
			singleMeasurementMaya.getSpectra();			
			spectra = singleMeasurementMaya.returnSpectra();
			wavelength = singleMeasurementMaya.returnWavelength();
			
			lsControl.setTTLSwitchLow();
			
			try {
				TimeUnit.MILLISECONDS.sleep((int) (restTime * 1000));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*	
			//taking reflectance measurements
			SpectraAcquisitionMaya singleMeasurementMaya = new SpectraAcquisitionMaya(acquisitionM, integrationTRef,
					scanToAvg, darkCurrentCorr, nonlinearityCorr, boxcarW, spectrometerInd, wrapper_t);
			singleMeasurementMaya.setParameters();
			lsControl.setTTLSwitchHigh();
			singleMeasurementMaya.getSpectra();
			spectraMRef = singleMeasurementMaya.returnSpectra();
			wavelengthMRef = singleMeasurementMaya.returnWavelength();
			lsControl.setTTLSwitchLow();
			*/
			
			break;
		}
		ctrlTTL.mystop();
		wrapper_t.closeAllSpectrometers();
				
		status = "Segment Run Successful.";
		return 0;
	}



	/**
	 * @return the seg_run_id
	 */
	public Integer getSeg_run_id() {
		return seg_run_id;
	}



	/**
	 * @return the spectra
	 */
	public double[] getSpectra() {
		return spectra;
	}



	/**
	 * @return the wavelength
	 */
	public double[] getWavelength() {
		return wavelength;
	}



	/**
	 * @param spectra the spectra to set
	 */
	public void setSpectra(double[] spectra) {
		this.spectra = spectra;
	}



	/**
	 * @param wavelength the wavelength to set
	 */
	public void setWavelength(double[] wavelength) {
		this.wavelength = wavelength;
	}

	/**
	 * @param spectra the spectra to set
	 */
	public void setSpectra(double spectra, int i) {
		this.spectra[i] = spectra;
	}



	/**
	 * @param wavelength the wavelength to set
	 */
	public void setWavelength(double wavelength, int i) {
		this.wavelength[i] = wavelength;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	public void initWaveSpectra(int size) {
		this.wavelength = new double[size];
		this.spectra = new double[size];
	}
}
