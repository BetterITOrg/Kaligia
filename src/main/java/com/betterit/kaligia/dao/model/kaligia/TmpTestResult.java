package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class TmpTestResult implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.tmp_testresult.run_id
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	private Integer runId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.tmp_testresult.run_no
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	private Integer runNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.tmp_testresult.wavenumber
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	private Float wavenumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.tmp_testresult.photon_count
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	private Float photonCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.tmp_testresult
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.tmp_testresult.run_id
	 * @return  the value of kaligia.tmp_testresult.run_id
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public Integer getRunId() {
		return runId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.tmp_testresult.run_id
	 * @param runId  the value for kaligia.tmp_testresult.run_id
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.tmp_testresult.run_no
	 * @return  the value of kaligia.tmp_testresult.run_no
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public Integer getRunNo() {
		return runNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.tmp_testresult.run_no
	 * @param runNo  the value for kaligia.tmp_testresult.run_no
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public void setRunNo(Integer runNo) {
		this.runNo = runNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.tmp_testresult.wavenumber
	 * @return  the value of kaligia.tmp_testresult.wavenumber
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public Float getWavenumber() {
		return wavenumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.tmp_testresult.wavenumber
	 * @param wavenumber  the value for kaligia.tmp_testresult.wavenumber
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public void setWavenumber(Float wavenumber) {
		this.wavenumber = wavenumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.tmp_testresult.photon_count
	 * @return  the value of kaligia.tmp_testresult.photon_count
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public Float getPhotonCount() {
		return photonCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.tmp_testresult.photon_count
	 * @param photonCount  the value for kaligia.tmp_testresult.photon_count
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	public void setPhotonCount(Float photonCount) {
		this.photonCount = photonCount;
	}
}