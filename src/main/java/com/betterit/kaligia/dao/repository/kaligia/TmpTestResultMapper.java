package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.TmpTestResult;
import com.betterit.kaligia.dao.model.kaligia.TmpTestResultExample;
import java.util.List;

public interface TmpTestResultMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.tmp_testresult
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	List<TmpTestResult> selectByExample(TmpTestResultExample example);
}