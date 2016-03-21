/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.DeviceList;
import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.DeviceExample;
import com.betterit.kaligia.dao.model.kaligia.DeviceInst;
import com.betterit.kaligia.dao.model.kaligia.DeviceInstExample;
import com.betterit.kaligia.dao.repository.kaligia.DeviceInstMapper;
import com.betterit.kaligia.dao.repository.kaligia.DeviceMapper;

/**
 * @author V135012
 *
 */
@Service
public class DeviceService {
	
	@Autowired
	private DeviceMapper dm;
	
	@Autowired
	private DeviceInstMapper dim;
	
	
	public List<Device> findAll() {
		return dm.selectByExample(null);	
	}
	
	public List<Device> findByType(String type) {
		DeviceExample de = new DeviceExample();
		de.createCriteria().andTypeEqualTo(type);
		return dm.selectByExample(de);
	}
	
	public int insertDeviceInst(List<DeviceList> dil) {
		
		DeviceInstExample diex = new DeviceInstExample();
		
		for(int i=0; i<dil.size(); i++) {
			//Check if already existing
			diex.createCriteria().andDeviceIdEqualTo(dil.get(i).getDiObj().getDeviceId()).andSerialIdEqualTo(dil.get(i).getDiObj().getSerialId());
			List<DeviceInst> dlist = dim.selectByExample(diex);
			if(dlist.size() == 0) {
				dim.insert(dil.get(i).getDiObj());
			} else {
				dil.get(i).getDiObj().setDeviceInstId(dlist.get(0).getDeviceInstId());
			}
		}
		
		return 0;
	}
}
