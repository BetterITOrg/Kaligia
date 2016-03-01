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


}
