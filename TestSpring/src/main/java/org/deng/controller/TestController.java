package org.deng.controller;

import java.util.HashMap;
import java.util.Map;

import org.deng.domain.User;
import org.deng.error.ExampleException;
import org.deng.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	public String test(@RequestParam("name") String name, Model model) {
		String result = testService.testService(name);
		model.addAttribute("result", result);
		return "hello";
	}

	@RequestMapping("/testResponeException")
	public ResponseEntity<Object> testResponeException(
			@RequestParam("flag") Boolean flag) {
		if (flag) {
			throw new RuntimeException("测试异常");
		}
		return new ResponseEntity(new User("eric", "7!jd#h23"), HttpStatus.OK);
	}

	@RequestMapping("/testExampleException")
	@ResponseBody
	public Object testExceptionException(@RequestParam("flag") Boolean flag) {
		if (flag) {
			throw new ExampleException("测试异常");
		}
		return new User("eric", "7!jd#h23");
	}

	@RequestMapping("/testModelMap")
	public ModelMap testModelMap() {
		ModelMap model = new ModelMap("hello");
		model.addAttribute("result", "林龙灯");
		return model;
	}

	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(@RequestParam("name") String name) {
		ModelAndView modelAndView = new ModelAndView("hello", "result", name);
		return modelAndView;

	}

	@RequestMapping("/testMap")
	public Map<String, Object> testMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "测试");
		return map;
	}

}
