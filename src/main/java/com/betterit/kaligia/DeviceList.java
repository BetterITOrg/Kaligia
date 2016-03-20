/**
 * 
 */
package com.betterit.kaligia;

import com.betterit.kaligia.dao.model.kaligia.DeviceInst;

/**
 * @author nayar
 *
 */
public class DeviceList {
	
	DeviceInst diObj;
	String usedFor;
	public DeviceInst getDiObj() {
		return diObj;
	}
	public void setDiObj(DeviceInst diObj) {
		this.diObj = diObj;
	}
	public String getUsedFor() {
		return usedFor;
	}
	public void setUsedFor(String usedFor) {
		this.usedFor = usedFor;
	}
	@Override
	public String toString() {
		return "DeviceList [" + (diObj != null ? "diObj=" + diObj + ", " : "")
				+ (usedFor != null ? "usedFor=" + usedFor : "") + "]";
	}

}
