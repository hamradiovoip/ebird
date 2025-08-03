package dev.infomatik.ebird.entity.regioncode;
/**
 * For getting region code from census site. FCC has one also.
 * 
 *  https://geocoding.geo.census.gov/geocoder/geographies/coordinates?x="+lon+"&y="+lat +"&benchmark=4&vintage=4&format=json";
 */

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"lat",
	"lon",
	"censusYear"
})

public class Input {

	@JsonProperty("lat")
	private Integer lat;
	@JsonProperty("lon")
	private Integer lon;
	@JsonProperty("censusYear")
	private String censusYear;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("lat")
	public Integer getLat() {
		return lat;
	}

	@JsonProperty("lat")
	public void setLat(Integer lat) {
		this.lat = lat;
	}

	@JsonProperty("lon")
	public Integer getLon() {
		return lon;
	}

	@JsonProperty("lon")
	public void setLon(Integer lon) {
		this.lon = lon;
	}

	@JsonProperty("censusYear")
	public String getCensusYear() {
		return censusYear;
	}

	@JsonProperty("censusYear")
	public void setCensusYear(String censusYear) {
		this.censusYear = censusYear;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}