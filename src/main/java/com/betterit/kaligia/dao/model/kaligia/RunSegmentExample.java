package com.betterit.kaligia.dao.model.kaligia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunSegmentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public RunSegmentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
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

		public Criteria andRunSegmentIdIsNull() {
			addCriterion("run_segment_id is null");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdIsNotNull() {
			addCriterion("run_segment_id is not null");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdEqualTo(Integer value) {
			addCriterion("run_segment_id =", value, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdNotEqualTo(Integer value) {
			addCriterion("run_segment_id <>", value, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdGreaterThan(Integer value) {
			addCriterion("run_segment_id >", value, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("run_segment_id >=", value, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdLessThan(Integer value) {
			addCriterion("run_segment_id <", value, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdLessThanOrEqualTo(Integer value) {
			addCriterion("run_segment_id <=", value, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdIn(List<Integer> values) {
			addCriterion("run_segment_id in", values, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdNotIn(List<Integer> values) {
			addCriterion("run_segment_id not in", values, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdBetween(Integer value1, Integer value2) {
			addCriterion("run_segment_id between", value1, value2, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andRunSegmentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("run_segment_id not between", value1, value2, "runSegmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdIsNull() {
			addCriterion("segment_id is null");
			return (Criteria) this;
		}

		public Criteria andSegmentIdIsNotNull() {
			addCriterion("segment_id is not null");
			return (Criteria) this;
		}

		public Criteria andSegmentIdEqualTo(Integer value) {
			addCriterion("segment_id =", value, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdNotEqualTo(Integer value) {
			addCriterion("segment_id <>", value, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdGreaterThan(Integer value) {
			addCriterion("segment_id >", value, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("segment_id >=", value, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdLessThan(Integer value) {
			addCriterion("segment_id <", value, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdLessThanOrEqualTo(Integer value) {
			addCriterion("segment_id <=", value, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdIn(List<Integer> values) {
			addCriterion("segment_id in", values, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdNotIn(List<Integer> values) {
			addCriterion("segment_id not in", values, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdBetween(Integer value1, Integer value2) {
			addCriterion("segment_id between", value1, value2, "segmentId");
			return (Criteria) this;
		}

		public Criteria andSegmentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("segment_id not between", value1, value2, "segmentId");
			return (Criteria) this;
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

		public Criteria andRunNoIsNull() {
			addCriterion("run_no is null");
			return (Criteria) this;
		}

		public Criteria andRunNoIsNotNull() {
			addCriterion("run_no is not null");
			return (Criteria) this;
		}

		public Criteria andRunNoEqualTo(Integer value) {
			addCriterion("run_no =", value, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoNotEqualTo(Integer value) {
			addCriterion("run_no <>", value, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoGreaterThan(Integer value) {
			addCriterion("run_no >", value, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoGreaterThanOrEqualTo(Integer value) {
			addCriterion("run_no >=", value, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoLessThan(Integer value) {
			addCriterion("run_no <", value, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoLessThanOrEqualTo(Integer value) {
			addCriterion("run_no <=", value, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoIn(List<Integer> values) {
			addCriterion("run_no in", values, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoNotIn(List<Integer> values) {
			addCriterion("run_no not in", values, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoBetween(Integer value1, Integer value2) {
			addCriterion("run_no between", value1, value2, "runNo");
			return (Criteria) this;
		}

		public Criteria andRunNoNotBetween(Integer value1, Integer value2) {
			addCriterion("run_no not between", value1, value2, "runNo");
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
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.runsegment
	 * @mbggenerated  Mon Mar 07 21:27:55 EST 2016
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
     * This class corresponds to the database table kaligia.runsegment
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 23 09:56:32 EST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}