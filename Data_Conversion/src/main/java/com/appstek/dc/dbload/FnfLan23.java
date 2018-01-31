package com.appstek.dc.dbload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Malli
 * 
 *         Database mapping entity class for fnf_lan23 table.
 */
@Entity
@Table(name = "dbo.fnf_lan23")
public class FnfLan23 implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long countyaid;
	private Integer lan23id;
	private Integer cyclenum;
	private String statepostalcode;
	private String countyname;
	private String apnorpinnumber;
	private String duplicateapnmultiaddressid;
	private String propaddrsrceflag;
	private Integer prophousenumber;
	private String prophousealpha;
	private String propstreetdirectionleft;
	private String propstreetname;
	private String propstreetsuffix;
	private String propstreetdirectionright;
	private String propunitnumber;
	private String propfullstreetaddress;
	private String propcityname;
	private String propstate;
	private Integer propzipcode;
	private String propzip4;
	private String assesseeownername;
	private String secondAssesseeownername;
	private String assevestingidcode;
	private String taxaccountnumber;
	private String assemailcareofname;
	private String assemailhousenumber;
	private String assemailhousealpha;
	private String assemailstreetdirectionleft;
	private String assemailstreetname;
	private String assemailstreetsuffix;
	private String assemailstreetdirectionright;
	private String assemailunitnumber;
	private String assemailfullstreetaddress;
	private String assemailcityname;
	private String assemailstate;
	private Integer assemailzipcode;
	private Integer assemailzip4;
	private String statelandusecode;
	private String owneroccupiedsfrcondo;
	private Integer assessedlandvalue;
	private Integer assessedimprovementvalue;
	private Integer totalassessedvalue;
	private Integer assessmentyear;
	private String califhomeownersexemption;
	private String taxexemptioncode;
	private String taxratecodearea;
	private Integer taxamount;
	private String taxyear;
	private String taxdelinquentyear;
	private String recordersdocumentnumber;
	private String recordersbooknumber;
	private String recorderspagenumber;
	private Integer recordingdate;
	private String documenttypecountydescription;
	private Integer salesprice;
	private String salespricecode;
	private Integer priorsaledate;
	private Integer priorsalesprice;
	private String priorsalescode;
	private String legallotcode;
	private String legallotnumber;
	private String legallandlot;
	private String legalblock;
	private String legalsection;
	private Integer legaldistrict;
	private String legalunit;
	private String legalcitymunicipalitytownship;
	private String legalsubdivisionname;
	private String legalphaseno;
	private String legaltractno;
	private String legalsectwprngmer;
	private String legalbriefdescription;
	private String legalassessorsmapref;
	private String countylandusedescription;
	private Integer countylandusecode;
	private Integer standardizedlandusecode;
	private String timesharecode;
	private String zoning;
	private String lotsizeorarea;
	private Integer buildingarea;
	private Integer yearbuilt;
	private Integer noofbuildings;
	private String noofstories;
	private String noofunits;
	private String totalnoofrooms;
	private String noofbedrooms;
	private String noofbaths;
	private String noofpartialbaths;
	private String garagetypeparking;
	private Integer parkingnumberofcars;
	private String pool;
	private String mailcitystzip;
	private Integer fipscode;
	private Integer tapecutdate;
	private String censustract;
	private String recordtype;
	private Integer marketvalueland;
	private Integer marketvalueimprovement;
	private Integer totalmarketvalue;
	private Integer marketvalueyear;
	private String buildingclass;
	private String style;
	private String typeconstruction;
	private String exteriorwalls;
	private String foundation;
	private String roofcover;
	private String heating;
	private String airconditioning;
	private String elevator;
	private Integer fireplace;
	private String basement;
	private Integer editionnumber;
	private String propertycountrycode;
	private String buildingareaindicator;
	private String propaddrmatchcode;
	private String propaddrunitdesignator;
	private String propaddrunitnumber;
	private String propaddrcarrierroute;
	private String propaddrgeocodematchcode;
	private String propaddrlatitude;
	private String propaddrlongitude;
	private String propaddrcensustractblock;
	private String mailaddrmatchcode;
	private String mailaddrunitdesignator;
	private String mailaddrunitnumber;
	private String mailaddrcarrierroute;
	private Integer assesseeownernameindicator;
	private String secondAssesseeownerIndicator;
	private Integer mailcareofnameindicator;
	private String assesseeownernametype;
	private String secondassesseeownernametype;
	private String altoldapnindicator;
	private Integer certificationdate;
	private Integer lotsizesqft;
	private String buildingquality;
	private String floorcover;
	private String noofplumbingfixtures;
	private Integer buildingarea1;
	private String buildingarea1indicator;
	private Integer buildingarea2;
	private String buildingarea2indicator;
	private Integer buildingarea3;
	private String buildingarea3indicator;
	private Integer buildingarea4;
	private String buildingarea4indicator;
	private Integer buildingarea5;
	private String buildingarea5indicator;
	private Integer buildingarea6;
	private String buildingarea6indicator;
	private Integer buildingarea7;
	private String buildingarea7indicator;
	private String effectiveyearbuilt;
	private String heatingfueltype;
	private String airconditioningtype;
	private Integer lotsizeacres;
	private String mortgagelendername;
	private String interiorwalls;
	private Integer schooltaxdistrict1;
	private String schooltaxdistrict1indicator;
	private String schooltaxdistrict2;
	private String schooltaxdistrict2indicator;
	private String schooltaxdistrict3;
	private String schooltaxdistrict3indicator;
	private String siteinfluence;
	private String amenities;
	private String otherimprbuildingindicator1;
	private String otherimprbuildingindicator2;
	private String otherimprbuildingindicator3;
	private String otherimprbuildingindicator4;
	private String neighborhoodcode;
	private String condoprojectbuildingname;
	private String otherimprbuildingindicator5;
	private String amenities2;
	private Integer otherimprbuildingarea1;
	private Integer otherimprbuildingarea2;
	private Integer otherimprbuildingarea3;
	private Integer otherimprbuildingarea4;
	private Integer otherimprbuildingarea5;
	private String otherrooms;
	private Integer extrafeatures1area;
	private String extrafeatures1indicator;
	private String topography;
	private String rooftype;
	private Integer extrafeatures2area;
	private String extrafeatures2indicator;
	private Integer extrafeatures3area;
	private String extrafeatures3indicator;
	private Integer extrafeatures4area;
	private String extrafeatures4indicator;
	private String oldapn;
	private String buildingcondition;
	private String lotsizefrontagefeet;
	private String lotsizedepthfeet;
	private String comments;
	private String water;
	private String sewer;
	private String newrecordtype;

	@Column(name = "COUNTYAID")
	public Long getCountyaid() {
		return this.countyaid;
	}

	public void setCountyaid(Long countyaid) {
		this.countyaid = countyaid;
	}

	@Id
	@Column(name = "LAN23ID")
	public Integer getLan23id() {
		return this.lan23id;
	}

	public void setLan23id(Integer lan23id) {
		this.lan23id = lan23id;
	}

	@Column(name = "CYCLENUM")
	public Integer getCyclenum() {
		return this.cyclenum;
	}

	public void setCyclenum(Integer cyclenum) {
		this.cyclenum = cyclenum;
	}

	@Column(name = "STATEPOSTALCODE", length = 10)
	public String getStatepostalcode() {
		return this.statepostalcode;
	}

	public void setStatepostalcode(String statepostalcode) {
		this.statepostalcode = statepostalcode;
	}

	@Column(name = "COUNTYNAME", length = 26)
	public String getCountyname() {
		return this.countyname;
	}

	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}

	@Column(name = "APNORPINNUMBER", length = 150)
	public String getApnorpinnumber() {
		return this.apnorpinnumber;
	}

	public void setApnorpinnumber(String apnorpinnumber) {
		this.apnorpinnumber = apnorpinnumber;
	}

	@Column(name = "DUPLICATEAPNMULTIADDRESSID", length = 20)
	public String getDuplicateapnmultiaddressid() {
		return this.duplicateapnmultiaddressid;
	}

	public void setDuplicateapnmultiaddressid(String duplicateapnmultiaddressid) {
		this.duplicateapnmultiaddressid = duplicateapnmultiaddressid;
	}

	@Column(name = "PROPADDRSRCEFLAG", length = 20)
	public String getPropaddrsrceflag() {
		return this.propaddrsrceflag;
	}

	public void setPropaddrsrceflag(String propaddrsrceflag) {
		this.propaddrsrceflag = propaddrsrceflag;
	}

	@Column(name = "PROPHOUSENUMBER")
	public Integer getProphousenumber() {
		return this.prophousenumber;
	}

	public void setProphousenumber(Integer prophousenumber) {
		this.prophousenumber = prophousenumber;
	}

	@Column(name = "PROPHOUSEALPHA", length = 150)
	public String getProphousealpha() {
		return this.prophousealpha;
	}

	public void setProphousealpha(String prophousealpha) {
		this.prophousealpha = prophousealpha;
	}

	@Column(name = "PROPSTREETDIRECTIONLEFT", length = 150)
	public String getPropstreetdirectionleft() {
		return this.propstreetdirectionleft;
	}

	public void setPropstreetdirectionleft(String propstreetdirectionleft) {
		this.propstreetdirectionleft = propstreetdirectionleft;
	}

	@Column(name = "PROPSTREETNAME", length = 150)
	public String getPropstreetname() {
		return this.propstreetname;
	}

	public void setPropstreetname(String propstreetname) {
		this.propstreetname = propstreetname;
	}

	@Column(name = "PROPSTREETSUFFIX", length = 150)
	public String getPropstreetsuffix() {
		return this.propstreetsuffix;
	}

	public void setPropstreetsuffix(String propstreetsuffix) {
		this.propstreetsuffix = propstreetsuffix;
	}

	@Column(name = "PROPSTREETDIRECTIONRIGHT", length = 150)
	public String getPropstreetdirectionright() {
		return this.propstreetdirectionright;
	}

	public void setPropstreetdirectionright(String propstreetdirectionright) {
		this.propstreetdirectionright = propstreetdirectionright;
	}

	@Column(name = "PROPUNITNUMBER", length = 150)
	public String getPropunitnumber() {
		return this.propunitnumber;
	}

	public void setPropunitnumber(String propunitnumber) {
		this.propunitnumber = propunitnumber;
	}

	@Column(name = "PROPFULLSTREETADDRESS", length = 150)
	public String getPropfullstreetaddress() {
		return this.propfullstreetaddress;
	}

	public void setPropfullstreetaddress(String propfullstreetaddress) {
		this.propfullstreetaddress = propfullstreetaddress;
	}

	@Column(name = "PROPCITYNAME", length = 150)
	public String getPropcityname() {
		return this.propcityname;
	}

	public void setPropcityname(String propcityname) {
		this.propcityname = propcityname;
	}

	@Column(name = "PROPSTATE", length = 150)
	public String getPropstate() {
		return this.propstate;
	}

	public void setPropstate(String propstate) {
		this.propstate = propstate;
	}

	@Column(name = "PROPZIPCODE")
	public Integer getPropzipcode() {
		return this.propzipcode;
	}

	public void setPropzipcode(Integer propzipcode) {
		this.propzipcode = propzipcode;
	}

	@Column(name = "PROPZIP4", length = 50)
	public String getPropzip4() {
		return this.propzip4;
	}

	public void setPropzip4(String propzip4) {
		this.propzip4 = propzip4;
	}

	@Column(name = "ASSESSEEOWNERNAME", length = 150)
	public String getAssesseeownername() {
		return this.assesseeownername;
	}

	public void setAssesseeownername(String assesseeownername) {
		this.assesseeownername = assesseeownername;
	}

	@Column(name = "SECOND_ASSESSEEOWNERNAME", length = 150)
	public String getSecondAssesseeownername() {
		return this.secondAssesseeownername;
	}

	public void setSecondAssesseeownername(String secondAssesseeownername) {
		this.secondAssesseeownername = secondAssesseeownername;
	}

	@Column(name = "ASSEVESTINGIDCODE", length = 150)
	public String getAssevestingidcode() {
		return this.assevestingidcode;
	}

	public void setAssevestingidcode(String assevestingidcode) {
		this.assevestingidcode = assevestingidcode;
	}

	@Column(name = "TAXACCOUNTNUMBER", length = 150)
	public String getTaxaccountnumber() {
		return this.taxaccountnumber;
	}

	public void setTaxaccountnumber(String taxaccountnumber) {
		this.taxaccountnumber = taxaccountnumber;
	}

	@Column(name = "ASSEMAILCAREOFNAME", length = 150)
	public String getAssemailcareofname() {
		return this.assemailcareofname;
	}

	public void setAssemailcareofname(String assemailcareofname) {
		this.assemailcareofname = assemailcareofname;
	}

	@Column(name = "ASSEMAILHOUSENUMBER", length = 150)
	public String getAssemailhousenumber() {
		return this.assemailhousenumber;
	}

	public void setAssemailhousenumber(String assemailhousenumber) {
		this.assemailhousenumber = assemailhousenumber;
	}

	@Column(name = "ASSEMAILHOUSEALPHA", length = 150)
	public String getAssemailhousealpha() {
		return this.assemailhousealpha;
	}

	public void setAssemailhousealpha(String assemailhousealpha) {
		this.assemailhousealpha = assemailhousealpha;
	}

	@Column(name = "ASSEMAILSTREETDIRECTIONLEFT", length = 150)
	public String getAssemailstreetdirectionleft() {
		return this.assemailstreetdirectionleft;
	}

	public void setAssemailstreetdirectionleft(String assemailstreetdirectionleft) {
		this.assemailstreetdirectionleft = assemailstreetdirectionleft;
	}

	@Column(name = "ASSEMAILSTREETNAME", length = 150)
	public String getAssemailstreetname() {
		return this.assemailstreetname;
	}

	public void setAssemailstreetname(String assemailstreetname) {
		this.assemailstreetname = assemailstreetname;
	}

	@Column(name = "ASSEMAILSTREETSUFFIX", length = 150)
	public String getAssemailstreetsuffix() {
		return this.assemailstreetsuffix;
	}

	public void setAssemailstreetsuffix(String assemailstreetsuffix) {
		this.assemailstreetsuffix = assemailstreetsuffix;
	}

	@Column(name = "ASSEMAILSTREETDIRECTIONRIGHT", length = 26)
	public String getAssemailstreetdirectionright() {
		return this.assemailstreetdirectionright;
	}

	public void setAssemailstreetdirectionright(String assemailstreetdirectionright) {
		this.assemailstreetdirectionright = assemailstreetdirectionright;
	}

	@Column(name = "ASSEMAILUNITNUMBER", length = 150)
	public String getAssemailunitnumber() {
		return this.assemailunitnumber;
	}

	public void setAssemailunitnumber(String assemailunitnumber) {
		this.assemailunitnumber = assemailunitnumber;
	}

	@Column(name = "ASSEMAILFULLSTREETADDRESS", length = 150)
	public String getAssemailfullstreetaddress() {
		return this.assemailfullstreetaddress;
	}

	public void setAssemailfullstreetaddress(String assemailfullstreetaddress) {
		this.assemailfullstreetaddress = assemailfullstreetaddress;
	}

	@Column(name = "ASSEMAILCITYNAME", length = 150)
	public String getAssemailcityname() {
		return this.assemailcityname;
	}

	public void setAssemailcityname(String assemailcityname) {
		this.assemailcityname = assemailcityname;
	}

	@Column(name = "ASSEMAILSTATE", length = 150)
	public String getAssemailstate() {
		return this.assemailstate;
	}

	public void setAssemailstate(String assemailstate) {
		this.assemailstate = assemailstate;
	}

	@Column(name = "ASSEMAILZIPCODE")
	public Integer getAssemailzipcode() {
		return this.assemailzipcode;
	}

	public void setAssemailzipcode(Integer assemailzipcode) {
		this.assemailzipcode = assemailzipcode;
	}

	@Column(name = "ASSEMAILZIP4")
	public Integer getAssemailzip4() {
		return this.assemailzip4;
	}

	public void setAssemailzip4(Integer assemailzip4) {
		this.assemailzip4 = assemailzip4;
	}

	@Column(name = "STATELANDUSECODE", length = 150)
	public String getStatelandusecode() {
		return this.statelandusecode;
	}

	public void setStatelandusecode(String statelandusecode) {
		this.statelandusecode = statelandusecode;
	}

	@Column(name = "OWNEROCCUPIEDSFRCONDO", length = 150)
	public String getOwneroccupiedsfrcondo() {
		return this.owneroccupiedsfrcondo;
	}

	public void setOwneroccupiedsfrcondo(String owneroccupiedsfrcondo) {
		this.owneroccupiedsfrcondo = owneroccupiedsfrcondo;
	}

	@Column(name = "ASSESSEDLANDVALUE")
	public Integer getAssessedlandvalue() {
		return this.assessedlandvalue;
	}

	public void setAssessedlandvalue(Integer assessedlandvalue) {
		this.assessedlandvalue = assessedlandvalue;
	}

	@Column(name = "ASSESSEDIMPROVEMENTVALUE")
	public Integer getAssessedimprovementvalue() {
		return this.assessedimprovementvalue;
	}

	public void setAssessedimprovementvalue(Integer assessedimprovementvalue) {
		this.assessedimprovementvalue = assessedimprovementvalue;
	}

	@Column(name = "TOTALASSESSEDVALUE")
	public Integer getTotalassessedvalue() {
		return this.totalassessedvalue;
	}

	public void setTotalassessedvalue(Integer totalassessedvalue) {
		this.totalassessedvalue = totalassessedvalue;
	}

	@Column(name = "ASSESSMENTYEAR")
	public Integer getAssessmentyear() {
		return this.assessmentyear;
	}

	public void setAssessmentyear(Integer assessmentyear) {
		this.assessmentyear = assessmentyear;
	}

	@Column(name = "CALIFHOMEOWNERSEXEMPTION", length = 150)
	public String getCalifhomeownersexemption() {
		return this.califhomeownersexemption;
	}

	public void setCalifhomeownersexemption(String califhomeownersexemption) {
		this.califhomeownersexemption = califhomeownersexemption;
	}

	@Column(name = "TAXEXEMPTIONCODE", length = 150)
	public String getTaxexemptioncode() {
		return this.taxexemptioncode;
	}

	public void setTaxexemptioncode(String taxexemptioncode) {
		this.taxexemptioncode = taxexemptioncode;
	}

	@Column(name = "TAXRATECODEAREA", length = 150)
	public String getTaxratecodearea() {
		return this.taxratecodearea;
	}

	public void setTaxratecodearea(String taxratecodearea) {
		this.taxratecodearea = taxratecodearea;
	}

	@Column(name = "TAXAMOUNT")
	public Integer getTaxamount() {
		return this.taxamount;
	}

	public void setTaxamount(Integer taxamount) {
		this.taxamount = taxamount;
	}

	@Column(name = "TAXYEAR", length = 150)
	public String getTaxyear() {
		return this.taxyear;
	}

	public void setTaxyear(String taxyear) {
		this.taxyear = taxyear;
	}

	@Column(name = "TAXDELINQUENTYEAR", length = 26)
	public String getTaxdelinquentyear() {
		return this.taxdelinquentyear;
	}

	public void setTaxdelinquentyear(String taxdelinquentyear) {
		this.taxdelinquentyear = taxdelinquentyear;
	}

	@Column(name = "RECORDERSDOCUMENTNUMBER", length = 150)
	public String getRecordersdocumentnumber() {
		return this.recordersdocumentnumber;
	}

	public void setRecordersdocumentnumber(String recordersdocumentnumber) {
		this.recordersdocumentnumber = recordersdocumentnumber;
	}

	@Column(name = "RECORDERSBOOKNUMBER", length = 150)
	public String getRecordersbooknumber() {
		return this.recordersbooknumber;
	}

	public void setRecordersbooknumber(String recordersbooknumber) {
		this.recordersbooknumber = recordersbooknumber;
	}

	@Column(name = "RECORDERSPAGENUMBER", length = 150)
	public String getRecorderspagenumber() {
		return this.recorderspagenumber;
	}

	public void setRecorderspagenumber(String recorderspagenumber) {
		this.recorderspagenumber = recorderspagenumber;
	}

	@Column(name = "RECORDINGDATE")
	public Integer getRecordingdate() {
		return this.recordingdate;
	}

	public void setRecordingdate(Integer recordingdate) {
		this.recordingdate = recordingdate;
	}

	@Column(name = "DOCUMENTTYPECOUNTYDESCRIPTION", length = 150)
	public String getDocumenttypecountydescription() {
		return this.documenttypecountydescription;
	}

	public void setDocumenttypecountydescription(String documenttypecountydescription) {
		this.documenttypecountydescription = documenttypecountydescription;
	}

	@Column(name = "SALESPRICE")
	public Integer getSalesprice() {
		return this.salesprice;
	}

	public void setSalesprice(Integer salesprice) {
		this.salesprice = salesprice;
	}

	@Column(name = "SALESPRICECODE", length = 150)
	public String getSalespricecode() {
		return this.salespricecode;
	}

	public void setSalespricecode(String salespricecode) {
		this.salespricecode = salespricecode;
	}

	@Column(name = "PRIORSALEDATE")
	public Integer getPriorsaledate() {
		return this.priorsaledate;
	}

	public void setPriorsaledate(Integer priorsaledate) {
		this.priorsaledate = priorsaledate;
	}

	@Column(name = "PRIORSALESPRICE")
	public Integer getPriorsalesprice() {
		return this.priorsalesprice;
	}

	public void setPriorsalesprice(Integer priorsalesprice) {
		this.priorsalesprice = priorsalesprice;
	}

	@Column(name = "PRIORSALESCODE", length = 150)
	public String getPriorsalescode() {
		return this.priorsalescode;
	}

	public void setPriorsalescode(String priorsalescode) {
		this.priorsalescode = priorsalescode;
	}

	@Column(name = "LEGALLOTCODE", length = 150)
	public String getLegallotcode() {
		return this.legallotcode;
	}

	public void setLegallotcode(String legallotcode) {
		this.legallotcode = legallotcode;
	}

	@Column(name = "LEGALLOTNUMBER", length = 150)
	public String getLegallotnumber() {
		return this.legallotnumber;
	}

	public void setLegallotnumber(String legallotnumber) {
		this.legallotnumber = legallotnumber;
	}

	@Column(name = "LEGALLANDLOT", length = 150)
	public String getLegallandlot() {
		return this.legallandlot;
	}

	public void setLegallandlot(String legallandlot) {
		this.legallandlot = legallandlot;
	}

	@Column(name = "LEGALBLOCK", length = 150)
	public String getLegalblock() {
		return this.legalblock;
	}

	public void setLegalblock(String legalblock) {
		this.legalblock = legalblock;
	}

	@Column(name = "LEGALSECTION", length = 150)
	public String getLegalsection() {
		return this.legalsection;
	}

	public void setLegalsection(String legalsection) {
		this.legalsection = legalsection;
	}

	@Column(name = "LEGALDISTRICT")
	public Integer getLegaldistrict() {
		return this.legaldistrict;
	}

	public void setLegaldistrict(Integer legaldistrict) {
		this.legaldistrict = legaldistrict;
	}

	@Column(name = "LEGALUNIT", length = 150)
	public String getLegalunit() {
		return this.legalunit;
	}

	public void setLegalunit(String legalunit) {
		this.legalunit = legalunit;
	}

	@Column(name = "LEGALCITYMUNICIPALITYTOWNSHIP", length = 150)
	public String getLegalcitymunicipalitytownship() {
		return this.legalcitymunicipalitytownship;
	}

	public void setLegalcitymunicipalitytownship(String legalcitymunicipalitytownship) {
		this.legalcitymunicipalitytownship = legalcitymunicipalitytownship;
	}

	@Column(name = "LEGALSUBDIVISIONNAME", length = 150)
	public String getLegalsubdivisionname() {
		return this.legalsubdivisionname;
	}

	public void setLegalsubdivisionname(String legalsubdivisionname) {
		this.legalsubdivisionname = legalsubdivisionname;
	}

	@Column(name = "LEGALPHASENO", length = 150)
	public String getLegalphaseno() {
		return this.legalphaseno;
	}

	public void setLegalphaseno(String legalphaseno) {
		this.legalphaseno = legalphaseno;
	}

	@Column(name = "LEGALTRACTNO", length = 150)
	public String getLegaltractno() {
		return this.legaltractno;
	}

	public void setLegaltractno(String legaltractno) {
		this.legaltractno = legaltractno;
	}

	@Column(name = "LEGALSECTWPRNGMER", length = 150)
	public String getLegalsectwprngmer() {
		return this.legalsectwprngmer;
	}

	public void setLegalsectwprngmer(String legalsectwprngmer) {
		this.legalsectwprngmer = legalsectwprngmer;
	}

	@Column(name = "LEGALBRIEFDESCRIPTION", length = 150)
	public String getLegalbriefdescription() {
		return this.legalbriefdescription;
	}

	public void setLegalbriefdescription(String legalbriefdescription) {
		this.legalbriefdescription = legalbriefdescription;
	}

	@Column(name = "LEGALASSESSORSMAPREF", length = 150)
	public String getLegalassessorsmapref() {
		return this.legalassessorsmapref;
	}

	public void setLegalassessorsmapref(String legalassessorsmapref) {
		this.legalassessorsmapref = legalassessorsmapref;
	}

	@Column(name = "COUNTYLANDUSEDESCRIPTION", length = 150)
	public String getCountylandusedescription() {
		return this.countylandusedescription;
	}

	public void setCountylandusedescription(String countylandusedescription) {
		this.countylandusedescription = countylandusedescription;
	}

	@Column(name = "COUNTYLANDUSECODE")
	public Integer getCountylandusecode() {
		return this.countylandusecode;
	}

	public void setCountylandusecode(Integer countylandusecode) {
		this.countylandusecode = countylandusecode;
	}

	@Column(name = "STANDARDIZEDLANDUSECODE")
	public Integer getStandardizedlandusecode() {
		return this.standardizedlandusecode;
	}

	public void setStandardizedlandusecode(Integer standardizedlandusecode) {
		this.standardizedlandusecode = standardizedlandusecode;
	}

	@Column(name = "TIMESHARECODE", length = 150)
	public String getTimesharecode() {
		return this.timesharecode;
	}

	public void setTimesharecode(String timesharecode) {
		this.timesharecode = timesharecode;
	}

	@Column(name = "ZONING", length = 150)
	public String getZoning() {
		return this.zoning;
	}

	public void setZoning(String zoning) {
		this.zoning = zoning;
	}

	@Column(name = "LOTSIZEORAREA", length = 150)
	public String getLotsizeorarea() {
		return this.lotsizeorarea;
	}

	public void setLotsizeorarea(String lotsizeorarea) {
		this.lotsizeorarea = lotsizeorarea;
	}

	@Column(name = "BUILDINGAREA")
	public Integer getBuildingarea() {
		return this.buildingarea;
	}

	public void setBuildingarea(Integer buildingarea) {
		this.buildingarea = buildingarea;
	}

	@Column(name = "YEARBUILT")
	public Integer getYearbuilt() {
		return this.yearbuilt;
	}

	public void setYearbuilt(Integer yearbuilt) {
		this.yearbuilt = yearbuilt;
	}

	@Column(name = "NOOFBUILDINGS")
	public Integer getNoofbuildings() {
		return this.noofbuildings;
	}

	public void setNoofbuildings(Integer noofbuildings) {
		this.noofbuildings = noofbuildings;
	}

	@Column(name = "NOOFSTORIES", length = 150)
	public String getNoofstories() {
		return this.noofstories;
	}

	public void setNoofstories(String noofstories) {
		this.noofstories = noofstories;
	}

	@Column(name = "NOOFUNITS", length = 150)
	public String getNoofunits() {
		return this.noofunits;
	}

	public void setNoofunits(String noofunits) {
		this.noofunits = noofunits;
	}

	@Column(name = "TOTALNOOFROOMS", length = 150)
	public String getTotalnoofrooms() {
		return this.totalnoofrooms;
	}

	public void setTotalnoofrooms(String totalnoofrooms) {
		this.totalnoofrooms = totalnoofrooms;
	}

	@Column(name = "NOOFBEDROOMS", length = 150)
	public String getNoofbedrooms() {
		return this.noofbedrooms;
	}

	public void setNoofbedrooms(String noofbedrooms) {
		this.noofbedrooms = noofbedrooms;
	}

	@Column(name = "NOOFBATHS", length = 150)
	public String getNoofbaths() {
		return this.noofbaths;
	}

	public void setNoofbaths(String noofbaths) {
		this.noofbaths = noofbaths;
	}

	@Column(name = "NOOFPARTIALBATHS", length = 150)
	public String getNoofpartialbaths() {
		return this.noofpartialbaths;
	}

	public void setNoofpartialbaths(String noofpartialbaths) {
		this.noofpartialbaths = noofpartialbaths;
	}

	@Column(name = "GARAGETYPEPARKING", length = 150)
	public String getGaragetypeparking() {
		return this.garagetypeparking;
	}

	public void setGaragetypeparking(String garagetypeparking) {
		this.garagetypeparking = garagetypeparking;
	}

	@Column(name = "PARKINGNUMBEROFCARS")
	public Integer getParkingnumberofcars() {
		return this.parkingnumberofcars;
	}

	public void setParkingnumberofcars(Integer parkingnumberofcars) {
		this.parkingnumberofcars = parkingnumberofcars;
	}

	@Column(name = "POOL", length = 150)
	public String getPool() {
		return this.pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	@Column(name = "MAILCITYSTZIP", length = 150)
	public String getMailcitystzip() {
		return this.mailcitystzip;
	}

	public void setMailcitystzip(String mailcitystzip) {
		this.mailcitystzip = mailcitystzip;
	}

	@Column(name = "FIPSCODE")
	public Integer getFipscode() {
		return this.fipscode;
	}

	public void setFipscode(Integer fipscode) {
		this.fipscode = fipscode;
	}

	@Column(name = "TAPECUTDATE")
	public Integer getTapecutdate() {
		return this.tapecutdate;
	}

	public void setTapecutdate(Integer tapecutdate) {
		this.tapecutdate = tapecutdate;
	}

	@Column(name = "CENSUSTRACT", length = 150)
	public String getCensustract() {
		return this.censustract;
	}

	public void setCensustract(String censustract) {
		this.censustract = censustract;
	}

	@Column(name = "RECORDTYPE", length = 150)
	public String getRecordtype() {
		return this.recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	@Column(name = "MARKETVALUELAND")
	public Integer getMarketvalueland() {
		return this.marketvalueland;
	}

	public void setMarketvalueland(Integer marketvalueland) {
		this.marketvalueland = marketvalueland;
	}

	@Column(name = "MARKETVALUEIMPROVEMENT")
	public Integer getMarketvalueimprovement() {
		return this.marketvalueimprovement;
	}

	public void setMarketvalueimprovement(Integer marketvalueimprovement) {
		this.marketvalueimprovement = marketvalueimprovement;
	}

	@Column(name = "TOTALMARKETVALUE")
	public Integer getTotalmarketvalue() {
		return this.totalmarketvalue;
	}

	public void setTotalmarketvalue(Integer totalmarketvalue) {
		this.totalmarketvalue = totalmarketvalue;
	}

	@Column(name = "MARKETVALUEYEAR")
	public Integer getMarketvalueyear() {
		return this.marketvalueyear;
	}

	public void setMarketvalueyear(Integer marketvalueyear) {
		this.marketvalueyear = marketvalueyear;
	}

	@Column(name = "BUILDINGCLASS", length = 150)
	public String getBuildingclass() {
		return this.buildingclass;
	}

	public void setBuildingclass(String buildingclass) {
		this.buildingclass = buildingclass;
	}

	@Column(name = "STYLE", length = 150)
	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "TYPECONSTRUCTION", length = 150)
	public String getTypeconstruction() {
		return this.typeconstruction;
	}

	public void setTypeconstruction(String typeconstruction) {
		this.typeconstruction = typeconstruction;
	}

	@Column(name = "EXTERIORWALLS", length = 150)
	public String getExteriorwalls() {
		return this.exteriorwalls;
	}

	public void setExteriorwalls(String exteriorwalls) {
		this.exteriorwalls = exteriorwalls;
	}

	@Column(name = "FOUNDATION", length = 150)
	public String getFoundation() {
		return this.foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	@Column(name = "ROOFCOVER", length = 150)
	public String getRoofcover() {
		return this.roofcover;
	}

	public void setRoofcover(String roofcover) {
		this.roofcover = roofcover;
	}

	@Column(name = "HEATING", length = 150)
	public String getHeating() {
		return this.heating;
	}

	public void setHeating(String heating) {
		this.heating = heating;
	}

	@Column(name = "AIRCONDITIONING", length = 150)
	public String getAirconditioning() {
		return this.airconditioning;
	}

	public void setAirconditioning(String airconditioning) {
		this.airconditioning = airconditioning;
	}

	@Column(name = "ELEVATOR", length = 150)
	public String getElevator() {
		return this.elevator;
	}

	public void setElevator(String elevator) {
		this.elevator = elevator;
	}

	@Column(name = "FIREPLACE")
	public Integer getFireplace() {
		return this.fireplace;
	}

	public void setFireplace(Integer fireplace) {
		this.fireplace = fireplace;
	}

	@Column(name = "BASEMENT", length = 150)
	public String getBasement() {
		return this.basement;
	}

	public void setBasement(String basement) {
		this.basement = basement;
	}

	@Column(name = "EDITIONNUMBER")
	public Integer getEditionnumber() {
		return this.editionnumber;
	}

	public void setEditionnumber(Integer editionnumber) {
		this.editionnumber = editionnumber;
	}

	@Column(name = "PROPERTYCOUNTRYCODE", length = 150)
	public String getPropertycountrycode() {
		return this.propertycountrycode;
	}

	public void setPropertycountrycode(String propertycountrycode) {
		this.propertycountrycode = propertycountrycode;
	}

	@Column(name = "BUILDINGAREAINDICATOR", length = 150)
	public String getBuildingareaindicator() {
		return this.buildingareaindicator;
	}

	public void setBuildingareaindicator(String buildingareaindicator) {
		this.buildingareaindicator = buildingareaindicator;
	}

	@Column(name = "PROPADDRMATCHCODE", length = 150)
	public String getPropaddrmatchcode() {
		return this.propaddrmatchcode;
	}

	public void setPropaddrmatchcode(String propaddrmatchcode) {
		this.propaddrmatchcode = propaddrmatchcode;
	}

	@Column(name = "PROPADDRUNITDESIGNATOR", length = 150)
	public String getPropaddrunitdesignator() {
		return this.propaddrunitdesignator;
	}

	public void setPropaddrunitdesignator(String propaddrunitdesignator) {
		this.propaddrunitdesignator = propaddrunitdesignator;
	}

	@Column(name = "PROPADDRUNITNUMBER", length = 150)
	public String getPropaddrunitnumber() {
		return this.propaddrunitnumber;
	}

	public void setPropaddrunitnumber(String propaddrunitnumber) {
		this.propaddrunitnumber = propaddrunitnumber;
	}

	@Column(name = "PROPADDRCARRIERROUTE", length = 150)
	public String getPropaddrcarrierroute() {
		return this.propaddrcarrierroute;
	}

	public void setPropaddrcarrierroute(String propaddrcarrierroute) {
		this.propaddrcarrierroute = propaddrcarrierroute;
	}

	@Column(name = "PROPADDRGEOCODEMATCHCODE", length = 150)
	public String getPropaddrgeocodematchcode() {
		return this.propaddrgeocodematchcode;
	}

	public void setPropaddrgeocodematchcode(String propaddrgeocodematchcode) {
		this.propaddrgeocodematchcode = propaddrgeocodematchcode;
	}

	@Column(name = "PROPADDRLATITUDE", length = 150)
	public String getPropaddrlatitude() {
		return this.propaddrlatitude;
	}

	public void setPropaddrlatitude(String propaddrlatitude) {
		this.propaddrlatitude = propaddrlatitude;
	}

	@Column(name = "PROPADDRLONGITUDE", length = 150)
	public String getPropaddrlongitude() {
		return this.propaddrlongitude;
	}

	public void setPropaddrlongitude(String propaddrlongitude) {
		this.propaddrlongitude = propaddrlongitude;
	}

	@Column(name = "PROPADDRCENSUSTRACTBLOCK", length = 150)
	public String getPropaddrcensustractblock() {
		return this.propaddrcensustractblock;
	}

	public void setPropaddrcensustractblock(String propaddrcensustractblock) {
		this.propaddrcensustractblock = propaddrcensustractblock;
	}

	@Column(name = "MAILADDRMATCHCODE", length = 150)
	public String getMailaddrmatchcode() {
		return this.mailaddrmatchcode;
	}

	public void setMailaddrmatchcode(String mailaddrmatchcode) {
		this.mailaddrmatchcode = mailaddrmatchcode;
	}

	@Column(name = "MAILADDRUNITDESIGNATOR", length = 150)
	public String getMailaddrunitdesignator() {
		return this.mailaddrunitdesignator;
	}

	public void setMailaddrunitdesignator(String mailaddrunitdesignator) {
		this.mailaddrunitdesignator = mailaddrunitdesignator;
	}

	@Column(name = "MAILADDRUNITNUMBER", length = 150)
	public String getMailaddrunitnumber() {
		return this.mailaddrunitnumber;
	}

	public void setMailaddrunitnumber(String mailaddrunitnumber) {
		this.mailaddrunitnumber = mailaddrunitnumber;
	}

	@Column(name = "MAILADDRCARRIERROUTE", length = 150)
	public String getMailaddrcarrierroute() {
		return this.mailaddrcarrierroute;
	}

	public void setMailaddrcarrierroute(String mailaddrcarrierroute) {
		this.mailaddrcarrierroute = mailaddrcarrierroute;
	}

	@Column(name = "ASSESSEEOWNERNAMEINDICATOR")
	public Integer getAssesseeownernameindicator() {
		return this.assesseeownernameindicator;
	}

	public void setAssesseeownernameindicator(Integer assesseeownernameindicator) {
		this.assesseeownernameindicator = assesseeownernameindicator;
	}

	@Column(name = "SECOND_ASSESSEEOWNER_INDICATOR", length = 150)
	public String getSecondAssesseeownerIndicator() {
		return this.secondAssesseeownerIndicator;
	}

	public void setSecondAssesseeownerIndicator(String secondAssesseeownerIndicator) {
		this.secondAssesseeownerIndicator = secondAssesseeownerIndicator;
	}

	@Column(name = "MAILCAREOFNAMEINDICATOR")
	public Integer getMailcareofnameindicator() {
		return this.mailcareofnameindicator;
	}

	public void setMailcareofnameindicator(Integer mailcareofnameindicator) {
		this.mailcareofnameindicator = mailcareofnameindicator;
	}

	@Column(name = "ASSESSEEOWNERNAMETYPE", length = 150)
	public String getAssesseeownernametype() {
		return this.assesseeownernametype;
	}

	public void setAssesseeownernametype(String assesseeownernametype) {
		this.assesseeownernametype = assesseeownernametype;
	}

	@Column(name = "SECONDASSESSEEOWNERNAMETYPE", length = 150)
	public String getSecondassesseeownernametype() {
		return this.secondassesseeownernametype;
	}

	public void setSecondassesseeownernametype(String secondassesseeownernametype) {
		this.secondassesseeownernametype = secondassesseeownernametype;
	}

	@Column(name = "ALTOLDAPNINDICATOR", length = 150)
	public String getAltoldapnindicator() {
		return this.altoldapnindicator;
	}

	public void setAltoldapnindicator(String altoldapnindicator) {
		this.altoldapnindicator = altoldapnindicator;
	}

	@Column(name = "CERTIFICATIONDATE")
	public Integer getCertificationdate() {
		return this.certificationdate;
	}

	public void setCertificationdate(Integer certificationdate) {
		this.certificationdate = certificationdate;
	}

	@Column(name = "LOTSIZESQFT")
	public Integer getLotsizesqft() {
		return this.lotsizesqft;
	}

	public void setLotsizesqft(Integer lotsizesqft) {
		this.lotsizesqft = lotsizesqft;
	}

	@Column(name = "BUILDINGQUALITY", length = 150)
	public String getBuildingquality() {
		return this.buildingquality;
	}

	public void setBuildingquality(String buildingquality) {
		this.buildingquality = buildingquality;
	}

	@Column(name = "FLOORCOVER", length = 150)
	public String getFloorcover() {
		return this.floorcover;
	}

	public void setFloorcover(String floorcover) {
		this.floorcover = floorcover;
	}

	@Column(name = "NOOFPLUMBINGFIXTURES", length = 150)
	public String getNoofplumbingfixtures() {
		return this.noofplumbingfixtures;
	}

	public void setNoofplumbingfixtures(String noofplumbingfixtures) {
		this.noofplumbingfixtures = noofplumbingfixtures;
	}

	@Column(name = "BUILDINGAREA1")
	public Integer getBuildingarea1() {
		return this.buildingarea1;
	}

	public void setBuildingarea1(Integer buildingarea1) {
		this.buildingarea1 = buildingarea1;
	}

	@Column(name = "BUILDINGAREA1INDICATOR", length = 150)
	public String getBuildingarea1indicator() {
		return this.buildingarea1indicator;
	}

	public void setBuildingarea1indicator(String buildingarea1indicator) {
		this.buildingarea1indicator = buildingarea1indicator;
	}

	@Column(name = "BUILDINGAREA2")
	public Integer getBuildingarea2() {
		return this.buildingarea2;
	}

	public void setBuildingarea2(Integer buildingarea2) {
		this.buildingarea2 = buildingarea2;
	}

	@Column(name = "BUILDINGAREA2INDICATOR", length = 150)
	public String getBuildingarea2indicator() {
		return this.buildingarea2indicator;
	}

	public void setBuildingarea2indicator(String buildingarea2indicator) {
		this.buildingarea2indicator = buildingarea2indicator;
	}

	@Column(name = "BUILDINGAREA3")
	public Integer getBuildingarea3() {
		return this.buildingarea3;
	}

	public void setBuildingarea3(Integer buildingarea3) {
		this.buildingarea3 = buildingarea3;
	}

	@Column(name = "BUILDINGAREA3INDICATOR", length = 150)
	public String getBuildingarea3indicator() {
		return this.buildingarea3indicator;
	}

	public void setBuildingarea3indicator(String buildingarea3indicator) {
		this.buildingarea3indicator = buildingarea3indicator;
	}

	@Column(name = "BUILDINGAREA4")
	public Integer getBuildingarea4() {
		return this.buildingarea4;
	}

	public void setBuildingarea4(Integer buildingarea4) {
		this.buildingarea4 = buildingarea4;
	}

	@Column(name = "BUILDINGAREA4INDICATOR", length = 150)
	public String getBuildingarea4indicator() {
		return this.buildingarea4indicator;
	}

	public void setBuildingarea4indicator(String buildingarea4indicator) {
		this.buildingarea4indicator = buildingarea4indicator;
	}

	@Column(name = "BUILDINGAREA5")
	public Integer getBuildingarea5() {
		return this.buildingarea5;
	}

	public void setBuildingarea5(Integer buildingarea5) {
		this.buildingarea5 = buildingarea5;
	}

	@Column(name = "BUILDINGAREA5INDICATOR", length = 150)
	public String getBuildingarea5indicator() {
		return this.buildingarea5indicator;
	}

	public void setBuildingarea5indicator(String buildingarea5indicator) {
		this.buildingarea5indicator = buildingarea5indicator;
	}

	@Column(name = "BUILDINGAREA6")
	public Integer getBuildingarea6() {
		return this.buildingarea6;
	}

	public void setBuildingarea6(Integer buildingarea6) {
		this.buildingarea6 = buildingarea6;
	}

	@Column(name = "BUILDINGAREA6INDICATOR", length = 150)
	public String getBuildingarea6indicator() {
		return this.buildingarea6indicator;
	}

	public void setBuildingarea6indicator(String buildingarea6indicator) {
		this.buildingarea6indicator = buildingarea6indicator;
	}

	@Column(name = "BUILDINGAREA7")
	public Integer getBuildingarea7() {
		return this.buildingarea7;
	}

	public void setBuildingarea7(Integer buildingarea7) {
		this.buildingarea7 = buildingarea7;
	}

	@Column(name = "BUILDINGAREA7INDICATOR", length = 150)
	public String getBuildingarea7indicator() {
		return this.buildingarea7indicator;
	}

	public void setBuildingarea7indicator(String buildingarea7indicator) {
		this.buildingarea7indicator = buildingarea7indicator;
	}

	@Column(name = "EFFECTIVEYEARBUILT", length = 150)
	public String getEffectiveyearbuilt() {
		return this.effectiveyearbuilt;
	}

	public void setEffectiveyearbuilt(String effectiveyearbuilt) {
		this.effectiveyearbuilt = effectiveyearbuilt;
	}

	@Column(name = "HEATINGFUELTYPE", length = 150)
	public String getHeatingfueltype() {
		return this.heatingfueltype;
	}

	public void setHeatingfueltype(String heatingfueltype) {
		this.heatingfueltype = heatingfueltype;
	}

	@Column(name = "AIRCONDITIONINGTYPE", length = 150)
	public String getAirconditioningtype() {
		return this.airconditioningtype;
	}

	public void setAirconditioningtype(String airconditioningtype) {
		this.airconditioningtype = airconditioningtype;
	}

	@Column(name = "LOTSIZEACRES")
	public Integer getLotsizeacres() {
		return this.lotsizeacres;
	}

	public void setLotsizeacres(Integer lotsizeacres) {
		this.lotsizeacres = lotsizeacres;
	}

	@Column(name = "MORTGAGELENDERNAME", length = 150)
	public String getMortgagelendername() {
		return this.mortgagelendername;
	}

	public void setMortgagelendername(String mortgagelendername) {
		this.mortgagelendername = mortgagelendername;
	}

	@Column(name = "INTERIORWALLS", length = 150)
	public String getInteriorwalls() {
		return this.interiorwalls;
	}

	public void setInteriorwalls(String interiorwalls) {
		this.interiorwalls = interiorwalls;
	}

	@Column(name = "SCHOOLTAXDISTRICT1")
	public Integer getSchooltaxdistrict1() {
		return this.schooltaxdistrict1;
	}

	public void setSchooltaxdistrict1(Integer schooltaxdistrict1) {
		this.schooltaxdistrict1 = schooltaxdistrict1;
	}

	@Column(name = "SCHOOLTAXDISTRICT1INDICATOR", length = 150)
	public String getSchooltaxdistrict1indicator() {
		return this.schooltaxdistrict1indicator;
	}

	public void setSchooltaxdistrict1indicator(String schooltaxdistrict1indicator) {
		this.schooltaxdistrict1indicator = schooltaxdistrict1indicator;
	}

	@Column(name = "SCHOOLTAXDISTRICT2", length = 150)
	public String getSchooltaxdistrict2() {
		return this.schooltaxdistrict2;
	}

	public void setSchooltaxdistrict2(String schooltaxdistrict2) {
		this.schooltaxdistrict2 = schooltaxdistrict2;
	}

	@Column(name = "SCHOOLTAXDISTRICT2INDICATOR", length = 150)
	public String getSchooltaxdistrict2indicator() {
		return this.schooltaxdistrict2indicator;
	}

	public void setSchooltaxdistrict2indicator(String schooltaxdistrict2indicator) {
		this.schooltaxdistrict2indicator = schooltaxdistrict2indicator;
	}

	@Column(name = "SCHOOLTAXDISTRICT3", length = 150)
	public String getSchooltaxdistrict3() {
		return this.schooltaxdistrict3;
	}

	public void setSchooltaxdistrict3(String schooltaxdistrict3) {
		this.schooltaxdistrict3 = schooltaxdistrict3;
	}

	@Column(name = "SCHOOLTAXDISTRICT3INDICATOR", length = 150)
	public String getSchooltaxdistrict3indicator() {
		return this.schooltaxdistrict3indicator;
	}

	public void setSchooltaxdistrict3indicator(String schooltaxdistrict3indicator) {
		this.schooltaxdistrict3indicator = schooltaxdistrict3indicator;
	}

	@Column(name = "SITEINFLUENCE", length = 150)
	public String getSiteinfluence() {
		return this.siteinfluence;
	}

	public void setSiteinfluence(String siteinfluence) {
		this.siteinfluence = siteinfluence;
	}

	@Column(name = "AMENITIES", length = 150)
	public String getAmenities() {
		return this.amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	@Column(name = "OTHERIMPRBUILDINGINDICATOR1", length = 150)
	public String getOtherimprbuildingindicator1() {
		return this.otherimprbuildingindicator1;
	}

	public void setOtherimprbuildingindicator1(String otherimprbuildingindicator1) {
		this.otherimprbuildingindicator1 = otherimprbuildingindicator1;
	}

	@Column(name = "OTHERIMPRBUILDINGINDICATOR2", length = 150)
	public String getOtherimprbuildingindicator2() {
		return this.otherimprbuildingindicator2;
	}

	public void setOtherimprbuildingindicator2(String otherimprbuildingindicator2) {
		this.otherimprbuildingindicator2 = otherimprbuildingindicator2;
	}

	@Column(name = "OTHERIMPRBUILDINGINDICATOR3", length = 150)
	public String getOtherimprbuildingindicator3() {
		return this.otherimprbuildingindicator3;
	}

	public void setOtherimprbuildingindicator3(String otherimprbuildingindicator3) {
		this.otherimprbuildingindicator3 = otherimprbuildingindicator3;
	}

	@Column(name = "OTHERIMPRBUILDINGINDICATOR4", length = 150)
	public String getOtherimprbuildingindicator4() {
		return this.otherimprbuildingindicator4;
	}

	public void setOtherimprbuildingindicator4(String otherimprbuildingindicator4) {
		this.otherimprbuildingindicator4 = otherimprbuildingindicator4;
	}

	@Column(name = "NEIGHBORHOODCODE", length = 150)
	public String getNeighborhoodcode() {
		return this.neighborhoodcode;
	}

	public void setNeighborhoodcode(String neighborhoodcode) {
		this.neighborhoodcode = neighborhoodcode;
	}

	@Column(name = "CONDOPROJECTBUILDINGNAME", length = 150)
	public String getCondoprojectbuildingname() {
		return this.condoprojectbuildingname;
	}

	public void setCondoprojectbuildingname(String condoprojectbuildingname) {
		this.condoprojectbuildingname = condoprojectbuildingname;
	}

	@Column(name = "OTHERIMPRBUILDINGINDICATOR5", length = 150)
	public String getOtherimprbuildingindicator5() {
		return this.otherimprbuildingindicator5;
	}

	public void setOtherimprbuildingindicator5(String otherimprbuildingindicator5) {
		this.otherimprbuildingindicator5 = otherimprbuildingindicator5;
	}

	@Column(name = "AMENITIES2", length = 150)
	public String getAmenities2() {
		return this.amenities2;
	}

	public void setAmenities2(String amenities2) {
		this.amenities2 = amenities2;
	}

	@Column(name = "OTHERIMPRBUILDINGAREA1")
	public Integer getOtherimprbuildingarea1() {
		return this.otherimprbuildingarea1;
	}

	public void setOtherimprbuildingarea1(Integer otherimprbuildingarea1) {
		this.otherimprbuildingarea1 = otherimprbuildingarea1;
	}

	@Column(name = "OTHERIMPRBUILDINGAREA2")
	public Integer getOtherimprbuildingarea2() {
		return this.otherimprbuildingarea2;
	}

	public void setOtherimprbuildingarea2(Integer otherimprbuildingarea2) {
		this.otherimprbuildingarea2 = otherimprbuildingarea2;
	}

	@Column(name = "OTHERIMPRBUILDINGAREA3")
	public Integer getOtherimprbuildingarea3() {
		return this.otherimprbuildingarea3;
	}

	public void setOtherimprbuildingarea3(Integer otherimprbuildingarea3) {
		this.otherimprbuildingarea3 = otherimprbuildingarea3;
	}

	@Column(name = "OTHERIMPRBUILDINGAREA4")
	public Integer getOtherimprbuildingarea4() {
		return this.otherimprbuildingarea4;
	}

	public void setOtherimprbuildingarea4(Integer otherimprbuildingarea4) {
		this.otherimprbuildingarea4 = otherimprbuildingarea4;
	}

	@Column(name = "OTHERIMPRBUILDINGAREA5")
	public Integer getOtherimprbuildingarea5() {
		return this.otherimprbuildingarea5;
	}

	public void setOtherimprbuildingarea5(Integer otherimprbuildingarea5) {
		this.otherimprbuildingarea5 = otherimprbuildingarea5;
	}

	@Column(name = "OTHERROOMS", length = 150)
	public String getOtherrooms() {
		return this.otherrooms;
	}

	public void setOtherrooms(String otherrooms) {
		this.otherrooms = otherrooms;
	}

	@Column(name = "EXTRAFEATURES1AREA")
	public Integer getExtrafeatures1area() {
		return this.extrafeatures1area;
	}

	public void setExtrafeatures1area(Integer extrafeatures1area) {
		this.extrafeatures1area = extrafeatures1area;
	}

	@Column(name = "EXTRAFEATURES1INDICATOR", length = 150)
	public String getExtrafeatures1indicator() {
		return this.extrafeatures1indicator;
	}

	public void setExtrafeatures1indicator(String extrafeatures1indicator) {
		this.extrafeatures1indicator = extrafeatures1indicator;
	}

	@Column(name = "TOPOGRAPHY", length = 150)
	public String getTopography() {
		return this.topography;
	}

	public void setTopography(String topography) {
		this.topography = topography;
	}

	@Column(name = "ROOFTYPE", length = 150)
	public String getRooftype() {
		return this.rooftype;
	}

	public void setRooftype(String rooftype) {
		this.rooftype = rooftype;
	}

	@Column(name = "EXTRAFEATURES2AREA")
	public Integer getExtrafeatures2area() {
		return this.extrafeatures2area;
	}

	public void setExtrafeatures2area(Integer extrafeatures2area) {
		this.extrafeatures2area = extrafeatures2area;
	}

	@Column(name = "EXTRAFEATURES2INDICATOR", length = 150)
	public String getExtrafeatures2indicator() {
		return this.extrafeatures2indicator;
	}

	public void setExtrafeatures2indicator(String extrafeatures2indicator) {
		this.extrafeatures2indicator = extrafeatures2indicator;
	}

	@Column(name = "EXTRAFEATURES3AREA")
	public Integer getExtrafeatures3area() {
		return this.extrafeatures3area;
	}

	public void setExtrafeatures3area(Integer extrafeatures3area) {
		this.extrafeatures3area = extrafeatures3area;
	}

	@Column(name = "EXTRAFEATURES3INDICATOR", length = 150)
	public String getExtrafeatures3indicator() {
		return this.extrafeatures3indicator;
	}

	public void setExtrafeatures3indicator(String extrafeatures3indicator) {
		this.extrafeatures3indicator = extrafeatures3indicator;
	}

	@Column(name = "EXTRAFEATURES4AREA")
	public Integer getExtrafeatures4area() {
		return this.extrafeatures4area;
	}

	public void setExtrafeatures4area(Integer extrafeatures4area) {
		this.extrafeatures4area = extrafeatures4area;
	}

	@Column(name = "EXTRAFEATURES4INDICATOR", length = 150)
	public String getExtrafeatures4indicator() {
		return this.extrafeatures4indicator;
	}

	public void setExtrafeatures4indicator(String extrafeatures4indicator) {
		this.extrafeatures4indicator = extrafeatures4indicator;
	}

	@Column(name = "OLDAPN", length = 150)
	public String getOldapn() {
		return this.oldapn;
	}

	public void setOldapn(String oldapn) {
		this.oldapn = oldapn;
	}

	@Column(name = "BUILDINGCONDITION", length = 150)
	public String getBuildingcondition() {
		return this.buildingcondition;
	}

	public void setBuildingcondition(String buildingcondition) {
		this.buildingcondition = buildingcondition;
	}

	@Column(name = "LOTSIZEFRONTAGEFEET", length = 150)
	public String getLotsizefrontagefeet() {
		return this.lotsizefrontagefeet;
	}

	public void setLotsizefrontagefeet(String lotsizefrontagefeet) {
		this.lotsizefrontagefeet = lotsizefrontagefeet;
	}

	@Column(name = "LOTSIZEDEPTHFEET", length = 150)
	public String getLotsizedepthfeet() {
		return this.lotsizedepthfeet;
	}

	public void setLotsizedepthfeet(String lotsizedepthfeet) {
		this.lotsizedepthfeet = lotsizedepthfeet;
	}

	@Column(name = "COMMENTS", length = 150)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "WATER", length = 150)
	public String getWater() {
		return this.water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	@Column(name = "SEWER", length = 150)
	public String getSewer() {
		return this.sewer;
	}

	public void setSewer(String sewer) {
		this.sewer = sewer;
	}

	@Column(name = "NEWRECORDTYPE", length = 150)
	public String getNewrecordtype() {
		return this.newrecordtype;
	}

	public void setNewrecordtype(String newrecordtype) {
		this.newrecordtype = newrecordtype;
	}

}
