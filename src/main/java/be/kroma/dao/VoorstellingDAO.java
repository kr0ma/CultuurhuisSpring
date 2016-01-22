package be.kroma.dao;

import java.util.Set;

import be.kroma.entities.Voorstelling;

public interface VoorstellingDAO {
	Iterable<Voorstelling> findVoorstellingenByGenreId(int genreId);

	Voorstelling findVoorstellingById(int id);

	Iterable<Voorstelling> findVoorstellingenByIDS(Set<Integer> voorstellingIDS);
	
	int updateVoorstellingPlaatsen(int id, int plaatsen);
}
