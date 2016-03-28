package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpec;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestSegmentSpecMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.testsegmentspec
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	int deleteByExample(TestSegmentSpecExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.testsegmentspec
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	int insert(TestSegmentSpec record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.testsegmentspec
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	int insertSelective(TestSegmentSpec record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.testsegmentspec
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	List<TestSegmentSpec> selectByExample(TestSegmentSpecExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.testsegmentspec
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	int updateByExampleSelective(@Param("record") TestSegmentSpec record,
			@Param("example") TestSegmentSpecExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.testsegmentspec
	 * @mbggenerated  Mon Mar 28 12:28:14 EDT 2016
	 */
	int updateByExample(@Param("record") TestSegmentSpec record, @Param("example") TestSegmentSpecExample example);
}