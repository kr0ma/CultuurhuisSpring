package be.kroma.web;

import java.util.Map;

interface Mandje {
	void addVoorstelling(int voorstellingID, int aantal);
	int getVoorstellingAantal(int voorstellingID);
	boolean isVoorstellingAanwezig(int voorstellingID);
	boolean isLeeg();
	Map<Integer, Integer> getVoorstellingen();
	void verwijderVoorstellingen(Iterable<Integer> ids);
	void clear();	
}
