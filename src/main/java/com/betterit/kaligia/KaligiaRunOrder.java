/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;

/**
 * @author nayar
 *
 */
public class KaligiaRunOrder {
	private String orderNo;
	private String Description;

	private String type;
	private String testProcedure;
	private String specimen;
	private int testProcedureId=0;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTestProcedure() {
		return testProcedure;
	}
	public void setTestProcedure(String testProcedure) {
		this.testProcedure = testProcedure;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public int getTestProcedureId() {
		return testProcedureId;
	}
	public void setTestProcedureId(int testProcedureId) {
		this.testProcedureId = testProcedureId;
	}
	
	@Override
	public String toString() {
		return "KaligiaRunOrder [" + (orderNo != null ? "orderNo=" + orderNo + ", " : "")
				+ (Description != null ? "Description=" + Description + ", " : "")
				+ (type != null ? "type=" + type + ", " : "")
				+ (testProcedure != null ? "testProcedure=" + testProcedure + ", " : "")
				+ (specimen != null ? "specimen=" + specimen + ", " : "") + "testProcedureId=" + testProcedureId + "]";
	}
	
	

}
