package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class RunDevices implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.rundevices.run_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private Integer runId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.rundevices.device_inst_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private Integer deviceInstId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.rundevices
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.rundevices.run_id
	 * @return  the value of kaligia.rundevices.run_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public Integer getRunId() {
		return runId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.rundevices.run_id
	 * @param runId  the value for kaligia.rundevices.run_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.rundevices.device_inst_id
	 * @return  the value of kaligia.rundevices.device_inst_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public Integer getDeviceInstId() {
		return deviceInstId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.rundevices.device_inst_id
	 * @param deviceInstId  the value for kaligia.rundevices.device_inst_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setDeviceInstId(Integer deviceInstId) {
		this.deviceInstId = deviceInstId;
	}
}