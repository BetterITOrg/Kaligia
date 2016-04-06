/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;

import com.betterit.kaligia.dao.model.kaligia.TestProcedureSpecs;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpec;

/**
 * @author V135012
 *
 */
public class ProcedureView {
	
	private int procedureID;
	private String name;
	private String description;
	private String type;
	private String status;
	private int noSegments;
	
	private List<TestProcedureSpecs> procSpecs;
	
	private List<TestSegmentSpec>[] segParams;
	
}
