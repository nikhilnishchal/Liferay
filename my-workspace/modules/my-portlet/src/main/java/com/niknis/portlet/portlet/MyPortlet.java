package com.niknis.portlet.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.niknis.service.MyService;

/**
 * @author Nikhil
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=my-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyPortlet extends MVCPortlet {
	

	public MyService getMyServiceLocalService() {
		return _sampleService;
}
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		BundleContext bundleContext = FrameworkUtil.getBundle(MyPortlet.class).getBundleContext();
        
	//	ServiceReference ref = bundleContext.getServiceReference(MyService.class.getName());
		         
		//_sampleService = (MyService)bundleContext.getService(ref);
		         
		int result = _sampleService.add(100, 200);
		System.out.println("MyPortlet doView(): Result of addition- "+result);
		super.doView(renderRequest, renderResponse);
	}
	
	 @Reference
	 private volatile MyService _sampleService;

	
	
}