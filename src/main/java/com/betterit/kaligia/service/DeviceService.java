/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.DeviceExample;
import com.betterit.kaligia.dao.model.kaligia.DeviceInst;
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
	
	public int insertDeviceInst(List<DeviceInst> dil) {
		for(int i=0; i<dil.size(); i++) {
			dim.insert(dil.get(i));
		}
		
		return 0;
	}
}
