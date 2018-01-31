package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Malli
 * 
 * Database mapping entity class for fnf_lan_table_columns table.
 */
@Embeddable
public class FnfLanTableColumnsId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tableId;
	private String tableName;
	private String columnName;
	private Integer columnId;
	private String otherColumnName;
	private String dataType;
	private Integer dataLength;

	public FnfLanTableColumnsId() {
	}

	public FnfLanTableColumnsId(Integer tableId, String tableName, String columnName, Integer columnId,
			String otherColumnName, String dataType, Integer dataLength) {
		this.tableId = tableId;
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnId = columnId;
		this.otherColumnName = otherColumnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
	}

	@Column(name = "TABLE_ID")
	public Integer getTableId() {
		return this.tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	@Column(name = "TABLE_NAME", length = 200)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "COLUMN_NAME", length = 200)
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "COLUMN_ID")
	public Integer getColumnId() {
		return this.columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	@Column(name = "OTHER_COLUMN_NAME", length = 500)
	public String getOtherColumnName() {
		return this.otherColumnName;
	}

	public void setOtherColumnName(String otherColumnName) {
		this.otherColumnName = otherColumnName;
	}

	@Column(name = "DATA_TYPE", length = 50)
	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "DATA_LENGTH")
	public Integer getDataLength() {
		return this.dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FnfLanTableColumnsId))
			return false;
		FnfLanTableColumnsId castOther = (FnfLanTableColumnsId) other;

		return ((this.getTableId() == castOther.getTableId()) || (this.getTableId() != null
				&& castOther.getTableId() != null && this.getTableId().equals(castOther.getTableId())))
				&& ((this.getTableName() == castOther.getTableName()) || (this.getTableName() != null
						&& castOther.getTableName() != null && this.getTableName().equals(castOther.getTableName())))
				&& ((this.getColumnName() == castOther.getColumnName()) || (this.getColumnName() != null
						&& castOther.getColumnName() != null && this.getColumnName().equals(castOther.getColumnName())))
				&& ((this.getColumnId() == castOther.getColumnId()) || (this.getColumnId() != null
						&& castOther.getColumnId() != null && this.getColumnId().equals(castOther.getColumnId())))
				&& ((this.getOtherColumnName() == castOther.getOtherColumnName())
						|| (this.getOtherColumnName() != null && castOther.getOtherColumnName() != null
								&& this.getOtherColumnName().equals(castOther.getOtherColumnName())))
				&& ((this.getDataType() == castOther.getDataType()) || (this.getDataType() != null
						&& castOther.getDataType() != null && this.getDataType().equals(castOther.getDataType())))
				&& ((this.getDataLength() == castOther.getDataLength())
						|| (this.getDataLength() != null && castOther.getDataLength() != null
								&& this.getDataLength().equals(castOther.getDataLength())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTableId() == null ? 0 : this.getTableId().hashCode());
		result = 37 * result + (getTableName() == null ? 0 : this.getTableName().hashCode());
		result = 37 * result + (getColumnName() == null ? 0 : this.getColumnName().hashCode());
		result = 37 * result + (getColumnId() == null ? 0 : this.getColumnId().hashCode());
		result = 37 * result + (getOtherColumnName() == null ? 0 : this.getOtherColumnName().hashCode());
		result = 37 * result + (getDataType() == null ? 0 : this.getDataType().hashCode());
		result = 37 * result + (getDataLength() == null ? 0 : this.getDataLength().hashCode());
		return result;
	}

}
