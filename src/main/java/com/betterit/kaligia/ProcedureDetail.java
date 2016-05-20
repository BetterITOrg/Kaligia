/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;

/**
 * @author nayar
 *
 */
public class ProcedureDetail {
	private String Name;
	private String Description;
	private String Status;
	private String Laser;
	private String Spectrometer;
	private String Probe;
	private String Labjack;
	private int NoOfSegments;
	private String Type;
	private Integer procedureID;
	private String Tube;
	private String collectionFiber;
	private String excitationFiber;
	
	//FL Removal Parameters
	private String startPos;
	private String endPos;
	private String threshold;
	private String totalRunTime;
	
	private List<segmentParams> segmentList;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getLaser() {
		return Laser;
	}
	public void setLaser(String laser) {
		Laser = laser;
	}
	public String getSpectrometer() {
		return Spectrometer;
	}
	public void setSpectrometer(String spectrometer) {
		Spectrometer = spectrometer;
	}
	public String getProbe() {
		return Probe;
	}
	public void setProbe(String probe) {
		Probe = probe;
	}
	public int getNoOfSegments() {
		return NoOfSegments;
	}
	public void setNoOfSegments(int noOfSegments) {
		NoOfSegments = noOfSegments;
	}
	public List<segmentParams> getSegmentList() {
		return segmentList;
	}
	public void setSegmentList(List<segmentParams> segmentList) {
		this.segmentList = segmentList;
	}

	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	/**
	 * @return the labjack
	 */
	public String getLabjack() {
		return Labjack;
	}
	/**
	 * @param labjack the labjack to set
	 */
	public void setLabjack(String labjack) {
		Labjack = labjack;
	}
	
	@Override
	public String toString() {
		return "ProcedureDetail [" + (Name != null ? "Name=" + Name + ", " : "")
				+ (Description != null ? "Description=" + Description + ", " : "")
				+ (Status != null ? "Status=" + Status + ", " : "") + (Laser != null ? "Laser=" + Laser + ", " : "")
				+ (Spectrometer != null ? "Spectrometer=" + Spectrometer + ", " : "")
				+ (Probe != null ? "Probe=" + Probe + ", " : "") + (Labjack != null ? "Labjack=" + Labjack + ", " : "")
				+ "NoOfSegments=" + NoOfSegments + ", " + (Type != null ? "Type=" + Type + ", " : "")
				+ (procedureID != null ? "procedureID=" + procedureID + ", " : "")
				+ (Tube != null ? "Tube=" + Tube + ", " : "")
				+ (collectionFiber != null ? "collectionFiber=" + collectionFiber + ", " : "")
				+ (excitationFiber != null ? "excitationFiber=" + excitationFiber + ", " : "")
				+ (startPos != null ? "startPos=" + startPos + ", " : "")
				+ (endPos != null ? "endPos=" + endPos + ", " : "")
				+ (threshold != null ? "threshold=" + threshold + ", " : "")
				+ (totalRunTime != null ? "totalRunTime=" + totalRunTime + ", " : "")
				+ (segmentList != null ? "segmentList=" + segmentList : "") + "]";
	}
	/**
	 * @return the procedureID
	 */
	public Integer getProcedureID() {
		return procedureID;
	}
	/**
	 * @param procedureID the procedureID to set
	 */
	public void setProcedureID(Integer procedureID) {
		this.procedureID = procedureID;
	}
	public String getTube() {
		return Tube;
	}
	public void setTube(String tube) {
		Tube = tube;
	}
	public String getCollectionFiber() {
		return collectionFiber;
	}
	public void setCollectionFiber(String collectionFiber) {
		this.collectionFiber = collectionFiber;
	}
	public String getExcitationFiber() {
		return excitationFiber;
	}
	public void setExcitationFiber(String excitationFiber) {
		this.excitationFiber = excitationFiber;
	}
	/**
	 * @param startPos the startPos to set
	 */
	public void setStartPos(String startPos) {
		this.startPos = startPos;
	}
	/**
	 * @param endPos the endPos to set
	 */
	public void setEndPos(String endPos) {
		this.endPos = endPos;
	}
	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	/**
	 * @return the startPos
	 */
	public String getStartPos() {
		return startPos;
	}
	/**
	 * @return the endPos
	 */
	public String getEndPos() {
		return endPos;
	}
	/**
	 * @return the threshold
	 */
	public String getThreshold() {
		return threshold;
	}
	public String getTotalRunTime() {
		return totalRunTime;
	}
	public void setTotalRunTime(String totalRunTime) {
		this.totalRunTime = totalRunTime;
	}


}
