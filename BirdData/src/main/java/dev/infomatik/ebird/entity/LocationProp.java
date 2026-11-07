/**
 * 
 */
package dev.infomatik.ebird.entity;

import java.util.Objects;

/**
 * Location properties, lat, lon, name etc
 */
public class LocationProp {
	
	
	private double latitude;   // ":39.338255,
	private double longitude;   // ":-82.983737,
	private String locName; 
	private String distance;
	private String back;
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
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
	/**
	 * @return the back
	 */
	public String getBack() {
		return back;
	}
	/**
	 * @param back the back to set
	 */
	public void setBack(String back) {
		this.back = back;
	}
	@Override
	public String toString() {
		return "LocationProp [latitude=" + latitude + ", longitude=" + longitude + ", locName=" + locName
				+ ", distance=" + distance + ", back=" + back + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(back, distance, latitude, locName, longitude);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationProp other = (LocationProp) obj;
		return back == other.back && distance == other.distance
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Objects.equals(locName, other.locName)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude);
	}
	
	

}
