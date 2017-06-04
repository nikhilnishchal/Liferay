package com.niknis.serviceimpl;

import org.osgi.service.component.annotations.Component;

import com.niknis.service.MyService;

@Component(
	    immediate = true,
	    property = {
	        // TODO enter required service properties
	    },
	    service = MyService.class
	)
	public class MyServiceImpl implements MyService {
	 
	    @Override
	    public int add(int number1, int number2) {
	        System.err.println("***************MyServiceImpl add(): Calling add() method***************");
	        return number1 + number2;
	    }
	}