package org.deng.controller;

import java.util.List;

import javax.validation.Valid;

import org.deng.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class UserController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@JsonView(User.WithoutPasswordView.class)
	public User getUser() {
		return new User("eric", "7!jd#h23");

	}

	@Autowired
	private Validator validator;

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public Object insertUser( User user, ModelMap model) {
		DataBinder binder = new DataBinder(user);
		binder.setValidator(validator);
		binder.validate();
		BindingResult bindingResult = binder.getBindingResult();
		if (bindingResult.hasErrors()) {
		 FieldError fieldError = bindingResult.getFieldError();
			ModelAndView view = new ModelAndView("errorjson");
			model.put("errorMsg", fieldError.getField() + "的" + fieldError.getDefaultMessage());
			view.addAllObjects(model);
			return view;
		}
		user.setName("4343243243243423432432434");
		return user;
	}

}