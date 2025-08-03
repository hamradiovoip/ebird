package dev.infomatik.ebird.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Observation {


	/* Individual bird observation */
	private String speciesCode;   // ": "gwfgoo",
	@JsonIgnore // can add later since not in API
	private String commonName;   // ": 
	@JsonIgnore
	private String hideFlags;   // : [],
	private String  obsDt;   // ": "2018-01-04 18:29",
	private String  subnational1Code;   // ": "US-NY",
	private int howManyAtleast;   // ": 1,
	private int  howManyAtmost;   // ": 1,
	private String  howManyStr;   // ": "1",
	private boolean present;   // ": false,
	private String  projId;   // ": "EBIRD",
	@JsonIgnore
	private String exoticCategory;
	private String  subId;   // ": "S29893687",
	private String  obsId;   // ": "OBS406047821"
	//@JsonIgnore
	private List < ObsAux > subAux;
	private List < ObsAux > obsAux;
	private String comments;
	private String userDoNotCount;
	@JsonIgnore
	private List <String> mediaCounts;




	/**
	 * @return the mediaCounts
	 */
	public List<String> getMediaCounts() {
		return mediaCounts;
	}
	/**
	 * @param mediaCounts the mediaCounts to set
	 */
	public void setMediaCounts(List<String> mediaCounts) {
		this.mediaCounts = mediaCounts;
	}
	/**
	 * @return the userDoNotCount
	 */
	public String getUserDoNotCount() {
		return userDoNotCount;
	}
	/**
	 * @param userDoNotCount the userDoNotCount to set
	 */
	public void setUserDoNotCount(String userDoNotCount) {
		this.userDoNotCount = userDoNotCount;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the commonName
	 */
	public String getCommonName() {
		return commonName;
	}
	/**
	 * @param commonName the commonName to set
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	/**
	 * @return the obsAux
	 */
	public List<ObsAux> getObsAux() {
		return obsAux;
	}
	/**
	 * @param obsAux the obsAux to set
	 */
	public void setObsAux(List<ObsAux> obsAux) {
		this.obsAux = obsAux;
	}
	/**
	 * @return the subAux
	 */
	public List<ObsAux> getSubAux() {
		return subAux;
	}
	/**
	 * @param subAux the subAux to set
	 */
	public void setSubAux(List<ObsAux> subAux) {
		this.subAux = subAux;
	}
	/**
	 * @return the exoticCategory
	 */
	public String getExoticCategory() {
		return exoticCategory;
	}
	/**
	 * @param exoticCategory the exoticCategory to set
	 */
	public void setExoticCategory(String exoticCategory) {
		this.exoticCategory = exoticCategory;
	}
	/**
	 * @return the speciesCode
	 */
	public String getSpeciesCode() {
		return speciesCode;
	}
	/**
	 * @param speciesCode the speciesCode to set
	 */
	public void setSpeciesCode(String speciesCode) {
		this.speciesCode = speciesCode;
	}
	/**
	 * @return the hideFlags
	 */
	public String getHideFlags() {
		return hideFlags;
	}
	/**
	 * @param hideFlags the hideFlags to set
	 */
	public void setHideFlags(String hideFlags) {
		this.hideFlags = hideFlags;
	}
	/**
	 * @return the obsDt
	 */
	public String getObsDt() {
		return obsDt;
	}
	/**
	 * @param obsDt the obsDt to set
	 */
	public void setObsDt(String obsDt) {
		this.obsDt = obsDt;
	}
	/**
	 * @return the subnational1Code
	 */
	public String getSubnational1Code() {
		return subnational1Code;
	}
	/**
	 * @param subnational1Code the subnational1Code to set
	 */
	public void setSubnational1Code(String subnational1Code) {
		this.subnational1Code = subnational1Code;
	}
	/**
	 * @return the howManyAtleast
	 */
	public int getHowManyAtleast() {
		return howManyAtleast;
	}
	/**
	 * @param howManyAtleast the howManyAtleast to set
	 */
	public void setHowManyAtleast(int howManyAtleast) {
		this.howManyAtleast = howManyAtleast;
	}
	/**
	 * @return the howManyAtmost
	 */
	public int getHowManyAtmost() {
		return howManyAtmost;
	}
	/**
	 * @param howManyAtmost the howManyAtmost to set
	 */
	public void setHowManyAtmost(int howManyAtmost) {
		this.howManyAtmost = howManyAtmost;
	}
	/**
	 * @return the howManyStr
	 */
	public String getHowManyStr() {
		return howManyStr;
	}
	/**
	 * @param howManyStr the howManyStr to set
	 */
	public void setHowManyStr(String howManyStr) {
		this.howManyStr = howManyStr;
	}
	/**
	 * @return the present
	 */
	public boolean isPresent() {
		return present;
	}
	/**
	 * @param present the present to set
	 */
	public void setPresent(boolean present) {
		this.present = present;
	}
	/**
	 * @return the projId
	 */
	public String getProjId() {
		return projId;
	}
	/**
	 * @param projId the projId to set
	 */
	public void setProjId(String projId) {
		this.projId = projId;
	}
	/**
	 * @return the subId
	 */
	public String getSubId() {
		return subId;
	}
	/**
	 * @param subId the subId to set
	 */
	public void setSubId(String subId) {
		this.subId = subId;
	}
	/**
	 * @return the obsId
	 */
	public String getObsId() {
		return obsId;
	}
	/**
	 * @param obsId the obsId to set
	 */
	public void setObsId(String obsId) {
		this.obsId = obsId;
	}

	//private ObsAux obsAux;




}
