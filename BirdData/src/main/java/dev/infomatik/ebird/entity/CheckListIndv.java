package dev.infomatik.ebird.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CheckListIndv {

	@JsonIgnore
	private String projectIds; // TODO newly added but not in documentation
	@JsonIgnore
	private String effortAreaEnteredUnit; // TODO newly added but not in documentation
	private String projId;
	private String subId;
	private String protocolId;
	private String locId;
	private double durationHrs;
	private boolean allObsReported;
	private String creationDt;
	private String lastEditedDt;
	private String obsDt;
	private boolean obsTimeValid;
	private String checklistId;
	private int numObservers;
	private float effortDistanceKm;
	@JsonIgnore
	private String effortDistanceEnteredUnit;
	private String subnational1Code;
	private String submissionMethodCode;
	private String submissionMethodVersion;
	private String userDisplayName;
	private int numSpecies;
	private String submissionMethodVersionDisp;
	private List < ObsAux > subAux;
	private List < Object > subAuxAi;
	private List < Observation > obs; // birds seen data
	private String comments;

	private String groupId;
	@JsonIgnore
	private String track;
	@JsonIgnore
	private boolean deleteTrack;




	/**
	 * @return the effortAreaEnteredUnit
	 */
	public String getEffortAreaEnteredUnit() {
		return effortAreaEnteredUnit;
	}
	/**
	 * @param effortAreaEnteredUnit the effortAreaEnteredUnit to set
	 */
	public void setEffortAreaEnteredUnit(String effortAreaEnteredUnit) {
		this.effortAreaEnteredUnit = effortAreaEnteredUnit;
	}
	/**
	 * @return the projectIds
	 */
	public String getProjectIds() {
		return projectIds;
	}
	/**
	 * @param projectIds the projectIds to set
	 */
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	/**
	 * @return the deleteTrack
	 */
	public boolean getDeleteTrack() {
		return deleteTrack;
	}
	/**
	 * @param deleteTrack the deleteTrack to set
	 */
	public void setDeleteTrack(boolean deleteTrack) {
		this.deleteTrack = deleteTrack;
	}
	/**
	 * @return the track
	 */
	public String getTrack() {
		return track;
	}
	/**
	 * @param track the track to set
	 */
	public void setTrack(String track) {
		this.track = track;
	}
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	 * @return the effortDistanceKm
	 */
	public float getEffortDistanceKm() {
		return effortDistanceKm;
	}
	/**
	 * @param effortDistanceKm the effortDistanceKm to set
	 */
	public void setEffortDistanceKm(float effortDistanceKm) {
		this.effortDistanceKm = effortDistanceKm;
	}
	/**
	 * @return the effortDistanceEnteredUnit
	 */
	public String getEffortDistanceEnteredUnit() {
		return effortDistanceEnteredUnit;
	}
	/**
	 * @param effortDistanceEnteredUnit the effortDistanceEnteredUnit to set
	 */
	public void setEffortDistanceEnteredUnit(String effortDistanceEnteredUnit) {
		this.effortDistanceEnteredUnit = effortDistanceEnteredUnit;
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
	 * @return the submissionMethodVersion
	 */
	public String getSubmissionMethodVersion() {
		return submissionMethodVersion;
	}
	/**
	 * @param submissionMethodVersion the submissionMethodVersion to set
	 */
	public void setSubmissionMethodVersion(String submissionMethodVersion) {
		this.submissionMethodVersion = submissionMethodVersion;
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
	 * @return the submissionMethodVersionDisp
	 */
	public String getSubmissionMethodVersionDisp() {
		return submissionMethodVersionDisp;
	}
	/**
	 * @param submissionMethodVersionDisp the submissionMethodVersionDisp to set
	 */
	public void setSubmissionMethodVersionDisp(String submissionMethodVersionDisp) {
		this.submissionMethodVersionDisp = submissionMethodVersionDisp;
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
	 * @return the subAuxAi
	 */
	public List<Object> getSubAuxAi() {
		return subAuxAi;
	}
	/**
	 * @param subAuxAi the subAuxAi to set
	 */
	public void setSubAuxAi(List<Object> subAuxAi) {
		this.subAuxAi = subAuxAi;
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





}
