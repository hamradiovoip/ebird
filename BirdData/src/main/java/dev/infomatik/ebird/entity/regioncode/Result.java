package dev.infomatik.ebird.entity.regioncode;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	
"block_fips",
"bbox",
"county_fips",
"county_name",
"state_fips",
"state_code",
"state_name",
"block_pop_2020",
"amt",
"bea",
"bta",
"cma",
"eag",
"ivm",
"mea",
"mta",
"pea",
"rea",
"rpc",
"vpc"
})

public class Result {

@JsonProperty("block_fips")
private String blockFips;
@JsonProperty("bbox")
private List<Double> bbox;
@JsonProperty("county_fips")
private String countyFips;
@JsonProperty("county_name")
private String countyName;
@JsonProperty("state_fips")
private String stateFips;
@JsonProperty("state_code")
private String stateCode;
@JsonProperty("state_name")
private String stateName;
@JsonProperty("block_pop_2020")
private Integer blockPop2020;
@JsonProperty("amt")
private String amt;
@JsonProperty("bea")
private String bea;
@JsonProperty("bta")
private String bta;
@JsonProperty("cma")
private String cma;
@JsonProperty("eag")
private String eag;
@JsonProperty("ivm")
private String ivm;
@JsonProperty("mea")
private String mea;
@JsonProperty("mta")
private String mta;
@JsonProperty("pea")
private String pea;
@JsonProperty("rea")
private String rea;
@JsonProperty("rpc")
private String rpc;
@JsonProperty("vpc")
private String vpc;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("block_fips")
public String getBlockFips() {
return blockFips;
}

@JsonProperty("block_fips")
public void setBlockFips(String blockFips) {
this.blockFips = blockFips;
}

@JsonProperty("bbox")
public List<Double> getBbox() {
return bbox;
}

@JsonProperty("bbox")
public void setBbox(List<Double> bbox) {
this.bbox = bbox;
}

@JsonProperty("county_fips")
public String getCountyFips() {
return countyFips;
}

@JsonProperty("county_fips")
public void setCountyFips(String countyFips) {
this.countyFips = countyFips;
}

@JsonProperty("county_name")
public String getCountyName() {
return countyName;
}

@JsonProperty("county_name")
public void setCountyName(String countyName) {
this.countyName = countyName;
}

@JsonProperty("state_fips")
public String getStateFips() {
return stateFips;
}

@JsonProperty("state_fips")
public void setStateFips(String stateFips) {
this.stateFips = stateFips;
}

@JsonProperty("state_code")
public String getStateCode() {
return stateCode;
}

@JsonProperty("state_code")
public void setStateCode(String stateCode) {
this.stateCode = stateCode;
}

@JsonProperty("state_name")
public String getStateName() {
return stateName;
}

@JsonProperty("state_name")
public void setStateName(String stateName) {
this.stateName = stateName;
}

@JsonProperty("block_pop_2020")
public Integer getBlockPop2020() {
return blockPop2020;
}

@JsonProperty("block_pop_2020")
public void setBlockPop2020(Integer blockPop2020) {
this.blockPop2020 = blockPop2020;
}

@JsonProperty("amt")
public String getAmt() {
return amt;
}

@JsonProperty("amt")
public void setAmt(String amt) {
this.amt = amt;
}

@JsonProperty("bea")
public String getBea() {
return bea;
}

@JsonProperty("bea")
public void setBea(String bea) {
this.bea = bea;
}

@JsonProperty("bta")
public String getBta() {
return bta;
}

@JsonProperty("bta")
public void setBta(String bta) {
this.bta = bta;
}

@JsonProperty("cma")
public String getCma() {
return cma;
}

@JsonProperty("cma")
public void setCma(String cma) {
this.cma = cma;
}

@JsonProperty("eag")
public String getEag() {
return eag;
}

@JsonProperty("eag")
public void setEag(String eag) {
this.eag = eag;
}

@JsonProperty("ivm")
public String getIvm() {
return ivm;
}

@JsonProperty("ivm")
public void setIvm(String ivm) {
this.ivm = ivm;
}

@JsonProperty("mea")
public String getMea() {
return mea;
}

@JsonProperty("mea")
public void setMea(String mea) {
this.mea = mea;
}

@JsonProperty("mta")
public String getMta() {
return mta;
}

@JsonProperty("mta")
public void setMta(String mta) {
this.mta = mta;
}

@JsonProperty("pea")
public String getPea() {
return pea;
}

@JsonProperty("pea")
public void setPea(String pea) {
this.pea = pea;
}

@JsonProperty("rea")
public String getRea() {
return rea;
}

@JsonProperty("rea")
public void setRea(String rea) {
this.rea = rea;
}

@JsonProperty("rpc")
public String getRpc() {
return rpc;
}

@JsonProperty("rpc")
public void setRpc(String rpc) {
this.rpc = rpc;
}

@JsonProperty("vpc")
public String getVpc() {
return vpc;
}

@JsonProperty("vpc")
public void setVpc(String vpc) {
this.vpc = vpc;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "Result [blockFips=" + blockFips + ", bbox=" + bbox + ", countyFips=" + countyFips + ", countyName="
			+ countyName + ", stateFips=" + stateFips + ", stateCode=" + stateCode + ", stateName=" + stateName
			+ ", blockPop2020=" + blockPop2020 + ", amt=" + amt + ", bea=" + bea + ", bta=" + bta + ", cma=" + cma
			+ ", eag=" + eag + ", ivm=" + ivm + ", mea=" + mea + ", mta=" + mta + ", pea=" + pea + ", rea=" + rea
			+ ", rpc=" + rpc + ", vpc=" + vpc + ", additionalProperties=" + additionalProperties + "]";
}






}
