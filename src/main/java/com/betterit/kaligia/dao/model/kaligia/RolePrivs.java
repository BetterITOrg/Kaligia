package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class RolePrivs implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.roleprivs.role_id
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	private Integer roleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.roleprivs.priv_id
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	private Integer privId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.roleprivs
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.roleprivs.role_id
	 * @return  the value of kaligia.roleprivs.role_id
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.roleprivs.role_id
	 * @param roleId  the value for kaligia.roleprivs.role_id
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.roleprivs.priv_id
	 * @return  the value of kaligia.roleprivs.priv_id
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	public Integer getPrivId() {
		return privId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.roleprivs.priv_id
	 * @param privId  the value for kaligia.roleprivs.priv_id
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	public void setPrivId(Integer privId) {
		this.privId = privId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roleprivs
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", roleId=").append(roleId);
		sb.append(", privId=").append(privId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}