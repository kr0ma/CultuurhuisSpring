package be.kroma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.kroma.dao.KlantDAO;
import be.kroma.dao.ReservatieDAO;
import be.kroma.dao.VoorstellingDAO;
import be.kroma.entities.Reservatie;

@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
@Service
class ReservatieServiceImpl implements ReservatieService {

	private final ReservatieDAO reservatieDAO;
	private final VoorstellingDAO voorstellingDAO;
	private final KlantDAO klantDAO;

	@Autowired
	public ReservatieServiceImpl(ReservatieDAO reservatieDAO, VoorstellingDAO voorstellingDAO, KlantDAO klantDAO) {
		this.reservatieDAO = reservatieDAO;
		this.voorstellingDAO = voorstellingDAO;
		this.klantDAO = klantDAO;
	}

	@Transactional(readOnly = false)
	@Override
	public void createReservaties(List<Reservatie> reservaties, String name) {
		int klantID = klantDAO.findKlantID(name);
		if (klantID != 0) {
			for (Reservatie reservatie : reservaties) {
				if (voorstellingDAO.updateVoorstellingPlaatsen(reservatie.getVoorstellingsid(),
						reservatie.getPlaatsen()) == 1) {
					reservatie.setKlantid(klantID);
					reservatieDAO.create(reservatie);
					if (reservatie.getId() != 0) {
						System.out.println(reservatie.getId() + " is gelukt :)");
					} else {
						System.out.println("reservatie voor " + reservatie.getVoorstellingsid() + " is mislukt");
					}
				} else {
					System.out.println("reservatie voor " + reservatie.getVoorstellingsid() + " is mislukt");
				}
			}
		} else {
			System.out.println("klant niet gevonden");
		}
	}

}
