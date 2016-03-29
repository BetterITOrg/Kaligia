/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.DeviceList;
import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.DeviceExample;
import com.betterit.kaligia.dao.model.kaligia.DeviceInst;
import com.betterit.kaligia.dao.model.kaligia.DeviceInstExample;
import com.betterit.kaligia.dao.model.kaligia.EndPointDevices;
import com.betterit.kaligia.dao.repository.kaligia.DeviceInstMapper;
import com.betterit.kaligia.dao.repository.kaligia.DeviceMapper;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author V135012
 *
 */
@Service
public class DeviceService {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceService.class);
	
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
			diex.clear();
			diex.createCriteria().andDeviceIdEqualTo(dil.get(i).getDiObj().getDeviceId()).andSerialIdEqualTo(dil.get(i).getDiObj().getSerialId());
			List<DeviceInst> dlist = dim.selectByExample(diex);
			if(dlist.size() == 0) {
				dim.insert(dil.get(i).getDiObj());
				log.info("Device Inst ID: " + dil.get(i).getDiObj().getDeviceInstId());
			} else {
				dil.get(i).getDiObj().setDeviceInstId(dlist.get(0).getDeviceInstId());
			}
		}
		
		return 0;
	}
	
	public List<DeviceInst> getDeviceInst(List<EndPointDevices> epdl) {
		 
		List<DeviceInst> dil = new ArrayList<DeviceInst>();
		for(int i=0; i<epdl.size(); i++) {
			dil.add(dim.selectByPrimaryKey(epdl.get(i).getDeviceInstId()));
		}
		
		return dil;
	}

	public List<DeviceList> getDeviceInstOrd(List<EndPointDevices> epdl) {
		 
		DeviceList[] dilo = new DeviceList[9];
		DeviceInst di;
		int compute = 0;
		int laser = 1;
		int spectrometer = 2;
		int probe1 = 3;
		int labjack = 4;
		int collect = 5;
		int excite = 6;
		int probe2 = 7;
		int camera = 8;
		int p=1;
		
		for(int j=0; j<9; j++) {
			dilo[j] = new DeviceList();
		}
		
		for(int i=0; i<epdl.size(); i++) {
			di = dim.selectByPrimaryKey(epdl.get(i).getDeviceInstId());
			Device device = dm.selectByPrimaryKey(di.getDeviceId());
			switch(device.getType()) {
			case "Compute" : dilo[compute].setDiObj(di); dilo[compute].setUsedFor(epdl.get(i).getUsedFor()); break;
			case "Laser" : dilo[laser].setDiObj(di); dilo[laser].setUsedFor(epdl.get(i).getUsedFor()); break;
			case "Spectrometer" : dilo[spectrometer].setDiObj(di); dilo[spectrometer].setUsedFor(epdl.get(i).getUsedFor()); break;
			case "Probe" : 
				if(p==1) { 
					dilo[probe1].setDiObj(di); 
					p++; 
					dilo[probe1].setUsedFor(epdl.get(i).getUsedFor());
				} else { 
					dilo[probe2].setDiObj(di); 
					dilo[probe2].setUsedFor(epdl.get(i).getUsedFor());
				} 
				break;
			case "LabJack" : dilo[labjack].setDiObj(di); dilo[labjack].setUsedFor(epdl.get(i).getUsedFor()); break;
			case "ExcitationFiber" : dilo[excite].setDiObj(di); dilo[excite].setUsedFor(epdl.get(i).getUsedFor()); break;
			case "CollectionFiber" : dilo[collect].setDiObj(di); dilo[collect].setUsedFor(epdl.get(i).getUsedFor()); break;
			case "Camera" : dilo[camera].setDiObj(di); dilo[camera].setUsedFor(epdl.get(i).getUsedFor()); break;
			}
		}
		
		return Arrays.asList(dilo);
	}
}
