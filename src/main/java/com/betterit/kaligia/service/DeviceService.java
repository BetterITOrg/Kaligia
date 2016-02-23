/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.DeviceExample;
import com.betterit.kaligia.dao.repository.kaligia.DeviceMapper;
import com.betterit.kaligia.dao.repository.kaligia.DeviceSpecMapper;

/**
 * @author V135012
 *
 */
@Service
public class DeviceService {
	
	@Autowired
	private DeviceMapper dm;
	
	@Autowired 
	private DeviceSpecMapper dsm;
	
	public List<Device> findAll() {
		return dm.selectByExample(null);	
	}
	
	public List<Device> findByType(String type) {
		DeviceExample de = new DeviceExample();
		de.createCriteria().andTypeEqualTo(type);
		return dm.selectByExample(de);
	}
}
