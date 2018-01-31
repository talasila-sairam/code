package com.appstek.dc.dbload;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

/**
 * @author Sushanta
 * 
 * Database mapping entity class for rule_master table.
 */
@Entity
@Table(name = "dbo.rule_master")
@Proxy(lazy = false)
public class RuleMaster {
	
	@Id
	@GenericGenerator(name="rulMstr" , strategy="increment")
	@GeneratedValue(generator="rulMstr")
	@Column(name="RULE_ID" , unique = true, nullable = false)
	private int ruleId;
	@Column(name="RULE_NAME")
	private String ruleName;
	@Column(name="FIELD_NUMBER")
	private int fieldNumber;
	/*@Column(name="RULE_LEVEL")
	private int ruleLevel;*/
	@Column(name="STATUS",columnDefinition="boolean default true")
	private boolean status;
	@Column(name="EDITION")
	private String edition;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "ruleMaster")
	private Set<RuleQuery> rulequeries = new HashSet<RuleQuery>();
	public Set<RuleQuery> getRulequeries() {
		return rulequeries;
	}
	public void setRulequeries(Set<RuleQuery> rulequeries) {
		this.rulequeries = rulequeries;
	}*/
	
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RULE_LEVEL", nullable = true)
	private Territory territory;
	
	
	public Territory getTerritory() {
		return territory;
	}
	public void setTerritory(Territory territory) {
		this.territory = territory;
	}
	
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	public int getFieldNumber() {
		return fieldNumber;
	}
	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
