package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.model.kaligia.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int deleteByExample(UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int deleteByPrimaryKey(Integer userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int insert(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int insertSelective(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	List<Users> selectByExample(UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	Users selectByPrimaryKey(Integer userId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int updateByPrimaryKeySelective(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.users
	 * @mbggenerated  Thu Feb 25 22:09:43 EST 2016
	 */
	int updateByPrimaryKey(Users record);
}