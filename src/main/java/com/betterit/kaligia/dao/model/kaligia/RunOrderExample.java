package com.betterit.kaligia.dao.model.kaligia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunOrderExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public RunOrderExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runorder
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.runorder
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

		public Criteria andRunIdIsNull() {
			addCriterion("run_id is null");
			return (Criteria) this;
		}

		public Criteria andRunIdIsNotNull() {
			addCriterion("run_id is not null");
			return (Criteria) this;
		}

		public Criteria andRunIdEqualTo(Integer value) {
			addCriterion("run_id =", value, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdNotEqualTo(Integer value) {
			addCriterion("run_id <>", value, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdGreaterThan(Integer value) {
			addCriterion("run_id >", value, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("run_id >=", value, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdLessThan(Integer value) {
			addCriterion("run_id <", value, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdLessThanOrEqualTo(Integer value) {
			addCriterion("run_id <=", value, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdIn(List<Integer> values) {
			addCriterion("run_id in", values, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdNotIn(List<Integer> values) {
			addCriterion("run_id not in", values, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdBetween(Integer value1, Integer value2) {
			addCriterion("run_id between", value1, value2, "runId");
			return (Criteria) this;
		}

		public Criteria andRunIdNotBetween(Integer value1, Integer value2) {
			addCriterion("run_id not between", value1, value2, "runId");
			return (Criteria) this;
		}

		public Criteria andOrderIdIsNull() {
			addCriterion("order_id is null");
			return (Criteria) this;
		}

		public Criteria andOrderIdIsNotNull() {
			addCriterion("order_id is not null");
			return (Criteria) this;
		}

		public Criteria andOrderIdEqualTo(Integer value) {
			addCriterion("order_id =", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdNotEqualTo(Integer value) {
			addCriterion("order_id <>", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdGreaterThan(Integer value) {
			addCriterion("order_id >", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("order_id >=", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdLessThan(Integer value) {
			addCriterion("order_id <", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
			addCriterion("order_id <=", value, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdIn(List<Integer> values) {
			addCriterion("order_id in", values, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdNotIn(List<Integer> values) {
			addCriterion("order_id not in", values, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdBetween(Integer value1, Integer value2) {
			addCriterion("order_id between", value1, value2, "orderId");
			return (Criteria) this;
		}

		public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
			addCriterion("order_id not between", value1, value2, "orderId");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("type like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("type not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andProcedureIdIsNull() {
			addCriterion("procedure_id is null");
			return (Criteria) this;
		}

		public Criteria andProcedureIdIsNotNull() {
			addCriterion("procedure_id is not null");
			return (Criteria) this;
		}

		public Criteria andProcedureIdEqualTo(Integer value) {
			addCriterion("procedure_id =", value, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdNotEqualTo(Integer value) {
			addCriterion("procedure_id <>", value, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdGreaterThan(Integer value) {
			addCriterion("procedure_id >", value, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("procedure_id >=", value, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdLessThan(Integer value) {
			addCriterion("procedure_id <", value, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdLessThanOrEqualTo(Integer value) {
			addCriterion("procedure_id <=", value, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdIn(List<Integer> values) {
			addCriterion("procedure_id in", values, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdNotIn(List<Integer> values) {
			addCriterion("procedure_id not in", values, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdBetween(Integer value1, Integer value2) {
			addCriterion("procedure_id between", value1, value2, "procedureId");
			return (Criteria) this;
		}

		public Criteria andProcedureIdNotBetween(Integer value1, Integer value2) {
			addCriterion("procedure_id not between", value1, value2, "procedureId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdIsNull() {
			addCriterion("specimen_id is null");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdIsNotNull() {
			addCriterion("specimen_id is not null");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdEqualTo(Integer value) {
			addCriterion("specimen_id =", value, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdNotEqualTo(Integer value) {
			addCriterion("specimen_id <>", value, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdGreaterThan(Integer value) {
			addCriterion("specimen_id >", value, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("specimen_id >=", value, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdLessThan(Integer value) {
			addCriterion("specimen_id <", value, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdLessThanOrEqualTo(Integer value) {
			addCriterion("specimen_id <=", value, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdIn(List<Integer> values) {
			addCriterion("specimen_id in", values, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdNotIn(List<Integer> values) {
			addCriterion("specimen_id not in", values, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdBetween(Integer value1, Integer value2) {
			addCriterion("specimen_id between", value1, value2, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSpecimenIdNotBetween(Integer value1, Integer value2) {
			addCriterion("specimen_id not between", value1, value2, "specimenId");
			return (Criteria) this;
		}

		public Criteria andSiteIdIsNull() {
			addCriterion("site_id is null");
			return (Criteria) this;
		}

		public Criteria andSiteIdIsNotNull() {
			addCriterion("site_id is not null");
			return (Criteria) this;
		}

		public Criteria andSiteIdEqualTo(Integer value) {
			addCriterion("site_id =", value, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdNotEqualTo(Integer value) {
			addCriterion("site_id <>", value, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdGreaterThan(Integer value) {
			addCriterion("site_id >", value, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("site_id >=", value, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdLessThan(Integer value) {
			addCriterion("site_id <", value, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdLessThanOrEqualTo(Integer value) {
			addCriterion("site_id <=", value, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdIn(List<Integer> values) {
			addCriterion("site_id in", values, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdNotIn(List<Integer> values) {
			addCriterion("site_id not in", values, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdBetween(Integer value1, Integer value2) {
			addCriterion("site_id between", value1, value2, "siteId");
			return (Criteria) this;
		}

		public Criteria andSiteIdNotBetween(Integer value1, Integer value2) {
			addCriterion("site_id not between", value1, value2, "siteId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdIsNull() {
			addCriterion("end_point_id is null");
			return (Criteria) this;
		}

		public Criteria andEndPointIdIsNotNull() {
			addCriterion("end_point_id is not null");
			return (Criteria) this;
		}

		public Criteria andEndPointIdEqualTo(Integer value) {
			addCriterion("end_point_id =", value, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdNotEqualTo(Integer value) {
			addCriterion("end_point_id <>", value, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdGreaterThan(Integer value) {
			addCriterion("end_point_id >", value, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("end_point_id >=", value, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdLessThan(Integer value) {
			addCriterion("end_point_id <", value, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdLessThanOrEqualTo(Integer value) {
			addCriterion("end_point_id <=", value, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdIn(List<Integer> values) {
			addCriterion("end_point_id in", values, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdNotIn(List<Integer> values) {
			addCriterion("end_point_id not in", values, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdBetween(Integer value1, Integer value2) {
			addCriterion("end_point_id between", value1, value2, "endPointId");
			return (Criteria) this;
		}

		public Criteria andEndPointIdNotBetween(Integer value1, Integer value2) {
			addCriterion("end_point_id not between", value1, value2, "endPointId");
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

		public Criteria andStartTimeIsNull() {
			addCriterion("start_time is null");
			return (Criteria) this;
		}

		public Criteria andStartTimeIsNotNull() {
			addCriterion("start_time is not null");
			return (Criteria) this;
		}

		public Criteria andStartTimeEqualTo(Date value) {
			addCriterion("start_time =", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotEqualTo(Date value) {
			addCriterion("start_time <>", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeGreaterThan(Date value) {
			addCriterion("start_time >", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("start_time >=", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeLessThan(Date value) {
			addCriterion("start_time <", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeLessThanOrEqualTo(Date value) {
			addCriterion("start_time <=", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeIn(List<Date> values) {
			addCriterion("start_time in", values, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotIn(List<Date> values) {
			addCriterion("start_time not in", values, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeBetween(Date value1, Date value2) {
			addCriterion("start_time between", value1, value2, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotBetween(Date value1, Date value2) {
			addCriterion("start_time not between", value1, value2, "startTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeIsNull() {
			addCriterion("end_time is null");
			return (Criteria) this;
		}

		public Criteria andEndTimeIsNotNull() {
			addCriterion("end_time is not null");
			return (Criteria) this;
		}

		public Criteria andEndTimeEqualTo(Date value) {
			addCriterion("end_time =", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotEqualTo(Date value) {
			addCriterion("end_time <>", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeGreaterThan(Date value) {
			addCriterion("end_time >", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("end_time >=", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeLessThan(Date value) {
			addCriterion("end_time <", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeLessThanOrEqualTo(Date value) {
			addCriterion("end_time <=", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeIn(List<Date> values) {
			addCriterion("end_time in", values, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotIn(List<Date> values) {
			addCriterion("end_time not in", values, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeBetween(Date value1, Date value2) {
			addCriterion("end_time between", value1, value2, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotBetween(Date value1, Date value2) {
			addCriterion("end_time not between", value1, value2, "endTime");
			return (Criteria) this;
		}

		public Criteria andCreatedDateIsNull() {
			addCriterion("created_date is null");
			return (Criteria) this;
		}

		public Criteria andCreatedDateIsNotNull() {
			addCriterion("created_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedDateEqualTo(Date value) {
			addCriterion("created_date =", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateNotEqualTo(Date value) {
			addCriterion("created_date <>", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateGreaterThan(Date value) {
			addCriterion("created_date >", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
			addCriterion("created_date >=", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateLessThan(Date value) {
			addCriterion("created_date <", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
			addCriterion("created_date <=", value, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateIn(List<Date> values) {
			addCriterion("created_date in", values, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateNotIn(List<Date> values) {
			addCriterion("created_date not in", values, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateBetween(Date value1, Date value2) {
			addCriterion("created_date between", value1, value2, "createdDate");
			return (Criteria) this;
		}

		public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
			addCriterion("created_date not between", value1, value2, "createdDate");
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

		public Criteria andRunNotesIsNull() {
			addCriterion("run_notes is null");
			return (Criteria) this;
		}

		public Criteria andRunNotesIsNotNull() {
			addCriterion("run_notes is not null");
			return (Criteria) this;
		}

		public Criteria andRunNotesEqualTo(String value) {
			addCriterion("run_notes =", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesNotEqualTo(String value) {
			addCriterion("run_notes <>", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesGreaterThan(String value) {
			addCriterion("run_notes >", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesGreaterThanOrEqualTo(String value) {
			addCriterion("run_notes >=", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesLessThan(String value) {
			addCriterion("run_notes <", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesLessThanOrEqualTo(String value) {
			addCriterion("run_notes <=", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesLike(String value) {
			addCriterion("run_notes like", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesNotLike(String value) {
			addCriterion("run_notes not like", value, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesIn(List<String> values) {
			addCriterion("run_notes in", values, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesNotIn(List<String> values) {
			addCriterion("run_notes not in", values, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesBetween(String value1, String value2) {
			addCriterion("run_notes between", value1, value2, "runNotes");
			return (Criteria) this;
		}

		public Criteria andRunNotesNotBetween(String value1, String value2) {
			addCriterion("run_notes not between", value1, value2, "runNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesIsNull() {
			addCriterion("result_notes is null");
			return (Criteria) this;
		}

		public Criteria andResultNotesIsNotNull() {
			addCriterion("result_notes is not null");
			return (Criteria) this;
		}

		public Criteria andResultNotesEqualTo(String value) {
			addCriterion("result_notes =", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesNotEqualTo(String value) {
			addCriterion("result_notes <>", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesGreaterThan(String value) {
			addCriterion("result_notes >", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesGreaterThanOrEqualTo(String value) {
			addCriterion("result_notes >=", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesLessThan(String value) {
			addCriterion("result_notes <", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesLessThanOrEqualTo(String value) {
			addCriterion("result_notes <=", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesLike(String value) {
			addCriterion("result_notes like", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesNotLike(String value) {
			addCriterion("result_notes not like", value, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesIn(List<String> values) {
			addCriterion("result_notes in", values, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesNotIn(List<String> values) {
			addCriterion("result_notes not in", values, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesBetween(String value1, String value2) {
			addCriterion("result_notes between", value1, value2, "resultNotes");
			return (Criteria) this;
		}

		public Criteria andResultNotesNotBetween(String value1, String value2) {
			addCriterion("result_notes not between", value1, value2, "resultNotes");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.runorder
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
     * This class corresponds to the database table kaligia.runorder
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 23 09:56:32 EST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}