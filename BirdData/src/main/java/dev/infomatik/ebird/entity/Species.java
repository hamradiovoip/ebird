package dev.infomatik.ebird.entity;

import java.util.Objects;

public class Species {

	
	private String comName;     //  ":"Common Ostrich",
	private String speciesCode;     //  ":"ostric2",
	private String region;
	private int days;  // known as back
	private boolean adjacent;
	private int maxResults;
	
	
	
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the adjacent
	 */
	public boolean isAdjacent() {
		return adjacent;
	}
	/**
	 * @param adjacent the adjacent to set
	 */
	public void setAdjacent(boolean adjacent) {
		this.adjacent = adjacent;
	}
	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}
	/**
	 * @param maxResults the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
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
	@Override
	public String toString() {
		return "Species [comName=" + comName + ", speciesCode=" + speciesCode + ", region=" + region + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(comName, region, speciesCode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Species other = (Species) obj;
		return Objects.equals(comName, other.comName) && Objects.equals(region, other.region)
				&& Objects.equals(speciesCode, other.speciesCode);
	}
	
	
	
	
}
