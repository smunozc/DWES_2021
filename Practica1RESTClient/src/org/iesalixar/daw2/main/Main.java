package org.iesalixar.daw2.main;

import org.iesalixar.daw2.model.Firearm;
import org.iesalixar.daw2.model.Soldier;
import org.iesalixar.daw2.rest.RestClient1;
import org.iesalixar.daw2.rest.RestClient2;
import org.iesalixar.daw2.rest.RestClient3;
import org.iesalixar.daw2.rest.RestClient4;

public class Main {

	public static void main(String[] args) {
		
		// REST CLIENT 2
		
		System.out.println(" ---------- REST CLIENT 2 - POST ---------- ");
		
		Firearm firearm1 = new Firearm();
		firearm1.setName("m4");
		firearm1.setSerialNum(345646745);
		firearm1.setCal("5.56mm");
		
		Soldier soldier1 = new Soldier();
		soldier1.setName("Roberto");
		soldier1.setSurname("Ortiz");
		soldier1.setFirearm(firearm1);
		
//		JSONObject json = new JSONObject(soldier1);
//		System.out.println(json.toString());
		
		RestClient2.addSoldier(soldier1);
		
		// REST CLIENT 1
		
		System.out.println(" ---------- REST CLIENT 1 - GET ---------- ");
		
		RestClient1.getAllSoldier();
		
		// REST CLIENT 3
		
		System.out.println(" ---------- REST CLIENT 3 - PATCH ---------- ");
		
		soldier1.setSurname("Sanchez");
		RestClient3.patchSoldier(soldier1);
		
		// REST CLIENT 4
		
		System.out.println(" ---------- REST CLIENT 4 - DELETE ---------- ");
		
		RestClient4.deleteSoldier(soldier1);
		
		System.out.println(" ---------- CHECK RESULT OF DELETE - GET ---------- ");
		
		RestClient1.getAllSoldier();

	}

}
