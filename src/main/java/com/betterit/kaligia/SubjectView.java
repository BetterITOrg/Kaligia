/**
 * 
 */
package com.betterit.kaligia;

import java.util.Date;
import java.util.List;

import com.betterit.kaligia.dao.model.kaligia.SubjectLog;

/**
 * @author V135012
 *
 */
public class SubjectView {
	
	private int specimenID;
	private String specimen;
	private String specType;
	
	private int subjectID;
	private String subName;
	private Date subDOB;
	private String subGender;
	private String subEthnicity;
	
	private List<SubjectLog> subVitals;

}
