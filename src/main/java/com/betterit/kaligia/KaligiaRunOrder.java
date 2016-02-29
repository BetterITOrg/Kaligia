/**
 * 
 */
package com.betterit.kaligia;

import java.util.ArrayList;
import java.util.Arrays;
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
	private String subject;
	private int testProcedureId=0;
	
	private List<String> testStatus = new ArrayList<String>();
	private int wavenumber[][] = new int[10][];
	private float photon[][] = new float[10][];
	
	private Integer runID;
	private String resultNotes;
	
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
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KaligiaRunOrder [orderNo=" + orderNo + ", Description=" + Description + ", type=" + type
				+ ", testProcedure=" + testProcedure + ", specimen=" + specimen + ", subject=" + subject
				+ ", testProcedureId=" + testProcedureId + ", testStatus=" + testStatus + ", wavenumber="
				+ Arrays.toString(wavenumber) + ", photon=" + Arrays.toString(photon) + ", runID=" + runID
				+ ", resultNotes=" + resultNotes + "]";
	}
	/**
	 * @return the testStatus
	 */
	public List<String> getTestStatus() {
		return testStatus;
	}
	/**
	 * @param testStatus the testStatus to set
	 */
	public void setTestStatus(List<String> testStatus) {
		this.testStatus = testStatus;
	}
	/**
	 * @param testStatus the testStatus to set
	 */
	public void setTestStatus(String testStatus, int i) {
		this.testStatus.add(testStatus);
	}

	/**
	 * @return the wavenumber
	 */
	public int[][] getWavenumber() {
		return wavenumber;
	}
	/**
	 * @param wavenumber the wavenumber to set
	 */
	public void setWavenumber(int[][] wavenumber) {
		this.wavenumber = wavenumber;
	}
	/**
	 * @return the photon
	 */
	public float[][] getPhoton() {
		return photon;
	}
	/**
	 * @param photon the photon to set
	 */
	public void setPhoton(float[][] photon) {
		this.photon = photon;
	}
	/**
	 * @param photon the photon to set
	 */
	public void setPhoton(float[] photon, int i) {
		this.photon[i] = photon;
	}
	/**
	 * @param wavenumber the wavenumber to set
	 */
	public void setWavenumber(int[] wavenumber, int i) {
		this.wavenumber[i] = wavenumber;
	}
	/**
	 * @return the runID
	 */
	public Integer getRunID() {
		return runID;
	}
	/**
	 * @param runID the runID to set
	 */
	public void setRunID(Integer runID) {
		this.runID = runID;
	}
	/**
	 * @return the resultNotes
	 */
	public String getResultNotes() {
		return resultNotes;
	}
	/**
	 * @param resultNotes the resultNotes to set
	 */
	public void setResultNotes(String resultNotes) {
		this.resultNotes = resultNotes;
	}
	

}
