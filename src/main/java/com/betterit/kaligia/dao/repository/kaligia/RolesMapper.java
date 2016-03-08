package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.Roles;
import com.betterit.kaligia.dao.model.kaligia.RolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int deleteByExample(RolesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int deleteByPrimaryKey(Integer roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int insert(Roles record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int insertSelective(Roles record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	List<Roles> selectByExample(RolesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	Roles selectByPrimaryKey(Integer roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int updateByPrimaryKeySelective(Roles record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.roles
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	int updateByPrimaryKey(Roles record);
}