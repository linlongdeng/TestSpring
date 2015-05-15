package org.deng.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {
	
	
	@RequestMapping(value="/error", produces="text/html")
    public String  handleText(HttpServletRequest request, ModelMap model) {
        Enumeration enumeration = request.getAttributeNames();
       while(enumeration.hasMoreElements()){
    	   Object name = enumeration.nextElement();
    	   System.out.println("name:" + name +  ", value:" + request.getAttribute((String)name) ) ;
       }


        return "error";
    }

    @RequestMapping(value="/error", produces="application/json")
    @ResponseBody
    public Map<String, Object> handle(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));

        return map;
    }
    
    

}