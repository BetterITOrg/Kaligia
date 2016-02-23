package com.betterit.kaligia.dao.repository.kaligia;

import com.betterit.kaligia.dao.model.kaligia.DeviceSpec;
import com.betterit.kaligia.dao.model.kaligia.DeviceSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeviceSpecMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.devicespec
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	int deleteByExample(DeviceSpecExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.devicespec
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	int insert(DeviceSpec record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.devicespec
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	int insertSelective(DeviceSpec record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.devicespec
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	List<DeviceSpec> selectByExample(DeviceSpecExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.devicespec
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	int updateByExampleSelective(@Param("record") DeviceSpec record, @Param("example") DeviceSpecExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.devicespec
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	int updateByExample(@Param("record") DeviceSpec record, @Param("example") DeviceSpecExample example);
}