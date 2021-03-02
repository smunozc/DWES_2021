package org.iesalixar.daw2.dao;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.model.Dealer;
import org.iesalixar.daw2.model.Garage;

public class GarageDAOImpl{
	
	public static Set<Garage> getAllGarages() {
		if(!MemoryData.isLoaded)
			MemoryData.load();
		return MemoryData.list_garages;
	}
	
	public static Set<Garage> getAllGaragesByDealerName(String dealerName){
		if(!MemoryData.isLoaded)
			MemoryData.load();
		Set<Garage> result = new HashSet<Garage>();
		for(Garage garage:MemoryData.list_garages) {
			if(garage.getDealer().getName().equals(dealerName))
				result.add(garage);
		}
		return result;
	}
	
	public static void createGarage(Garage garage) {}
	public static void setDealer(Garage garage,Dealer dealer) {}
	public static void remove(Garage garage) {}
	
	public static boolean removeGarage(Garage garage) {
		
		boolean result = false;
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Garage systemGarage = null;
		
		for (Garage item : MemoryData.list_garages) {
			if(item.getName().equals(garage.getName())) {
				systemGarage = item;
				break;
			}
		}
		if(systemGarage!=null) {
			Dealer d = null;
			
			for (Dealer dealer : MemoryData.list_dealers) {
				if(dealer.getName().equals(systemGarage.getDealer().getName())) {
					d = dealer;
					break;
				}
			}
			if(d!=null) {
				Set<Garage> garages = new HashSet<Garage>();
				for (Garage operating : d.getGarages()) {
					if(!operating.getName().equals(garage.getName()))
						garages.add(operating);
				}
				d.setGarages(garages);
			}
			MemoryData.list_garages.remove(systemGarage);
			result = true;
		}
		
		return result;
	}
}
