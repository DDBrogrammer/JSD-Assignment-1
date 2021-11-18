package fa.training.entities;

public class Helicopter extends Airplane implements java.io.Serializable{
	 private double range;
	 @Override
	public String toString() {
		return "Helicopter [id=" + this.getId() + ", model=" + this.getModel() + ", cruiseSpeed=" + this.getCruiseSpeed() + ", emptyWeight=" + this.getEmptyWeight()
				+ ", maxTakeoffWeight=" + this.getMaxTakeoffWeight() +", range="+range+ "]";
	}
	private final String FLY_METHOD="rotated wing";
   public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public String getFLY_METHOD() {
		return FLY_METHOD;
	}
  
   public Helicopter() {
	
	// TODO Auto-generated constructor stub
  }
public Helicopter(String id, String model,  Double cruiseSpeed, Double emptyWeight,
		Double maxTakeoffWeight,double range) {
	super();
	this.setId(id);
	this.setModel (model);
	this.setCruiseSpeed (cruiseSpeed);
	this.setEmptyWeight(emptyWeight) ;
	this.setMaxTakeoffWeight(maxTakeoffWeight);
	this.setRange(range); 
}

   
}
