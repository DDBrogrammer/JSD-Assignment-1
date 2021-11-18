package fa.training.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import fa.training.entities.Airplane;
import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.services.AirplaneService;
import fa.training.services.AirportService;
import fa.training.services.FixedwingService;
import fa.training.services.HelicopterService;
import fa.training.utils.AirplaneValidate;
import fa.training.utils.AirportValidate;
import fa.training.utils.FixedwingValidate;
import fa.training.utils.HelicopterValidate;

public class AirplaneManagement {
	public static AirportService airportService = new AirportService();
	public static FixedwingService fixedwingService = new FixedwingService();
	public static HelicopterService helicopterService = new HelicopterService();
	public static AirportValidate airportValidate = new AirportValidate();
	public static HelicopterValidate helicopterValidate = new HelicopterValidate();
	public static FixedwingValidate fixedwingValidate=new FixedwingValidate();

	public static void printMenu() {
		System.out.println("Choses action:\n" 
				+ "[1] Airport management.\n"
				+ "[2] Fixed wing airplane management.\n" 
				+ "[3] Helicopter management group.\n"
				+ "[4] Close program." );

	}

	public static int getAction(String ask) {
		boolean run=true;
		int a=0;
		while(run==true) {try {
			Scanner sc = new Scanner(System.in);
			System.out.println(ask);
			 a = sc.nextInt();
				run=false;
			
		}catch(Exception e ) {
			System.out.println("You must enter a number :v");
		}
	
		}
		
		return (int)a;
	}

	public static String getStringInfor(String ask) {
		boolean run=true;
		String s="";
		while(run==true) {try {
		Scanner sc = new Scanner(System.in);
		System.out.println(ask);
		s = sc.nextLine();
		run=false;
		}
		catch(Exception e ) {
			System.out.println("You must enter some text :D");
		}
			
		}
		return s;
	}

	public static Double getDoubleInfor(String ask) {
		boolean run=true;
		Double d=0.0;
		while(run==true) {try {
		Scanner sc = new Scanner(System.in);
		System.out.println(ask);
		 d = sc.nextDouble();
			run=false;
		}
		catch(Exception e ) {
			System.out.println("You must enter anumber :D");
		}
		
		}
		return (Double)d;
	}

