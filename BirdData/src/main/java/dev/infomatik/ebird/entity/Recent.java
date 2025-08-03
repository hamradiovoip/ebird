package dev.infomatik.ebird.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recent {


	@JsonProperty("speciesCode")
	private String speciesCode; // ": "hoocro1",
	private String comName; // ": "Hooded Crow",
	private String sciName; // ": "Corvus cornix",
	private String locId; // ": "L7884500",
	private String locName; // ": "улица Старикова, Chundzha KZ-Almaty (43.5309,79.4551)",
	private String obsDt; // ": "2020-01-21 16:35",
	private int howMany; // ": 1,
	private double lat; // ": 43.530936,
	private double lng; // ": 79.455132,
	private boolean obsValid; // ": true,
	private boolean obsReviewed; // ": false,
	private boolean locationPrivate; // ": true,
	private String subId; // ": "S63619695"
	private String exoticCategory;
	
	
	
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
	@Override
	public int hashCode() {
		return Objects.hash(comName, howMany, lat, lng, locId, locName, locationPrivate, obsDt, obsReviewed, obsValid,
				speciesCode, sciName, subId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recent other = (Recent) obj;
		return Objects.equals(comName, other.comName) && howMany == other.howMany
				&& Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.lat)
				&& Double.doubleToLongBits(lng) == Double.doubleToLongBits(other.lng)
				&& Objects.equals(locId, other.locId) && Objects.equals(locName, other.locName)
				&& locationPrivate == other.locationPrivate && Objects.equals(obsDt, other.obsDt)
				&& obsReviewed == other.obsReviewed && obsValid == other.obsValid
				&& Objects.equals(speciesCode, other.speciesCode) && Objects.equals(sciName, other.sciName)
				&& Objects.equals(subId, other.subId);
	}
	@Override
	public String toString() {
		return "Recent [peciesCode=" + speciesCode + ", comName=" + comName + ", sciName=" + sciName + ", locId=" + locId
				+ ", locName=" + locName + ", obsDt=" + obsDt + ", howMany=" + howMany + ", lat=" + lat + ", lng=" + lng
				+ ", obsValid=" + obsValid + ", obsReviewed=" + obsReviewed + ", locationPrivate=" + locationPrivate
				+ ", subId=" + subId + "]";
	}







}
