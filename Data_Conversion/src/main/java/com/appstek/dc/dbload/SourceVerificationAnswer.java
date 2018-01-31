package com.appstek.dc.dbload;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author Sushanta
 * 
 * Database mapping entity class for source_verification_answer table.
 */
@Entity
@Table(name = "dbo.source_verification_answer")
@Proxy(lazy = false)
public class SourceVerificationAnswer implements Serializable {
	
	@Id
	@GenericGenerator(name="answer" , strategy="increment")
	@GeneratedValue(generator="answer")
	@Column(name="ANSWER_ID")
	private int answerId;
	
	@Column(name="QUESTION_ID")
	private int questionId;
	
	@Column(name="ANSWER")
	private String answer;  
	
	@Column(name="COUNTY_FIPS_CODE")
	private Long countyFipsCode;
	
	@Column(name="STATE_FIPS_CODE")
	private Long stateFipsCode;
	
	@Column(name="COUNTRY_FIPS_CODE")
	private Long countryFipsCode;
	
	@Column(name="EDITION")
	private String edition;

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getCountyFipsCode() {
		return countyFipsCode;
	}

	public void setCountyFipsCode(Long countyFipsCode) {
		this.countyFipsCode = countyFipsCode;
	}

	public Long getStateFipsCode() {
		return stateFipsCode;
	}

	public void setStateFipsCode(Long stateFipsCode) {
		this.stateFipsCode = stateFipsCode;
	}

	public Long getCountryFipsCode() {
		return countryFipsCode;
	}

	public void setCountryFipsCode(Long countryFipsCode) {
		this.countryFipsCode = countryFipsCode;
	}
	
}
