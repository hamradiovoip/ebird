package dev.infomatik.ebird.entity;

public class Nearby {

	/* Nearby is for data for Nearest observations of a species */
	
	private String speciesCode; //":"moudov",
	private String comName; //":"Mourning Dove",
	private String sciName; //":"Zenaida macroura",
	private String locId; //":"L35949302",
	private String locName; //":"Highway 103,Lunenburg CA-NS 44.39848,-64.49840",
	private String obsDt; //":"2024-08-21 08:31",
	private int howMany; //":1,
	private	 double lat; //:44.398481,"
	private double lng; //":-64.498405,
	private	 boolean obsValid; //":true,
	private	 boolean obsReviewed; //":false,
	private	 boolean locationPrivate; //":true,
	private String	subId; //":"S192234556"}]
	
	private  String exoticCategory;
	
	
	
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
	 * @return the lng
	 */
	public double getLng() {
		return lng;
	}
	/**
	 * @param lng the lng to set
	 */
	public void setLng(double lng) {
		this.lng = lng;
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
	 * @return the locId
	 */
	public String getLocId() {
		return locId;
	}
	/**
	 * @param locId the locId to set
	 */
	public void setLocId(String locId) {
		this.locId = locId;
	}
	/**
	 * @return the locName
	 */
	public String getLocName() {
		return locName;
	}
	/**
	 * @param locName the locName to set
	 */
	public void setLocName(String locName) {
		this.locName = locName;
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
	 * @return the howMany
	 */
	public int getHowMany() {
		return howMany;
	}
	/**
	 * @param howMany the howMany to set
	 */
	public void setHowMany(int howMany) {
		this.howMany = howMany;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the obsValid
	 */
	public boolean isObsValid() {
		return obsValid;
	}
	/**
	 * @param obsValid the obsValid to set
	 */
	public void setObsValid(boolean obsValid) {
		this.obsValid = obsValid;
	}
	/**
	 * @return the obsReviewed
	 */
	public boolean isObsReviewed() {
		return obsReviewed;
	}
	/**
	 * @param obsReviewed the obsReviewed to set
	 */
	public void setObsReviewed(boolean obsReviewed) {
		this.obsReviewed = obsReviewed;
	}
	/**
	 * @return the locationPrivate
	 */
	public boolean isLocationPrivate() {
		return locationPrivate;
	}
	/**
	 * @param locationPrivate the locationPrivate to set
	 */
	public void setLocationPrivate(boolean locationPrivate) {
		this.locationPrivate = locationPrivate;
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
	
	
	
	
	
	
	
	
}
