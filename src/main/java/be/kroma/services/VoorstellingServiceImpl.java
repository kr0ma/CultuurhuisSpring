package be.kroma.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.kroma.dao.VoorstellingDAO;
import be.kroma.entities.Voorstelling;

@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Service
class VoorstellingServiceImpl implements VoorstellingService{
	
	private final VoorstellingDAO voorstellingDAO;
	
	@Autowired
	public VoorstellingServiceImpl(VoorstellingDAO voorstellingDAO) {
		this.voorstellingDAO = voorstellingDAO;
	}

	@Override
	public Iterable<Voorstelling> findVoorstellingenByGenreId(int genreId) {
		return voorstellingDAO.findVoorstellingenByGenreId(genreId);
	}

	@Override
	public Voorstelling findVoorstellingById(int id) {
		return voorstellingDAO.findVoorstellingById(id);
	}

	@Override
	public Iterable<Voorstelling> findVoorstellingenByIDS(Set<Integer> voorstellingIDS) {
		return voorstellingDAO.findVoorstellingenByIDS(voorstellingIDS);
	}
	

	

}
