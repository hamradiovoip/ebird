package dev.infomatik.ebird.service;
/**
 * Gets data (JSON) from the eBird API.
 * https://documenter.getpostman.com/view/664302/S1ENwy59#intro
 * eBird API 2.0
 * 
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import dev.infomatik.ebird.entity.Coords;
import dev.infomatik.ebird.entity.HotSpot;
import dev.infomatik.ebird.entity.LocationProp;
import dev.infomatik.ebird.entity.MapPoints;
import dev.infomatik.ebird.entity.Nearby;
import dev.infomatik.ebird.entity.Notable;
import dev.infomatik.ebird.entity.NotableList;
import dev.infomatik.ebird.entity.Recent;
import dev.infomatik.ebird.entity.Region;
import dev.infomatik.ebird.entity.RegionSpecies;
import dev.infomatik.ebird.entity.States;
import dev.infomatik.ebird.entity.regioncode.CountySub;
import dev.infomatik.ebird.entity.regioncode.Result;
import dev.infomatik.ebird.entity.tax.Name;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonIgnoreProperties
@Service
public class BirdService {

	protected static final Logger logger = LogManager.getLogger(); // TODO

	@Value("${api.key}")
	/** APi key needed to access the ebird api, put it in the application.prop file.
	 * Get it here: https://ebird.org/api/keygen */
	private String apikey; 	

	/** URLs */
	/** 
	 * Get the list of recent, notable observations (up to 30 days ago) of birds seen in a country, region or location.
	 */
	@Value("${url.recent}")
	private String urlStrRecent;// = "https://api.ebird.org/v2/data/obs/geo/recent?";// lat={{lat}}&lng={{lng}}

	/** 
	 * Get information on the location of a hotspot.
	 */
	@Value("${url.hotspot}")
	private String urlStrHotSpot;// = "https://api.ebird.org/v2/ref/hotspot/geo?"; //lat={{lat}}&lng={{lng}}
	
	/**
	 * Get the list of recent, notable observations (up to 30 days ago) of birds seen in a country, region or location.
	 * Notable observations can be for locally or nationally 
	 * rare species or are otherwise unusual, e.g. over-wintering birds in a species which is normally only a summer visitor.
	 */
	@Value("${url.notable}")
	private String urlStrNotable; // = "https://api.ebird.org/v2/data/obs/"; 
	
	/** main url */
	@Value("${url.ebird}")
	private String ebirdUrl; // = "https://api.ebird.org/"; 

	/** for adding api to the header */
	@Value("${api.header}")
	private String header; //: {{x-ebirdapitoken}}'


	/** testing */
	//private String allDataFilename = "/home/thorn/Documents/workspace-spring-tool-suite-4-4.23.1.RELEASE/BirdData/src/main/resources/data/ebird.json";
	
	/** Save bird name data used for searching common name etc. */
	Map<String,String> birdNameMap = new HashMap<>();
	
	/** Save bird name data used for searching common name etc. */
	Map<String,String> reverseBirdNameMap = new HashMap<>();

	/** List of bird names.  */
	private List <Name> birdNames;
	
	/** List of bird names, String.  */
	private List <String> birdNamesStr;
	
	/** List for states */
	List <States> states;
	
	/**  Location properties, lat, lon, name etc */
	List <LocationProp> locationProps;


	/**
	 * @return the birdNamesStr
	 */
	public List<String> getBirdNamesStr() {
		return birdNamesStr;
	}

	/**
	 * @param birdNamesStr the birdNamesStr to set
	 */
	public void setBirdNamesStr(List<String> birdNamesStr) {
		this.birdNamesStr = birdNamesStr;
	}

	/**
	 * @return the birdNames
	 */
	public List<Name> getBirdNames() {
		return birdNames;
	}

	/**
	 * @param birdNames the birdNames to set
	 */
	public void setBirdNames(List<Name> birdNames) {
		this.birdNames = birdNames;
	}


	/**
	 * Get coords of the center of sata/region.
	 * TODO nice to have other countries
	 * 
	 * @param stateCode (ie MD, NY)
	 * @return Coords of center of region/state
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Coords getCoordsState(String stateCode) throws FileNotFoundException, IOException {

		// Probably better way to do this vs list
		List <States> stateList = readStateData(); 

		double lat = 39;
		double lon  =-90;

		for (States  element : stateList) {

			String r = element.getCode();

			if(r.contains(stateCode)) {

				lat = element.getLat();
				lon  = element.getLon(); 

			}
		}

		Coords coords = new Coords(lat, lon);

		return coords;
	}


	/**
	 * Recent notable observations in a region.  Get the list of recent, notable observations (up to 30 days ago)
	 * of birds seen in a country, region or location.
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 */
	public String callEbirdNotableRegion(String region, int back) {

		//	https://api.ebird.org/v2/data/obs/{{regionCode}}/recent/notable?detail=full
		String url = urlStrNotable + region + "/recent/notable?detail=full&back=" + back; 	

		//System.out.println("Notable url = "+ url);

		String resp = callExternalApi(url);

		//		System.out.println(resp);

		return (resp);

	}

	// https://ebird.org/barchart?byr=1900&eyr=2024&bmo=1&emo=12&r=US-MD-043


	/**
	 * Get the list of notable observations (up to 30 days ago) 
	 * of birds seen at locations within a radius of up to 50 kilometers, from a given set of coordinates.
	 * Notable observations can be for locally or nationally rare species or are otherwise unusual, for example 
	 * over-wintering birds in a species which is normally only a summer visitor.
	 * 
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return
	 */
	public String callEbirdNotable(double lat, double lon, double dist, int back) {

		String url = urlStrNotable + "geo/recent/notable?lat="+
				lat + "&lng=" + lon + "&detail=full&" + "dist=" + dist + "&back="  + back;

		String  resp = callExternalApi(url);

		//System.out.println(resp);

		return (resp);

	}

	/**
	 * Get the list of recent observations (up to 30 days ago) of birds seen at locations within a radius of up to 50 kilometers,
	 * from a given set of coordinates. Results include only the most recent observation for each species in the region specified.
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
 	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return
	 */
	public String callEbirdRecent(double lat, double lon, double dist, int back) {


		String url = urlStrRecent +  "lat="+lat + "&lng="+lon + "&dist=" + dist + "&back=" + back +
				"&includeProvisional=true";//   {{lng}}		

		String  resp = callExternalApi(url);

		//	System.out.println(resp);

		return (resp);

	}


	/**
	 * Get the list of hotspots, within region.
	 * https://api.ebird.org/v2/ref/hotspot/{{regionCode}}
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
 	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return
	 */
	public String callEbirdHotSpotRegion(String region, double dist, int back) {

		String url = ebirdUrl + "v2/ref/hotspot/" +  region + "?back=" + back +"&fmt=json";

		String  resp = callExternalApi(url);

		//System.out.println(resp);

		return (resp);

	}

	/**
	 * Get the list of hotspots, within a radius of up to 50 kilometers, from a given set of coordinates.
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
 	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return
	 */
	public String callEbirdHotSpot(double lat, double lon, double dist, int back) {

		String url = urlStrHotSpot +  "lat="+lat + "&lng="+lon + "&dist=" + dist + "&back=" + back +
				"&fmt=json";//   {{lng}}		

		String  resp = callExternalApi(url);	

		return (resp);

	}

	/**
	 * Get checklist without coords for map, just a list
	 * https://api.ebird.org/v2/product/checklist/view/{{subId}}
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 */
	public String callEbirdChecklistDate(String regionCode, String year, String month, String day, String max) {

		String url = ebirdUrl + "v2/product/lists/" + regionCode +
				"?maxResults=" + max + "&year=" + year + "&month=" + month + "&day=" + day; 

		String resp = callExternalApi(url);

		return (resp);
	}



	/**
	 * Get checklist without coords for map, just a list
	 * https://api.ebird.org/v2/product/checklist/view/{{subId}}
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 */
	public String callEbirdChecklist(String subId) {

		String subIdUrl = ebirdUrl + "v2/product/lists/" + subId +"?maxResults=100"; //{{subId}}

		String  resp =	callExternalApi(subIdUrl);

		return (resp);
	}


	/**
	 * Get the details and observations of a checklist.
	 * https://api.ebird.org/v2/product/checklist/view/{{subId}}
	 * @return Get the details and observations of a checklist.
	 */
	public String callEbirdChecklistView(String subId) {

		String subIdUrl = ebirdUrl + "v2/product/checklist/view/" + subId; //{{subId}}

		String  resp =	callExternalApi(subIdUrl);

		return (resp);
	}


	/**
	 * Recent observations of a species in a region
	 * https://api.ebird.org/v2/data/obs/{{regionCode}}/recent/{{speciesCode}}
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 */
	public String callEbirdRecentSpecies(String region, String species, int days) {

		//	String subIdUrl = "https://api.ebird.org/v2/product/spplist/" + region +"/recent/" + species; 
		// https://api.ebird.org/v2/data/obs/{{regionCode}}/recent/{{speciesCode}}
		//?regionCode=US-MD-043&speciesCode=wooduc

		int back = days;

		if (days == 0) {
			back = 1;
		}
		String subIdUrl =  ebirdUrl + "v2/data/obs/"+ region +"/recent/" + species + "?back=" + back;

		String resp = callExternalApi(subIdUrl);

		return (resp);
	}
	

	/**
	 * Get a list of species codes ever seen in a region, in taxonomic order (species taxa only)
	 * https://api.ebird.org/v2/product/spplist/{{regionCode}}
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 */
	public String callEbirdSpecies(String region) {

		String subIdUrl = ebirdUrl + "v2/product/spplist/" + region; //{{subId}}

		String resp = callExternalApi(subIdUrl);

		System.out.println("response: "+ resp);	

		return (resp);
	}

	/**
	 * Call ebird query using apikey and "Referrer-Policy" in header.
	 * @param url
	 * @return String response body.
	 */

	@PostMapping
	@ResponseBody
	public String callExternalApi(@RequestBody String url) {
		
		logger.info("Calling ebird api {} header = {} ", url, header);
		
		HttpHeaders headers = new HttpHeaders();

		// need this for maps to work without 403 issues
		// Tile Usage Policy
		// https://wiki.openstreetmap.org/wiki/Referer

		headers.set("Referrer-Policy", "same-origin");
		headers.set("User-Agent", "CustomApp/1.0");	

		headers.add(header, apikey);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		return response.getBody();
	}


	/**
	 * Get the list of countries or regions that share a border with this one.
	 * https://api.ebird.org/v2/ref/adjacent/{{regionCode}}
	 *  
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param regionString
	 * @return
	 */
	public List <Region> getAdjacentRegions(double lat, double lon, String regionString) {


		String url = "";
		Map<String,String> map = new HashMap<>();

		if(regionString != null) {
			url = ebirdUrl + "2/ref/adjacent/" + regionString;
		}
		else {

			String regionCode = "";
			try {
				//regionCode = getRegionCode(lat, lon); //get from FCC DB

				regionCode = getStateCode(lat, lon); //get state code from FCC DB

			} catch (Exception e) {
				logger.error("Problem with : https://api.ebird.org/v2/ref/adjacent/ {} {} {}", regionCode, lat, lon);
				e.printStackTrace();
			}

			url = "https://api.ebird.org/v2/ref/adjacent/" + regionCode;
		}

		String jString = callExternalApi(url);

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		Region region = new Region();

		ObjectMapper jsonObjectMapper = new ObjectMapper();
		List<Region> regionList = new ArrayList<Region>();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				region = jsonObjectMapper.readValue(str, Region.class);				

			} catch (JsonProcessingException e) {
				
				logger.error("Problem with jsonObjectMapper  {} {}",  lat, lon);
				e.printStackTrace();
				//throw e;
			}

			regionList.add(region);

		}

		return regionList;
	}


	/**
	 * Recent observations of a species in a region
	 * @param region
	 * @param species
	 * @return
	 */
	public List <RegionSpecies>  getRecentSpeciesRegion(String region, String species, int days) {

		ObjectMapper jsonObjectMapper = new ObjectMapper();
		List<RegionSpecies> regionSpeciesList = new ArrayList<RegionSpecies>();

		String jString = callEbirdRecentSpecies(region, species, days);

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();
		RegionSpecies regionSpecies = null;

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				regionSpecies = jsonObjectMapper.readValue(str, RegionSpecies.class);				

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				//throw e;
			}

			regionSpeciesList.add(regionSpecies);

		}
		return regionSpeciesList;

	}



	/**
	 * Get information on the name and geographical area covered by a region.
	 * https://api.ebird.org/v2/ref/region/info/{{regionCode}}
	 * @param region
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public String getPlaceName(String region) throws JsonMappingException, JsonProcessingException {

		ObjectMapper jsonObjectMapper = new ObjectMapper();		
		String url = ebirdUrl + "v2/ref/region/info/"+ region +"?regionNameFormat=detailed";	

		String place =  callExternalApi(url);

		JsonNode rootNode = jsonObjectMapper.readTree(place); // placeName

		String placeName = rootNode.get("result").asText();

		return placeName;
	}


	/**
	 * Nearest observations of a species
	 * https://api.ebird.org/v2/data/nearest/geo/recent/{{speciesCode}}?lat={{lat}}&lng={{lng}}
	 * speciesCode	The eBird species code.
	 * @param speciesCode
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return List of species
	 * @throws JsonProcessingException 
	 */
	public List <Nearby> getNearestObsSpecies(String speciesCode, double lat, double lon, double dist, int back) throws JsonProcessingException {

		String url = ebirdUrl + "v2/data/nearest/geo/recent/"+ speciesCode +
				"?lat=" +lat + "&lng=" +lon + "&dist=" + dist + "&back=" + back + "&maxResults=250";
		
		String jString =  callExternalApi(url);
			
		JSONArray jsonArray = new org.json.JSONArray(jString);

		int maxSize = jsonArray.length();
		
		String jsonStr = jString.substring(1, jString.length() - 1); // strip off [] on the ends

		
		ObjectMapper jsonObjectMapper = new ObjectMapper();		
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		List <Nearby> nearbyList = new ArrayList<Nearby>();

		Nearby nearby;
		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();


			try {
				nearby = jsonObjectMapper.readValue(str, Nearby.class);				

			} catch (JsonProcessingException e) {				

				e.printStackTrace();
				throw e;
			}


			nearbyList.add(nearby);	
		}
		return nearbyList;
	}


	/**
	 * TODO could use for lookup?
	 * @return
	 */
	public String getSubNational1RegionData() {

		String urls = ebirdUrl + "v2/ref/region/list/subnational1/US.json";
		String usajson =  callExternalApi(urls);		
		//		System.out.println("usajson = "+ usajson);			
		return usajson;
	}


	/**
	 * TODO could use for lookup?
	 * @return
	 */
	public String getSubNational2RegionData() {

		String urls2 = ebirdUrl + "v2/ref/region/list/subnational2/US.json";
		String usajson2 = callExternalApi(urls2);

		// System.out.println("usajson2 = "+ usajson2);		

		return usajson2;
	}	


	/**
	 * Get a list of species codes ever seen in a region, in taxonomic order (species taxa only)
	 * 	https://api.ebird.org/v2/product/spplist/{{regionCode}}
	 * @return List of species.
	 */
	public List <String> getSpeciesListRegion(String region) {

		String jString = callEbirdSpecies(region);

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List <String> commonNameList =  new ArrayList<String>();

		Map map = createMap();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String SpeciesCodeName = o.toString();

			// lookup and add			TODO

			String commonName = getCommonName(SpeciesCodeName);		

			commonNameList.add(commonName);	
		}

		return commonNameList;			
	}


	/**
	 * Get a list of species codes ever seen in a region, in taxonomic order (species taxa only)
	 * 	https://api.ebird.org/v2/product/spplist/{{regionCode}}
	 * @return List of species.
	 */
	public Map getSpeciesListRegionMap(String region) {

		Map<String,String> map2 = new HashMap<>();

		String jString = callEbirdSpecies(region);

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List <String> commonNameList =  new ArrayList<String>();

		Map map = createMap();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String SpeciesCodeName = o.toString();

			//	String SpeciesCodeName = obs.get(i).getSpeciesCode();

			// lookup and add			TODO
			String commonName = getCommonName(SpeciesCodeName);	

			map2.put(SpeciesCodeName, commonName);

			commonNameList.add(commonName);	
		}
		return map2;			
	}


	/**
	 * Recent observations of a species in a region
	 * 	https://api.ebird.org/v2/product/spplist/{{regionCode}}
	 * @return List of species.
	 */
	public Map <String,String> getRecentSpeciesRegionMap2(String region, String species, int days) {

		Map<String,String> map2 = new HashMap<>();

		String jString = callEbirdRecentSpecies(region, species, days);

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List <String> commonNameList =  new ArrayList<String>();

		Map map = createMap();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String SpeciesCodeName = o.toString();

			//	String SpeciesCodeName = obs.get(i).getSpeciesCode();

			// lookup and add			TODO

			String commonName = getCommonName(SpeciesCodeName);	

			map2.put(SpeciesCodeName, commonName);


			commonNameList.add(commonName);	
		}


		return map2;			
	}



	/**
	 * Return a list of notable objects to be displayed from json data.
	 * Get the list of recent observations (up to 30 days ago) of birds seen in a country,
	 * state, county, or location. Results include only the most recent observation for 
	 * each species in the region specified.
	 * 
	 * https://api.ebird.org/v2/data/obs/{{regionCode}}/recent/notable?detail=full
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return List<Recent>  a list of Recent objects to be displayed.
	 * @throws Exception 
	 */
	public NotableList getNotableRegion(String region, int back) throws Exception {

		ObjectMapper jsonObjectMapper = new ObjectMapper();
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map

		NotableList notableLists = new NotableList();

		String jString = callEbirdNotableRegion(region, back);	

		JSONArray jsonArray = new org.json.JSONArray(jString);

		int maxSize = jsonArray.length();

		List<Notable> notableList = new ArrayList<Notable>();
		Notable notable = new Notable();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				notable = jsonObjectMapper.readValue(str, Notable.class);

			} catch (JsonProcessingException e) {

				e.printStackTrace();
				throw e;
			}

			MapPoints point = new MapPoints();
			point.setLat(notable.getLat());
			point.setLon(notable.getLng());
			point.setSubId(notable.getSubId());
			point.setTextDescription(notable.getLocName());
			point.setBirdComName(notable.getComName());
			point.setDate(notable.getObsDt());
			pointList.add(point);

			// TODO need to create record  vs class and return it with both data.	

			notableList.add(notable);
		}
		notableLists.setNotable(notableList);
		notableLists.setPointList(pointList);
		return notableLists;
	}



	/**
	 * Return a list of notable objects to be displayed from json data.
	 * Get the list of recent observations (up to 30 days ago) of birds seen in a country,
	 * state, county, or location. Results include only the most recent observation for 
	 * each species in the region specified.
	 * 
	 * https://api.ebird.org/v2/data/obs/geo/recent/notable?lat={{lat}}&lng={{lng}}
	 * 
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return List<Recent>  a list of Recent objects to be displayed.
	 * @throws Exception 
	 */
	public NotableList getNotable(double lat, double lon, double dist, int back) throws Exception {
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map

		NotableList notableLists = new NotableList();
		String region = null;
		String jString = null;	

		try {
			region = getRegionCodeCensus(lat, lon);
			jString = callEbirdNotable(lat, lon, dist, back);	

		}
		catch(MismatchedInputException e) {

			throw e;
		}
		
		JSONArray jsonArray = new org.json.JSONArray(jString);

		int maxSize = jsonArray.length();

		List<Notable> notableList = new ArrayList<Notable>();
		Notable notable = new Notable();

		// build list of notables
		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();
			
			try {
				notable = jsonObjectMapper.readValue(str, Notable.class);			

			} catch (JsonProcessingException e) {				

				e.printStackTrace();
				throw e;
			}

			MapPoints point = new MapPoints();
			point.setLat(notable.getLat());
			point.setBirdComName(notable.getComName());
			point.setDate(notable.getObsDt());
			point.setLon(notable.getLng());
			point.setSubId(notable.getSubId());
			point.setTextDescription(notable.getLocName());
			pointList.add(point);

			// TODO need to create Record vs Class and return it with both data?

			notableList.add(notable);
		}
		notableLists.setNotable(notableList);
		notableLists.setPointList(pointList);

		return notableLists;
	}


	/**
	 * Return a list of Recent objects to be displayed from json data.
	 * Get the list of recent observations (up to 30 days ago) of birds seen in a country,
	 * state, county, or location. Results include only the most recent observation for 
	 * each species in the region specified.
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return List<Recent>  a list of Recent objects to be displayed.
	 */
	public List<Recent> getRecent(double lat, double lon, double dist, int back) {
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map

		String jString = callEbirdRecent(lat, lon, dist, back);	

		JSONArray jsonArray = new org.json.JSONArray(jString);

		int maxSize = jsonArray.length();

		List<Recent> recentList = new ArrayList<Recent>();
		Recent recent = new Recent();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				recent = jsonObjectMapper.readValue(str, Recent.class);				

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				//throw e;
			}

			MapPoints point = new MapPoints();
			point.setLat(recent.getLat());
			point.setLon(recent.getLng());
			point.setSubId(recent.getSubId());
			point.setTextDescription(recent.getLocName());
			pointList.add(point);

			//TODO need to create record vs class and return it with both data.	
			recentList.add(recent);
		}

		return recentList;
	}


	/**
	 * Get a summary of the number of checklist submitted, species seen and contributors on a given date for a country or region.
	 * TODO, not sure if useful
	 * @param regionCode
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 * @throws IOException
	 */
	public String getStatsRegion(String regionCode, int y, int m, int d) throws IOException {
		String  sUrl = "https://api.ebird.org/v2/product/stats/"+regionCode + "/" +
				y + "/" + m + "/" + d;

		String resp = callExternalApi(sUrl);

		return resp;
	}


	/**
	 * Get a summary of the number of checklist submitted, species seen and contributors on a given date for a country or region.
	 * TODO, not sure if useful
	 * https://api.ebird.org/v2/ref/region/info/{{regionCode}}
	 * @param regionCode
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 * @throws IOException
	 */
	public String getInfoRegion(String regionCode) throws IOException {
		String  sUrl = "https://api.ebird.org/v2/ref/region/info/" + regionCode + "/";				

		String resp = callExternalApi(sUrl);

		return resp;
	}

	/**
	 * CENSUS Version
	 * USA only for now.
	 * TODO outside USA; town lookup?
	 * Given latitude & longitude, extract and return eBird "regionCode" from FCC database
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 * @throws Exception 
	 */
	private String getStateCodeCensus(double lat, double lon) throws Exception {

		//String  sUrl = "https://geo.fcc.gov/api/census/area?lat=" +lat + "&lon=" +lon + "&format=json";
		String  sUrl = "https://geocoding.geo.census.gov/geocoder/geographies/coordinates?x="+lon+"&y="+lat +"&benchmark=4&vintage=4&format=json";

		URL url = new URL(sUrl);
		dev.infomatik.ebird.entity.regioncode.States differentResult = null;
		ObjectMapper jsonObjectMapper = new ObjectMapper();

		JsonNode jn;
		try {
			jn = getJsonNode(url);
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
			throw e;
		}

		JsonNode jn2 = jn.get("results");	
		
		JsonNode rootNode = jsonObjectMapper.readTree(jn2.toString());

		String str = rootNode.toString();

		String jsonStr = str.substring(1, str.length() - 1);

		JsonNode rootNode2 = jsonObjectMapper.readTree(jsonStr);
		try {
			differentResult = jsonObjectMapper.treeToValue(rootNode2, dev.infomatik.ebird.entity.regioncode.States.class);
		}
		catch(MismatchedInputException e) {
			System.out.println("Problem with DB call to get region info");
			throw e;
		}

		String regionCode = "";

		String state = differentResult.getStusab() ;


		//CountySub county = differentResult.getCountySub();
		//String countyFips = county.getCOUNTY();
		//	String countyFips = differentResult.getCountyFips();
		//	String subnationalType2 = countyFips.substring(countyFips.length() - 3); // subnational type 2 last 3 used

		regionCode =  "US-"+ state;// + "-" + subnationalType2;

		return regionCode;

	} // Census version

	/**
	 * FCC Version
	 * USA only for now.
	 * TODO outside USA; town lookup?
	 * Given latitude & longitude, extract and return eBird "regionCode" from FCC database
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 * @throws Exception 
	 */
	private String getStateCodeFCC(double lat, double lon) throws Exception {

		String  sUrl = "https://geo.fcc.gov/api/census/area?lat=" +lat + "&lon=" +lon + "&format=json";

		URL url = new URL(sUrl);
		Result differentResult = null;
		ObjectMapper jsonObjectMapper = new ObjectMapper();

		JsonNode jn;
		try {
			jn = getJsonNode(url);
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
			throw e;
		}

		JsonNode jn2 = jn.get("results");	
		
		JsonNode rootNode = jsonObjectMapper.readTree(jn2.toString());

		String str = rootNode.toString();

		String jsonStr = str.substring(1, str.length() - 1);

		JsonNode rootNode2 = jsonObjectMapper.readTree(jsonStr);
		try {
			differentResult = jsonObjectMapper.treeToValue(rootNode2, Result.class);
		}
		catch(MismatchedInputException e) {
			System.out.println("Problem with FCC DB  call to get region info");
			throw e;
		}
		//fcc version
		String regionCode = "";

		regionCode =  "US-"+ differentResult.getStateCode();// + "-" + subnationalType2;

		return regionCode;
	}



	/**
	 * Census Version
	 * USA only for now.
	 * TODO outside USA; town lookup?
	 * Given latitude & longitude, extract and return eBird "regionCode" from FCC database
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return region code.
	 * @throws Exception 
	 */
	private String getRegionCodeCensus(double lat, double lon) throws Exception {

		String  sUrl = "https://geocoding.geo.census.gov/geocoder/geographies/coordinates?x="+lon+"&y="+lat +"&benchmark=4&vintage=4&format=json";

		URL url = new URL(sUrl);
		dev.infomatik.ebird.entity.regioncode.States stateResult = null;
		CountySub countyResults = null;

		ObjectMapper jsonObjectMapper = new ObjectMapper();

		JsonNode jn;

		try {
			jn = getJsonNode(url);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw e;
		}

		JsonNode jn2 = jn.get("result");			

		JsonNode geographies = jn2.get("geographies");			
		JsonNode jState = geographies.get("States");	
		JsonNode jCounty = geographies.get("County Subdivisions");	

		String strState = jState.toString();
		String strCounty = jCounty.toString();		


		String jsonStrState = strState.substring(1, strState.length() - 1);
		String jsonStrCounty = strCounty.substring(1, strCounty.length() - 1);

		JsonNode rootNodeState = jsonObjectMapper.readTree(jsonStrState);		
		JsonNode rootNodeCounty = jsonObjectMapper.readTree(jsonStrCounty);			

		try {
			countyResults = jsonObjectMapper.treeToValue(rootNodeCounty,  dev.infomatik.ebird.entity.regioncode.CountySub.class);
			stateResult = jsonObjectMapper.treeToValue(rootNodeState, dev.infomatik.ebird.entity.regioncode.States.class);
		}
		catch(MismatchedInputException e) {
			System.out.println("Problem with Census DB call to get region info");
			throw e;
		}

		String regionCode = "";
		String subnationalType2 = countyResults.getCounty();
		String stateStr = stateResult.getStusab();

		regionCode =  "US-"+ stateStr + "-" + subnationalType2;

		return regionCode;

	} // county Census version


	/**
	 * FCC version
	 * USA only for now.
	 * TODO outside USA; town lookup?
	 * Given latitude & longitude, extract and return eBird "regionCode" from FCC database
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 * @throws Exception 
	 */
	private String getRegionCodeFCC(double lat, double lon) throws Exception {

		String  sUrl = "https://geo.fcc.gov/api/census/area?lat=" +lat + "&lon=" +lon + "&format=json";

		URL url = new URL(sUrl);
		Result differentResult = null;
		ObjectMapper jsonObjectMapper = new ObjectMapper();

		JsonNode jn;
		try {
			jn = getJsonNode(url);
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
			throw e;
		}

		JsonNode jn2 = jn.get("results");	
		
		JsonNode rootNode = jsonObjectMapper.readTree(jn2.toString());

		String str = rootNode.toString();

		String jsonStr = str.substring(1, str.length() - 1);

		JsonNode rootNode2 = jsonObjectMapper.readTree(jsonStr);
		try {
			differentResult = jsonObjectMapper.treeToValue(rootNode2, Result.class);
		}
		catch(MismatchedInputException e) {
			System.out.println("Problem with DB call to get region info");
			throw e;
		}

		String regionCode = "";
		String countyFips = differentResult.getCountyFips();
		String subnationalType2 = countyFips.substring(countyFips.length() - 3); // subnational type 2 last 3 used

		regionCode =  "US-"+ differentResult.getStateCode() + "-" + subnationalType2;
		return regionCode;
	} // FCC


	/**
	 * Generic
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 * @throws Exception
	 */
	public String getRegionCode(double lat, double lon) throws Exception 
	{
		String regionCode = "";

		//Try census first
		try {
			regionCode = getRegionCodeCensus(lat, lon);
		}
		catch(Exception e) {
			System.out.println("Problem with Census call to get region info, try FCC next.");
			try {
				regionCode= getRegionCodeFCC(lat, lon); // if census fails
			}
			catch(Exception e2) {
				System.out.println("Problem with DB call to get region info");
				throw e2;
			}			
		}

		return(regionCode);
	}



	/**
	 * Generic
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @return
	 * @throws Exception
	 */
	public String getStateCode(double lat, double lon) throws Exception 
	{
		String regionCode = "";

		//Try census first
		try {

			regionCode = getStateCodeCensus(lat, lon);

		}
		catch(Exception e) {
			System.out.println("Problem with Census call to get region info, try FCC next.");
			try {
				regionCode= getStateCodeFCC(lat, lon);  // if census fails
			}
			catch(Exception e2) {
				System.out.println("Problem with FCC call to get region info");
				throw e2;
			}			
		}

		return(regionCode);
	}



	/**
	 * Get the list of hotspots, within region.
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return list of HotSpots
	 */
	public List <HotSpot> getHotSpotsRegion(String region, double dist, int days) {

		List <HotSpot> hotspotList = new ArrayList<HotSpot>();
		HotSpot hotspot = null;		
		String json = callEbirdHotSpotRegion(region, dist, days);	// days == back
		JSONArray jsonArray = new org.json.JSONArray(json);
		int maxSize = jsonArray.length();

		ObjectMapper jsonObjectMapper = new ObjectMapper();

		for(int i = 0; i < maxSize; i++) {

			Object o = jsonArray.get(i);
			String str = o.toString();
			
			try {
				hotspot = jsonObjectMapper.readValue(str, HotSpot.class );
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			hotspotList.add(hotspot);

		}

		//String jsonStr = json.substring(1, json.length() - 1); // strip off [] on the ends

		return hotspotList;


	}

	/**
	 * Get the list of hotspots, within a radius of up to 50 kilometers, from a given set of coordinates.
	 * @param lat Latitude to 2 decimal places.
	 * @param lon Longitude to 2 decimal places.
	 * @param dist Distance or the search radius from the given position, in kilometers.
	 * @param back The number of days back to fetch observations.
	 * @return list of HotSpots
	 */
	public List <HotSpot> getHotSpots(double lat, double lon, double dist, int days) {

		List <HotSpot> hotspotList = new ArrayList<HotSpot>();
		HotSpot hotspot = null;		
		String json = callEbirdHotSpot(lat, lon, dist, days);	// days == back
		JSONArray jsonArray = new org.json.JSONArray(json);
		int maxSize = jsonArray.length();

		ObjectMapper jsonObjectMapper = new ObjectMapper();

		for(int i = 0; i < maxSize; i++) {

			Object o = jsonArray.get(i);
			String str = o.toString();
			
			try {
				hotspot = jsonObjectMapper.readValue(str, HotSpot.class );
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			hotspotList.add(hotspot);

		}

		//String jsonStr = json.substring(1, json.length() - 1); // strip off [] on the ends

		return hotspotList;


	}


	/**
	 * Read URL (JSON data) and return as string.
	 * @param url
	 * @return
	 */
	public static String getJSON(URL url) {


		//	logger.info("Getting data from eBird");
		try (InputStream input = url.openStream()) {
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr);
			StringBuilder json = new StringBuilder();
			int c;
			while ((c = reader.read()) != -1) {
				json.append((char) c);
			}
			return json.toString();
		} catch (IOException e) {
			
			logger.error("stream/read problem");		
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * Get json node from url api call
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static JsonNode getJsonNode(URL url) throws IOException,InterruptedException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode jn = null;

		int i = 0;
		do {
			i++;
			if(i >= 4)
				throw new IOException(); // tried 3 times and failed
			try {
				jn = mapper.readTree(url);
			} catch (IOException e){
				try {
					// to sleep 4 seconds
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					// recommended because catching InterruptedException clears interrupt flag
					Thread.currentThread().interrupt();
					// you probably want to quit if the thread is interrupted

					throw e1;
				}
				
				continue; // go again
			}
			break;
		} while (i <= 3);

		return jn;
	}



	/**
	 * Get bird name data used for searching common name etc.
	 * @return Map of species code and common name, faster search.
	 */
	public Map<String, String> createMap() {

		List<Name> names = getBirdNamesData();

		//	Map<String,String> map = new HashMap<>();
		int maxSize = names.size();


		for(int i = 0; i < maxSize; i++){

			Name n = names.get(i);
			String code =	n.getSpeciesCode();
			String comName = n.getComName();			

			//	data = data + "'" + code +"'='" + comName + "'" + "\n";

			//logger.info("code=" + code + "comName=" +comName);
			this.birdNameMap.put(code, comName);	


			this.reverseBirdNameMap.put(comName, code);
		}

		//		try {
		//			FileWriter myWriter = new FileWriter("/home/thorn/birdData.txt");
		//			myWriter.write(data);
		//			myWriter.close();
		//			System.out.println("Successfully wrote to the file.");
		//		} catch (IOException e) {
		//			System.out.println("An error occurred.");
		//			e.printStackTrace();
		//		}


		return this.birdNameMap;		

	}

	/**
	 * Get bird name data used for searching common name etc.
	 * @return Map of species code and common name, faster search.
	 */
	public Map<String, String> createReverseMap() {

		List<Name> names = getBirdNamesData();

		//	Map<String,String> map = new HashMap<>();
		int maxSize = names.size();

		for(int i = 0; i < maxSize; i++){

			Name n = names.get(i);
			String code =	n.getSpeciesCode();
			String comName = n.getComName();			

			//	data = data + "'" + code +"'='" + comName + "'" + "\n";

			//logger.info("code=" + code + "comName=" +comName);

			this.reverseBirdNameMap.put(comName, code);
		}

		//	TODO	try {
		//			FileWriter myWriter = new FileWriter("/home/thorn/birdData.txt");
		//			myWriter.write(data);
		//			myWriter.close();
		//			System.out.println("Successfully wrote to the file.");
		//		} catch (IOException e) {
		//			System.out.println("An error occurred.");
		//			e.printStackTrace();
		//		}


		return this.reverseBirdNameMap;		

	}


	/**
	 * Get common name from species code.
	 * @param speciesCode
	 * @return common name. 
	 */
	public String getCommonName(String speciesCode) {

		if(birdNameMap == null || birdNameMap.isEmpty()) {
			this.birdNameMap =  createMap(); 
		}

		String commonName = (String) this.birdNameMap.get(speciesCode);
		return commonName;
	}

	/**
	 * Get species code from common name.
	 * @param comName
	 * @return code. 
	 */
	public String getCodeName(String comName) {

		if(this.reverseBirdNameMap == null || this.reverseBirdNameMap.isEmpty()) {
			this.reverseBirdNameMap =  createReverseMap(); 
		}

		String code = (String) this.reverseBirdNameMap.get(comName);
		return code;
	}



	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private InputStream getFileAsIOStream(final String fileName) 
	{
		InputStream ioStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream(fileName);

		if (ioStream == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}
		return ioStream;
	}


	/**
	 * read in json file to Name lists used for creating map for searching.
	 */

	public List<Name> getBirdNamesData(){
		//String sUrl = "https://ebird.org/ws2.0/ref/taxonomy/ebird?cat=species,hybrid&fmt=json&locale=en_US";
		//	ResponseEntity<String>  resp = callExternalApi(sUrl);		
		//	String jString = resp.getBody();

		ObjectMapper jsonObjectMapper = new ObjectMapper();

		BirdService instance  = new BirdService();
		String jsonStr = "";

		InputStream is = instance.getFileAsIOStream("data/ebird.json");

		try (InputStreamReader isr = new InputStreamReader(is); 
				BufferedReader br = new BufferedReader(isr);) 
		{
			String line;
			while ((line = br.readLine()) != null) {

				jsonStr = jsonStr + line;
			}
			is.close();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		JSONArray jsonArray = new org.json.JSONArray(jsonStr);

		int maxSize = jsonArray.length();

		List<Name> nameList = new ArrayList<Name>();
		birdNamesStr = new ArrayList<String>();


		Name name = new Name();

		for(int i = 0; i < maxSize; i++){

			Object o = jsonArray.get(i);
			String str = o.toString();
			
			try {
				name = jsonObjectMapper.readValue(str, Name.class );
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}


			birdNamesStr.add(name.getComName());

			nameList.add(name);

		}

		return nameList;
	}


	/**
	 * read in json file to Name lists used for creating map for searching.
	 */

	public List<String> getBirdCommNames(){

		List<String> nameStrList = new ArrayList<String>(); 
		List<Name> namesList = getBirdNamesData();


		for (Name  element : namesList) {

			nameStrList.add(element.getComName());

		}
		return nameStrList;
	}



	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List <States> readStateData() throws IOException, FileNotFoundException {

		List <States> states =  new ArrayList<>();

		BirdService instance  = new BirdService();
		InputStream is = instance.getFileAsIOStream("data/state.txt");

		try (InputStreamReader isr = new InputStreamReader(is); 
				BufferedReader br = new BufferedReader(isr);) 
		{
			String line;
			while ((line = br.readLine()) != null) {

				String COMMA_DELIMITER = ",";
				String[] values = line.split(COMMA_DELIMITER );

				States state = new States();
				//US-AK,	63.588753,	-154.493062, Alaska	
				state.setCode(values[0]);
				state.setLat(Double.parseDouble(values[1]));
				state.setLon(Double.parseDouble(values[2]));
				state.setName(values[3]);
				states.add(state);		
			}
			is.close();
		} catch (IOException e1) {

			e1.printStackTrace();
		}


		this.states= states;

		return states;		

	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public List <LocationProp> readLocationData() throws IOException, FileNotFoundException {

		List <LocationProp> locationProps =  new ArrayList<>();

		BirdService instance  = new BirdService();
		InputStream is = instance.getFileAsIOStream("data/locationprop.txt");

		try (InputStreamReader isr = new InputStreamReader(is); 
				BufferedReader br = new BufferedReader(isr);) 
		{
			String line;
			while ((line = br.readLine()) != null) {

				String COMMA_DELIMITER = ",";
				String[] values = line.split(COMMA_DELIMITER );

				LocationProp locationProp = new LocationProp();
				//Tuscon AZ, 32.2769, -110.7472,  50, 7


				locationProp.setLocName(values[0]);				
				locationProp.setLatitude(Double.parseDouble(values[1]));
				locationProp.setLongitude(Double.parseDouble(values[2]));
				locationProp.setDistance(values[3]);
				locationProp.setBack(values[4]);

				locationProps.add(locationProp);		
			}
			is.close();
		} catch (IOException e1) {

			e1.printStackTrace();
		}


		this.locationProps= locationProps;

		return locationProps;		

	}

} // end class




