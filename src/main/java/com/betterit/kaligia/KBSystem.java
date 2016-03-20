/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;

import com.betterit.kaligia.dao.model.kaligia.EndPoint;
import com.betterit.kaligia.dao.model.kaligia.DeviceInst;

/**
 * @author nayar
 *
 */
public class KBSystem {
	
	EndPoint endpoint;
	List<DeviceList> kbsDeviceList;
	public EndPoint getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(EndPoint endpoint) {
		this.endpoint = endpoint;
	}
	public List<DeviceList> getKbsDeviceList() {
		return kbsDeviceList;
	}
	public void setKbsDeviceList(List<DeviceList> kbsDeviceList) {
		this.kbsDeviceList = kbsDeviceList;
	}
	@Override
	public String toString() {
		return "KBSystem [" + (endpoint != null ? "endpoint=" + endpoint + ", " : "")
				+ (kbsDeviceList != null ? "kbsDeviceList=" + kbsDeviceList : "") + "]";
	}
	
		
}
