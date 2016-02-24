/**
 * 
 */
package com.betterit.kaligia;

/**
 * @author nayar
 *
 */
public class segmentParams {

	private String integrationTime;
	private String scan2Average;
	private String boxCarWidth;
	private String electricDark;
	private String nonLinearCorrect;
	private String delay;
	private String power;
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
