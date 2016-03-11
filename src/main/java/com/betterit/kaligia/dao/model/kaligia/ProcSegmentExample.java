package com.betterit.kaligia.dao.model.kaligia;

import java.util.ArrayList;
import java.util.List;

public class ProcSegmentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public ProcSegmentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Fri Mar 11 11:31:47 EST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.procsegment
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

		public Criteria andSegmentNoIsNull() {
			addCriterion("segment_no is null");
			return (Criteria) this;
		}

		public Criteria andSegmentNoIsNotNull() {
			addCriterion("segment_no is not null");
			return (Criteria) this;
		}

		public Criteria andSegmentNoEqualTo(Integer value) {
			addCriterion("segment_no =", value, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoNotEqualTo(Integer value) {
			addCriterion("segment_no <>", value, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoGreaterThan(Integer value) {
			addCriterion("segment_no >", value, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoGreaterThanOrEqualTo(Integer value) {
			addCriterion("segment_no >=", value, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoLessThan(Integer value) {
			addCriterion("segment_no <", value, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoLessThanOrEqualTo(Integer value) {
			addCriterion("segment_no <=", value, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoIn(List<Integer> values) {
			addCriterion("segment_no in", values, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoNotIn(List<Integer> values) {
			addCriterion("segment_no not in", values, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoBetween(Integer value1, Integer value2) {
			addCriterion("segment_no between", value1, value2, "segmentNo");
			return (Criteria) this;
		}

		public Criteria andSegmentNoNotBetween(Integer value1, Integer value2) {
			addCriterion("segment_no not between", value1, value2, "segmentNo");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.procsegment
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
     * This class corresponds to the database table kaligia.procsegment
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 23 09:56:32 EST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}