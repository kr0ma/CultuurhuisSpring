package be.kroma.web;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.entities.Reservatie;
import be.kroma.entities.Voorstelling;
import be.kroma.services.ReservatieService;
import be.kroma.services.VoorstellingService;

@Controller
@RequestMapping("/reservatie")
class ReservatieController {

	// autowired beans
	private final VoorstellingService voorstellingService;
	private final ReservatieService reservatieService;
	private final Mandje mandje;

	// constructor injection
	@Autowired
	ReservatieController(VoorstellingService voorstellingService, Mandje mandje, ReservatieService reservatieService) {
		this.voorstellingService = voorstellingService;
		this.reservatieService = reservatieService;
		this.mandje = mandje;
	}
	
	private BigDecimal berekenTotaalMandje(List<Voorstelling> voorstellingen) {
		BigDecimal totaal = BigDecimal.ZERO;
		for (Voorstelling voorstelling : voorstellingen) {
			totaal = totaal.add(voorstelling.getPrijs()
					.multiply(BigDecimal.valueOf(mandje.getVoorstellingAantal(voorstelling.getId()))));
		}
		return totaal;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView reservatieMandje() {
		if (mandje.isLeeg()) {
			return new ModelAndView("redirect:/");
		} else {
			List<Voorstelling> voorstellingInMandje = (List<Voorstelling>) voorstellingService
					.findVoorstellingenByIDS(mandje.getVoorstellingen().keySet());
			return new ModelAndView("reservatie/mandje", "voorstellinginmandje", voorstellingInMandje)
					.addObject(new VerwijderVoorstellingForm()).addObject("mandjeIsLeeg", mandje.isLeeg())
					.addObject("mandje", mandje.getVoorstellingen())
					.addObject("totaal", berekenTotaalMandje(voorstellingInMandje));
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	ModelAndView verwijderVoorstelling(@Valid VerwijderVoorstellingForm verwijderVoorstellingForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors() || !verwijderVoorstellingForm.getVoorstellingIDs().stream()
				.allMatch(id -> mandje.getVoorstellingen().containsKey(id))) {			
			List<Voorstelling> voorstellingInMandje = (List<Voorstelling>) voorstellingService
					.findVoorstellingenByIDS(mandje.getVoorstellingen().keySet());
			return new ModelAndView("reservatie/mandje", "voorstellinginmandje", voorstellingInMandje)
					.addObject("mandjeIsLeeg", mandje.isLeeg()).addObject("mandje", mandje.getVoorstellingen())
					.addObject("totaal", berekenTotaalMandje(voorstellingInMandje));
		}
		mandje.verwijderVoorstellingen(verwijderVoorstellingForm.getVoorstellingIDs());
		return new ModelAndView("redirect:/reservatie").addObject("mandjeIsLeeg", mandje.isLeeg());
	}
	
	@RequestMapping(value = "/bevestig", method = RequestMethod.GET)
	String bevestigReservatie(){
		return "reservatie/bevestig";
	}

	@RequestMapping(value = "/bevestigen", method = RequestMethod.POST)
	String reservatieInboeken(Principal principal){				
		List<Reservatie> reservaties = new ArrayList<>();
		for (Entry<Integer, Integer> entry : mandje.getVoorstellingen().entrySet()){
			reservaties.add(new Reservatie(entry.getKey(), entry.getValue()));			
		}
		reservatieService.createReservaties(reservaties, principal.getName());
		mandje.clear();		
		return "redirect:/reservatie/bevestig";
	}

}
