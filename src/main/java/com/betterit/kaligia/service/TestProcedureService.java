/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.ProcSegment;
import com.betterit.kaligia.dao.model.kaligia.ProcSegmentExample;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.dao.model.kaligia.TestProcedureExample;
import com.betterit.kaligia.dao.model.kaligia.TestSegment;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpec;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.model.kaligia.UsersExample;
import com.betterit.kaligia.dao.repository.kaligia.ProcSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestProcedureMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentSpecMapper;
import com.betterit.kaligia.dao.repository.kaligia.UsersMapper;

/**
 * @author V135012
 *
 */
@Service
public class TestProcedureService {
	
	@Autowired
	private TestProcedureMapper tpm;
	
	@Autowired
	private ProcSegmentMapper psm;
	
	@Autowired
	private TestSegmentMapper tsm;
	
	@Autowired
	private TestSegmentSpecMapper tssm;
	
	@Autowired
	private UsersMapper um;
	
	public List<TestProcedure> findAll() {
		
		List<TestProcedure> tpl = tpm.selectByExample(null);
		return tpl;
		
	}

	
	public List<TestProcedure> findAllByType(String type) {
		
		TestProcedureExample tpe = new TestProcedureExample();
		tpe.createCriteria().andTypeEqualTo(type);
		List<TestProcedure> tpl = tpm.selectByExample(tpe);
		return tpl;
		
	}

	public int runTestProcedure(TestProcedure tp) {
		
		return 0;
	}
	
	public List<TestSegment> getSegmentForProc(TestProcedure tp) {
		
		List<ProcSegment> psl;
		List<TestSegment> tsl = new ArrayList<TestSegment>();
		
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(tp.getProcedureId());
		psl = psm.selectByExample(pse);
		for(int i=0; i<psl.size(); i++) {
			tsl.add(tsm.selectByPrimaryKey(psl.get(i).getSegmentId()));
		}
		return tsl;
	}
	
	public int createTestProcedure(
			String name,
			String description,
			String status,
			int noOfSegments
			) {
		
		UsersExample ue = new UsersExample();
		ue.createCriteria().andNameEqualTo("Olesia Gololobova");
		List<Users> user=um.selectByExample(ue);
		
		TestProcedure tp = new TestProcedure();
		tp.setName(name);
		tp.setDescription(description);
		tp.setStatus(status);
		tp.setNoOfSegments(noOfSegments);
		tp.setCreationDate(new Date());
		tp.setCreatedBy(user.get(0).getUserId());
		return 0;
	}

}