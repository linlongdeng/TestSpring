package org.deng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.deng.domain.Person;
import org.deng.validator.MapValidator;
import org.deng.validator.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	 private MessageSource messages;

	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PersonValidator());
    }
	
	@RequestMapping("/testValidte")
	@ResponseBody
	public Object testValidte(Person person){
		DataBinder binder = new DataBinder(person);
		binder.setValidator(new PersonValidator());
		binder.validate();
		BindingResult bindingResult = binder.getBindingResult();
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			String message = messages.getMessage(fieldError.getCode(), null, Locale.UK);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("errorMsg", message);
			return map;
		}		
		return person;		
	}
	
	@RequestMapping("/testMap")
	@ResponseBody
	public Object testMap(@RequestParam Map<String, Object> param){
		DataBinder binder = new DataBinder(param);
		binder.setValidator(new MapValidator());
		binder.validate();
		BindingResult bindingResult = binder.getBindingResult();
		if(bindingResult.hasErrors()){
		 ObjectError error = bindingResult.getAllErrors().get(0);
			String message = messages.getMessage(error.getCode(), null, Locale.UK);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("errorMsg", message);
			return map;
		}		
		return param;		
	}
}
