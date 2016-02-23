package com.betterit.kaligia.dao.model.kaligia;

import java.io.Serializable;

public class ProcSegment implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.procsegment.segment_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer segmentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.procsegment.procedure_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer procedureId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column kaligia.procsegment.segment_no
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private Integer segmentNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table kaligia.procsegment
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.procsegment.segment_id
	 * @return  the value of kaligia.procsegment.segment_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getSegmentId() {
		return segmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.procsegment.segment_id
	 * @param segmentId  the value for kaligia.procsegment.segment_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setSegmentId(Integer segmentId) {
		this.segmentId = segmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.procsegment.procedure_id
	 * @return  the value of kaligia.procsegment.procedure_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getProcedureId() {
		return procedureId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.procsegment.procedure_id
	 * @param procedureId  the value for kaligia.procsegment.procedure_id
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column kaligia.procsegment.segment_no
	 * @return  the value of kaligia.procsegment.segment_no
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public Integer getSegmentNo() {
		return segmentNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column kaligia.procsegment.segment_no
	 * @param segmentNo  the value for kaligia.procsegment.segment_no
	 * @mbggenerated  Tue Feb 23 13:16:55 EST 2016
	 */
	public void setSegmentNo(Integer segmentNo) {
		this.segmentNo = segmentNo;
	}
}