/**
 * 
 */
package com.betterit.kaligia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author V135012
 *
 */
public class TestResult {
	private static final Logger log = LoggerFactory.getLogger(TestResult.class);
	
	private int seg_run_id;
	private double wavelength[];
	private double photon_count[];

	/**
	 * @param seg_run_id
	 * @param wavelength
	 * @param photon_count
	 */
	public TestResult(int seg_run_id, double[] wavelength, double[] photon_count) {
		super();
		this.seg_run_id = seg_run_id;
		this.wavelength = wavelength;
		this.photon_count = photon_count;
	}

	/**
	 * @return the seg_run_id
	 */
	public int getSeg_run_id() {
		return seg_run_id;
	}

	/**
	 * @param seg_run_id the seg_run_id to set
	 */
	public void setSeg_run_id(int seg_run_id) {
		this.seg_run_id = seg_run_id;
	}

	/**
	 * @return the wavelength
	 */
	public double[] getWavelength() {
		return wavelength;
	}

	/**
	 * @param wavelength the wavelength to set
	 */
	public void setWavelength(double[] wavelength) {
		this.wavelength = wavelength;
	}

	/**
	 * @return the photon_count
	 */
	public double[] getPhoton_count() {
		return photon_count;
	}

	/**
	 * @param photon_count the photon_count to set
	 */
	public void setPhoton_count(double[] photon_count) {
		this.photon_count = photon_count;
	}

}
