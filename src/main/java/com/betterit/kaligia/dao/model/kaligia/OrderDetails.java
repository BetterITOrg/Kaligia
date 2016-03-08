package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class OrderDetails implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.orderdetails.order_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private Integer orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.orderdetails.name
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.orderdetails.value
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private String value;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.orderdetails
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.orderdetails.order_id
	 * @return  the value of kaligia.orderdetails.order_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.orderdetails.order_id
	 * @param orderId  the value for kaligia.orderdetails.order_id
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.orderdetails.name
	 * @return  the value of kaligia.orderdetails.name
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.orderdetails.name
	 * @param name  the value for kaligia.orderdetails.name
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.orderdetails.value
	 * @return  the value of kaligia.orderdetails.value
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public String getValue() {
		return value;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.orderdetails.value
	 * @param value  the value for kaligia.orderdetails.value
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}
}