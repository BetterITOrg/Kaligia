package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.Privileges;
import com.betterit.kaligia.dao.model.kaligia.PrivilegesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrivilegesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int deleteByExample(PrivilegesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int deleteByPrimaryKey(Integer privId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int insert(Privileges record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int insertSelective(Privileges record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	List<Privileges> selectByExample(PrivilegesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	Privileges selectByPrimaryKey(Integer privId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int updateByExampleSelective(@Param("record") Privileges record, @Param("example") PrivilegesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int updateByExample(@Param("record") Privileges record, @Param("example") PrivilegesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int updateByPrimaryKeySelective(Privileges record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.privileges
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	int updateByPrimaryKey(Privileges record);
}