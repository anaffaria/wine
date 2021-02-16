package com.algaworks.wine.controller;

import com.algaworks.wine.model.Wine;
import com.algaworks.wine.model.WineType;
import com.algaworks.wine.repository.Wines;
import com.algaworks.wine.service.WineCreateService;
import com.algaworks.wine.storage.PhotoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/wines")
public class WineController {

	@Autowired
	private Wines wines;

	@Autowired
	private WineCreateService createWineService;

	@Autowired
	private PhotoStorage phototorage;

	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/wines/ListWine");
		mv.addObject("wines", wines.findAll());
		return mv;
	}

	@RequestMapping("/new")
	public ModelAndView newWine(Wine wine) {
		ModelAndView mv = new ModelAndView("/wines/NewWine");
		mv.addObject("types", WineType.values());
		return mv;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView create(@Valid Wine wine, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return newWine(wine);
		}
		createWineService.save(wine);
		attributes.addFlashAttribute("message", "Vinho Salvo com sucesso!");
		return new ModelAndView("redirect:/wines/new");
	}

	@RequestMapping("/{code}")
	public ModelAndView view(@PathVariable("code") Wine wine) {
		ModelAndView mv = new ModelAndView("/wines/ViewWine");

		if(wine.hasPhoto()) {
			wine.setUrl(phototorage.getUrl(wine.getPhoto()));
		}

		mv.addObject(wine);

		return mv;
	}
}
