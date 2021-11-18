package fa.training.utils;

public class AirplaneValidate {
	public boolean validateId(String id) {
		 final String VALIDATE_ID="/[A][P][0-9][0-9][0-9][0-9][0-9]/gm";
		 if (id.matches(VALIDATE_ID)&&id.length()==7) { return true;}
		 else {return false;}
	 }

}
