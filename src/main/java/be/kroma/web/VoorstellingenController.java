package be.kroma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.entities.Voorstelling;
import be.kroma.services.VoorstellingService;

@Controller
@RequestMapping("/voorstelling")
class VoorstellingenController {
	private final VoorstellingService voorstellingService;
	private final Mandje mandje;

	@Autowired
	public VoorstellingenController(VoorstellingService voorstellingService, Mandje mandje) {
		this.voorstellingService = voorstellingService;
		this.mandje = mandje;
	}

	private final String RESERVEREN = "voorstelling/reserveren";

	@RequestMapping("/{id}/reserveren")
	ModelAndView voorstellingReserveren(@PathVariable int id) {
		ReserverenForm reserverenForm = new ReserverenForm();
		if (mandje.isVoorstellingAanwezig(id)) {
			reserverenForm.setAantal(mandje.getVoorstellingAantal(id));
		}
		return new ModelAndView(RESERVEREN, "voorstelling", voorstellingService.findVoorstellingById(id))
				.addObject("mandjeIsLeeg", mandje.isLeeg())
				.addObject(reserverenForm);
	}

	@RequestMapping(value = "/{id}/reserveren", method = RequestMethod.POST)
	ModelAndView blabla(@PathVariable int id, @Valid ReserverenForm reserverenForm, BindingResult bindingResult) {
		Voorstelling voorstelling = voorstellingService.findVoorstellingById(id);
		if (!bindingResult.hasErrors() && voorstelling.getVrijeplaatsen() < reserverenForm.getAantal()){
			bindingResult.rejectValue("aantal", null,"max aantal plaatsen = " + voorstelling.getVrijeplaatsen());
		}
		if (bindingResult.hasErrors()){			
			return new ModelAndView("voorstelling/reserveren", "voorstelling", voorstelling)
					.addObject("mandjeIsLeeg", mandje.isLeeg());
		}		
		mandje.addVoorstelling(id, reserverenForm.getAantal());		
		return new ModelAndView("redirect:/reservatie").addObject("mandjeIsLeeg", mandje.isLeeg());
	}	
	
	@InitBinder
	void initBinderReserverenForm(DataBinder dataBinder){
		dataBinder.initDirectFieldAccess();
	}

}
