package be.kroma.dao;

import be.kroma.entities.Klant;

public interface KlantDAO {
	void create(Klant klant);

	boolean bestaatGebruikersnaam(String gebruikersnaam);

	boolean bestaatKlant(int klantid);

	int findKlantID(String name);
}
