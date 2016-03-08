package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class SpecimenSpec implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.specimenspec.specimen_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private Integer specimenId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.specimenspec.name
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.specimenspec.value
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private String value;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.specimenspec.unit
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private String unit;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.specimenspec
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.specimenspec.specimen_id
	 * @return  the value of kaligia.specimenspec.specimen_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public Integer getSpecimenId() {
		return specimenId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.specimenspec.specimen_id
	 * @param specimenId  the value for kaligia.specimenspec.specimen_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.specimenspec.name
	 * @return  the value of kaligia.specimenspec.name
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.specimenspec.name
	 * @param name  the value for kaligia.specimenspec.name
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.specimenspec.value
	 * @return  the value of kaligia.specimenspec.value
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public String getValue() {
		return value;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.specimenspec.value
	 * @param value  the value for kaligia.specimenspec.value
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.specimenspec.unit
	 * @return  the value of kaligia.specimenspec.unit
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.specimenspec.unit
	 * @param unit  the value for kaligia.specimenspec.unit
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}
}