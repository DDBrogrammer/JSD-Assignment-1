package fa.training.entities;

public class Airplane implements java.io.Serializable{
 private String id;
 private String model;
 @Override
public String toString() {
	return "Airplane [id=" + id + ", model=" + model + ", cruiseSpeed=" + cruiseSpeed + ", emptyWeight=" + emptyWeight
			+ ", maxTakeoffWeight=" + maxTakeoffWeight + "]";
}
private Double cruiseSpeed;
 private Double emptyWeight;
 private Double maxTakeoffWeight;
 
 
 
public Airplane(){};
public Airplane(String id, String model, String planeType, Double cruiseSpeed, Double emptyWeight,
		Double maxTakeoffWeight) {
	super();
	this.id = id;
	this.model = model;
	this.cruiseSpeed = cruiseSpeed;
	this.emptyWeight = emptyWeight;
	this.maxTakeoffWeight = maxTakeoffWeight;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}

public Double getCruiseSpeed() {
	return cruiseSpeed;
}
public void setCruiseSpeed(Double cruiseSpeed) {
	this.cruiseSpeed = cruiseSpeed;
}
public Double getEmptyWeight() {
	return emptyWeight;
}
public void setEmptyWeight(Double emptyWeight) {
	this.emptyWeight = emptyWeight;
}
public Double getMaxTakeoffWeight() {
	return maxTakeoffWeight;
}
public void setMaxTakeoffWeight(Double maxTakeoffWeight) {
	this.maxTakeoffWeight = maxTakeoffWeight;
}
 
}
