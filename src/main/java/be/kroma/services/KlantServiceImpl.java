package be.kroma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.kroma.dao.KlantDAO;
import be.kroma.entities.Klant;

@Service
class KlantServiceImpl implements KlantService{
	
	private final KlantDAO klantDAO;	
	
	@Autowired
	public KlantServiceImpl(KlantDAO klantDAO) {
		this.klantDAO = klantDAO;
	}

	@Override
	public void create(Klant klant) {
		klantDAO.create(klant);
	}

	@Override
	public boolean bestaatKlant(String gebruikersnaam) {
		return klantDAO.bestaatKlant(gebruikersnaam);
	}

}
