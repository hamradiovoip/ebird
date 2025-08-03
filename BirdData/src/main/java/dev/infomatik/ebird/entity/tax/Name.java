package dev.infomatik.ebird.entity.tax;

import java.util.List;

import jakarta.persistence.Entity;


public class Name {
	
	private String sciName;     //  ":"Struthio camelus",
	private String comName;     //  ":"Common Ostrich",
	private String speciesCode;     //  ":"ostric2",
	private String category;     //  ":"species",
	private String taxonOrder;     //  ":2.0,
	private List <String> bandingCodes;     //  ":[],
	private List <String> comNameCodes;     //  ":["COOS"],
	private List <String> sciNameCodes;     //  ":["STCA"],
	private String order;     //  ":"Struthioniformes",
	private String familyCode;     //  ":"struth1",
	private String familyComName;     //  ":"Ostriches",
	private String familySciName;     //  ":"Struthionidae"},
	private String extinct; //
	private String extinctYear; 
	private String comments;
	private String region;
	
	
	 
	 
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
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
	 * @return the extinctYear
	 */
	public String getExtinctYear() {
		return extinctYear;
	}
	/**
	 * @param extinctYear the extinctYear to set
	 */
	public void setExtinctYear(String extinctYear) {
		this.extinctYear = extinctYear;
	}
	/**
	 * @return the extinct
	 */
	public String getExtinct() {
		return extinct;
	}
	/**
	 * @param extinct the extinct to set
	 */
	public void setExtinct(String extinct) {
		this.extinct = extinct;
	}
	/**
	 * @return the sciName
	 */
	public String getSciName() {
		return sciName;
	}
	/**
	 * @param sciName the sciName to set
	 */
	public void setSciName(String sciName) {
		this.sciName = sciName;
	}
	/**
	 * @return the comName
	 */
	public String getComName() {
		return comName;
	}
	/**
	 * @param comName the comName to set
	 */
	public void setComName(String comName) {
		this.comName = comName;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the taxonOrder
	 */
	public String getTaxonOrder() {
		return taxonOrder;
	}
	/**
	 * @param taxonOrder the taxonOrder to set
	 */
	public void setTaxonOrder(String taxonOrder) {
		this.taxonOrder = taxonOrder;
	}
	/**
	 * @return the bandingCodes
	 */
	public List<String> getBandingCodes() {
		return bandingCodes;
	}
	/**
	 * @param bandingCodes the bandingCodes to set
	 */
	public void setBandingCodes(List<String> bandingCodes) {
		this.bandingCodes = bandingCodes;
	}
	/**
	 * @return the comNameCodes
	 */
	public List<String> getComNameCodes() {
		return comNameCodes;
	}
	/**
	 * @param comNameCodes the comNameCodes to set
	 */
	public void setComNameCodes(List<String> comNameCodes) {
		this.comNameCodes = comNameCodes;
	}
	/**
	 * @return the sciNameCodes
	 */
	public List<String> getSciNameCodes() {
		return sciNameCodes;
	}
	/**
	 * @param sciNameCodes the sciNameCodes to set
	 */
	public void setSciNameCodes(List<String> sciNameCodes) {
		this.sciNameCodes = sciNameCodes;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return the familyCode
	 */
	public String getFamilyCode() {
		return familyCode;
	}
	/**
	 * @param familyCode the familyCode to set
	 */
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	/**
	 * @return the familyComName
	 */
	public String getFamilyComName() {
		return familyComName;
	}
	/**
	 * @param familyComName the familyComName to set
	 */
	public void setFamilyComName(String familyComName) {
		this.familyComName = familyComName;
	}
	/**
	 * @return the familySciName
	 */
	public String getFamilySciName() {
		return familySciName;
	}
	/**
	 * @param familySciName the familySciName to set
	 */
	public void setFamilySciName(String familySciName) {
		this.familySciName = familySciName;
	}
	
	
	
	
}
