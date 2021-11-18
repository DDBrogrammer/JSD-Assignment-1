package fa.training.services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.entities.Airport;
import fa.training.entities.Airport;
import fa.training.entities.Airport;

public class AirportService {

	public static FixedwingService fixedwingService = new FixedwingService();
	public static HelicopterService helicopterService = new HelicopterService();
	
	
      final File AIRPORT_REPOSITORY=new File("AirportRepository.txt");
      public boolean save(Airport airport) {
  		boolean ok = false;
  		ArrayList<Airport> airports = new ArrayList();
  		if(AIRPORT_REPOSITORY.length()!=0) {
  			try {
  	  			FileInputStream fi = new FileInputStream(AIRPORT_REPOSITORY);
  	  			ObjectInputStream oi = new ObjectInputStream(fi);
  	  			// Read objects
  	  			ArrayList<Airport> rawHelicopters = (ArrayList<Airport>) oi.readObject();
  	  			for (Airport oldHelicopter : rawHelicopters) {
  	  				airports.add(oldHelicopter);
  	  			}
  	  			oi.close();
  	  			fi.close();
  	  		    ArrayList<Airport> toRemove = new ArrayList<Airport>(); 
  	  		    for(Airport repositoryAirport:airports)
  	  		    { if  (repositoryAirport.getId().equals(airport.getId())) {
  	  		    	 toRemove.add(repositoryAirport);
  	  		    }  } 	  			
  	  			airports.removeAll(toRemove);
  	  			airports.add(airport);
  	  			FileOutputStream f = new FileOutputStream(AIRPORT_REPOSITORY);
  	  			ObjectOutputStream o = new ObjectOutputStream(f);  	  			
  	  			o.writeObject(airports);
  	  			o.flush();
  	  			o.close();
  	  			ok = true;

  	  		} catch (EOFException eof) {
  	  			// end of file reached, do nothing
  	  		} catch (FileNotFoundException e) {
  	  			ok = false;
  	  			System.out.println("File not found");
  	  		} catch (IOException e) {
  	  			ok = false;
  	  			System.out.println(e);
  	  			System.out.println("Error initializing stream");
  	  		} finally {
  	  			return ok;
  	  		}
  			
  		}else {
  			try {
  	  			FileOutputStream f = new FileOutputStream(AIRPORT_REPOSITORY);
  	  			ObjectOutputStream o = new ObjectOutputStream(f);
  	  			airports.add(airport);
  	  			o.writeObject(airports);
  	  			o.flush();
  	  			o.close();
  	  			ok = true;
  	  		} catch (EOFException eof) {
  	  			// end of file reached, do nothing
  	  		} catch (FileNotFoundException e) {
  	  			ok = false;
  	  			System.out.println("File not found");
  	  		} catch (IOException e) {
  	  			ok = false;
  	  			System.out.println(e);
  	  			System.out.println("Error initializing stream");
  	  		} finally {
  	  			return ok;
  	  		}
  			
  		}
  		
  	}
      public boolean addFixedwingToAirport(String airportId,String fixedWingId) {
    	  boolean check=false;
 		 ArrayList<Fixedwing> addAbleFixedwingList= fixedwingService.getAddAbleList(findById(airportId));
 		 Airport airport=findById(airportId);
 		 List<String> oldFixedWingId=airport.getListOfFixedWingAirplaneId();
 		 for(Fixedwing fixedwing:addAbleFixedwingList) {
 			 if(fixedwing.getId().equals(fixedWingId)) {
 				oldFixedWingId.add(fixedWingId);
 				airport.setListOfFixedWingAirplaneId(oldFixedWingId);;
 				save(airport);
 				check=true;
 				break;
 			 }
 			 
 		 }
 		 
 		 return check;
		
	}
	 public boolean addHelicopterToAirport(String airportId, String helicpoterId){
		 boolean check=false;
		 ArrayList<Helicopter> addAbleHelicopterList= helicopterService.getAddAbleList();
		 Airport airport=findById(airportId);
		 List<String> oldHelicopterId=airport.getListOfHelicopterId();
		 for(Helicopter helicopter:addAbleHelicopterList) {
			 if(helicopter.getId().equals(helicpoterId)) {
				oldHelicopterId.add(helicpoterId);
				airport.setListOfHelicopterId(oldHelicopterId);;
				save(airport);
				check=true;
				break;
			 }
			 
		 }
		 
		 return check;
		 
		 
	 }
      public Airport findById(String id) {
  		ArrayList<Airport> airports = getAll();
  		Airport airport = new Airport();
  		for (Airport h : airports) {
  			if (h.getId().equals(id)) {
  				airport = h;
  				break;

  			}
  		}
  		return airport;
  	}
      public boolean removeFixedwingFormAirport(String airportId,String fixedwingId) {
    	  boolean check=false;
    	  Airport airport=findById(airportId);
     	 List<String> toRemove=new ArrayList<String>();
     	 for(String oldFixedwingId:airport.getListOfFixedWingAirplaneId()) {
     		if(oldFixedwingId.equals(fixedwingId)) {
     			toRemove.add(fixedwingId);
     			break;
     		}
     	}
     	  List<String> newFixedwingIdList= airport.getListOfFixedWingAirplaneId();
      	if(newFixedwingIdList.removeAll(toRemove)) {
      		airport.setListOfFixedWingAirplaneId(newFixedwingIdList);
      		save(airport);
      		check=true;
      	}
      	return check;
    	  
      }
    public boolean removeHelicopterFromAirport(String airportId, String helicopterId) {
    	 boolean check=false;
    	 Airport airport=findById(airportId);
    	 List<String> toRemove=new ArrayList<String>();
    	 for(String oldHelicopterId:airport.getListOfHelicopterId()) {
    		if(oldHelicopterId.equals(helicopterId)) {
    			toRemove.add(helicopterId);
    			break;
    		}
    	}
    	  List<String> newHelicopterIdList= airport.getListOfHelicopterId();
    	if(newHelicopterIdList.removeAll(toRemove)) {
    		airport.setListOfHelicopterId(newHelicopterIdList);
    		save(airport);
    		check=true;
    	}
    	return check;
    }
  	public boolean deleteById(String id) {
  		ArrayList<Airport> airports = getAll();
  		Airport airport = new Airport();
  		boolean ok = false;
  		for(int i=0;i<airports.size();i++) {
  			
  			if(airports.get(i).getId().equals(id)) {
  				airports.remove(i);
  				break;

  			}
  		}
  		try {
  		deleteAll();
  		FileOutputStream f = new FileOutputStream(AIRPORT_REPOSITORY);
  		ObjectOutputStream o = new ObjectOutputStream(f);
  		o.writeObject(airports);
  		o.flush();
  		o.close();
  		ok=true;}
  		catch (EOFException eof) {
  			// end of file reached, do nothing
  		} catch (FileNotFoundException e) {
  			ok = false;
  			System.out.println("File not found");
  		} catch (IOException e) {
  			ok = false;
  			System.out.println(e);
  			System.out.println("Error initializing stream");
  		} finally {
  			return ok;
  		}
  		
  	}
  	
   
    public ArrayList<Airport> getSortList(){
    	ArrayList<Airport> normalList=getAll();
        List<String>  listId=new ArrayList<String>();
    	for(Airport airport:normalList) {
    		listId.add(airport.getId());
    	}
    	Collections.sort(listId, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
    	ArrayList<Airport> sortList=new ArrayList<Airport>() ;
    	for(String id:listId) {
    		for(Airport airport:normalList) {
                 if(airport.getId().equals(id)) {
                	 sortList.add(airport);
                 }
                 }
    	}
    	
    	return sortList;
    }
    
    public ArrayList<Airport> getAll() {
		ArrayList<Airport> airports = new ArrayList();
		if(AIRPORT_REPOSITORY.length()==0) {
			return airports;
			
		}else {
			try {
			FileInputStream fi = new FileInputStream(AIRPORT_REPOSITORY);
			ObjectInputStream oi = new ObjectInputStream(fi);
			// Read objects
			ArrayList<Airport> rawAirports = (ArrayList<Airport>) oi.readObject();
			for (Airport airport : rawAirports) {
				airports.add(airport);
			}
			oi.close();
			fi.close();
		}

		catch (FileNotFoundException e) {

			System.out.println("File not found");
		} catch (EOFException e) {
		
		} catch (IOException e) {

			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return airports;
		}}
		
	}
    public boolean deleteAll() {
    	boolean ok = false;
    
  		try {
  		
  		new FileOutputStream(AIRPORT_REPOSITORY).close();
  		ok=true;}
  		catch (EOFException eof) {
  			// end of file reached, do nothing
  		} catch (FileNotFoundException e) {
  			ok = false;
  			System.out.println("File not found");
  		} catch (IOException e) {
  			ok = false;
  			System.out.println(e);
  			System.out.println("Error initializing stream");
  		} finally {
  			return ok;
  		}
    }
}
