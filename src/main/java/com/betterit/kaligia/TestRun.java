package com.betterit.kaligia;

import java.util.Date;

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
	private double[] spectraM;
	private double[] wavelengthM;
	private String spectrometerType = "MAYA";			//QEPro or MAYA
	
	// laser output power
	private double laserPowV = 0.8; // must be in [0 1.2]V

	// lab jack parameters
	private int intHandle = 0;
	private int portNumber = 16;
	private int portNumberLaser = 18;
	private int acquisitionM = 4;
	
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

	
	
	public TestResult doTestRun() {
		
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
		//bufferCtrl_t = wrapper_t.getFeatureControllerDataBuffer(spectrometerIndex);
		
		// initialize lab jack
		IntByReference refHandle = new IntByReference(0);
		LJUD.openLabJack(LJUD.Constants.dtU3, LJUD.Constants.ctUSB, "1", 1, refHandle);
		int intHandle = refHandle.getValue();
		LJUD.ePut(intHandle, LJUD.Constants.ioPIN_CONFIGURATION_RESET, 0, 0, 0);
		
		// start TTL control thread
		ctrlTTL = new TTLControl("set high TTL", intHandle, portNumber);
		Thread newTTLc = new Thread(ctrlTTL);
		newTTLc.start();
		
		// set laser power
		lsControl = new LaserControl(intHandle, portNumberLaser);
		lsControl.setTTLSwitchLow();
		// lsControl.setLaserPower(laserPowV); //works on new OEM laser unit
	
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Spectrometer Initialization Failed");
			return null;
		}

		switch (spectrometerType) {
		// QEPro
		case "QEPro":
			// Set Parameters		
			SpectraAcquisitionQEPro singleMeasurement = new SpectraAcquisitionQEPro(acquisitionM, integrationTime, restTime, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t, bufferCtrl_t);
			
			singleMeasurement.setParameters();
			singleMeasurement.setBuffer();
			lsControl.setTTLSwitchHigh();
			
			singleMeasurement.getSpectra();
			
			spectraM = singleMeasurement.returnSpectra();
			wavelengthM = singleMeasurement.returnWavelength();
			
			lsControl.setTTLSwitchLow();
			
		case "MAYA":
			// MAYA
			// test run, and make sure the acquisition mode is properly set
			
			SpectraAcquisitionMaya singleMeasurementMaya_t = new SpectraAcquisitionMaya(acquisitionM, 0.01, restTime, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t);
			singleMeasurementMaya_t.setParameters();
			singleMeasurementMaya_t.getSpectra();
			
			SpectraAcquisitionMaya singleMeasurementMaya = new SpectraAcquisitionMaya(acquisitionM, integrationTime, restTime, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t);
			singleMeasurementMaya.setParameters();
			lsControl.setTTLSwitchHigh();

			singleMeasurementMaya.getSpectra();

			spectraM = singleMeasurementMaya.returnSpectra();
			wavelengthM = singleMeasurementMaya.returnWavelength();
			lsControl.setTTLSwitchLow();
			
		}
		
		ctrlTTL.mystop();
		wrapper_t.closeAllSpectrometers();
		
		return new TestResult(seg_run_id, wavelengthM, spectraM);
	}

}