	public static void findAirportInfor() {
		try {
	      String id =getStringInfor("Enter airport id");
	      Airport airport= airportService.findById(id);
	      System.out.println(airport.toString());
	      List<String> fixedWingList=airport.getListOfFixedWingAirplaneId();
	      List<String> rotatedWingList=airport.getListOfHelicopterId();
	      if(fixedWingList.size()>0) {
	    	  for(String fixedwingId:fixedWingList) {
	    		  System.out.println( fixedwingService.findById(fixedwingId).toString());
	    		 
	    	  }
	    	  
	      }
	      if(rotatedWingList.size()>0) {
	    	  for(String helicopterId:rotatedWingList) {
	    		  System.out.println( helicopterService.findById(helicopterId).toString());
	    		 
	    	  }
	    	  
	      }
	  }catch(Exception e) {
		System.out.println("Try again");
		findAirportInfor();
	}
	}
	public static boolean addFixedwingToAirport() {
		boolean ok=false;
		 String aiportId=getStringInfor("Enter airport id:");
		  Airport airport=airportService.findById(aiportId);
		 ArrayList<Fixedwing> addAbleFixedwingList=fixedwingService.getAddAbleList(airport);
		 System.out.println("Avaliable fixedwing: ");
		 if(addAbleFixedwingList.size()>0) {
			 for(Fixedwing fixedwing:addAbleFixedwingList) {
				 System.out.println(fixedwing.toString());
			 }
		 }
		 String fixedwingId=getStringInfor("Emter fixedwing id:");
		if( airportService.addFixedwingToAirport(aiportId,fixedwingId)) {
			ok=true;
		}
		
		return ok;
	}
	public static boolean addHelicopterToAirport() {
		boolean ok=false;
		 String aiportId=getStringInfor("Enter airport id:");
		 ArrayList<Helicopter> addAbleHelicopterlist=helicopterService.getAddAbleList();
		 System.out.println("Avaliable helicopter: ");
		 if(addAbleHelicopterlist.size()>0) {
			 for(Helicopter helicopter:addAbleHelicopterlist) {
				 System.out.println(helicopter.toString());
			 }
		 }
		 String helicopterId=getStringInfor("Emter helicopter id:");
		if( airportService.addHelicopterToAirport(aiportId, helicopterId)) {
			ok=true;
		}
		
		return ok;
	}
	public static boolean removeFixedwingFromAirport() {
		boolean ok=false;
		String aiportId=getStringInfor("Enter airport id");
	    Airport airport=airportService.findById(aiportId);
	    System.out.println("Removeable fixedwing: ");
	    if(airport.getListOfFixedWingAirplaneId().size()>0) {
	    	for(String inFixedwingId:airport.getListOfFixedWingAirplaneId()) {
		    	System.out.println(fixedwingService.findById(inFixedwingId).toString());
		    }
	    }
	    String fixedwingId=getStringInfor("Emter helicopter id:");
	    if(airportService.removeFixedwingFormAirport(aiportId, fixedwingId)) {
	    	ok=true;
	    }
	    return ok;		
	}
	public static boolean removeHelicopterFromAirport() {
		boolean ok=false;
		String aiportId=getStringInfor("Enter airport id");
	    Airport airport=airportService.findById(aiportId);
	    System.out.println("Removeable helicopter: ");
	    if(airport.getListOfHelicopterId().size()>0) {
	    	for(String inHelicopterId:airport.getListOfHelicopterId()) {
		    	System.out.println(helicopterService.findById(inHelicopterId).toString());
		    }
	    }
	    String helicopterId=getStringInfor("Emter helicopter id:");
	    if(airportService.removeHelicopterFromAirport(aiportId, helicopterId)) {
	    	ok=true;
	    }
	    return ok;}
	public static boolean changeFixedWingInfor( ArrayList<Fixedwing> changeAbleFixedwingList) {
		boolean ok=false;
		boolean in=false;
		System.out.println("Changeable fixedwing list");
		for(Fixedwing fixedwing:changeAbleFixedwingList) {
			System.out.println(fixedwing.toString());
		}
		String fixedwingId=getStringInfor("Enter fixedwing id");
		for(Fixedwing fixedwing:changeAbleFixedwingList) {
			if(fixedwing.getId().equals(fixedwingId)) {
				in=true;
			}
		}
		Fixedwing fixedwing= fixedwingService.findById(fixedwingId);
		if(in==true) {
			String type=getStringInfor("Enter plane type code:");
			if(fixedwingValidate.validateType(type)) {
				fixedwing.setPlaneType(type);
			}
			Double minRunawaySize=(Double)getDoubleInfor("Enter runaway size:");
			fixedwing.setMinNeededRunawaySize(minRunawaySize);
			if(fixedwingService.save(fixedwing)) {
				ok=true;
			}
		}
	    return ok;
	}
	
