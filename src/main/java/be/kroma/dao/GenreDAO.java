package be.kroma.dao;

import be.kroma.entities.Genre;

public interface GenreDAO {
	Iterable<Genre> findAll();
}
