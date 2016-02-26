package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.RunSegmentLog;
import com.betterit.kaligia.dao.model.kaligia.RunSegmentLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunSegmentLogMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int deleteByExample(RunSegmentLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int insert(RunSegmentLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int insertSelective(RunSegmentLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	List<RunSegmentLog> selectByExample(RunSegmentLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int updateByExampleSelective(@Param("record") RunSegmentLog record, @Param("example") RunSegmentLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegmentlog
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int updateByExample(@Param("record") RunSegmentLog record, @Param("example") RunSegmentLogExample example);
}