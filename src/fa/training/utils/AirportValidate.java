package fa.training.utils;

import java.util.ArrayList;
import java.util.Random;

public class AirportValidate {
	
	  public ArrayList<Integer> listDemoId = new ArrayList<Integer>(100000);
	  
	  public  String getValidateId( int min,
		         int max) {
		    
		     Random random = new Random();
		     int randomNumber = random.nextInt((max - min) + 1) + min;
		     if (!this.listDemoId.contains(randomNumber)) {
	             this.listDemoId.add(randomNumber);
	         }
		 
		     return "AP"+String.valueOf(randomNumber);}
	  
	  
	  
       public boolean validateName(String name) {
    	   if (name=="") {return false;}else {return true;}
       }
       public boolean validateRunawaySize(Double size) {
    	   if (size <= 0) {return false;}else {return true;}
       }
    
       public boolean validateMaxRotatedWingParkingPlace (Double size) {
    	   if (size <= 0) {return false;}else {return true;}
       }
  
	public boolean validateMaxFixedWingParkingPlace(Double size) {
		// TODO Auto-generated method stub
		if (size <= 0) {return false;}else {return true;}
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
