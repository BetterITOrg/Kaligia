/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.Site;
import com.betterit.kaligia.dao.model.kaligia.SiteExample;
import com.betterit.kaligia.dao.repository.kaligia.SiteMapper;

/**
 * @author V135012
 *
 */
@Service
public class SiteService {
	
	private static final Logger log = LoggerFactory.getLogger(SiteService.class);
	
	@Autowired
	private SiteMapper siteMapper;
	
	public Site addSite(Site site) throws Exception {
		
		if(site != null) {
			int rc = siteMapper.insert(site);
			if(rc != 1 ) {
				log.info("Failed to insert Site.");
			}
		} else {
			log.info("Site is Null");
			throw new Exception("Site is Null");
		}	
		return site;
	}

	public List<Site> getSiteByName(String Name) {
		
		SiteExample se = new SiteExample();
		se.createCriteria().andNameEqualTo(Name);
		return siteMapper.selectByExample(se);
		
	}
	
	public List<Site> findAll() {
		return siteMapper.selectByExample(null);	
	}
}
