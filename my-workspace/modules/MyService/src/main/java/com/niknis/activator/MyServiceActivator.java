package com.niknis.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.niknis.service.MyService;
import com.niknis.serviceimpl.MyServiceImpl;

public class MyServiceActivator implements BundleActivator {
 
    private ServiceRegistration registration;
     
    @Override
    public void start(BundleContext context) throws Exception {
 
        registration = context.registerService(MyService.class.getName(), new MyServiceImpl(), null);
        System.out.println("***************MyServiceActivator start(): Service Registered Successfully***************");
    }
 
    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        System.out.println("***************MyServiceActivator stop: Service Unregistered***************");
         
    }
 
}