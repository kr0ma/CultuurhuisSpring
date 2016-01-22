package be.kroma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.kroma.dao.GenreDAO;
import be.kroma.entities.Genre;

@Service
class GenreServiceImpl implements GenreService {
	// autowired beans
	private final GenreDAO genreDAO;

	@Autowired
	GenreServiceImpl(GenreDAO genreDAO) {
		this.genreDAO = genreDAO;
	}

	@Override
	public Iterable<Genre> findAll() {
		return genreDAO.findAll();
	}

}
