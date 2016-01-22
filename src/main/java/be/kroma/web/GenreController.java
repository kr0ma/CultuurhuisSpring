package be.kroma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.services.GenreService;
import be.kroma.services.VoorstellingService;

@Controller
@RequestMapping("/genre")
class GenreController {

	private final VoorstellingService voorstellingService;
	private final GenreService genreService;
	private final Mandje mandje;

	@Autowired
	GenreController(VoorstellingService voorstellingService, GenreService genreService,Mandje mandje ) {
		this.voorstellingService = voorstellingService;
		this.genreService = genreService;
		this.mandje = mandje;
	}

	@RequestMapping("/{id}")
	ModelAndView voorstellingenByGenre(@PathVariable int id) {
		return new ModelAndView("index", "voorstellingen", voorstellingService.findVoorstellingenByGenreId(id))
				.addObject("genres", genreService.findAll())
				.addObject("mandjeIsLeeg", mandje.isLeeg());
	}

}
