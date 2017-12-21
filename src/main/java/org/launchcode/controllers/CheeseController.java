package org.launchcode.controllers;

import org.launchcode.controllers.models.Category;
import org.launchcode.controllers.models.Cheese;
import org.launchcode.controllers.models.data.CategoryDao;
import org.launchcode.controllers.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors,
                                       @RequestParam int categoryId, Model model) {

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }
        return "redirect:";
    }

    /*@RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
        public String displayEditForm(Model model, @PathVariable int cheeseId) {
            model.addAttribute(CheeseData.getById(cheeseId));
            return "cheese/edit";
        }


    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
        public String processEditForm(int cheeseId, String name, String description) {
            Cheese newCheese = CheeseData.getById(cheeseId);
            newCheese.setName(name);
            newCheese.setDescription(description);
            return "redirect:/cheese";
        }*/




}