	public static Airport getAirport() {
		
		Airport newAirport = new Airport();
		
			try { String name = getStringInfor("Enter airport name: ");
					if (airportValidate.validateName(name)) {
						newAirport.setName(name);
					} else {
						System.out.println("You enter wrong name format !!!");
						
					}
					Double runwaySize = getDoubleInfor("Enter runway size: ");
					if (airportValidate.validateRunawaySize(runwaySize)) {
						newAirport.setRunawaySize(runwaySize);
					} else {
						System.out.println("You enter wrong runway size format !!!");
						
					}
					Double maxFixedWingParkingPlace = getDoubleInfor("Enter max fixedwing parking place: ");
					if (airportValidate.validateMaxFixedWingParkingPlace(maxFixedWingParkingPlace)) {
						newAirport.setMaxFixedWingParkingPlace(maxFixedWingParkingPlace);
					} else {
						System.out.println("You enter wrong max fixedwing parking place format !!!");
						
					}
					Double maxRotatedWingParkingPlace = getDoubleInfor("Enter max rotated wing parking place: ");
					if (airportValidate.validateMaxRotatedWingParkingPlace(maxRotatedWingParkingPlace)) {
						newAirport.setMaxRotatedWingParkingPlace(maxRotatedWingParkingPlace);
					} else {
						System.out.println("You enter wrong max fixedwing parking place format !!!");
						
					}
					String id = airportValidate.getValidateId(9999, 100000);
					newAirport.setId(id);
				
					
			    }catch(Exception e) {
						System.out.println("Try again");
						getAirport();
					}
			return newAirport;
			}
			
	
   //effect add some demo helicopters  
	public static void addDemoHelicopters() {
	
		Helicopter h1 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 747", 230.4, (double) 500,
				(double) 600, (double) 20);
		Helicopter h2 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 742vip", 230.4, (double) 501,
				(double) 621, (double) 21);
		Helicopter h3 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 744sos", 230.4, (double) 532,
				(double) 623, (double) 22);
		Helicopter h4 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 746okok", 230.4, (double) 550,
				(double) 624, (double) 23);
		Helicopter h5 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 748ok", 230.4, (double) 570,
				(double) 625, (double) 24);
		Helicopter h6 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 749abc", 230.4, (double) 580,
				(double) 626, (double) 25);
		Helicopter h7 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 7av", 230.4, (double) 570,
				(double) 627, (double) 26);
		Helicopter h8 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 7vip", 230.4, (double) 600,
				(double) 650, (double) 27);
		Helicopter h9 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 747fg", 230.4, (double) 567,
				(double) 667, (double) 28);
		Helicopter h10 = new Helicopter(helicopterValidate.getValidateId(9999, 100000), "Boeing 747e", 230.4, (double) 580,
				(double) 670, (double) 29);
		helicopterService.save(h1);
		helicopterService.save(h2);
		helicopterService.save(h3);
		helicopterService.save(h4);
		helicopterService.save(h5);
		helicopterService.save(h6);
		helicopterService.save(h7);
		helicopterService.save(h8);
		helicopterService.save(h9);
		helicopterService.save(h10);
	}
	//CAG (Cargo), LGR (Long range) and PRV (Private).
    public static void addDemoFixedwings() {
    	Fixedwing f1= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 100","CAG",300.55,(double)1020,(double)1460,200.485);
    	Fixedwing f2= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 200","LGR",300.55,(double)1030,(double)1330,400.75);
    	Fixedwing f3= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 300","CAG",300.55,(double)1040,(double)1404,200.75);
    	Fixedwing f4= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 400","LGR",300.55,(double)1050,(double)1230,300.75);
    	Fixedwing f5= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 700","PRV",300.55,(double)1060,(double)1330,700.65);
    	Fixedwing f6= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 800","LGR",300.55,(double)1070,(double)1340,200.55);
    	Fixedwing f7= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 900","CAG",300.55,(double)1080,(double)1200,400.45);
    	Fixedwing f8= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 189","LGR",300.55,(double)1090,(double)1300,500.35);
    	Fixedwing f9= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 123","CAG",300.55,(double)1100,(double)1400,600.235);
    	Fixedwing f10= new Fixedwing(fixedwingValidate.getValidateId(9999, 100000),"B52 provip 321","PRV",300.55,(double)1010,(double)1300,300.35);
    	
        fixedwingService.save(f1);
    	fixedwingService.save(f2);
    	fixedwingService.save(f3);
    	fixedwingService.save(f4);
    	fixedwingService.save(f5);
    	fixedwingService.save(f6);
    	fixedwingService.save(f7);
    	fixedwingService.save(f8);
    	fixedwingService.save(f9);
    	fixedwingService.save(f10);
   
    }
    
    public static void addDemoAirports() {
    	List<String> helicopterIds=new ArrayList<String>();
		helicopterIds.add("RW48717");
		helicopterIds.add("RW60119");
    	List<String> demoFixedwingIds=new ArrayList<String>();
    	List<String> demoHelicopterIds=new ArrayList<String>();
    	Airport a1=new Airport(airportValidate.getValidateId(9999,100000),"Noi Bai",(double)400,(double)50000,demoFixedwingIds,helicopterIds,(double)30000);
    	Airport a2=new Airport(airportValidate.getValidateId(9999,100000),"Tan Son Nhat",(double)500,(double)50000,demoFixedwingIds,demoHelicopterIds,(double)30000);
    	Airport a3=new Airport(airportValidate.getValidateId(9999,100000),"Cam Ranh",(double)600,(double)50000,demoFixedwingIds,demoHelicopterIds,(double)30000);
    	Airport a4=new Airport(airportValidate.getValidateId(9999,100000),"Phu Bai",(double)800,(double)50000,demoFixedwingIds,demoHelicopterIds,(double)30000);
    	airportService.save(a1);
    	airportService.save(a2);
    	airportService.save(a3);
    	airportService.save(a4);
    	
    	
    }
	public static void main(String[] args) {
		
		    int n = 0;
	        boolean continueRun = true;
	        //main
	        while (n != 5 && continueRun) { //loop for users choices and stop when boolean continou =false
	            try {
	                printMenu();
	                n= getAction("Enter number form 1 to 5:");
	              } catch (Exception line) {
	                System.out.println("you must enter number!!!");
	                n= getAction("Enter number form 1 to 5:");
	            }//tell users if they not enter number
	            switch (n) {// create and compare each user's choices
	                case 1:
	                     int n1=0;
	                    	 ArrayList<Airport> sortListAirPort=  airportService.getSortList();
		                     System.out.println("AIRPORT LIST");
	                         for(Airport airport:sortListAirPort) {
	                        	System.out.println(airport.toString());
	                        }
		                	 try{   n1=getAction("Action:\n"
		                			+ " [1] Add an airport.\n"
		                			+ " [2] Get information of an airport.\n"
		                			+ " [3] Add a helicopter to an airport.\n"
		                			+ " [4] Add a airplane to an airprot.\n"
		                			+ " [5] Remove a helicopter form an airport.\n"
		                			+ " [6] Remove a fixedwing form an airport.\n"
		                			+ " [7] Back to menu.\n"
		                			+ " Enter action:");}
		                	 catch(Exception e) {
		                				System.out.println("you must enter number!!!");
		                				n1= getAction("Enter number form 1 to 4:");
		                	}
		                	 
	                    	if(n1==1) {
	                    		if(airportService.save(getAirport())) {
	                    			System.out.println("Airport saved");
	                    		}else {	System.out.println("Cant save airport");}
	                    	
	                    	 
				                    if (!askYesNo()) {
				                        System.out.print("program end!!!");//end the program when asked
				                        continueRun = false;
				                    }// add user  if they want to continue or stop the program(ask2)
				                    break;
									
								
	                    		
	                    	}else if(n1==2) {
	                    		findAirportInfor();
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                 
	                    	}else if(n1==3) {
	                    		if(addHelicopterToAirport()) {
	                    			System.out.println("Helicopter have bean added");
	                    		}else {	System.out.println("Cant add helicopter");}
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}else if(n1==4) {
	                    		if(addFixedwingToAirport()) {
	                    			System.out.println("Helicopter have bean added");
	                    		}else {	System.out.println("Cant add helicopter");}
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}else if(n1==5) {
	                    		if(removeHelicopterFromAirport()) {
	                    			System.out.println("Helicopter have bean removed");
	                    		}else {	System.out.println("Cant remove helicopter");}
	                    		if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}
	                    	else if(n1==6) {
	                    		if(removeFixedwingFromAirport()) {
	                    			System.out.println("Fixedwing have bean removed");
	                    		}else {	System.out.println("Cant remove fixedwing");}
	                               if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
	                    	}else if(n1==7 ) {
	                    		break;
	                    		
	                    	} else { 
	                    		if (!askYesNo()) {
		                        System.out.print("program end!!!");//end the program when asked
		                        continueRun = false;
		                        
		                         }
	                    		// add user  if they want to continue or stop the program(ask2)
		                      }
	                     
	                    break;
	                case 2:	
	                	showAllHelicopter();
	                	if (!askYesNo()) {
	                        System.out.print("program end!!!");//end the program when asked
	                        continueRun = false;}
	                	 break;
	                case 3:
	                	int n3=0;
	                	ArrayList<Fixedwing> changeAbleFixedwingList= showAllFixedeWing();
	                	try{   n3=getAction("Action:\n"
	                			+ " [1] Change fixedwing information.\n"
	                			+ " [2] Back to menu.\n"
	                			+ " Enter action:");}
	                	 catch(Exception e) {
	                				System.out.println("you must enter number!!!");
	                				n1= getAction("Enter number form 1 to 4:");
	                	}
	                	if(n3==1) {
                    		if(changeFixedWingInfor(changeAbleFixedwingList)) {
                    			System.out.println("Fixedwing information have been change");
                    		}else {	System.out.println("Cant change fixedwing information");}
                    	
                    	 
			                    if (!askYesNo()) {
			                        System.out.print("program end!!!");//end the program when asked
			                        continueRun = false;
			                    }// add user  if they want to continue or stop the program(ask2)
			                    break;
								
							
                    		
                    	}else if(n3==2 ) {
                    		break;
                    		
                    	} else { 
                    		if (!askYesNo()) {
	                        System.out.print("program end!!!");//end the program when asked
	                        continueRun = false;
	                        
	                         }
                    		// add user  if they want to continue or stop the program(ask2)
	                      }
	                	
	                	 break;
	                default:
	                    System.out.print("invalid value!!!");// tell the users when  value is invalid
	            }

	        }

	    }
       
		public static void showAllHelicopter() {
		 ArrayList<Airport> listAirport= 	airportService.getSortList();
		 ArrayList<Helicopter> listHelicopter= helicopterService.getSortList();	
		 ArrayList<Helicopter> toRemove=new ArrayList<Helicopter>();
	     	for(Airport airport:listAirport) {
	     		if(airport.getListOfHelicopterId().size()>0) {
	     			for(String ListOfHelicopterId:airport.getListOfHelicopterId() ) {
	     				
	     				for(Helicopter helicopter:listHelicopter) {
		     				if(ListOfHelicopterId.equals(helicopter.getId())) {
		     					toRemove.add(helicopter);
		     					System.out.println(helicopter.toString()+"=[Airport [id="+airport.getId()+", name="+airport.getName()+"]" );
		     				}
		     			}
	     			}
	     			
	     		}
	     	}
	     	listHelicopter.removeAll(toRemove);
	     	for(Helicopter helicopter:listHelicopter) {
	     		System.out.println(helicopter.toString());
	     	}
		}
		public static ArrayList<Fixedwing> showAllFixedeWing() {
			ArrayList<Airport> listAirport= 	airportService.getSortList();
			 ArrayList<Fixedwing> listFixedWing= fixedwingService.getSortList();	
			 ArrayList<Fixedwing> toRemove=new ArrayList<Fixedwing>();
		     	for(Airport airport:listAirport) {
		     		if(airport.getListOfFixedWingAirplaneId().size()>0) {
		     			for(String ListOfFixedWingAirplaneId:airport.getListOfFixedWingAirplaneId() ) {
		     				
		     				for(Fixedwing fixedwing:listFixedWing) {
			     				if(ListOfFixedWingAirplaneId.equals(fixedwing.getId())) {
			     					toRemove.add(fixedwing);
			     					System.out.println(fixedwing.toString()+"=[Airport [id="+airport.getId()+", name="+airport.getName()+"]" );
			     				}
			     			}
		     			}
		     			
		     		}
		     	}
		     	listFixedWing.removeAll(toRemove);
		     	for(Fixedwing fixedwing:listFixedWing) {
		     		System.out.println(fixedwing.toString());
		     	}
		     	return listFixedWing;
			
		}
		
		   public static boolean askYesNo() {
			   boolean ok=false;
			   boolean runAgain=true;
			   String ans="";
			   while(runAgain==true) {
				   try {
                  ans = getStringInfor("Do you want to continue with another function \n" +
                        "[Y]  yes\n" +
                        "[N]  no\n" +
                        "Choose an option: "); }catch (Exception e) {
				// TODO: handle exceptions
            	   ans = getStringInfor("You must enter Y(yes) or N(no).");
			     }
				  if(ans.toUpperCase().equals("Y")) {
					  ok=true;
					  runAgain=false;
				  }else if(ans.toUpperCase().equals("N")) {
					  ok=false;
					  runAgain=false;
				  }else {
					  runAgain=true;
				  }
				   
				   
			   }
			  return ok; 
			  
		   }
		
	

	

}
