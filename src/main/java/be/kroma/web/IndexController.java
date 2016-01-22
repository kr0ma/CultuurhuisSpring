package be.kroma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.kroma.services.GenreService;

@Controller
@RequestMapping("/")
class IndexController {

	// autowired beans
	private final GenreService genreService;	
	private final Mandje mandje;

	// constructor injection
	@Autowired
	IndexController(GenreService genreService, Mandje mandje) {
		this.genreService = genreService;		
		this.mandje = mandje;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
		return new ModelAndView("index", "genres", genreService.findAll()).addObject("mandjeIsLeeg", mandje.isLeeg());
	}
}
