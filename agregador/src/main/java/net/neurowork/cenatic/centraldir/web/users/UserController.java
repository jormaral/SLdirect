/*
 * Copyright 2010 Nuevo Banco del Chaco S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neurowork.cenatic.centraldir.web.users;

import java.util.Collection;

import net.neurowork.cenatic.centraldir.model.User;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 11/11/2010
 */
@Controller
@SessionAttributes(types = User.class)
public class UserController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users/search", method = RequestMethod.GET)
	public String searchUser(Model model) {
		model.addAttribute("user", new User());
		return "users/search";
	}
	
	@RequestMapping(value = "/users/results", method = RequestMethod.GET)
	public String searchUser(User user, BindingResult result, Model model) {
		if (user.getUsername() == null) {
			user.getUsername();
		}
		Collection<User> results;
		try {
			results = this.userService.findUsers(user.getUsername());
			if (results.size() < 1) {
				result.rejectValue("name", "notFound", "Usuario No Encontrado");
				return "users/search";
			}
			if (results.size() > 1) {
				model.addAttribute("users", results);
				return "users/list";
			}else{
				// 1 owner found
				user = results.iterator().next();
				return "redirect:/users/" + user.getUsername();
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		return "users/list";
	}	

	@RequestMapping("/users")
	public ModelAndView usersHandler() {
		ModelAndView mv = new ModelAndView("users/list");
		Collection<User> users;
		try {
			users = userService.getUsers();
			mv.addObject("users", users);
		} catch (ServiceException e) {
		}
		return mv;
	}
	
	@RequestMapping("/users/{username}")
	public ModelAndView userHandler(@PathVariable("username") String username) {
		if(logger.isTraceEnabled())
			logger.trace("Showing Information for User: " + username);
		ModelAndView mav = new ModelAndView("users/show");
		User user;
		try {
			user = this.userService.loadUser(username);
			mav.addObject("user", user);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		} 
		return mav;
	}
	
	
}
