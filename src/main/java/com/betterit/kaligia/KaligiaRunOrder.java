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
	private String patientId;
	private String dateOfBirth;
	private String patientGender;
	private String patientEthnicity;
	private String skinColor;
	private String patientWeight;
	private String patientHeight;
	private String systolicBP;
	private String diastolicBP;
	private String patientTemp;
	private String patientHeartRate;
	private String patientOLevel;
	private String Description;

	private String type;
	private String testProcedure;
	private String specimen;
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
	
	@Override
	public String toString() {
		return "KaligiaRunOrder [" + (orderNo != null ? "orderNo=" + orderNo + ", " : "")
				+ (patientId != null ? "patientId=" + patientId + ", " : "")
				+ (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "")
				+ (patientGender != null ? "patientGender=" + patientGender + ", " : "")
				+ (patientEthnicity != null ? "patientEthnicity=" + patientEthnicity + ", " : "")
				+ (skinColor != null ? "skinColor=" + skinColor + ", " : "")
				+ (patientWeight != null ? "patientWeight=" + patientWeight + ", " : "")
				+ (patientHeight != null ? "patientHeight=" + patientHeight + ", " : "")
				+ (systolicBP != null ? "systolicBP=" + systolicBP + ", " : "")
				+ (diastolicBP != null ? "diastolicBP=" + diastolicBP + ", " : "")
				+ (patientTemp != null ? "patientTemp=" + patientTemp + ", " : "")
				+ (patientHeartRate != null ? "patientHeartRate=" + patientHeartRate + ", " : "")
				+ (patientOLevel != null ? "patientOLevel=" + patientOLevel + ", " : "")
				+ (Description != null ? "Description=" + Description + ", " : "")
				+ (type != null ? "type=" + type + ", " : "")
				+ (testProcedure != null ? "testProcedure=" + testProcedure + ", " : "")
				+ (specimen != null ? "specimen=" + specimen + ", " : "") + "testProcedureId=" + testProcedureId + ", "
				+ (testStatus != null ? "testStatus=" + testStatus + ", " : "")
				+ (wavenumber != null ? "wavenumber=" + Arrays.toString(wavenumber) + ", " : "")
				+ (photon != null ? "photon=" + Arrays.toString(photon) + ", " : "")
				+ (runID != null ? "runID=" + runID + ", " : "")
				+ (resultNotes != null ? "resultNotes=" + resultNotes : "") + "]";
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
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getPatientEthnicity() {
		return patientEthnicity;
	}
	public void setPatientEthnicity(String patientEthnicity) {
		this.patientEthnicity = patientEthnicity;
	}
	public String getSkinColor() {
		return skinColor;
	}
	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}
	public String getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}
	public String getPatientHeight() {
		return patientHeight;
	}
	public void setPatientHeight(String patientHeight) {
		this.patientHeight = patientHeight;
	}
	public String getSystolicBP() {
		return systolicBP;
	}
	public void setSystolicBP(String systolicBP) {
		this.systolicBP = systolicBP;
	}
	public String getDiastolicBP() {
		return diastolicBP;
	}
	public void setDiastolicBP(String diastolicBP) {
		this.diastolicBP = diastolicBP;
	}
	public String getPatientTemp() {
		return patientTemp;
	}
	public void setPatientTemp(String patientTemp) {
		this.patientTemp = patientTemp;
	}
	public String getPatientHeartRate() {
		return patientHeartRate;
	}
	public void setPatientHeartRate(String patientHeartRate) {
		this.patientHeartRate = patientHeartRate;
	}
	public String getPatientOLevel() {
		return patientOLevel;
	}
	public void setPatientOLevel(String patientOLevel) {
		this.patientOLevel = patientOLevel;
	}
	

}
