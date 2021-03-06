package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.SubjectLog;
import com.betterit.kaligia.dao.model.kaligia.SubjectLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubjectLogMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.subjectlog
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	int deleteByExample(SubjectLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.subjectlog
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	int insert(SubjectLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.subjectlog
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	int insertSelective(SubjectLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.subjectlog
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	List<SubjectLog> selectByExample(SubjectLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.subjectlog
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	int updateByExampleSelective(@Param("record") SubjectLog record, @Param("example") SubjectLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.subjectlog
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	int updateByExample(@Param("record") SubjectLog record, @Param("example") SubjectLogExample example);
}