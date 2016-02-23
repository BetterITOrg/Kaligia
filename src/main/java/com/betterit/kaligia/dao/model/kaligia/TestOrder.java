package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class TestOrder implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.order_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.order_no
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private String orderNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.subject_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private Integer subjectId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.site_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private Integer siteId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.description
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.creation_date
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private Integer creationDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.testorder.created_by
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private Integer createdBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.testorder
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.order_id
	 * @return  the value of kaligia.testorder.order_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.order_id
	 * @param orderId  the value for kaligia.testorder.order_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.order_no
	 * @return  the value of kaligia.testorder.order_no
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.order_no
	 * @param orderNo  the value for kaligia.testorder.order_no
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.subject_id
	 * @return  the value of kaligia.testorder.subject_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.subject_id
	 * @param subjectId  the value for kaligia.testorder.subject_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.site_id
	 * @return  the value of kaligia.testorder.site_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public Integer getSiteId() {
		return siteId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.site_id
	 * @param siteId  the value for kaligia.testorder.site_id
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.description
	 * @return  the value of kaligia.testorder.description
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.description
	 * @param description  the value for kaligia.testorder.description
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.creation_date
	 * @return  the value of kaligia.testorder.creation_date
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public Integer getCreationDate() {
		return creationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.creation_date
	 * @param creationDate  the value for kaligia.testorder.creation_date
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setCreationDate(Integer creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.testorder.created_by
	 * @return  the value of kaligia.testorder.created_by
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.testorder.created_by
	 * @param createdBy  the value for kaligia.testorder.created_by
	 * @mbggenerated  Tue Feb 23 15:44:22 EST 2016
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
}