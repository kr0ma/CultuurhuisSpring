package be.kroma.dao;

import be.kroma.entities.Klant;

public interface KlantDAO {
	void create(Klant klant);

	boolean bestaatKlant(String gebruikersnaam);
}
