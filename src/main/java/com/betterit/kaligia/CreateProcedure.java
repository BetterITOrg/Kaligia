/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;

/**
 * @author nayar
 *
 */
public class CreateProcedure {
	private String Name;
	private String Description;
	private String Status;
	private String Laser;
	private String Spectrometer;
	private String Probe;
	private int NoOfSegments;
	private String Type;
	private String Labjack;
	
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
	public String getLabjack() {
		return Labjack;
	}
	public void setLabjack(String labjack) {
		Labjack = labjack;
	}
	@Override
	public String toString() {
		return "CreateProcedure [" + (Name != null ? "Name=" + Name + ", " : "")
				+ (Description != null ? "Description=" + Description + ", " : "")
				+ (Status != null ? "Status=" + Status + ", " : "") + (Laser != null ? "Laser=" + Laser + ", " : "")
				+ (Spectrometer != null ? "Spectrometer=" + Spectrometer + ", " : "")
				+ (Probe != null ? "Probe=" + Probe + ", " : "") + "NoOfSegments=" + NoOfSegments + ", "
				+ (Type != null ? "Type=" + Type + ", " : "") + (Labjack != null ? "Labjack=" + Labjack + ", " : "")
				+ (segmentList != null ? "segmentList=" + segmentList : "") + "]";
	}
	

}
