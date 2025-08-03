package dev.infomatik.ebird.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Notable {


	private String speciesCode;
	private String comName;
	private String sciName;
	private String locId;
	private String locName;
	private String obsDt;
	private int howMany;
	private float lat;
	private float lng;
	private boolean obsValid;
	private boolean obsReviewed;
	private boolean locationPrivate;
	private String subnational2Code;
	private String subnational2Name;
	private String subnational1Code;
	private String subnational1Name;
	private String countryCode;
	private String countryName;
	private String userDisplayName;
	private String subId;
	private String obsId;
	private String checklistId;
	private boolean presenceNoted;
	private boolean hasComments;
	private String firstName;
	private String lastName;
	private boolean hasRichMedia;
	private String locID;
	private String exoticCategory;
	@JsonIgnore
	private String region;
	@JsonIgnore
	private int days;
	@JsonIgnore
	private boolean adjacent;
	@JsonIgnore
	private int back;
	
	@JsonIgnore
	private String evidence;
	

	// Getter Methods 

	
	
	
	
	
	/**
	 * @return the back
	 */
	public int getBack() {
		return back;
	}

	/**
	 * @return the evidence
	 */
	public String getEvidence() {
		return evidence;
	}

	/**
	 * @param evidence the evidence to set
	 */
	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	/**
	 * @param back the back to set
	 */
	public void setBack(int back) {
		this.back = back;
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

	 public String getSpeciesCode() {
		 return speciesCode;
	 }

	 public String getComName() {
		 return comName;
	 }

	 public String getSciName() {
		 return sciName;
	 }

	 public String getLocId() {
		 return locId;
	 }

	 public String getLocName() {
		 return locName;
	 }

	 public String getObsDt() {
		 return obsDt;
	 }

	 public int getHowMany() {
		 return howMany;
	 }

	 public float getLat() {
		 return lat;
	 }

	 public float getLng() {
		 return lng;
	 }

	 public boolean getObsValid() {
		 return obsValid;
	 }

	 public boolean getObsReviewed() {
		 return obsReviewed;
	 }

	 public boolean getLocationPrivate() {
		 return locationPrivate;
	 }

	 public String getSubnational2Code() {
		 return subnational2Code;
	 }

	 public String getSubnational2Name() {
		 return subnational2Name;
	 }

	 public String getSubnational1Code() {
		 return subnational1Code;
	 }

	 public String getSubnational1Name() {
		 return subnational1Name;
	 }

	 public String getCountryCode() {
		 return countryCode;
	 }

	 public String getCountryName() {
		 return countryName;
	 }

	 public String getUserDisplayName() {
		 return userDisplayName;
	 }

	 public String getSubId() {
		 return subId;
	 }

	 public String getObsId() {
		 return obsId;
	 }

	 public String getChecklistId() {
		 return checklistId;
	 }

	 public boolean getPresenceNoted() {
		 return presenceNoted;
	 }

	 public boolean getHasComments() {
		 return hasComments;
	 }

	 public String getFirstName() {
		 return firstName;
	 }

	 public String getLastName() {
		 return lastName;
	 }

	 public boolean getHasRichMedia() {
		 return hasRichMedia;
	 }

	 public String getLocID() {
		 return locID;
	 }

	 // Setter Methods 

	 public void setSpeciesCode( String speciesCode ) {
		 this.speciesCode = speciesCode;
	 }

	 public void setComName( String comName ) {
		 this.comName = comName;
	 }

	 public void setSciName( String sciName ) {
		 this.sciName = sciName;
	 }

	 public void setLocId( String locId ) {
		 this.locId = locId;
	 }

	 public void setLocName( String locName ) {
		 this.locName = locName;
	 }

	 public void setObsDt( String obsDt ) {
		 this.obsDt = obsDt;
	 }

	 public void setHowMany( int howMany ) {
		 this.howMany = howMany;
	 }

	 public void setLat( float lat ) {
		 this.lat = lat;
	 }

	 public void setLng( float lng ) {
		 this.lng = lng;
	 }

	 public void setObsValid( boolean obsValid ) {
		 this.obsValid = obsValid;
	 }

	 public void setObsReviewed( boolean obsReviewed ) {
		 this.obsReviewed = obsReviewed;
	 }

	 public void setLocationPrivate( boolean locationPrivate ) {
		 this.locationPrivate = locationPrivate;
	 }

	 public void setSubnational2Code( String subnational2Code ) {
		 this.subnational2Code = subnational2Code;
	 }

	 public void setSubnational2Name( String subnational2Name ) {
		 this.subnational2Name = subnational2Name;
	 }

	 public void setSubnational1Code( String subnational1Code ) {
		 this.subnational1Code = subnational1Code;
	 }

	 public void setSubnational1Name( String subnational1Name ) {
		 this.subnational1Name = subnational1Name;
	 }

	 public void setCountryCode( String countryCode ) {
		 this.countryCode = countryCode;
	 }

	 public void setCountryName( String countryName ) {
		 this.countryName = countryName;
	 }

	 public void setUserDisplayName( String userDisplayName ) {
		 this.userDisplayName = userDisplayName;
	 }

	 public void setSubId( String subId ) {
		 this.subId = subId;
	 }

	 public void setObsId( String obsId ) {
		 this.obsId = obsId;
	 }

	 public void setChecklistId( String checklistId ) {
		 this.checklistId = checklistId;
	 }

	 public void setPresenceNoted( boolean presenceNoted ) {
		 this.presenceNoted = presenceNoted;
	 }

	 public void setHasComments( boolean hasComments ) {
		 this.hasComments = hasComments;
	 }

	 public void setFirstName( String firstName ) {
		 this.firstName = firstName;
	 }

	 public void setLastName( String lastName ) {
		 this.lastName = lastName;
	 }

	 public void setHasRichMedia( boolean hasRichMedia ) {
		 this.hasRichMedia = hasRichMedia;
	 }

	 public void setLocID( String locID ) {
		 this.locID = locID;
	 }


}
