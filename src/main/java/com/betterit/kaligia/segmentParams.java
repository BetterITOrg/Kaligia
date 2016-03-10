/**
 * 
 */
package com.betterit.kaligia;

/**
 * @author nayar
 *
 */
public class segmentParams {

	private String integrationTime="0";
	private String scan2Average="1";
	private String boxCarWidth="1";
	private String electricDark="1";
	private String nonLinearCorrect="0";
	private String delay="1";
	private String power="0.8";
	
	public String getIntegrationTime() {
		return integrationTime;
	}
	public void setIntegrationTime(String integrationTime) {
		this.integrationTime = integrationTime;
	}
	public String getScan2Average() {
		return scan2Average;
	}
	public void setScan2Average(String scan2Average) {
		this.scan2Average = scan2Average;
	}
	public String getBoxCarWidth() {
		return boxCarWidth;
	}
	public void setBoxCarWidth(String boxCarWidth) {
		this.boxCarWidth = boxCarWidth;
	}
	public String getElectricDark() {
		return electricDark;
	}
	public void setElectricDark(String electricDark) {
		this.electricDark = electricDark;
	}
	public String getNonLinearCorrect() {
		return nonLinearCorrect;
	}
	public void setNonLinearCorrect(String nonLinearCorrect) {
		this.nonLinearCorrect = nonLinearCorrect;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	@Override
	public String toString() {
		return "segmentParams [" + (integrationTime != null ? "integrationTime=" + integrationTime + ", " : "")
				+ (scan2Average != null ? "scan2Average=" + scan2Average + ", " : "")
				+ (boxCarWidth != null ? "boxCarWidth=" + boxCarWidth + ", " : "")
				+ (electricDark != null ? "electricDark=" + electricDark + ", " : "")
				+ (nonLinearCorrect != null ? "nonLinearCorrect=" + nonLinearCorrect + ", " : "")
				+ (delay != null ? "delay=" + delay + ", " : "") + (power != null ? "power=" + power : "") + "]";
	}
	
	
}
