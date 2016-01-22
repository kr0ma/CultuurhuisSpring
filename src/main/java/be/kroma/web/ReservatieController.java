package be.kroma.web;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.entities.Voorstelling;
import be.kroma.services.VoorstellingService;

@Controller
@RequestMapping("/reservatie")
class ReservatieController {

	// autowired beans
	private final VoorstellingService voorstellingService;
	private final Mandje mandje;

	// constructor injection
	@Autowired
	ReservatieController(VoorstellingService voorstellingService, Mandje mandje) {
		this.voorstellingService = voorstellingService;
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
	String reservatieInboeken(){
		System.out.println("inboeken zal nu gebeuren");
		return "redirect:/reservatie/bevestig";
	}

}
