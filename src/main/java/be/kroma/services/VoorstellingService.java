package be.kroma.services;

import java.util.Set;

import be.kroma.entities.Voorstelling;

public interface VoorstellingService {
	Iterable<Voorstelling> findVoorstellingenByGenreId(int genreId);

	Voorstelling findVoorstellingById(int id);

	Iterable<Voorstelling> findVoorstellingenByIDS(Set<Integer> voorstellingen);
}
