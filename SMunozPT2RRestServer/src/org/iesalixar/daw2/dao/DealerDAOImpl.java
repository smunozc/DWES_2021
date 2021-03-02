package org.iesalixar.daw2.dao;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.model.Dealer;
import org.iesalixar.daw2.model.Driver;

public class DealerDAOImpl{
	
	public static Set<Dealer> getAllDealers() {
		return MemoryData.list_dealers;
	}
	
	public static Set<Dealer> getDealersWithEqualNumberOfDriversAndGarages() {
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Set<Dealer> result = new HashSet<Dealer>();
		
		for(Dealer dealer:MemoryData.list_dealers) {
			if(dealer.getDrivers().size()==dealer.getGarages().size())
				result.add(dealer);
		}
		return result;
	}
	
	public static void createDealer(Dealer dealer) {}
	
	public static void addDriver(Driver driver, Dealer dealer) {}

	public static Dealer getDealerByNameByDriverLicense(String dealerName,String driverLicense) {
		if(!MemoryData.isLoaded)
			MemoryData.load();
		for(Dealer dealer:MemoryData.list_dealers) {
			if(dealer.getName().equals(dealerName)) {
				for(Driver driver:dealer.getDrivers()) {
					if(driver.getLicense().equals(driverLicense))
						return dealer;
				}
			}
		}
		return null;
	}
	
	

}
