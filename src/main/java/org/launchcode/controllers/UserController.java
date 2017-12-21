package org.launchcode.controllers;

import org.launchcode.controllers.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping(value = "")
    public String index(Model model) {
        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, @RequestParam String verify) {
        if (!verify.equals(user.getPassword())){
            return "user/add";
        }
        return "redirect:";
    }
}
