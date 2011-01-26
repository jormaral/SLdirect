/*
 * Copyright 2010 CENATIC
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

import net.neurowork.cenatic.centraldir.model.User;
import net.neurowork.cenatic.centraldir.service.ServiceException;
import net.neurowork.cenatic.centraldir.service.UserService;
import net.neurowork.cenatic.centraldir.validators.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 10/11/2010
 */
@Controller
@RequestMapping("/users/new")
@SessionAttributes(types = User.class)
public class AddUserController {
	private final static Logger logger = LoggerFactory.getLogger(AddUserController.class);
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		if(logger.isTraceEnabled())
			logger.trace("New User Form");
		User user = new User(); 
		model.addAttribute(user);
		return "users/form";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute User user, BindingResult result, SessionStatus status) {
		new UserValidator().validate(user, result);
		if (!result.hasErrors()) {
			try {
				this.userService.saveUser(user);
				status.setComplete();
				return "redirect:/users/" + user.getUsername();
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
		}
		return "users/form";
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public String updateeUser(@ModelAttribute User user, BindingResult result, SessionStatus status) {
		new UserValidator().validate(user, result);
		
		if (!result.hasErrors()){
			try {
				this.userService.saveUser(user);
				status.setComplete();
				return "redirect:/user/" + user.getUsername();
			} catch (ServiceException e) {
				logger.error(e.getMessage());
			}
		}
		return "users/form";
	}
	
}
