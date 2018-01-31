package com.appstek.dc.dbload;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Malli
 * 
 * Database mapping entity class for fnf_lan_table_columns table.
 */
@Entity
@Table(name = "dbo.fnf_lan_table_columns")
public class FnfLanTableColumns implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FnfLanTableColumnsId id;

	public FnfLanTableColumns() {
	}

	public FnfLanTableColumns(FnfLanTableColumnsId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "tableId", column = @Column(name = "TABLE_ID")),
			@AttributeOverride(name = "tableName", column = @Column(name = "TABLE_NAME", length = 200)),
			@AttributeOverride(name = "columnName", column = @Column(name = "COLUMN_NAME", length = 200)),
			@AttributeOverride(name = "columnId", column = @Column(name = "COLUMN_ID")),
			@AttributeOverride(name = "otherColumnName", column = @Column(name = "OTHER_COLUMN_NAME", length = 500)),
			@AttributeOverride(name = "dataType", column = @Column(name = "DATA_TYPE", length = 50)),
			@AttributeOverride(name = "dataLength", column = @Column(name = "DATA_LENGTH")) })
	public FnfLanTableColumnsId getId() {
		return this.id;
	}

	public void setId(FnfLanTableColumnsId id) {
		this.id = id;
	}

}
