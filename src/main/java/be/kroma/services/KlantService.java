package be.kroma.services;

import be.kroma.entities.Klant;

public interface KlantService {
	void create (Klant klant);
	boolean bestaatGebruikersnaam(String gebruikersnaam);	
}
