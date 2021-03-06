package org.iesalixar.daw2.helper;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.model.Driver;
import org.iesalixar.daw2.model.Dealer;
import org.iesalixar.daw2.model.Garage;

public class MemoryData {
	
	public static Driver 			driver1,driver2,driver3;
	public static Dealer 			dealer1,dealer2,dealer3;
	public static Garage 			garage1,garage2,garage3,
									garage4,garage5,garage6;

	public static Set<Dealer> 		list_dealers;
	public static Set<Driver>  		list_drivers;
	public static Set<Garage>		list_garages;
	public static boolean 			isLoaded = false;
	
	public static void load() {
		
		driver1 		= new Driver();
		driver1.setName("Driver1name");
		driver1.setSurname("Driver1surname");
		driver1.setLicense("1234-ABC");
		driver2 		= new Driver();
		driver2.setName("Driver2name");
		driver2.setSurname("Driver2surname");
		driver2.setLicense("2345-ABC");
		driver3 		= new Driver();
		driver3.setName("Driver3name");
		driver3.setSurname("Driver3surname");
		driver3.setLicense("3456-ABC");
	
		dealer1 		= new Dealer();
		dealer1.setName("Dealer 1 full name");
		dealer1.setAddress("Dealer 1 full address");
		dealer2 		= new Dealer();
		dealer2.setName("Dealer 2 full name");
		dealer2.setAddress("Dealer 2 full address");
		dealer3 		= new Dealer();
		dealer3.setName("Dealer 3 full name");
		dealer3.setAddress("Dealer 3 full address");
		
	
		garage1 	= new Garage();
		garage1.setName("Garage1");
		garage1.setSlot("2");
		garage1.setDealer(dealer1);
		
		garage2 	= new Garage();
		garage2.setName("Garage2");
		garage2.setSlot("3");
		garage2.setDealer(dealer1);
		
		garage3 	= new Garage();
		garage3.setName("Garage3");
		garage3.setSlot("4");
		garage3.setDealer(dealer1);
		
		garage4 	= new Garage();
		garage4.setName("Garage4");
		garage4.setSlot("5");
		garage4.setDealer(dealer2);
		
		garage5 	= new Garage();
		garage5.setName("Garage5");
		garage5.setSlot("3");
		garage5.setDealer(dealer3);
		
		garage6 	= new Garage();
		garage6.setName("Garage6");
		garage6.setSlot("4");
		garage6.setDealer(dealer3);
		
		Set<Dealer> list1_dealer = new HashSet<Dealer>();		
		list1_dealer.add(dealer1);
		list1_dealer.add(dealer2);
		list1_dealer.add(dealer3);
		
		Set<Dealer> list2_dealer = new HashSet<Dealer>();
		list2_dealer.add(dealer2);
		
		Set<Dealer> list3_dealer = new HashSet<Dealer>();		
		list3_dealer.add(dealer1);
		list3_dealer.add(dealer3);
	
		driver1.setDealers(list1_dealer);
		driver2.setDealers(list2_dealer);
		driver3.setDealers(list3_dealer);

		Set<Driver> list1_driver = new HashSet<Driver>();		
		list1_driver.add(driver1);
		list1_driver.add(driver3);
		
		Set<Driver> list2_driver = new HashSet<Driver>();		
		list2_driver.add(driver1);
		list2_driver.add(driver2);
		
		Set<Driver> list3_driver = new HashSet<Driver>();		
		list3_driver.add(driver1);
		list3_driver.add(driver3);
				
		Set<Garage> list1_garages = new HashSet<Garage>();
		list1_garages.add(garage1);
		list1_garages.add(garage2);
		list1_garages.add(garage3);
		
		Set<Garage> list2_garages = new HashSet<Garage>();
		list2_garages.add(garage4);
		
		Set<Garage> list3_garages = new HashSet<Garage>();
		list3_garages.add(garage5);
		list3_garages.add(garage6);
		
		dealer1.setDrivers(list1_driver);
		dealer1.setGarages(list1_garages);
		
		dealer2.setDrivers(list2_driver);
		dealer2.setGarages(list2_garages);
		
		dealer3.setDrivers(list3_driver);
		dealer3.setGarages(list3_garages);
		
		list_dealers = new HashSet<Dealer>();		
		list_dealers.add(dealer1);
		list_dealers.add(dealer2);
		list_dealers.add(dealer3);
		
		list_drivers = new HashSet<Driver>();		
		list_drivers.add(driver1);
		list_drivers.add(driver2);
		list_drivers.add(driver3);
		
		list_garages = new HashSet<Garage>();
		list_garages.add(garage1);
		list_garages.add(garage2);
		list_garages.add(garage3);
		list_garages.add(garage4);
		list_garages.add(garage5);
		list_garages.add(garage6);
		
		isLoaded = true;
	}

}
