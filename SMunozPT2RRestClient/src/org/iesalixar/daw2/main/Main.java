package org.iesalixar.daw2.main;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.model.Dealer;
import org.iesalixar.daw2.model.Driver;
import org.iesalixar.daw2.model.Garage;
import org.iesalixar.daw2.rest.RestClient1;
import org.iesalixar.daw2.rest.RestClient2;
import org.iesalixar.daw2.rest.RestClient3;
import org.iesalixar.daw2.rest.RestClient4;

public class Main {

	public static void main(String[] args) {
		
		// REST CLIENT 2
		
		System.out.println(" ---------- REST CLIENT 2 - POST ---------- ");
		
		Dealer dealer1 = new Dealer();
		dealer1.setDealer_id(1);
		
		Set<Dealer> dealers = new HashSet<Dealer>();
		dealers.add(dealer1);
		
		Driver driver1 = new Driver();
		driver1.setName("example name");
		driver1.setSurname("example surname");
		driver1.setLicense("license");
		driver1.setDealers(dealers);
		
		RestClient2.addDriver(driver1);
		
		// REST CLIENT 1
		
		System.out.println(" ---------- REST CLIENT 1 - GET ---------- ");
		
		RestClient1.getDealerByNameByDriver("1234-ABC", "Dealer 3 full name");
		
		// REST CLIENT 3
		
		System.out.println(" ---------- REST CLIENT 3 - PATCH ---------- ");
		
		driver1.setName("Driver1name");
		driver1.setSurname("Sanchez");
		driver1.setLicense("New License");
		
		RestClient3.patchDriver(driver1);
		
		// REST CLIENT 4
		
		System.out.println(" ---------- REST CLIENT 4 - DELETE ---------- ");
		
		Garage garage = new Garage();
		garage.setName("Garage1");
		
		RestClient4.deleteGarage(garage);

	}

}
