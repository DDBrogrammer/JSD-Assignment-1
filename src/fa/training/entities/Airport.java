package fa.training.entities;

import java.util.ArrayList;
import java.util.List;

public class Airport implements java.io.Serializable{
	private String id;
	private String name;
	private Double runawaySize;
	private Double maxFixedWingParkingPlace;
	private List<String>listOfFixedWingAirplaneId=new ArrayList<String>();
	private List<String>listOfHelicopterId=new ArrayList<String>() ;
	private Double maxRotatedWingParkingPlace;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRunawaySize() {
		return runawaySize;
	}
	public void setRunawaySize(Double runawaySize) {
		this.runawaySize = runawaySize;
	}
	public Double getMaxFixedWingParkingPlace() {
		return maxFixedWingParkingPlace;
	}
	public void setMaxFixedWingParkingPlace(Double maxFixedWingParkingPlace) {
		this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
	}
	public List<String> getListOfFixedWingAirplaneId() {
		return listOfFixedWingAirplaneId;
	}
	public void setListOfFixedWingAirplaneId(List<String> listOfFixedWingAirplaneId) {
		this.listOfFixedWingAirplaneId = listOfFixedWingAirplaneId;
	}
	public List<String> getListOfHelicopterId() {
		return listOfHelicopterId;
	}
	public void setListOfHelicopterId(List<String> listOfHelicopterId) {
		this.listOfHelicopterId = listOfHelicopterId;
	}
	public Double getMaxRotatedWingParkingPlace() {
		return maxRotatedWingParkingPlace;
	}
	public void setMaxRotatedWingParkingPlace(Double maxRotatedWingParkingPlace) {
		this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
	}
	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + ", runawaySize=" + runawaySize + ", maxFixedWingParkingPlace="
				+ maxFixedWingParkingPlace + ", listOfFixedWingAirplaneId=" + listOfFixedWingAirplaneId
				+ ", listOfHelicopterId=" + listOfHelicopterId + ", maxRotatedWingParkingPlace="
				+ maxRotatedWingParkingPlace + "]";
	}
	public  Airport(){}
	public Airport(String id, String name, Double runawaySize, Double maxFixedWingParkingPlace,
			List<String> listOfFixedWingAirplaneId, List<String> listOfHelicopterId,
			Double maxRotatedWingParkingPlace) {
		super();
		this.id = id;
		this.name = name;
		this.runawaySize = runawaySize;
		this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
		this.listOfFixedWingAirplaneId = listOfFixedWingAirplaneId;
		this.listOfHelicopterId = listOfHelicopterId;
		this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
	};
	

}
