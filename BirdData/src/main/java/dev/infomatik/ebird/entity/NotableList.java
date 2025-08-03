package dev.infomatik.ebird.entity;

import java.util.List;

public class NotableList {

	
	 List <Notable> notable;
	 List <MapPoints> pointList;
	 
	 
	/**
	 * @return the notable
	 */
	public List <Notable> getNotable() {
		return notable;
	}
	/**
	 * @param notable the notable to set
	 */
	public void setNotable(List<Notable> notable) {
		this.notable = notable;
	}
	/**
	 * @return the pointList
	 */
	public List<MapPoints> getPointList() {
		return pointList;
	}
	/**
	 * @param pointList the pointList to set
	 */
	public void setPointList(List<MapPoints> pointList) {
		this.pointList = pointList;
	}
	
	
	
}
