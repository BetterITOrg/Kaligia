package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.ProcSegment;
import com.betterit.kaligia.dao.model.kaligia.ProcSegmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcSegmentMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int deleteByExample(ProcSegmentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int insert(ProcSegment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int insertSelective(ProcSegment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	List<ProcSegment> selectByExample(ProcSegmentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int updateByExampleSelective(@Param("record") ProcSegment record, @Param("example") ProcSegmentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int updateByExample(@Param("record") ProcSegment record, @Param("example") ProcSegmentExample example);
}