package com.betterit.kaligia.dao.model.kaligia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceInstExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public DeviceInstExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andDeviceInstIdIsNull() {
			addCriterion("device_inst_id is null");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdIsNotNull() {
			addCriterion("device_inst_id is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdEqualTo(Integer value) {
			addCriterion("device_inst_id =", value, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdNotEqualTo(Integer value) {
			addCriterion("device_inst_id <>", value, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdGreaterThan(Integer value) {
			addCriterion("device_inst_id >", value, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("device_inst_id >=", value, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdLessThan(Integer value) {
			addCriterion("device_inst_id <", value, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdLessThanOrEqualTo(Integer value) {
			addCriterion("device_inst_id <=", value, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdIn(List<Integer> values) {
			addCriterion("device_inst_id in", values, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdNotIn(List<Integer> values) {
			addCriterion("device_inst_id not in", values, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdBetween(Integer value1, Integer value2) {
			addCriterion("device_inst_id between", value1, value2, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceInstIdNotBetween(Integer value1, Integer value2) {
			addCriterion("device_inst_id not between", value1, value2, "deviceInstId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNull() {
			addCriterion("device_id is null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIsNotNull() {
			addCriterion("device_id is not null");
			return (Criteria) this;
		}

		public Criteria andDeviceIdEqualTo(Integer value) {
			addCriterion("device_id =", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotEqualTo(Integer value) {
			addCriterion("device_id <>", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThan(Integer value) {
			addCriterion("device_id >", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("device_id >=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThan(Integer value) {
			addCriterion("device_id <", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdLessThanOrEqualTo(Integer value) {
			addCriterion("device_id <=", value, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdIn(List<Integer> values) {
			addCriterion("device_id in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotIn(List<Integer> values) {
			addCriterion("device_id not in", values, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdBetween(Integer value1, Integer value2) {
			addCriterion("device_id between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andDeviceIdNotBetween(Integer value1, Integer value2) {
			addCriterion("device_id not between", value1, value2, "deviceId");
			return (Criteria) this;
		}

		public Criteria andSerialIdIsNull() {
			addCriterion("serial_id is null");
			return (Criteria) this;
		}

		public Criteria andSerialIdIsNotNull() {
			addCriterion("serial_id is not null");
			return (Criteria) this;
		}

		public Criteria andSerialIdEqualTo(String value) {
			addCriterion("serial_id =", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdNotEqualTo(String value) {
			addCriterion("serial_id <>", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdGreaterThan(String value) {
			addCriterion("serial_id >", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdGreaterThanOrEqualTo(String value) {
			addCriterion("serial_id >=", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdLessThan(String value) {
			addCriterion("serial_id <", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdLessThanOrEqualTo(String value) {
			addCriterion("serial_id <=", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdLike(String value) {
			addCriterion("serial_id like", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdNotLike(String value) {
			addCriterion("serial_id not like", value, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdIn(List<String> values) {
			addCriterion("serial_id in", values, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdNotIn(List<String> values) {
			addCriterion("serial_id not in", values, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdBetween(String value1, String value2) {
			addCriterion("serial_id between", value1, value2, "serialId");
			return (Criteria) this;
		}

		public Criteria andSerialIdNotBetween(String value1, String value2) {
			addCriterion("serial_id not between", value1, value2, "serialId");
			return (Criteria) this;
		}

		public Criteria andPartNoIsNull() {
			addCriterion("part_no is null");
			return (Criteria) this;
		}

		public Criteria andPartNoIsNotNull() {
			addCriterion("part_no is not null");
			return (Criteria) this;
		}

		public Criteria andPartNoEqualTo(String value) {
			addCriterion("part_no =", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoNotEqualTo(String value) {
			addCriterion("part_no <>", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoGreaterThan(String value) {
			addCriterion("part_no >", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoGreaterThanOrEqualTo(String value) {
			addCriterion("part_no >=", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoLessThan(String value) {
			addCriterion("part_no <", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoLessThanOrEqualTo(String value) {
			addCriterion("part_no <=", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoLike(String value) {
			addCriterion("part_no like", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoNotLike(String value) {
			addCriterion("part_no not like", value, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoIn(List<String> values) {
			addCriterion("part_no in", values, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoNotIn(List<String> values) {
			addCriterion("part_no not in", values, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoBetween(String value1, String value2) {
			addCriterion("part_no between", value1, value2, "partNo");
			return (Criteria) this;
		}

		public Criteria andPartNoNotBetween(String value1, String value2) {
			addCriterion("part_no not between", value1, value2, "partNo");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<String> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<String> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andCreatedByIsNull() {
			addCriterion("created_by is null");
			return (Criteria) this;
		}

		public Criteria andCreatedByIsNotNull() {
			addCriterion("created_by is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedByEqualTo(Integer value) {
			addCriterion("created_by =", value, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByNotEqualTo(Integer value) {
			addCriterion("created_by <>", value, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByGreaterThan(Integer value) {
			addCriterion("created_by >", value, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByGreaterThanOrEqualTo(Integer value) {
			addCriterion("created_by >=", value, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByLessThan(Integer value) {
			addCriterion("created_by <", value, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByLessThanOrEqualTo(Integer value) {
			addCriterion("created_by <=", value, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByIn(List<Integer> values) {
			addCriterion("created_by in", values, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByNotIn(List<Integer> values) {
			addCriterion("created_by not in", values, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByBetween(Integer value1, Integer value2) {
			addCriterion("created_by between", value1, value2, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreatedByNotBetween(Integer value1, Integer value2) {
			addCriterion("created_by not between", value1, value2, "createdBy");
			return (Criteria) this;
		}

		public Criteria andCreationDateIsNull() {
			addCriterion("creation_date is null");
			return (Criteria) this;
		}

		public Criteria andCreationDateIsNotNull() {
			addCriterion("creation_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreationDateEqualTo(Date value) {
			addCriterion("creation_date =", value, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateNotEqualTo(Date value) {
			addCriterion("creation_date <>", value, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateGreaterThan(Date value) {
			addCriterion("creation_date >", value, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateGreaterThanOrEqualTo(Date value) {
			addCriterion("creation_date >=", value, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateLessThan(Date value) {
			addCriterion("creation_date <", value, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateLessThanOrEqualTo(Date value) {
			addCriterion("creation_date <=", value, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateIn(List<Date> values) {
			addCriterion("creation_date in", values, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateNotIn(List<Date> values) {
			addCriterion("creation_date not in", values, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateBetween(Date value1, Date value2) {
			addCriterion("creation_date between", value1, value2, "creationDate");
			return (Criteria) this;
		}

		public Criteria andCreationDateNotBetween(Date value1, Date value2) {
			addCriterion("creation_date not between", value1, value2, "creationDate");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.deviceinst
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table kaligia.deviceinst
     *
     * @mbggenerated do_not_delete_during_merge Mon Mar 07 17:33:12 EST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}