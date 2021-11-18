package fa.training.services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.Helicopter;
import fa.training.entities.Helicopter;

public class HelicopterService {
	File HELICOPTER_REPOSITORY = new File("HelicopterRepository.txt");
	public static AirportService airportService = new AirportService();
	public static FixedwingService fixedwingService = new FixedwingService();

	public boolean save(Helicopter helicopter) {
		boolean ok = false;
		ArrayList<Helicopter> helicopters = new ArrayList();
		if( HELICOPTER_REPOSITORY.length()!=0 ) {
			try {
				FileInputStream fi = new FileInputStream(HELICOPTER_REPOSITORY);
				ObjectInputStream oi = new ObjectInputStream(fi);
				// Read objects
				ArrayList<Helicopter> rawHelicopters = (ArrayList<Helicopter>) oi.readObject();
				for (Helicopter oldHelicopter : rawHelicopters) {
					helicopters.add(oldHelicopter);
				}
				oi.close();
				fi.close();
				deleteAll();
				FileOutputStream f = new FileOutputStream(HELICOPTER_REPOSITORY);
				ObjectOutputStream o = new ObjectOutputStream(f);

				helicopters.add(helicopter);
				o.writeObject(helicopters);
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
				FileOutputStream f = new FileOutputStream(HELICOPTER_REPOSITORY);
				ObjectOutputStream o = new ObjectOutputStream(f);
				helicopters.add(helicopter);
				o.writeObject(helicopters);
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

	public Helicopter findById(String id) {
		ArrayList<Helicopter> helicopters = getAll();
		Helicopter helicopter = new Helicopter();
		for (Helicopter h : helicopters) {
			if (h.getId().equals(id)) {
				helicopter = h;
				break;

			}
		}
		return helicopter;

	}

	public boolean deleteById(String id) {
		ArrayList<Helicopter> helicopters = getAll();
		Helicopter helicopter = new Helicopter();
		boolean ok = false;
		for(int i=0;i<helicopters.size();i++) {
			
			if(helicopters.get(i).getId().equals(id)) {
				helicopters.remove(i);
				break;

			}
		}
		try {
		deleteAll();
		FileOutputStream f = new FileOutputStream(HELICOPTER_REPOSITORY);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(helicopters);
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
	public ArrayList<Helicopter> getAddAbleList() {
		
		ArrayList<Airport> sortAirportList= airportService.getSortList();
		ArrayList<Helicopter> addAbleList=getSortList();
		ArrayList<Helicopter> toRemove = new ArrayList<Helicopter>();
		
		for(Airport airport:sortAirportList) {
			for(String usedHelicopter:airport.getListOfHelicopterId()) {
				for(Helicopter helicopter:addAbleList) {
					if(helicopter.getId().equals(usedHelicopter)) {
						toRemove.add(helicopter);
					}
					
				}
					
			}
		}
		addAbleList.removeAll(toRemove);
		
		return addAbleList;	
	}
	public ArrayList<Helicopter> getSortList(){
    	ArrayList<Helicopter> normalList=getAll();
        List<String>  listId=new ArrayList<String>();
    	for(Helicopter helicopter:normalList) {
    		listId.add(helicopter.getId());
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
    	ArrayList<Helicopter> sortList=new ArrayList<Helicopter>() ;
    	for(String id:listId) {
    		for(Helicopter helicopter:normalList) {
                 if(helicopter.getId().equals(id)) {
                	 sortList.add(helicopter);
                 }
                 }
    	}
    	
    	return sortList;
    }
	
	
	
	
	public ArrayList<Helicopter> getAll() {
		ArrayList<Helicopter> helicopters = new ArrayList();
		if(HELICOPTER_REPOSITORY.length()!=0){
			try {
				FileInputStream fi = new FileInputStream(HELICOPTER_REPOSITORY);
				ObjectInputStream oi = new ObjectInputStream(fi);
				// Read objects
				ArrayList<Helicopter> rawHelicopters = (ArrayList<Helicopter>) oi.readObject();
				for (Helicopter helicopter : rawHelicopters) {
					helicopters.add(helicopter);
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
				return helicopters;
			}
			
		}else {return helicopters;}
	}	
	public boolean deleteAll() {
    	boolean ok = false;
  		try {
  		new FileOutputStream(HELICOPTER_REPOSITORY).close();
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
