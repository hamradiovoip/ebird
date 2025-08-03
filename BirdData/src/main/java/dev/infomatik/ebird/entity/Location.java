package dev.infomatik.ebird.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
	
	
	
	private String locId;   // ":"L35489503",
	private String name;   // ":"Yoctangee Park, Chillicothe US-OH 39.33826, -82.98374",
	private double latitude;   // ":39.338255,
	private double longitude;   // ":-82.983737,
	private String countryCode;   // ":"US",
	private String countryName;   // ":"United States",
	private String subnational1Name;   // ": "Ohio",
	private String subnational1Code;   // ":"US-OH",
	private String subnational2Code;   // ":"US-OH-141",
	private String subnational2Name;   // ":"Ross",
	private String isHotspot;   // ":false,
	@JsonProperty("lat")
	private double lat;   // ":39.338255;   // ,
	@JsonProperty("lng")
	private double lng;   // ":-82.983737;   // ,
	private String locName;   // ":"Yoctangee Park, Chillicothe US-OH 39.33826, -82.98374","
	private String hierarchicalName;   // ":"Yoctangee Park, Chillicothe US-OH 39.33826, -82.98374, Ross, Ohio, US",
	private String locID;   // ":"L35489503"}}
	private String isoObsDate;
	
	
	
	/**
	 * @return the isoObsDate
	 */
	public String getIsoObsDate() {
		return isoObsDate;
	}
	/**
	 * @param isoObsDate the isoObsDate to set
	 */
	public void setIsoObsDate(String isoObsDate) {
		this.isoObsDate = isoObsDate;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the subnational1Name
	 */
	public String getSubnational1Name() {
		return subnational1Name;
	}
	/**
	 * @param subnational1Name the subnational1Name to set
	 */
	public void setSubnational1Name(String subnational1Name) {
		this.subnational1Name = subnational1Name;
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
	 * @return the subnational2Code
	 */
	public String getSubnational2Code() {
		return subnational2Code;
	}
	/**
	 * @param subnational2Code the subnational2Code to set
	 */
	public void setSubnational2Code(String subnational2Code) {
		this.subnational2Code = subnational2Code;
	}
	/**
	 * @return the subnational2Name
	 */
	public String getSubnational2Name() {
		return subnational2Name;
	}
	/**
	 * @param subnational2Name the subnational2Name to set
	 */
	public void setSubnational2Name(String subnational2Name) {
		this.subnational2Name = subnational2Name;
	}
	/**
	 * @return the isHotspot
	 */
	public String getIsHotspot() {
		return isHotspot;
	}
	/**
	 * @param isHotspot the isHotspot to set
	 */
	public void setIsHotspot(String isHotspot) {
		this.isHotspot = isHotspot;
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
	 * @return the hierarchicalName
	 */
	public String getHierarchicalName() {
		return hierarchicalName;
	}
	/**
	 * @param hierarchicalName the hierarchicalName to set
	 */
	public void setHierarchicalName(String hierarchicalName) {
		this.hierarchicalName = hierarchicalName;
	}
	/**
	 * @return the locID
	 */
	public String getLocID() {
		return locID;
	}
	/**
	 * @param locID the locID to set
	 */
	public void setLocID(String locID) {
		this.locID = locID;
	}



}
