package org.deng.validator;

import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MapValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Map.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		Map<String, Object> map = (Map<String, Object>) target;
		String name = (String) map.get("name");
		if(name == null || "".equals(name)){
			e.reject("name.empty");
			
		}
		  
	}

}
