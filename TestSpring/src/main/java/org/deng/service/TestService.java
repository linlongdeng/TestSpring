package org.deng.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	public String testService(String name){
		return "hello,world" + name;
	}

}
