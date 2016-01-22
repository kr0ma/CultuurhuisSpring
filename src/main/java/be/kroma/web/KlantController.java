package be.kroma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.services.KlantService;

@Controller
@RequestMapping("/klant")
class KlantController {
	private final KlantService klantService;

	@Autowired
	public KlantController(KlantService klantService) {
		this.klantService = klantService;
	}

	@RequestMapping(value = "/nieuw", method = RequestMethod.GET)
	ModelAndView nieuweKlant() {
		return new ModelAndView("klant/nieuw").addObject(new NieuweKlantForm());
	}

	@RequestMapping(value = "/nieuw", method = RequestMethod.POST)
	ModelAndView nieuweKlantMaken(@Valid NieuweKlantForm nieuweKlantForm, BindingResult bindingResult) {
		if (!nieuweKlantForm.getVerifPaswoord().equals(nieuweKlantForm.getPaswoord())) {
			bindingResult.rejectValue("verifPaswoord", null, "Paswoorden moeten gelijk zijn");
		}
		if (klantService.bestaatKlant(nieuweKlantForm.getGebruikersnaam())) {
			bindingResult.rejectValue("gebruikersnaam", null, "Gebruikersnaam bestaat reeds");
		}
		if (bindingResult.hasErrors()) {
			return new ModelAndView("klant/nieuw");
		}
		klantService.create(nieuweKlantForm.getKlant());
		return new ModelAndView("redirect:/klant/nieuw");
	}

	@InitBinder
	void initBinderNieuweKlantForm(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

}
