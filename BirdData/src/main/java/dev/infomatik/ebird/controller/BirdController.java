package dev.infomatik.ebird.controller;
/**
 * Controller for handling API web requests for my bird web site. Passes requests to eBird API via service class
 * https://documenter.getpostman.com/view/664302/S1ENwy59#intro
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import dev.infomatik.ebird.entity.CheckListIndv;
import dev.infomatik.ebird.entity.ChecklistObservation;
import dev.infomatik.ebird.entity.Coords;
import dev.infomatik.ebird.entity.HotSpot;
import dev.infomatik.ebird.entity.LocationProp;
import dev.infomatik.ebird.entity.Recent;
import dev.infomatik.ebird.entity.Region;
import dev.infomatik.ebird.entity.RegionSpecies;
import dev.infomatik.ebird.entity.Species;
import dev.infomatik.ebird.entity.States;
import dev.infomatik.ebird.entity.tax.Name;
import dev.infomatik.ebird.service.BirdService;
import dev.infomatik.ebird.service.KmlParser;
import jakarta.servlet.http.HttpServletRequest;
import dev.infomatik.ebird.entity.MapPoints;
import dev.infomatik.ebird.entity.Nearby;
import dev.infomatik.ebird.entity.Notable;
import dev.infomatik.ebird.entity.NotableList;
import dev.infomatik.ebird.entity.Observation;

@Controller
@JsonIgnoreProperties
@RequestMapping("/")
public class BirdController {

	@Autowired
	private HttpServletRequest request;

	/**
	 * Logging
	 */
	protected static final Logger logger = LogManager.getLogger(); // TODO

	/* */
	//private List <String> birdNames;
	/* */
	private String birdId = "";	

	/**
	 * @return the birdId
	 */
	public String getBirdId() {
		return birdId;
	}

	/**
	 * @param birdId the birdId to set
	 */
	public void setBirdId(String birdId) {
		this.birdId = birdId;
	}

	/** */
	@Autowired
	BirdService service;

	// get all birds, taxonomy "https://api.ebird.org/v2/reflogger/taxonomy/ebird"
	// c("country", "subnational1", "subnational2"))
	//	  url <- glue("https://api.ebird.org/v2/ref/region/list/{region_type}/{parent_region}")
	// 	https://api.ebird.org/v2/ref/region/info/{{regionCode}}
	//	https://api.ebird.org/v2/data/obs/{{regionCode}}/recent/{{speciesCode}}


	/**
	 * 
	 * @return string to tell bots not to search here.
	 */
	@RequestMapping("/robots.txt")
	@ResponseBody
	public String robots() {
		return "User-agent: *\n" +
				"Disallow: /";
	}

	/**
	 * TODO getting 404 error, could be api does not supported it now
	 * Get a list of countries or regions that share a border with this one.
	 * https://api.ebird.org/v2/ref/adjacent/{{regionCode}}
	 * @param model
	 * @param lat
	 * @param lon
	 * @param regionString
	 * @return
	 */
	@GetMapping("/adjacentregions")
	public String adjacentregions(Model model, 
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lon", required = true) double lon,		
			@RequestParam(value = "region", required = false) String regionString) {

		
		System.out.println(" adjacentregions " + regionString);
		
		// lookup adjacent regions
		List <Region> regionList =  service.getAdjacentRegions(lat, lon, regionString);		
		
		model.addAttribute("regionlist", regionList);
		
		return "regionlist";		
		
		// http://localhost:8088/adjacentregions?lat=39.4798135&lon=-77.6325412&=region=US-MD
		
	}


	/**
	 * TODO
	 * Get information on the name and geographical area covered by a region.
	 * https://api.ebird.org/v2/ref/region/info/{{regionCode}}
	 * 
	 * 
	 * @param model
	 * @param region The country, subnational1, subnational2 or location code.
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/regioninfo")
	public String regionInfo(Model model, 
			@RequestParam(value = "region", required = false) String region) throws IOException  {

		String s = service.getInfoRegion(region);

		//TODO

		return s;	
	}

	/**
	 * TODO
	 * Get a summary of the number of checklist submitted, species seen and contributors on a given date for a country or region.
	 * https://api.ebird.org/v2/product/stats/{{regionCode}}/{{y}}/{{m}}/{{d}}
	 * 
	 * @param model
	 * @param lat
	 * @param lon
	 * @return  {"numChecklists":11,"numContributors":7,"numSpecies":29}
	 * @throws IOException
	 * 
	 */
	@GetMapping("/regionstats")
	public String regionstats(Model model, 
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon,
			@RequestParam(value = "year", required = false) int year,
			@RequestParam(value = "month", required = false) int month,
			@RequestParam(value = "day", required = false) int day) throws IOException  {

		String regionCode = "";

		try {
			regionCode= service.getRegionCode(lat, lon);

			String s = service.getStatsRegion(regionCode, year, month, day);


		} catch (Exception e) {
			model.addAttribute("error", "Coordinates outside of US");
			logger.error("FCC database problem or coordinates outside of US");
			e.printStackTrace();
			return "err";

		}

		return "err";		
	}



	/**
	 * TODO
	 * @param model
	 * @param lat
	 * @param lon
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/region")
	public String region(Model model, 
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon) throws IOException  {


		logger.info("Region IP: {}", getRemoteAddress());
		String regioncode = "";

		try {
			regioncode= service.getRegionCode(lat, lon);

		} catch (Exception e) {

			model.addAttribute("error", "Coordinates outside of US");
			logger.error("FCC database problem or coordinates outside of US");
			e.printStackTrace();
			return "err";
		}

		return regioncode;		

	}	

	/**
	 * Nearest observations of a species
	 * Find the nearest locations where a species has been seen recently.
	 * https://api.ebird.org/v2/data/nearest/geo/recent/{{speciesCode}}?lat={{lat}}&lng={{lng}}
	 * 
	 * @param model 
	 * @param lat
	 * @param lon
	 * @param species
	 * @return List
	 * @throws IOException
	 */
	@GetMapping("/nearbyspecies")
	public String nearbyspecies(Model model,
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon,		
			@RequestParam(value = "speciesCode", required = false) String speciesCode,
			@RequestParam(value = "dist", required = false) double dist,
			@RequestParam(value = "back", required = false) int back)  throws IOException {

		List <Nearby> nearby = service.getNearestObsSpecies(speciesCode, lat, lon, dist, back);

		String commonName = service.getCommonName(speciesCode);

		if(commonName == null || commonName.isEmpty()) {
			commonName = speciesCode;		
		}

		int maxSize = nearby.size();

		model.addAttribute("nearby", nearby);

		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1200px; height: 800px;");
		model.addAttribute("zoom", 9);

		model.addAttribute("speciesCode", speciesCode);	
		model.addAttribute("commonname", commonName);	
		model.addAttribute("size", maxSize);	
		model.addAttribute("distance", dist);	
		model.addAttribute("days", back);

		model.addAttribute("dist", 50);		// for links
		model.addAttribute("back", 7);	   // for links

		return "nearby";	
	}


	/**
	 * Get the recent observations, up to 30 days ago, of a particular species in a country, 
	 * region or location. Results include only the most recent observation from each location in the region specified.
	 * 
	 * https://api.ebird.org/v2/data/obs/{{regionCode}}/recent/{{speciesCode}}
	 * @param model
	 * @param region
	 * @param species
	 * @param lat
	 * @param lon
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/recentregionspecies")
	public String recentRegionSpecies(Model model, 
			@RequestParam(value = "region", required = true) String region,
			@RequestParam(value = "species", required = true) String species,
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon) throws IOException  {

		logger.info("Recent region species, region: {} species: {}" , region , species);
		int days = 7; // TODO pass via call

		List <Region> adjacentRegions =	 service.getAdjacentRegions(lat, lon, region);

		//		Region r = new Region ();		
		//		r.setCode(region);
		//		r.setName(region); // TODO need name		
		//		adjacentRegions.add(r);
		//List<String> newList = 
		List <RegionSpecies> rs = service.getRecentSpeciesRegion(region, species, days);
		Species curSpecies = new Species();
		String comName = service.getCommonName(species);
		curSpecies.setSpeciesCode(species);
		curSpecies.setRegion(region);
		curSpecies.setComName(comName);

		// combine adjacent regions
		for (Region  element : adjacentRegions) {
			List <RegionSpecies> rTemp = service.getRecentSpeciesRegion(element.getCode(), species, days);
			rs = Stream.concat(rs.stream(), rTemp.stream()).toList();
		}

		model.addAttribute("rs", rs);
		model.addAttribute("species",curSpecies);
		model.addAttribute("comName", comName);
		model.addAttribute("speciesname", comName);	
		model.addAttribute("regioncode", "Species List For Surrounding: "+ region);	
		model.addAttribute("title", "Species List For Surrounding: " + region);
		model.addAttribute("lat", lat); // center of map TODO?
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 8);
		model.addAttribute("dist", 50);		// for links
		model.addAttribute("back", days);	   // for links

		return "regionspecies";	
	}


	/**
	 * Get the list of recent, notable observations (up to 30 days ago)
	 * of birds seen in a country, region or location.
	 * Notable observations can be for locally or nationally rare species or are otherwise unusual,
	 * e.g. over-wintering birds in a species which is normally only a summer visitor.
	 * @param model
	 * @param lat
	 * @param lon
	 * @param dist
	 * @param back
	 * @return model
	 * @throws IOException
	 */
	@GetMapping("/notable")
	public String notable(Model model, 
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lon", required = true) double lon,
			@RequestParam(value = "dist", required = false) double dist,
			@RequestParam(value = "back", required = false) int back) throws IOException  {

		logger.info("notable IP:{}",  getRemoteAddress());

		HashSet<Notable> noDupSet = new HashSet<Notable>();	
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		List <Notable> notableList = new ArrayList<Notable>();
		NotableList notable = null;
		String location = null;

		try {
			location = service.getRegionCode(lat, lon);
		} catch (Exception e) {

			e.printStackTrace();
		}

		//	NotableList notable = null;
		try {

			// notable = service.getNotableRegion(lat, lon, back);
			notable = service.getNotable(lat, lon, dist, back);	 // just for radius of dist


		} catch (Exception e) {
			model.addAttribute("error", "FCC database problem or coordinates outside of US");
			logger.error("FCC database problem or coordinates outside of US");
			e.printStackTrace();
			return "error";
		}

		notableList = notable.getNotable();

		pointList = notable.getPointList();

		for (Notable  element : notableList) {

			String comName= element.getComName();
			String subId = element.getSubId();

			boolean flag = true;

			// go through and see if it is already in the list
			for (Notable  element2 : noDupSet) {

				if (element2.getComName().contentEquals(comName) && element2.getSubId().contains(subId) ) {
					flag = false; //found
				}
			}

			if(flag) {
				//save it
				noDupSet.add(element);
			}
			//			else
			//				System.out.println("duplicate: " + element.getComName() + " " +element.getComName());

		}

		// TODO use hash to get rid of duplicates, showing a lot of the same list and birds
		int maxSize = noDupSet.size();

		String centertitle = "Notable Bird Observations " + dist + " km distance for "+ back +" days";

		String title = "Notable List for " + location; 	
		model.addAttribute("title", title);
		model.addAttribute("center", centertitle);
		model.addAttribute("location", location); 
		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 9);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("notable",noDupSet); // notableList);		
		model.addAttribute("size", maxSize);	
		model.addAttribute("dist", 40);		
		model.addAttribute("back", 7);	

		return "notable"; 
	}



	/**
	 * Get the list of recent observations (up to 30 days ago) of birds seen in a 
	 * country, state, county, or location. Results include only the most recent 
	 * observation for each species in the region specified.
	 * 
	 * @param model
	 * @param lat
	 * @param lon
	 * @param dist
	 * @param back
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/recent")
	public String recent(Model model, 
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon,
			@RequestParam(value = "dist", required = false) double dist,
			@RequestParam(value = "back", required = false) int back) throws IOException  {


		logger.info("Recent, IP:{}",  getRemoteAddress());

		List <Recent> recentListWithCoords = new ArrayList<Recent>(); 
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		List<Recent> recentList = service.getRecent(lat, lon, dist, back); 

		int maxSize = recentList.size();

		HashSet<String> noDupSet = new HashSet<String>();	
		HashSet<String> noDupSpecices = new HashSet<String>();	

		for(int i = 0; i < maxSize; i++){


			Recent recent = recentList.get(i);

			MapPoints point = new MapPoints();
			point.setLat(recent.getLat());
			point.setLon(recent.getLng());
			// track unique
			noDupSet.add(recent.getSubId());
			noDupSpecices.add(recent.getSpeciesCode());

			point.setSubId(recent.getSubId());

			point.setTextDescription(recent.getLocName());

			pointList.add(point);

			recentListWithCoords.add(recent);

		}


		//	String s1 = service.getRegionCode(lat, lon);
		//	System.out.println("results from fcc  = "+ s1);

		//        // testing stats
		//		try {
		//			String s =	service.getStatsRegion("US-MD", 2024, 8, 1);
		//			//System.out.println("json  = "+ s);
		//			
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}	

		//	service.getNearestObsSpecies( "blujay",  lat, lon, 10, 12);
		//System.out.println("pointList = "+ pointList);


		model.addAttribute("checklistnumber", noDupSet.size());
		model.addAttribute("noDupSpecices", noDupSpecices.size());
		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1200px; height: 800px;");
		model.addAttribute("zoom", 9);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("recent", recentListWithCoords);		
		model.addAttribute("size", maxSize);	
		model.addAttribute("distance", dist);	
		model.addAttribute("days", back);

		model.addAttribute("dist", 20);		// for links
		model.addAttribute("back", 7);	   // for links

		return "recent"; 
	}


	/**
	 * Get the list of hotspots, within a region.
	 * @param model
	 * @param dist
	 * @param back
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/hotspotregion")
	public String hotspot(Model model, 
			@RequestParam(value = "region", required = true) String region,
			@RequestParam(value = "dist", required = false) double dist,
			@RequestParam(value = "back", required = false) int back)
					throws IOException  {
		logger.info("Hotspot Region: {} IP:{} " , region,  getRemoteAddress());

		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		List <HotSpot> hotspotList = service.getHotSpotsRegion(region, dist, back);		

		Coords coords = service.getCoordsState(region);
		List <Region> regionAdjList =  service.getAdjacentRegions(coords.getLat(), coords.getLon(), region);	

		int maxSize = hotspotList.size();

		for(int i = 0; i < maxSize; i++){

			HotSpot hotSpot = hotspotList.get(i);
			MapPoints point = new MapPoints();
			point.setLat(hotSpot.getLat());
			point.setLon(hotSpot.getLng());

			//point.setSubId(hotSpot.getSubId());

			point.setTextDescription(hotSpot.getLocName());

			pointList.add(point);

		}

		// todo get lat and lon based on region

		// Probably better way to do this vs list
		List <States> stateList = service.readStateData(); 

		double lat = coords.getLat();
		double lon  = coords.getLon();

		String title = "Local eBird Hotspot List for " + region; // 	<center>Local eBird Hotspot List (<span th:text="${title}" /> km distance for <span th:text="${days}" /> days)

		model.addAttribute("title", title);
		model.addAttribute("size", maxSize);
		model.addAttribute("lat", lat); // center of map 
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 6);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("hotspots", hotspotList);
		model.addAttribute("dist", 10);	// for links	
		model.addAttribute("back", 7);	//for links		

		model.addAttribute("distance", dist);	
		model.addAttribute("days", back);

		return "hotspot"; // "hotspot";

	}


	/**
	 * 
	 * Maps for Birder’s Guide to Maryland & DC, created by the Maryland Ornithological Society 
	 *  
	 * @return custom 
	 */
	@GetMapping("/custom")
	public String custom(Model model)	throws IOException  {
		logger.info("Custom Region: IP:{} " , getRemoteAddress());

		String title = " Birder’s Guide to Maryland & DC Hotspot Locations"; 	
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		KmlParser kml = new KmlParser();
		pointList = kml.parseKML();		

		//int maxSize = coordinates.size();

		model.addAttribute("title", title);

		model.addAttribute("lat", 39.5); // center of map 
		model.addAttribute("lon", -77);		
		model.addAttribute("mapsize", "width: 1200px; height: 900px;");
		model.addAttribute("zoom", 8);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("dist", 10);	// for links	
		model.addAttribute("back", 7);	//for links		
		return "custom"; 
	}


	/**
	 * Get the list of hotspots, within a radius of up to X kilometers,
	 * from a given set of coordinates.
	 * @param model
	 * @param lat
	 * @param lon
	 * @param dist
	 * @param back
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/hotspot")
	public String hotspot(Model model, 
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon,
			@RequestParam(value = "dist", required = false) double dist,
			@RequestParam(value = "back", required = false) int back)
					throws IOException  {

		String title = "Local eBird Hotspot List " + dist + "km for " + back + " days"; 	

		logger.info("hotspot: {} IP:{}", title,  getRemoteAddress());

		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		List <HotSpot> hotspotList = service.getHotSpots(lat, lon, dist, back);


		int maxSize = hotspotList.size();

		for(int i = 0; i < maxSize; i++){

			HotSpot hotSpot = hotspotList.get(i);
			MapPoints point = new MapPoints();
			point.setLat(hotSpot.getLat());
			point.setLon(hotSpot.getLng());

			//point.setSubId(hotSpot.getSubId());

			point.setTextDescription(hotSpot.getLocName());

			pointList.add(point);

		}		

		model.addAttribute("title", title);
		model.addAttribute("size", maxSize);
		model.addAttribute("lat", lat); // center of map 
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 8);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("hotspots", hotspotList);
		model.addAttribute("dist", 10);	// for links	
		model.addAttribute("back", 7);	//for links		

		model.addAttribute("distance", dist);	
		model.addAttribute("days", back);

		return "hotspot";

	}


	/**
	 * Get checklist based on checklist ID. No coords for map.
	 * https://api.ebird.org/v2/product/checklist/view/{{subId}}
	 * @param model
	 * @param subId
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/checklist")
	public String checklist(Model model,
			@RequestParam(value = "subid", required = false) String subId) throws IOException  {

		logger.info("checklist: {} IP:{}", subId,  getRemoteAddress());

		ObjectMapper jsonObjectMapper = new ObjectMapper();		
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map

		String jString = service.callEbirdChecklist(subId);	

		//JSONObject jsonObject = new JSONObject(jString);

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List<ChecklistObservation> checklistList = new ArrayList<ChecklistObservation>();
		ChecklistObservation checklist = new ChecklistObservation();

		for(int i = 0; i < maxSize; i++) {

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				checklist = jsonObjectMapper.readValue(str, ChecklistObservation.class );
			} catch (JsonProcessingException e) {
				logger.error("");
				model.addAttribute("error", "Coordinates outside of US");
				e.printStackTrace();
				return "error";

			}

			double lat = checklist.getLoc().getLat();
			double lon = checklist.getLoc().getLng();
			checklist.setLat(lat);
			checklist.setLon(lon);

			MapPoints point = new MapPoints();
			point.setLat(checklist.getLoc().getLat());
			point.setLon(checklist.getLoc().getLng());				
			point.setSubId(checklist.getSubId());
			point.setTextDescription(checklist.getLoc().getLocName());

			pointList.add(point);

			checklistList.add(checklist);
		}		

		model.addAttribute("lat", "39"); // center of map TODO
		model.addAttribute("lon", "-90");		
		model.addAttribute("size", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 7);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("checklist", checklistList);		
		model.addAttribute("number", maxSize);	
		model.addAttribute("dist", 20);		
		model.addAttribute("back", 7);		
		return "checklist"; 

	}


	/**
	 * Get checklists based on checklist ID, coords are for map.
	 * @param model
	 * @param lat
	 * @param lon
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/checklist2")
	public String checklistcoords(Model model,
			@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lon", required = true) double lon)	 throws IOException  {


		logger.info("checklist2 IP:{}",  getRemoteAddress());
		ObjectMapper jsonObjectMapper = new ObjectMapper();		
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map

		// get detailed subId (county)
		String regionCode = "";

		try {
			regionCode = service.getRegionCode(lat, lon);		
		}
		catch (Exception e) {

			// Probably not US, or FCC DB issue
			model.addAttribute("error", "FCC database problem or coordinates outside of US");
			logger.error("FCC database problem or coordinates outside of US");
			e.printStackTrace();
			return "error";
		}

		String placeName = service.getPlaceName(regionCode);
		String jString = service.callEbirdChecklist(regionCode);	

		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List<ChecklistObservation> checklistList = new ArrayList<ChecklistObservation>();
		ChecklistObservation checklist = new ChecklistObservation();

		for(int i = 0; i < maxSize; i++) {

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				checklist = jsonObjectMapper.readValue(str, ChecklistObservation.class );
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			checklist.setLat(checklist.getLoc().getLat());
			checklist.setLon(checklist.getLoc().getLng());

			MapPoints point = new MapPoints();
			point.setLat(checklist.getLoc().getLat());
			point.setLon(checklist.getLoc().getLng());
			point.setSubId(checklist.getSubId());
			point.setTextDescription(checklist.getLoc().getLocName());

			pointList.add(point);

			checklistList.add(checklist);
		}		

		model.addAttribute("placeName", placeName);
		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("size", "width: 1000px; height: 800px;");		
		model.addAttribute("zoom", 8);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("checklist", checklistList);		
		model.addAttribute("number", maxSize);	
		model.addAttribute("dist", 20);		
		model.addAttribute("back", 7);		

		return "checklist"; 

	}


	/**
	 * Get checklists based on checklist ID. coords for map.
	 * @param model
	 * @param lat
	 * @param lon
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/checklistRegion")
	public String checklistRegion(Model model,
			@RequestParam(value = "region", required = true) String regioncode) throws IOException {

		logger.info("checklistRegion {} IP:{}", regioncode,  getRemoteAddress());		

		ObjectMapper jsonObjectMapper = new ObjectMapper();		
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		double lat = 39;
		double lon = -90;

		// get detailed subId (county)

		String placeName = service.getPlaceName(regioncode);
		String jString = service.callEbirdChecklist(regioncode);			


		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List<ChecklistObservation> checklistList = new ArrayList<ChecklistObservation>();
		ChecklistObservation checklist = new ChecklistObservation();

		for(int i = 0; i < maxSize; i++) {

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				checklist = jsonObjectMapper.readValue(str, ChecklistObservation.class );
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}


			// get and set location
			lat = checklist.getLoc().getLat();
			lon = checklist.getLoc().getLng();			
			checklist.setLat(lat);
			checklist.setLon(lon);

			MapPoints point = new MapPoints();
			point.setLat(checklist.getLoc().getLat());
			point.setLon(checklist.getLoc().getLng());
			point.setSubId(checklist.getSubId());
			point.setTextDescription(checklist.getLoc().getLocName());

			pointList.add(point);

			checklistList.add(checklist);
		}		

		model.addAttribute("placeName", placeName);
		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("size", "width: 1000px; height: 800px;");		
		model.addAttribute("zoom", 9);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("checklist", checklistList);		
		model.addAttribute("number", maxSize);	
		model.addAttribute("dist", 20);		
		model.addAttribute("back", 7);		

		return "checklist"; 

	}


	/**
	 * TODO not working correctly
	 * Get checklists based on checklist ID. coords for map.
	 * https://api.ebird.org/v2/product/lists/{{regionCode}}/{{y}}/{{m}}/{{d}}
	 * @param model
	 * @param lat
	 * @param lon
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/checklistRegionDate")
	public String checklistRegionDate(Model model, 
			@RequestParam(value = "region", required = false) String regioncode,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "day", required = false) String day,
			@RequestParam(value = "max", required = false) String max) throws IOException {
		//System.out.println("checklistRegionDate");
		logger.info("checklistRegionDate IP:{}",  getRemoteAddress());

		ObjectMapper jsonObjectMapper = new ObjectMapper();		
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map
		double lat = 39;
		double lon = -90;

		// get detailed subId (county)

		String placeName = service.getPlaceName(regioncode);
		String jString = service.callEbirdChecklistDate(regioncode, year, month, day, max);	


		JSONArray jsonArray = new org.json.JSONArray(jString);
		int maxSize = jsonArray.length();

		List<ChecklistObservation> checklistList = new ArrayList<ChecklistObservation>();
		ChecklistObservation checklist = new ChecklistObservation();

		for(int i = 0; i < maxSize; i++) {

			Object o = jsonArray.get(i);
			String str = o.toString();

			try {
				checklist = jsonObjectMapper.readValue(str, ChecklistObservation.class );
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}
			//	lat = checklist.getLoc().getLat();
			//	lon = checklist.getLoc().getLng();			

			MapPoints point = new MapPoints();
			point.setLat(checklist.getLoc().getLat());
			point.setLon(checklist.getLoc().getLng());
			point.setSubId(checklist.getSubId());
			point.setTextDescription(checklist.getLoc().getLocName());

			pointList.add(point);

			checklistList.add(checklist);
		}		

		//		try {
		//
		//			System.out.println("regioncode = "+ regioncode);
		//
		//
		//			//		   # use the last three digits from the in-state fips code as the "subnational 2" identifier:
		//			//			    fips = response['county_fips']
		//
		//
		//
		//		} catch (Exception e) {
		//
		//			e.printStackTrace();
		//		}
		model.addAttribute("placeName", placeName);
		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("size", "width: 1000px; height: 800px;");		
		model.addAttribute("zoom", 9);
		model.addAttribute("pointList", pointList);	
		model.addAttribute("checklist", checklistList);		
		model.addAttribute("number", maxSize);	
		model.addAttribute("dist", 20);		
		model.addAttribute("back", 7);		

		return "checklist"; 


	}


	/**
	 * Species List for a Region
	 * @param model
	 * @param subId
	 * @return
	 * @throws IOException
	 */	
	@GetMapping("/speciesregion")
	public String speciesRegion(Model model, 
			@RequestParam(value = "region", required = false) String regioncode) throws IOException {



		logger.info("speciesregion: {} IP:{}", regioncode,  getRemoteAddress());

		List <String> codeNameList = new ArrayList<String>(); 
		String placeName = service.getPlaceName(regioncode);

		List <String> commonNameList = service.getSpeciesListRegion(regioncode);
		Map<String, String> map = service.getSpeciesListRegionMap(regioncode);

		model.addAttribute("map", map);

		//		model.addAttribute("commonlist", commonNameList);
		//		model.addAttribute("codelist", codeNameList);
		//		
		model.addAttribute("placeName", placeName);
		model.addAttribute("number", commonNameList.size());
		model.addAttribute("regioncode", "Region code: "+ regioncode);	
		model.addAttribute("title", "Species List For " + regioncode);

		return "specieslist";		
	}



	/**
	 * Species List for a Region
	 * @param model
	 * @param subId
	 * @return
	 * @throws IOException
	 */	
	@GetMapping("/species")
	public String species(Model model, 
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon) throws IOException {

		String regioncode= "";
		try {
			regioncode= service.getRegionCode(lat, lon);
		} catch (Exception e) {
			model.addAttribute("error", "FCC database problem or coordinates  outside of US");
			logger.error("FCC database problem or coordinates outside of US");
			e.printStackTrace();
			return "err";
		}

		Map<String, String> map = service.getSpeciesListRegionMap(regioncode);

		String placeName = service.getPlaceName(regioncode);

		logger.info("species: placeName:{}" + placeName);

		List <String> commonNameList = service.getSpeciesListRegion(regioncode);

		model.addAttribute("map", map);
		model.addAttribute("birdlist", commonNameList);
		model.addAttribute("placeName", placeName);
		model.addAttribute("number", commonNameList.size());
		model.addAttribute("regioncode", "Region code: "+ regioncode);	
		model.addAttribute("title", "Species List For " + regioncode);

		return "specieslist";		
	}


	/**
	 * viewchecklist, display specific checklist. Used in lists of checklists.
	 * @param model
	 * @param subId ID for checklist from ebird
	 * @param lat for map marker
	 * @param lon for map marker
	 * @return html page, viewchecklist.html for displaying checklist data.
	 * @throws IOException
	 * https://api.ebird.org/v2/product/checklist/view/{{subId}}
	 * https://ebird.org/species/yelgul1
	 */
	@GetMapping("/viewchecklist")
	public String viewchecklist(Model model, 
			@RequestParam(value = "lat", required = false) double lat,
			@RequestParam(value = "lon", required = false) double lon,
			@RequestParam(value = "subid", required = true) String subId) 
					throws IOException  {

		logger.info("viewchecklist IP:{}",  getRemoteAddress());

		List<Name> nameList =  service.getBirdNamesData();

		ObjectMapper jsonObjectMapper = new ObjectMapper();			

		String jString = service.callEbirdChecklistView(subId);	

		JsonNode rootNode = jsonObjectMapper.readTree(jString);

		//	jsonObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		CheckListIndv list = jsonObjectMapper.treeToValue(rootNode, CheckListIndv.class);

		List <Observation> obs  = list.getObs(); // to get bird obs data

		int maxSize = obs.size();
		Map map = service.createMap();

		// get and set common names for displaying
		for(int i = 0; i < maxSize; i++) {

			String SpeciesCodeName = obs.get(i).getSpeciesCode();
			String commonName = service.getCommonName(SpeciesCodeName);			

			//System.out.println("commonName = '" + commonName + "'");			

			if(commonName == null || commonName.isEmpty()) {

				obs.get(i).setCommonName(SpeciesCodeName);					

			}
			else {

				obs.get(i).setCommonName(commonName);		
			}						
		}


		model.addAttribute("observation", list);
		model.addAttribute("subId", subId);
		//model.addAttribute("obs", obs);
		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);			
		model.addAttribute("size", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 12);
		model.addAttribute("dist", 20);		
		model.addAttribute("back", 7);	

		return "viewchecklist"; 

	}


	/**
	 * Display species for region. TODO
	 * @param name
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@PostMapping("/showhotspot")
	public String showHotspot(HotSpot hotspot, Model model,
			HttpServletRequest request) throws FileNotFoundException, IOException { //ModelAndView

		logger.info("showhotspot IP:{}",  getRemoteAddress());
		//ModelAndView modelAndView = new ModelAndView();

		// get region  etc from user form.
		String region = hotspot.getRegion();			
		int days = hotspot.getDays();
		int dist = 10;

		// Probably better way to do this vs list
		List <States> stateList = service.readStateData(); 

		Coords coords = service.getCoordsState(region);
		double lat = coords.getLat();
		double lon  = coords.getLon();

		List <HotSpot> hotspotList = service.getHotSpotsRegion(region, dist, days);

		int size = hotspotList.size();

		String title = "eBird Hotspot List for " + region; 
		model.addAttribute("title", title);
		/*
		modelAndView.setViewName("hotspot");
		modelAndView.addObject("hotspots", hotspotList);
		modelAndView.addObject("size", size);	
		modelAndView.addObject("regioncode", "Location: "+ region);	
		modelAndView.addObject("lat", lat); 
		modelAndView.addObject("lon", lon);		
		modelAndView.addObject("mapsize", "width: 1000px; height: 800px;");
		modelAndView.addObject("zoom", 8);
		modelAndView.addObject("dist", 50);		// for links
		modelAndView.addObject("back", days);	   // for links
		modelAndView.addObject("days", days);	   // for links
		 */

		model.addAttribute("hotspots", hotspotList);
		model.addAttribute("size", size);	
		model.addAttribute("regioncode", "Location: "+ region);	
		model.addAttribute("lat", lat); 
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 8);
		model.addAttribute("dist", 50);		// for links
		model.addAttribute("back", days);	   // for links
		model.addAttribute("days", days);	   // for links	


		return "hotspot"; // modelAndView;// hotspotList
	}


	/**
	 * Display species for region. TODO
	 * @param name
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@PostMapping("/showspecies")
	public ModelAndView showSpecies(Species species, Model model, HttpServletRequest request) 
			throws FileNotFoundException, IOException {
		logger.info("showspecies IP:{}",  getRemoteAddress());
		ModelAndView modelAndView = new ModelAndView();

		String code= "";
		String region = "";

		//get code from user form
		code = service.getCodeName(species.getComName());       

		// get region for user form.
		region = species.getRegion();			
		int days = species.getDays();

		// todo get lat and lon based on region
		List <States> states = null;

		try {
			states = service.readStateData();
		} catch (IOException e) {

			e.printStackTrace();
		}


		// Probably better way to do this vs list
		List <States> stateList = service.readStateData(); 

		double lat = 39;
		double lon  =-77;

		for (States  element : stateList) {

			String r = element.getCode();

			if(r.contains(region)) {

				lat = element.getLat();
				lon  = element.getLon(); 
			}

		}

		List <RegionSpecies> rs = service.getRecentSpeciesRegion(region, code, days);		
		int size = rs.size();

		modelAndView.setViewName("regionspecies");
		modelAndView.addObject("species", species);
		modelAndView.addObject("code", code);

		modelAndView.addObject("rs", rs);	 
		modelAndView.addObject("size", size);	

		modelAndView.addObject("regioncode", "Location: "+ region);	

		modelAndView.addObject("lat", lat); // center of map TODO
		modelAndView.addObject("lon", lon);		
		modelAndView.addObject("mapsize", "width: 1000px; height: 800px;");
		modelAndView.addObject("zoom", 7);
		modelAndView.addObject("dist", 50);		// for links
		modelAndView.addObject("back", days);	   // for links
		modelAndView.addObject("days", days);	   // for links
		modelAndView.addObject("speciesname", species.getComName());	

		return modelAndView;
	}


	/**
	 * TODO needs work
	 * Display species for region. TODO
	 * @param name
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@PostMapping("/showspecies2")
	public String showSpecies2(Model model, HttpServletRequest request, 
	@RequestParam(value = "lat", required = false) double lat,
	@RequestParam(value = "lon", required = false) double lon,
	@RequestParam(value = "commonname", required = true) String commonName)		
			throws FileNotFoundException, IOException {
		
		logger.info("showspecies IP:{}",  getRemoteAddress());
		
		Species species = new Species();
		
		

		String region = "";			
		String regioncode= "";
		
		
		try {
			regioncode= service.getRegionCode(lat, lon);
		} catch (Exception e) {
			
			model.addAttribute("error", "FCC database problem or coordinates  outside of US");
			
			logger.error("FCC database problem or coordinates outside of US");
			e.printStackTrace();
			
			return "Error";
			
		}
				
		species.setSpeciesCode(service.getCodeName(commonName));
		species.setComName(commonName);
		species.setDays(7);
		species.setRegion(regioncode);
		

		Map<String, String> map = service.getSpeciesListRegionMap(regioncode);

		String placeName = service.getPlaceName(regioncode);

		logger.info("species: placeName:{}" + placeName);

		List <String> commonNameList = service.getSpeciesListRegion(regioncode);


		String code= "";
		

		//get code from user form
		code = service.getCodeName(species.getComName());       
		
		//code = subid;

		// get region for user form.
		region = species.getRegion();			
		//int days = species.getDays();
		int days = 7; // todo

		// todo get lat and lon based on region
		List <States> states = null;

		try {
			states = service.readStateData();
		} catch (IOException e) {
			model.addAttribute("error", "Problem reading state data");
			e.printStackTrace();
		}


		// Probably better way to do this vs list
		List <States> stateList = service.readStateData(); 

	//	double lat = 39;
	//	double lon  =-77;

		for (States  element : stateList) {

			String r = element.getCode();

			if(r.contains(region)) {

				lat = element.getLat();
				lon  = element.getLon(); 
			}

		}

		List <RegionSpecies> rs = service.getRecentSpeciesRegion(region, code, days);		
		int size = rs.size();

		
		model.addAttribute("species", species);
		model.addAttribute("code", code);

		model.addAttribute("rs", rs);	 
		model.addAttribute("size", size);	

		model.addAttribute("regioncode", "Location: "+ region);	

		model.addAttribute("lat", lat); // center of map TODO
		model.addAttribute("lon", lon);		
		model.addAttribute("mapsize", "width: 1000px; height: 800px;");
		model.addAttribute("zoom", 7);
		model.addAttribute("dist", 50);		// for links
		model.addAttribute("back", days);	   // for links
		model.addAttribute("days", days);	   // for links
		model.addAttribute("speciesname", species.getComName());	

		return "regionspecies";
	}

	
	/**
	 * Display rare species for region. 
	 * @param name
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/shownotable")
	public ModelAndView showNotable(Notable notable, Model model, HttpServletRequest request) throws Exception {

		logger.info("shownotable IP:{}", getRemoteAddress());

		ModelAndView modelAndView = new ModelAndView();
		HashSet<Notable> noDupSet = new HashSet<Notable>();	
		NotableList notableListClass = null;
		String code= "";
		String region = "";
		String comName = notable.getComName();

		//get code from user form
		code = service.getCodeName(comName);       

		// get region for user form.
		region = notable.getRegion();			
		int days = notable.getDays();

		if (days == 0) {
			days = 1;
		}

		int back = days;

		if (back == 0) {
			back = 1;
		}

		// get lat and lon based on state
		List <States> states = null;

		try {
			states = service.readStateData();
		} catch (IOException e) {

			e.printStackTrace();
		}

		// Probably better way to do this vs list
		List <States> stateList = service.readStateData(); 

		double lat = 39;
		double lon  =-77;

		for (States  element : stateList) {

			String r = element.getCode();

			if(r.contains(region)) {
				lat = element.getLat();
				lon  = element.getLon(); 
			}
		}

		notableListClass = service.getNotableRegion(region, back);

		List <Notable> notableList = notableListClass.getNotable();
		List <MapPoints> pointList = notableListClass.getPointList();

		for (Notable  element : notableList) {

			String comName1= element.getComName();
			String subId = element.getSubId();

			boolean flag = true;

			// go through and see if it is already in the list
			for (Notable  element2 : noDupSet) {

				if (element2.getComName().contentEquals(comName1) && element2.getSubId().contains(subId) ) {
					flag = false; //found
				}
			}

			if(flag) {
				//save it
				noDupSet.add(element);
			}
			//			else
			//				System.out.println("duplicate: " + element.getComName() + " " +element.getSubId());

		}



		//		for (Notable  element : notableList) {
		//
		//			if (!noDupSet.contains(element))
		//				noDupSet.add(element);
		//			else
		//				System.out.println("duplicate: " + element.getComName());
		//
		//		}

		int size = noDupSet.size();

		// add to page
		String centertitle = "Notable Bird Observations distance for "+ back +" days at " + region;

		String title = "Notable List for " + region; 	
		model.addAttribute("title", title);
		model.addAttribute("center", centertitle);		

		modelAndView.setViewName("notable");
		modelAndView.addObject("notable", noDupSet);		
		modelAndView.addObject("size", size);	
		modelAndView.addObject("regioncode", "Location: "+ region);	
		modelAndView.addObject("lat", lat); // center of map TODO
		modelAndView.addObject("lon", lon);		
		modelAndView.addObject("zoom", 7);
		modelAndView.addObject("dist", 50);		// for links
		modelAndView.addObject("back", days);	   // for links
		modelAndView.addObject("days", days);	   // for links
		modelAndView.addObject("mapsize", "width: 1000px; height: 800px;");
		modelAndView.addObject("pointList", pointList);	

		return modelAndView;
	}



	/**
	 * 
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */

	@GetMapping("/error/")
	public String error(Model model) throws IOException { 

		model.addAttribute("title", "ERROR PAGE");

		return "error";
	}



	/**
	 * Display a about page
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */

	@GetMapping("/about/")
	public String about(Model model) throws IOException { 
		logger.info("about IP:{}",  getRemoteAddress());
		return "menu/about";

	}


	/**
	 * Display a page with links
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */

	@GetMapping("/links/")
	public String links(Model model) throws IOException { 
		logger.info("links IP:{}",  getRemoteAddress());
		return "menu/links";

	}

	/**
	 * Display a page with links
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */

	@GetMapping("/recents/")
	public String recents(Model model) throws IOException { 
		logger.info("recents  IP:{}",  getRemoteAddress());

		List <LocationProp> prop = service.readLocationData();
		model.addAttribute("title", "Custom Recent List");
		model.addAttribute("test", "test location");			
		model.addAttribute("prop", prop);

		return "menu/recent";

	}

	/**
	 * Display a page with links
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */

	@GetMapping("/checklists/")
	public String checklists(Model model) throws IOException { 
		logger.info("checklists IP:{}",  getRemoteAddress());

		List <LocationProp> prop = service.readLocationData();
		model.addAttribute("title", "Custom Locations");					
		model.addAttribute("prop", prop);		

		return "menu/checklists";

	}
	/**
	 * Display a page with links
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */

	@GetMapping("/notableform/")
	public String notable(Model model) throws IOException { 
		logger.info("notableform IP:{}",  getRemoteAddress());
		Notable notable = new Notable();
		notable.setAdjacent(false);
		notable.setDays(7);

		model.addAttribute("notable", notable);	

		List <LocationProp> prop = service.readLocationData();
		model.addAttribute("title", "Notable Location List");					
		model.addAttribute("prop", prop);		

		return "menu/notableform";

	}

	/**
	 * Display a page with links/form
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/hotspots/")
	public String hotspots(Model model, HttpServletRequest request) throws IOException { 
		logger.info("hotspots IP:{}",  getRemoteAddress());
		HotSpot hotspot = new HotSpot();
		hotspot.setDays(7);		
		hotspot.setAdjacent(false);

		List <States> stateList = service.readStateData(); 
		List <String> stateCodeList =new ArrayList<String>();

		for (States  element : stateList) {
			stateCodeList.add(element.getCode());

		}
		model.addAttribute("dropdownOptions", stateCodeList);		

		List <LocationProp> prop = service.readLocationData();
		model.addAttribute("title", "Custom Hotspot List");		
		model.addAttribute("prop", prop);					
		model.addAttribute("hotspot", hotspot);				

		return "menu/hotspots";
	}

	/**
	 * Custom form a user can fill out to find species in region.
	 * @param model
	 * @return 
	 * @throws IOException
	 */
	@GetMapping("/regionspeciesform/")
	public String recentregionspeciesform(Model model) throws IOException { 
		logger.info("regionspeciesform IP:{}",  getRemoteAddress());
		//	Name name = new Name();
		Species species = new Species();
		species.setDays(7); // fixed for now
		species.setMaxResults(100); // show 100
		species.setAdjacent(false);
		model.addAttribute("species", species);	

		return "menu/regionspeciesform";
	}


	/**
	 * Filter based on user typing
	 * @param term
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/lookup")
	@ResponseBody
	public List <String> lookup(@RequestParam(value = "term", required = false, defaultValue = "") String term) throws IOException { 

		List <String> list2 = null; // new ArrayList<String>();
	
		// get bird list of common names 
		List <String> list = service.getBirdNamesStr();

		if (list != null) {
			
			list2  = findUsingStream(term, list);
		
			return list2;

		}
		else {
			list = service.getBirdCommNames();
			list2  = findUsingStream(term, list);	
		}
		return list2;
	}


	
	/**
	 * //TODO
	 * @param term
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/lookupstate")
	@ResponseBody
	public List <String>  lookupstate(@RequestParam(value = "term", required = false, defaultValue = "") String term) throws IOException { 

		//System.out.println("lookup=" + term);		

		List <String> list = service.getBirdNamesStr();

		List <String> list2 =  null; 

		if (list != null) {
			//	System.out.println("size=" + list.size());

			list2  = findUsingStream(term, list);

			//	System.out.println("size2=" + list2.size());

			return list2;

		}
		else {
			list = service.getBirdCommNames();
			list2  = findUsingStream(term, list);
			//	System.out.println("size2 else =" + list2.size());

		}
		return list2;
	}


	/**
	 * Filter by search string. TODO case dependence
	 * @param search
	 * @param list
	 * @return
	 */
	public List<String> findUsingStream(String search, List<String> list) {
		List<String> matchingElements = list.stream()
				.filter(str -> str.trim().contains(search))
				.collect(Collectors.toList());

		return matchingElements;
	}


	/**
	 * Display a page with links
	 * @param model
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) throws IOException { 

		//String userAgentStr = request.getHeader("User-Agent");		

		model.addAttribute("size", "width: 1000px; height: 800px;");		
		model.addAttribute("lat", "39"); // center of map TODO
		model.addAttribute("lon", "-95");		
		model.addAttribute("zoom", 4);		
		model.addAttribute("dist", 40);		
		model.addAttribute("back", 7);	

		return "menu/index";

	}

	/**
	 * 
	 * @return Remote IP address.
	 */
	String getRemoteAddress(){

		String ipAddress = request.getHeader("X-Forwarded-For");

		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress))
		{
			ipAddress = request.getRemoteAddr();
		}

		return ipAddress;
	}



}
