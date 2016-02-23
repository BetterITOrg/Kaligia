package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;
import java.util.Date;

public class TestProcedure implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.procedure_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer procedureId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.name
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.description
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.type
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private String type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.no_of_segments
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer noOfSegments;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.status
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private String status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.creation_date
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Date creationDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testprocedure.created_by
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer createdBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.testprocedure
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.procedure_id
	 * @return  the value of kaligia.testprocedure.procedure_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getProcedureId() {
		return procedureId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.procedure_id
	 * @param procedureId  the value for kaligia.testprocedure.procedure_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.name
	 * @return  the value of kaligia.testprocedure.name
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.name
	 * @param name  the value for kaligia.testprocedure.name
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.description
	 * @return  the value of kaligia.testprocedure.description
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.description
	 * @param description  the value for kaligia.testprocedure.description
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.type
	 * @return  the value of kaligia.testprocedure.type
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.type
	 * @param type  the value for kaligia.testprocedure.type
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.no_of_segments
	 * @return  the value of kaligia.testprocedure.no_of_segments
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getNoOfSegments() {
		return noOfSegments;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.no_of_segments
	 * @param noOfSegments  the value for kaligia.testprocedure.no_of_segments
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setNoOfSegments(Integer noOfSegments) {
		this.noOfSegments = noOfSegments;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.status
	 * @return  the value of kaligia.testprocedure.status
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.status
	 * @param status  the value for kaligia.testprocedure.status
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.creation_date
	 * @return  the value of kaligia.testprocedure.creation_date
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.creation_date
	 * @param creationDate  the value for kaligia.testprocedure.creation_date
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testprocedure.created_by
	 * @return  the value of kaligia.testprocedure.created_by
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testprocedure.created_by
	 * @param createdBy  the value for kaligia.testprocedure.created_by
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
}