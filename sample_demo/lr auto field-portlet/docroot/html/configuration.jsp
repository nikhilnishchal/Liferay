<%@include file="/html/init.jsp" %>
<liferay-portlet:actionURL var="configurationURL" portletConfiguration="true" />

<liferay-ui:success key="config-stored" message="Configuration Saved Successfully" />

<%
                                String roleNameIds = portletPreferences.getValue("roleNameIds", "");
%>

<aui:form name="fm" method="post" action="<%=configurationURL.toString()%>">
<aui:field-wrapper >
    <div id="dynamicFields">
        <% boolean isEdit = true;
            if(Validator.isNotNull(roleNameIds)){
                String[] roleNameIdsArr = roleNameIds.split(";");
                int memberCount = 0;
                for(String roleNameId :roleNameIdsArr){
                    if(Validator.isNotNull(roleNameId)){
                        String[] roleName = roleNameId.split("@@");
                        if(roleName != null && roleName.length == 2){
                            isEdit = false;
                            %>
                                     <div class="lfr-form-row lfr-form-row-inline"> 
                                          <div class="row-fields"> 
                                          <aui:input id='<%= "role" + memberCount %>' fieldParam='<%= "role" + memberCount %>' name='<%= "role" + memberCount %>' label="Role" placeholder="Enter Role" value="<%=roleName[0] %>"></aui:input>
                                          <aui:input id='<%= "name" + memberCount %>' name='<%= "name" + memberCount %>' fieldParam='<%= "name" + memberCount %>' label="Name" placeholder="Enter Name" value="<%=roleName[1] %>"></aui:input> 
                                          </div> 
                                     </div> 
                            <%
                            memberCount = memberCount +1 ;
                        }
                    }
                }
            }
            if(isEdit){
        %>
         <div class="lfr-form-row lfr-form-row-inline"> 
              <div class="row-fields"> 
                  <aui:input id="role0" name="role0" label="Role" placeholder="Enter Role" ></aui:input>
                  <aui:input id="name0" name="name0" label="Name" placeholder="Enter Name" ></aui:input> 
              </div> 
         </div> 
         <%} %>
     </div>
 </aui:field-wrapper>   
    <aui:button type="submit" name="Save" />
</aui:form>

<aui:script use="liferay-auto-fields"> 
   new Liferay.AutoFields({ 
    contentBox: '#dynamicFields', 
    fieldIndexes: '<portlet:namespace />fieldIndexes' 
    } 
   ).render(); 
 </aui:script>
