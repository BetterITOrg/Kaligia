/**
 * 
 */
package com.betterit.kaligia;

import com.betterit.kaligia.dao.model.kaligia.Users;

/**
 * @author nayar
 *
 */
public class UserView {

	String startDate;
	String endDate;
	Users userObject;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Users getUserObject() {
		return userObject;
	}
	public void setUserObject(Users userObject) {
		this.userObject = userObject;
	}
	@Override
	public String toString() {
		return "UserView [" + (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "")
				+ (userObject != null ? "userObject=" + userObject : "") + "]";
	}
	
}
