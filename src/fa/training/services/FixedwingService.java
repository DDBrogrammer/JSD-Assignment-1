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
import fa.training.entities.Fixedwing;
import fa.training.entities.Fixedwing;
import fa.training.entities.Fixedwing;

public class FixedwingService {
	public static AirportService airportService = new AirportService();
	public static HelicopterService helicopterService = new HelicopterService();
	   final File FIXEDWING_REPOSITORY=new File("FixedwingRepository.txt");
	   public boolean save(Fixedwing fixedwing) {
			boolean ok = false;
			ArrayList<Fixedwing> fixedwings = new ArrayList();
			if(FIXEDWING_REPOSITORY.length()!=0) {
				try {
					
					  FileInputStream fi = new FileInputStream(FIXEDWING_REPOSITORY);
					 ObjectInputStream oi = new ObjectInputStream(fi); // Read objects
					  ArrayList<Fixedwing> rawFixedwings = (ArrayList<Fixedwing>) oi.readObject();
					  for (Fixedwing oldHelicopter : rawFixedwings) {
					  fixedwings.add(oldHelicopter); }
					  oi.close(); 
					  fi.close();
					  deleteAll();
					FileOutputStream f = new FileOutputStream(FIXEDWING_REPOSITORY);
					ObjectOutputStream o = new ObjectOutputStream(f);

					fixedwings.add(fixedwing);
					o.writeObject(fixedwings);
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
				} catch (Exception e) {
					System.out.println(e);
					// end of file reached, do nothing
				}finally {
					return ok;
				}		
			}else {
				try { 
					FileOutputStream f = new FileOutputStream(FIXEDWING_REPOSITORY);
					ObjectOutputStream o = new ObjectOutputStream(f);
					fixedwings.add(fixedwing);
					o.writeObject(fixedwings);
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
				} catch (Exception e) {
					System.out.println(e);
					// end of file reached, do nothing
				}finally {return ok;}
				
			}
			
		}

		public Fixedwing findById(String id) {
			ArrayList<Fixedwing> fixedwings = getAll();
			Fixedwing fixedwing = new Fixedwing();
			for (Fixedwing h : fixedwings) {
				if (h.getId().equals(id)) {
					fixedwing = h;
					break;

				}
			}
			return fixedwing;

		}

		public boolean deleteById(String id) {
			ArrayList<Fixedwing> fixedwings = getAll();
			Fixedwing fixedwing = new Fixedwing();
			boolean ok = false;
			for(int i=0;i<fixedwings.size();i++) {
				
				if(fixedwings.get(i).getId().equals(id)) {
					fixedwings.remove(i);
					break;

				}
			}
			try {
            deleteAll();
			FileOutputStream f = new FileOutputStream(FIXEDWING_REPOSITORY);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(fixedwings);
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
		
		public ArrayList<Fixedwing> getAddAbleList(Airport inputAirport) {
			
			ArrayList<Airport> sortAirportList= airportService.getSortList();
			ArrayList<Fixedwing> addAbleList=getSortList();
			ArrayList<Fixedwing> toRemove = new ArrayList<Fixedwing>();
			
			for(Airport airport:sortAirportList) {
				for(String usedFixedwing:airport.getListOfFixedWingAirplaneId()) {
					for(Fixedwing fixedwing:addAbleList) {
						
						if(fixedwing.getId().equals(usedFixedwing) ) {
							toRemove.add(fixedwing);
						}
						
					}
						
				}
			}
			addAbleList.removeAll(toRemove);
			ArrayList<Fixedwing> removeList = new ArrayList<Fixedwing>();
			for(Fixedwing fixedwing:addAbleList) {
				if(fixedwing.getMinNeededRunawaySize()>=inputAirport.getRunawaySize()) {
					removeList.add(fixedwing);
				}
			}
			addAbleList.removeAll(removeList);
			return addAbleList;	
		}
		   public ArrayList<Fixedwing> getSortList(){
		    	ArrayList<Fixedwing> normalList=getAll();
		        List<String>  listId=new ArrayList<String>();
		    	for(Fixedwing fixedwing:normalList) {
		    		listId.add(fixedwing.getId());
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
		    	ArrayList<Fixedwing> sortList=new ArrayList<Fixedwing>() ;
		    	for(String id:listId) {
		    		for(Fixedwing fixedwing:normalList) {
		                 if(fixedwing.getId().equals(id)) {
		                	 sortList.add(fixedwing);
		                 }
		                 }
		    	}
		    	
		    	return sortList;
		    }
		public ArrayList<Fixedwing> getAll() {
			ArrayList<Fixedwing> fixedwings = new ArrayList();
			if(FIXEDWING_REPOSITORY.length()!=0) {
				try {
					FileInputStream fi = new FileInputStream(FIXEDWING_REPOSITORY);
					ObjectInputStream oi = new ObjectInputStream(fi);
					// Read objects
					ArrayList<Fixedwing> rawFixedwings = (ArrayList<Fixedwing>) oi.readObject();
					for (Fixedwing fixedwing : rawFixedwings) {
						fixedwings.add(fixedwing);
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
					return fixedwings;
				}
				
			}else {return fixedwings;}
			
		}
		   public boolean deleteAll() {
		    	boolean ok = false;
		  		try {
		  		new FileOutputStream(FIXEDWING_REPOSITORY).close();
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
