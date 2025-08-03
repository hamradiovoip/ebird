package dev.infomatik.ebird.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotSpot {
	
	
		private String locId;   //  ":"L1248328",
		private String locName;   //  ":"Ballenger Creek Park",
		private String countryCode;   //  ":"US",
		private String subnational1Code;   //  ":"US-MD",
		private String subnational2Code;   //  ":"US-MD-021",
		private double lat;   //  ":39.3827817,"
		private double lng;   //  ":-77.4448398,
		private String latestObsDt;   //  ":"2024-08-06 17:45",
		private int numSpeciesAllTime;   //  ":130},
		
		@JsonIgnore
		private String region;
		@JsonIgnore
		private int days;
		@JsonIgnore
		private boolean adjacent;
		@JsonIgnore
		private int back;
		@JsonIgnore
		private int dist;		
		
		
		/**
		 * @return the dist
		 */
		public int getDist() {
			return dist;
		}
		/**
		 * @param dist the dist to set
		 */
		public void setDist(int dist) {
			this.dist = dist;
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
		 * @return the back
		 */
		public int getBack() {
			return back;
		}
		/**
		 * @param back the back to set
		 */
		public void setBack(int back) {
			this.back = back;
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
		 * @return the latestObsDt
		 */
		public String getLatestObsDt() {
			return latestObsDt;
		}
		/**
		 * @param latestObsDt the latestObsDt to set
		 */
		public void setLatestObsDt(String latestObsDt) {
			this.latestObsDt = latestObsDt;
		}
		/**
		 * @return the numSpeciesAllTime
		 */
		public int getNumSpeciesAllTime() {
			return numSpeciesAllTime;
		}
		/**
		 * @param numSpeciesAllTime the numSpeciesAllTime to set
		 */
		public void setNumSpeciesAllTime(int numSpeciesAllTime) {
			this.numSpeciesAllTime = numSpeciesAllTime;
		}
		
		
		@Override
		public int hashCode() {
			return Objects.hash(countryCode, lat, latestObsDt, lng, locId, locName, numSpeciesAllTime, subnational1Code,
					subnational2Code);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HotSpot other = (HotSpot) obj;
			return Objects.equals(countryCode, other.countryCode)
					&& Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.lat)
					&& Objects.equals(latestObsDt, other.latestObsDt)
					&& Double.doubleToLongBits(lng) == Double.doubleToLongBits(other.lng)
					&& Objects.equals(locId, other.locId) && Objects.equals(locName, other.locName)
					&& numSpeciesAllTime == other.numSpeciesAllTime
					&& Objects.equals(subnational1Code, other.subnational1Code)
					&& Objects.equals(subnational2Code, other.subnational2Code);
		}
		@Override
		public String toString() {
			return "HotSpot [locId=" + locId + ", locName=" + locName + ", countryCode=" + countryCode
					+ ", subnational1Code=" + subnational1Code + ", subnational2Code=" + subnational2Code + ", lat="
					+ lat + ", lng=" + lng + ", latestObsDt=" + latestObsDt + ", numSpeciesAllTime=" + numSpeciesAllTime
					+ "]";
		}
		
		
		
		
	

}
