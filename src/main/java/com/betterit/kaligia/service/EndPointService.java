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
import com.betterit.kaligia.dao.model.kaligia.EndPointDevicesExample;
import com.betterit.kaligia.dao.model.kaligia.EndPointExample;
import com.betterit.kaligia.DeviceList;
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
	
	public boolean createEndPoint(EndPoint ep) 	{
		EndPointExample epe = new EndPointExample();
		epe.createCriteria().andNameEqualTo(ep.getName()).andSiteIdEqualTo(ep.getSiteId());
		List<EndPoint > epl = epm.selectByExample(epe);
		if(epl.size() == 0) {
			epm.insert(ep);
			return false;
		}
		
		//epe.clear;
		epe.createCriteria().andTypeEqualTo(ep.getType()).andStatusEqualTo(ep.getStatus()).andDescriptionEqualTo(ep.getDescription());
		ep = epl.get(0);
		epm.updateByExampleSelective(ep, epe);
		return true;
	}

	public int mapEndPointDevices(EndPoint ep, List<DeviceList> dil, boolean deleteAdd) {
		
		int endPointId = ep.getEndPointId();
		
		if (deleteAdd) {
			//delete existing mapping
			EndPointDevicesExample epde = new EndPointDevicesExample();
			epde.createCriteria().andEndPointIdEqualTo(endPointId);
			epdm.deleteByExample(epde);
		}
	
		EndPointDevices epd = new EndPointDevices();
		epd.setEndPointId(endPointId);
		epd.setCreatedBy(ep.getCreatedBy());
		epd.setCreationDate(ep.getCreationDate());
		
		for(int i=0; i<dil.size(); i++) {
			epd.setDeviceInstId(dil.get(i).getDiObj().getDeviceInstId());
			epd.setUsedFor(dil.get(i).getUsedFor());
			epdm.insert(epd);
		}
		
		return 0;
	}
	
}
