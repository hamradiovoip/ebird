/**
 * Parse KML file and return list of map points and data.
 * 
 * https://labs.micromata.de/docs/java-api-for-kml/
 * http://www.nilab.info/docs/jak091130/
 * https://github.com/urbancamo/javaapiforkml/tree/master
 * 
 */
package dev.infomatik.ebird.service;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import dev.infomatik.ebird.entity.MapPoints;


/**
 * 
 */
public class KmlParser 
{

	public KmlParser() {}

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
	 * Parse local KML file
	 * TODO: remote url kml file
	 * @return
	 */
	public List <MapPoints> parseKML() {

		KmlParser instance  = new KmlParser();

		InputStream is = instance.getFileAsIOStream("data/MOSBirdersGuideLocations.kml");

		Kml unmarshal = Kml.unmarshal(is);

		Document document = (Document) unmarshal.getFeature();

		int max = document.getFeature().size();
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		List <MapPoints> pointList = new ArrayList<MapPoints>(); // pts on map

		for (int x=0; x< max; x++) {
			Folder folder = (Folder) document.getFeature().get(x);
			folder.setName("Placemarks");
			int folderSize = folder.getFeature().size();

			for (int i = 0; i < folderSize; i++) {

				MapPoints mp = new MapPoints();

				Placemark placemark = (Placemark) folder.getFeature().get(i);
			//	System.out.println(placemark.getDescription());
			//	System.out.println(placemark.getName());

				mp.setTextDescription(placemark.getName());
				mp.setUrl(placemark.getDescription());				

				Point p = (Point) placemark.getGeometry();
				List<Coordinate> coordinate =  p.getCoordinates();
				mp.setLat(coordinate.get(0).getLatitude()); // only one 
				mp.setLon((coordinate.get(0).getLongitude()));// only one

				pointList.add(mp);
				coordinates.add(coordinate.get(0)); // only one
				
				// debug: parseCoordinate( coordinate.get(0));
			}

		}
		return pointList;

	}

	/**
	 * Print out
	 * @param coordinate
	 */

	private void parseCoordinate(Coordinate coordinate) {
		if(coordinate != null) {
			System.out.println("Longitude: " +  coordinate.getLongitude());
			System.out.println("Latitude : " +  coordinate.getLatitude());
			System.out.println("Altitude : " +  coordinate.getAltitude());
			System.out.println("");
		}
	}

}
