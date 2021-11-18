package fa.training.entities;

public class Fixedwing extends Airplane implements java.io.Serializable {
	@Override
	public String toString() {
		return "Fixedwing [id="+ this.getId() + ", model=" + this.getModel() + ", cruiseSpeed=" + this.getCruiseSpeed() + ", emptyWeight=" + this.getEmptyWeight()
		+ ", maxTakeoffWeight=" + this.getMaxTakeoffWeight()+", planeType=" + planeType + ", minNeededRunawaySize=" + minNeededRunawaySize + ", FLY_METHOD="
				+ FLY_METHOD + "]";
	}
	private String planeType;
	private Double minNeededRunawaySize;
    private final String FLY_METHOD="fixed wing";
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public Double getMinNeededRunawaySize() {
		return minNeededRunawaySize;
	}
	public void setMinNeededRunawaySize(Double minNeededRunawaySize) {
		this.minNeededRunawaySize = minNeededRunawaySize;
	}
	public String getFLY_METHOD() {
		return FLY_METHOD;
	}
	public Fixedwing(String planeType, Double minNeededRunawaySize) {
		super();
		this.planeType = planeType;
		this.minNeededRunawaySize = minNeededRunawaySize;
	}
	 public Fixedwing() {
			
			// TODO Auto-generated constructor stub
		  }
	public Fixedwing (String id, String model, String planeType, Double cruiseSpeed, Double emptyWeight,
				  Double maxTakeoffWeight,Double minNeededRunawaySize ) {
			super();
			this.setId(id);
			this.setModel (model);
			this.setPlaneType(planeType);
			this.setCruiseSpeed (cruiseSpeed);
			this.setEmptyWeight(emptyWeight) ;
			this.setMaxTakeoffWeight(maxTakeoffWeight);
			this.setMinNeededRunawaySize(minNeededRunawaySize);
			
		}
    
}
