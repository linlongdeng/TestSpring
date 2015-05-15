package org.deng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping("/hello")
	public String test(@RequestParam("flag") Boolean flag, Model model) {
		if(flag){
			throw new RuntimeException("测试异常");
		}
		model.addAttribute("result", "你好");
		return "hello";
	}
}
