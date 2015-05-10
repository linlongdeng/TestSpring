package org.deng.controller;

import java.util.HashMap;
import java.util.Map;

import org.deng.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	public String test(@RequestParam("name") String name, @ModelAttribute Model model) {
		String result = testService.testService(name);
		model.addAttribute("result", result);
		return "hello";
	}

	@RequestMapping("/testModelMap")
	public ModelMap testModelMap() {
		ModelMap model = new ModelMap("hello");
		model.addAttribute("result", "林龙灯");
		return model;
	}
	
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(@RequestParam("name") String name){
		ModelAndView modelAndView = new ModelAndView("hello", "result", name);
		return modelAndView;
		
	}
	@RequestMapping("/testMap")
	public Map<String, Object> testMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "测试");
		return map;
	}

}
