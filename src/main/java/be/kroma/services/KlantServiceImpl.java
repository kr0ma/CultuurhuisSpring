package be.kroma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.kroma.dao.KlantDAO;
import be.kroma.entities.Klant;

@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Service
class KlantServiceImpl implements KlantService{
	
	private final KlantDAO klantDAO;	
	
	@Autowired
	public KlantServiceImpl(KlantDAO klantDAO) {
		this.klantDAO = klantDAO;
	}

	@Transactional(readOnly = false)
	@Override
	public void create(Klant klant) {
		klantDAO.create(klant);
	}

	@Override
	public boolean bestaatGebruikersnaam(String gebruikersnaam) {
		return klantDAO.bestaatGebruikersnaam(gebruikersnaam);
	}

}
