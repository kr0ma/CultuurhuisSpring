package be.kroma.services;

import be.kroma.entities.Genre;

public interface GenreService {
	Iterable<Genre> findAll();
}
