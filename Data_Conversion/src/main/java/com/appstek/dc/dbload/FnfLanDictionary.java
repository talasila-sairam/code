package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Malli
 * 
 *         Database mapping entity class for fnf_lan_dictionary table.
 */
@Entity
@Table(name = "dbo.fnf_lan_dictionary")
public class FnfLanDictionary implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fieldNumber;
	private String fieldName;
	private Integer length;
	private Integer startPoint;
	private Integer endPoint;
	private String datatype;
	private String fieldType;
	private String defaultValue;
	private String indexFlag;
	private String modFlag;
	private String deedFlag;
	private String nodFlag;
	private String relFlag;
	private String mtgFlag;
	private String asnFlag;
	private String mappingFieldName;
	private String defaultFlag;
	@Transient
	private Integer ruleCount;

	public FnfLanDictionary() {
	}

	public FnfLanDictionary(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public FnfLanDictionary(int fieldNumber, String fieldName, Integer length, Integer startPoint, Integer endPoint,
			String datatype, String fieldType, String defaultValue, String indexFlag, String modFlag, String deedFlag,
			String nodFlag, String relFlag, String mtgFlag, String asnFlag,String mappingFieldName) {
		this.fieldNumber = fieldNumber;
		this.fieldName = fieldName;
		this.length = length;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.datatype = datatype;
		this.fieldType = fieldType;
		this.defaultValue = defaultValue;
		this.indexFlag = indexFlag;
		this.modFlag = modFlag;
		this.deedFlag = deedFlag;
		this.nodFlag = nodFlag;
		this.relFlag = relFlag;
		this.mtgFlag = mtgFlag;
		this.asnFlag = asnFlag;
		this.mappingFieldName = mappingFieldName;
	}
	
	@Id
	@Column(name = "FIELD_NUMBER", unique = true, nullable = false)
	public int getFieldNumber() {
		return this.fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	@Column(name = "FIELD_NAME", length = 2000)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "LENGTH")
	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Column(name = "START_POINT")
	public Integer getStartPoint() {
		return this.startPoint;
	}

	public void setStartPoint(Integer startPoint) {
		this.startPoint = startPoint;
	}

	@Column(name = "END_POINT")
	public Integer getEndPoint() {
		return this.endPoint;
	}

	public void setEndPoint(Integer endPoint) {
		this.endPoint = endPoint;
	}

	@Column(name = "DATATYPE", length = 10)
	public String getDatatype() {
		return this.datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@Column(name = "FIELD_TYPE", length = 2000)
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "DEFAULT_VALUE", length = 2000)
	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(name = "INDEX_FLAG", length = 1)
	public String getIndexFlag() {
		return this.indexFlag;
	}

	public void setIndexFlag(String indexFlag) {
		this.indexFlag = indexFlag;
	}

	@Column(name = "MOD_FLAG", length = 1)
	public String getModFlag() {
		return this.modFlag;
	}

	public void setModFlag(String modFlag) {
		this.modFlag = modFlag;
	}

	@Column(name = "DEED_FLAG", length = 1)
	public String getDeedFlag() {
		return this.deedFlag;
	}

	public void setDeedFlag(String deedFlag) {
		this.deedFlag = deedFlag;
	}

	@Column(name = "NOD_FLAG", length = 1)
	public String getNodFlag() {
		return this.nodFlag;
	}

	public void setNodFlag(String nodFlag) {
		this.nodFlag = nodFlag;
	}

	@Column(name = "REL_FLAG", length = 1)
	public String getRelFlag() {
		return this.relFlag;
	}

	public void setRelFlag(String relFlag) {
		this.relFlag = relFlag;
	}

	@Column(name = "MTG_FLAG", length = 1)
	public String getMtgFlag() {
		return this.mtgFlag;
	}

	public void setMtgFlag(String mtgFlag) {
		this.mtgFlag = mtgFlag;
	}

	@Column(name = "ASN_FLAG", length = 1)
	public String getAsnFlag() {
		return this.asnFlag;
	}

	public void setAsnFlag(String asnFlag) {
		this.asnFlag = asnFlag;
	}
	
	@Column(name = "MAPPING_FIELD_NAME", length = 2000)
	public String getMappingFieldName() {
		return mappingFieldName;
	}

	public void setMappingFieldName(String mappingFieldName) {
		this.mappingFieldName = mappingFieldName;
	}
	@Column(name="DEFAULT_FLAG")
	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	@Transient
	public int getRuleCount() {
		return ruleCount;
	}
	@Transient
	public void setRuleCount(int ruleCount) {
		this.ruleCount = ruleCount;
	}
}
