package com.autofield.conf;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

public class ConfigurationActionImpl implements ConfigurationAction {
	 
    @Override
    public void processAction(PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception {
 
        String portletResource = ParamUtil.getString(actionRequest,"portletResource");
 
       
        String fieldIndexes= actionRequest.getParameter("fieldIndexes"); 
        String[] indexOfRows = fieldIndexes.split(",");
        String roleNameIds = "";
        for (int i = 0; i < indexOfRows.length; i++) {
              long role = ParamUtil.getLong(actionRequest, "role" + indexOfRows[i]);
              String name = ParamUtil.getString(actionRequest, "name" + indexOfRows[i]).trim();
              if(role > 0 && Validator.isNotNull(name)){
                  System.out.println("userId"+role);
                  System.out.println("role"+name);
                  if("".equals(roleNameIds)){
                      roleNameIds = role+"@@"+name;
                  }else{
                      roleNameIds = roleNameIds+";"+role+"@@"+name;
                  }
              }
              //we can get values by specifying name of field and fieldIndex                     
           } 
 
        PortletPreferences prefs = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
 
       prefs.setValue("roleNameIds", roleNameIds);
 
        prefs.store();
 
        SessionMessages.add(actionRequest, "config-stored");
 
        SessionMessages.add(actionRequest,portletConfig.getPortletName() + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,portletResource);
 
    }
 
    @Override
    public String render(PortletConfig portletConfig, RenderRequest renderRequest,
            RenderResponse renderResponse) throws Exception {
       
        PortletURL url = renderResponse.createRenderURL();
        return "/html/configuration.jsp";
    }
}