package fa.training.utils;

import java.util.ArrayList;
import java.util.Random;

public class HelicopterValidate {
	 public ArrayList<Integer> listDemoId = new ArrayList<Integer>(100000);
   public boolean validateId(String id) {
	 final String VALIDATE_ID="/[R][W][0-9][0-9][0-9][0-9][0-9]/gm";
	 if (id.matches(VALIDATE_ID)&&id.length()==7) { return true;}
	 else {return false;}
 }
   public boolean validateModel(String model) {
	   if (model.length()>40 && model.length()<=1) {
		   return true ;
		   
	   }else {return false;}
	   
 }
   public boolean validateTakeoff(Double takeoffWeight, Double emptyWeight  ){
	 if (takeoffWeight<=emptyWeight*1.5) {
		 return true;
	 }else{return false;}
 }
   public String getValidateId( int min,
	              int max) {
	   Random random = new Random();
	     int randomNumber = random.nextInt((max - min) + 1) + min;
	     if (!this.listDemoId.contains(randomNumber)) {
           this.listDemoId.add(randomNumber);
       }
	 
	     return "RW"+String.valueOf(randomNumber);}
 	  
   
   
   
   } 

 

