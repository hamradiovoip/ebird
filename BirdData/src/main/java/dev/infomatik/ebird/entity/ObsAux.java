package dev.infomatik.ebird.entity;

import java.util.Objects;

public class ObsAux {

	private String subId;   // ": "S29893687",
	private String obsId;   // "": "OBS406047820",
	private String speciesCode;   // "": "cacgoo1",
	private String fieldName;   // "": "breeding_code",
	private String entryMethodCode;   // "": "ebird_breeding",
	private String auxCode;   // "": "UN",
	private String value;   // "": "C4"
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
	 * @return the obsId
	 */
	public String getObsId() {
		return obsId;
	}
	/**
	 * @param obsId the obsId to set
	 */
	public void setObsId(String obsId) {
		this.obsId = obsId;
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
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the entryMethodCode
	 */
	public String getEntryMethodCode() {
		return entryMethodCode;
	}
	/**
	 * @param entryMethodCode the entryMethodCode to set
	 */
	public void setEntryMethodCode(String entryMethodCode) {
		this.entryMethodCode = entryMethodCode;
	}
	/**
	 * @return the auxCode
	 */
	public String getAuxCode() {
		return auxCode;
	}
	/**
	 * @param auxCode the auxCode to set
	 */
	public void setAuxCode(String auxCode) {
		this.auxCode = auxCode;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(auxCode, entryMethodCode, fieldName, obsId, speciesCode, subId, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObsAux other = (ObsAux) obj;
		return Objects.equals(auxCode, other.auxCode) && Objects.equals(entryMethodCode, other.entryMethodCode)
				&& Objects.equals(fieldName, other.fieldName) && Objects.equals(obsId, other.obsId)
				&& Objects.equals(speciesCode, other.speciesCode) && Objects.equals(subId, other.subId)
				&& Objects.equals(value, other.value);
	}
	@Override
	public String toString() {
		return "ObsAux [subId=" + subId + ", obsId=" + obsId + ", speciesCode=" + speciesCode + ", fieldName="
				+ fieldName + ", entryMethodCode=" + entryMethodCode + ", auxCode=" + auxCode + ", value=" + value
				+ "]";
	}
	
	
	
	
	
	
	
	
}
