/**
 * 
 */
package com.betterit.kaligia;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oceanoptics.omnidriver.api.wrapper.Wrapper;
import com.oceanoptics.omnidriver.features.buffer.DataBuffer;
import com.oceanoptics.omnidriver.features.thermoelectric.ThermoElectricWrapper;

/**
 * @author Kaide Johar
 *
 */
public class TestRun {
	
	private static final Logger log = LoggerFactory.getLogger(TestRun.class);
	
	private String name;
	private String description;
	private Integer integrationTime;
	private Integer restTime;
	private Integer scanToAverage;
	private Integer darkCurrentCorrectFlag;
	private Integer nonlinearityCorrectFlag;
	private Integer boxcarWidth;
	private Integer spectrometerIndex;
	private double[] spectraM;
	private double[] wavelengthM;

	
    @Autowired
    JdbcTemplate jdbcTemplate;

	public TestRun (String name, 
					String description, 
					Integer integrationTime, 
					Integer restTime,
					Integer scanToAverage,
					Integer darkCurrent,
					Integer nonLinear,
					Integer boxcarWidth,
					Integer spectrometerIndex) {
		this.name = name;
		this.description = description;
		this.integrationTime = integrationTime;
		this.restTime = restTime;
		this.scanToAverage = scanToAverage;
		this.darkCurrentCorrectFlag = darkCurrent;
		this.nonlinearityCorrectFlag = nonLinear;
		this.boxcarWidth = boxcarWidth;
		this.spectrometerIndex = spectrometerIndex;
	}
	
	public Integer doTestRun() {
		
		//Create testrun in DB
		log.info("Name:" + name, "Description:" + description);
		//TO DO
		
		//Initialize Device
		Wrapper wrapper_t = new Wrapper();
		wrapper_t.openAllSpectrometers();
		DataBuffer bufferCtrl_t = wrapper_t.getFeatureControllerDataBuffer(spectrometerIndex);
		
		ThermoElectricWrapper tecController = wrapper_t.getFeatureControllerThermoElectric(spectrometerIndex);
		try {
			double detTemp = tecController.getDetectorTemperatureCelsius();
			log.info("Detector temperature: " + detTemp + " deg C");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Spectrometer Initialization Failed");
			return 100;
		}

		// Set Parameters
		SpectraAcquisition singleMeasurement = new SpectraAcquisition(integrationTime, restTime, scanToAverage, darkCurrentCorrectFlag, nonlinearityCorrectFlag, boxcarWidth, spectrometerIndex, wrapper_t, bufferCtrl_t);
		singleMeasurement.setParameters();
		singleMeasurement.setBuffer();
		
		//Run Test
		singleMeasurement.getSpectra();
		spectraM = singleMeasurement.returnSpectra();
		wavelengthM = singleMeasurement.returnWavelength();
		
		//Print Result
		for (int j = 0; j < spectraM.length; j++) {
			if (Double.toString(spectraM[j]) != null)
				System.out.println(wavelengthM[j] + "\t" + spectraM[j]);
		}
	
		/*
		try {
			jdbcTemplate.update("insert into kaligia.testresult values (2, 1, ?, ?", spectraM, wavelengthM);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Result Load Failed");
			return 200;

		}
		*/
		
		//Store Results
		return 0;
	}
}
