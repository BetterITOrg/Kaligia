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
	List<DeviceInst> kbsDeviceList;
	
	public EndPoint getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(EndPoint endpoint) {
		this.endpoint = endpoint;
	}
	@Override
	public String toString() {
		return "KBSystem [" + (endpoint != null ? "endpoint=" + endpoint + ", " : "")
				+ (kbsDeviceList != null ? "kbsDeviceList=" + kbsDeviceList : "") + "]";
	}
	public List<DeviceInst> getKbsDeviceList() {
		return kbsDeviceList;
	}
	public void setKbsDeviceList(List<DeviceInst> kbsDeviceList) {
		this.kbsDeviceList = kbsDeviceList;
	}

	
}
