package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;
import java.util.Date;

public class EndPointProcs implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.endpointprocs.end_point_id
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	private Integer endPointId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.endpointprocs.procedure_id
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	private Integer procedureId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.endpointprocs.status
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	private String status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.endpointprocs.created_by
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	private Integer createdBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.endpointprocs.creation_date
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	private Date creationDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.endpointprocs
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.endpointprocs.end_point_id
	 * @return  the value of kaligia.endpointprocs.end_point_id
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Integer getEndPointId() {
		return endPointId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.endpointprocs.end_point_id
	 * @param endPointId  the value for kaligia.endpointprocs.end_point_id
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setEndPointId(Integer endPointId) {
		this.endPointId = endPointId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.endpointprocs.procedure_id
	 * @return  the value of kaligia.endpointprocs.procedure_id
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Integer getProcedureId() {
		return procedureId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.endpointprocs.procedure_id
	 * @param procedureId  the value for kaligia.endpointprocs.procedure_id
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.endpointprocs.status
	 * @return  the value of kaligia.endpointprocs.status
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.endpointprocs.status
	 * @param status  the value for kaligia.endpointprocs.status
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.endpointprocs.created_by
	 * @return  the value of kaligia.endpointprocs.created_by
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.endpointprocs.created_by
	 * @param createdBy  the value for kaligia.endpointprocs.created_by
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.endpointprocs.creation_date
	 * @return  the value of kaligia.endpointprocs.creation_date
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.endpointprocs.creation_date
	 * @param creationDate  the value for kaligia.endpointprocs.creation_date
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}