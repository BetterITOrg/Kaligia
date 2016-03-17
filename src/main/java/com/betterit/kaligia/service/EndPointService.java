/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.EndPoint;
import com.betterit.kaligia.dao.model.kaligia.EndPointDevices;
import com.betterit.kaligia.dao.model.kaligia.DeviceInst;
import com.betterit.kaligia.dao.repository.kaligia.EndPointDevicesMapper;
import com.betterit.kaligia.dao.repository.kaligia.EndPointMapper;
import com.betterit.kaligia.dao.repository.kaligia.EndPointProcsMapper;

/**
 * @author V135012
 *
 */
@Service
public class EndPointService {
	
	private static final Logger log = LoggerFactory.getLogger(EndPointService.class);
	
	@Autowired
	private EndPointMapper epm;
	
	@Autowired
	private EndPointProcsMapper eppm;
	
	@Autowired
	private EndPointDevicesMapper epdm;
	
	public int createEndPoint(EndPoint ep) 	{
		return epm.insert(ep);
	}

	public int mapEndPointDevices(EndPoint ep, List<DeviceInst> dil) {
		int endPointId = ep.getEndPointId();
		EndPointDevices epd = new EndPointDevices();
		epd.setEndPointId(endPointId);
		epd.setCreatedBy(ep.getCreatedBy());
		epd.setCreationDate(ep.getCreationDate());
		
		for(int i=0; i<dil.size(); i++) {
			epd.setDeviceInstId(dil.get(i).getDeviceInstId());
			epdm.insert(epd);
		}
		
		return 0;
	}
	
}
