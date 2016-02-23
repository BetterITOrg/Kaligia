package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class RunSegmentLog implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.runsegmentlog.run_segment_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer runSegmentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.runsegmentlog.r_index
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer rIndex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.runsegmentlog.wavelength
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Double wavelength;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.runsegmentlog.photon_count
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Double photonCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.runsegmentlog.run_segment_id
	 * @return  the value of kaligia.runsegmentlog.run_segment_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getRunSegmentId() {
		return runSegmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.runsegmentlog.run_segment_id
	 * @param runSegmentId  the value for kaligia.runsegmentlog.run_segment_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setRunSegmentId(Integer runSegmentId) {
		this.runSegmentId = runSegmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.runsegmentlog.r_index
	 * @return  the value of kaligia.runsegmentlog.r_index
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getrIndex() {
		return rIndex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.runsegmentlog.r_index
	 * @param rIndex  the value for kaligia.runsegmentlog.r_index
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setrIndex(Integer rIndex) {
		this.rIndex = rIndex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.runsegmentlog.wavelength
	 * @return  the value of kaligia.runsegmentlog.wavelength
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Double getWavelength() {
		return wavelength;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.runsegmentlog.wavelength
	 * @param wavelength  the value for kaligia.runsegmentlog.wavelength
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setWavelength(Double wavelength) {
		this.wavelength = wavelength;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.runsegmentlog.photon_count
	 * @return  the value of kaligia.runsegmentlog.photon_count
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Double getPhotonCount() {
		return photonCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.runsegmentlog.photon_count
	 * @param photonCount  the value for kaligia.runsegmentlog.photon_count
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setPhotonCount(Double photonCount) {
		this.photonCount = photonCount;
	}
}