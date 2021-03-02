package org.iesalixar.daw2.dao;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.model.Dealer;
import org.iesalixar.daw2.model.Garage;
import org.iesalixar.daw2.model.Driver;


public class DriverDAOImpl{
	
	public static Set<Driver> getAllDrivers() {
		return MemoryData.list_drivers;
	}
	
	public static Set<Driver> getDriversWithEqualOrGreaterNumberOfDealers(String number) {
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Set<Driver> result = new HashSet<Driver>();
		for(Driver driver:MemoryData.list_drivers) {
			if(driver.getDealers().size()>(Integer.parseInt(number)-1))
				result.add(driver);
		}
		return result;
	}
	
	public static Set<Driver> getDriversByGarageSlotByDealerName(String garageSlot,String dealerName){
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Set<Driver> result = new HashSet<Driver>();
		
		for(Driver driver:MemoryData.list_drivers) {
			driverloop:
			for(Dealer dealer:driver.getDealers()) {
				if(dealer.getName().equals(dealerName)) {
					for(Garage garage : dealer.getGarages()) {
						if(garage.getSlot().equals(garageSlot)) {
							result.add(driver);
							break driverloop;
						}
							
					}
				}
				
			}			
		}
		return result;
	}
	
	public static void createDriver(Driver driver) {
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		Set<Dealer> dealers = MemoryData.list_dealers;
		Set<Dealer> dealers2 = driver.getDealers();
		Set<Dealer> dealers3 = new HashSet<Dealer>();
		for (Dealer dealer : dealers2) {
			for (Dealer dealer2 : dealers) {
				if(dealer.getDealer_id()==dealer2.getDealer_id()) {
					dealers3.add(dealer2);
					break;
				}				
			}
		}
		driver.setDealers(dealers3);	
		Set<Driver> tmp = null;
		Set<Driver> drivers = MemoryData.list_drivers;
		driver.setDriver_id(drivers.size()+1);
		drivers.add(driver);
		for (Dealer h : driver.getDealers()) {
			for (Dealer dealer2 : dealers) {
				if(h.getName().equals(dealer2.getName())) {
					if(dealer2.getDrivers()!=null)
						tmp = dealer2.getDrivers();
					else
						tmp = new HashSet<Driver>();
					tmp.add(driver);
					break;
				}
				
			}
		}
		
	}
	
	public static boolean updateDriverDetails(Driver driver) {
		
		
		boolean result = false;
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Driver systemDriver = null;
		
		for (Driver s : MemoryData.list_drivers) {
			if(s.getName().equals(driver.getName()))
				systemDriver = s;
		}
		
		if(systemDriver!=null) {
			if(!systemDriver.getSurname().equals(driver.getSurname()))
				systemDriver.setSurname(driver.getSurname());
			if(!systemDriver.getLicense().equals(driver.getLicense()))
				systemDriver.setLicense(driver.getLicense());
			result = true;
		}
		
		return result;
	}
	
	public static Driver addDealerToDriver(String driverName, String newDealerName) {
		
		return null;
	}
	
}
