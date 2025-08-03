package dev.infomatik.ebird.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChecklistObservation {
	
	@JsonProperty("projId")
	private String projId; //  ": "EBIRD",
	
	//@JsonProperty("subId")
	//@JsonIgnore
	private String subId; //  ": "S29893687",
	private String protocolId; //  ": "P21",
	private String locId; //  ": "L99381",
	private double durationHrs; //  ": 1,
	private boolean allObsReported; //  ": false,
	private String creationDt; //  ": "2018-01-04 14:30",
	private String lastEditedDt; //  ": "2018-01-09 18:45",
	private String obsDt; //  ": "2018-01-04 23:29",
	private boolean obsTimeValid; //  ": true,
	private String checklistId; //  ": "CL22224",
	private int numObservers; //  ": 1,
	private String subnational1Code; //  ": "US-NY",
	private String submissionMethodCode; //  ": "EBIRD_api",
	private String obsTime;
	private String userDisplayName; //  ": "Tim Lenz",
	private  List <Observation> obs;
	private String hierarchicalName;
	private int numSpecies;
	@JsonProperty("loc")
	private Location loc;
	private String subID;
	
	private String isoObsDate;
	@JsonIgnore
	private double lat;
	@JsonIgnore
	private double lon;
	
	
	
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
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * @return the obsTime
	 */
	public String getObsTime() {
		return obsTime;
	}
	/**
	 * @param obsTime the obsTime to set
	 */
	public void setObsTime(String obsTime) {
		this.obsTime = obsTime;
	}
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
	 * @return the protocolId
	 */
	public String getProtocolId() {
		return protocolId;
	}
	/**
	 * @param protocolId the protocolId to set
	 */
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
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
	 * @return the durationHrs
	 */
	public double getDurationHrs() {
		return durationHrs;
	}
	/**
	 * @param durationHrs the durationHrs to set
	 */
	public void setDurationHrs(double durationHrs) {
		this.durationHrs = durationHrs;
	}
	/**
	 * @return the allObsReported
	 */
	public boolean isAllObsReported() {
		return allObsReported;
	}
	/**
	 * @param allObsReported the allObsReported to set
	 */
	public void setAllObsReported(boolean allObsReported) {
		this.allObsReported = allObsReported;
	}
	/**
	 * @return the creationDt
	 */
	public String getCreationDt() {
		return creationDt;
	}
	/**
	 * @param creationDt the creationDt to set
	 */
	public void setCreationDt(String creationDt) {
		this.creationDt = creationDt;
	}
	/**
	 * @return the lastEditedDt
	 */
	public String getLastEditedDt() {
		return lastEditedDt;
	}
	/**
	 * @param lastEditedDt the lastEditedDt to set
	 */
	public void setLastEditedDt(String lastEditedDt) {
		this.lastEditedDt = lastEditedDt;
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
	 * @return the obsTimeValid
	 */
	public boolean isObsTimeValid() {
		return obsTimeValid;
	}
	/**
	 * @param obsTimeValid the obsTimeValid to set
	 */
	public void setObsTimeValid(boolean obsTimeValid) {
		this.obsTimeValid = obsTimeValid;
	}
	/**
	 * @return the checklistId
	 */
	public String getChecklistId() {
		return checklistId;
	}
	/**
	 * @param checklistId the checklistId to set
	 */
	public void setChecklistId(String checklistId) {
		this.checklistId = checklistId;
	}
	/**
	 * @return the numObservers
	 */
	public int getNumObservers() {
		return numObservers;
	}
	/**
	 * @param numObservers the numObservers to set
	 */
	public void setNumObservers(int numObservers) {
		this.numObservers = numObservers;
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
	 * @return the submissionMethodCode
	 */
	public String getSubmissionMethodCode() {
		return submissionMethodCode;
	}
	/**
	 * @param submissionMethodCode the submissionMethodCode to set
	 */
	public void setSubmissionMethodCode(String submissionMethodCode) {
		this.submissionMethodCode = submissionMethodCode;
	}
	/**
	 * @return the userDisplayName
	 */
	public String getUserDisplayName() {
		return userDisplayName;
	}
	/**
	 * @param userDisplayName the userDisplayName to set
	 */
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	/**
	 * @return the obs
	 */
	public List<Observation> getObs() {
		return obs;
	}
	/**
	 * @param obs the obs to set
	 */
	public void setObs(List<Observation> obs) {
		this.obs = obs;
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
	 * @return the numSpecies
	 */
	public int getNumSpecies() {
		return numSpecies;
	}
	/**
	 * @param numSpecies the numSpecies to set
	 */
	public void setNumSpecies(int numSpecies) {
		this.numSpecies = numSpecies;
	}
	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	/**
	 * @return the subID
	 */
	public String getSubID() {
		return subID;
	}
	/**
	 * @param subID the subID to set
	 */
	public void setSubID(String subID) {
		this.subID = subID;
	}
	
	
	
	
	
	

}
