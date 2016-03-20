package com.betterit.kaligia.dao.model.kaligia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EndPointExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public EndPointExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
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

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("description not between", value1, value2, "description");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table kaligia.endpoint
	 * @mbggenerated  Sun Mar 20 15:53:29 EDT 2016
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
     * This class corresponds to the database table kaligia.endpoint
     *
     * @mbggenerated do_not_delete_during_merge Mon Mar 07 17:33:12 EST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}