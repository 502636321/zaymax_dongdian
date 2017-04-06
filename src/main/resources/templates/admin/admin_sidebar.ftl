<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="${ ((sidebar!"")=="expatriate")?string("active", "") }" ><a href="/"><@spring.message code="expatriate_index" /> ${ (expatriatePage?exists)?string("[ " + ((expatriatePage.totalElements)!0) + " ]", "") }</a></li>
    </ul>
    <ul class="nav nav-sidebar">
        <@security.authorize  access="hasRole('ROLE_BasicController$indexBasic')">
            <li class="${ ((sidebar!"")=="basic")?string("active", "") }" ><a href="/admin/basic"><@spring.message code="basic_configuration" /></a></li>
        </@security.authorize>
        <@security.authorize  access="hasRole('ROLE_SystemController$indexBasic')">
            <li class="${ ((sidebar!"")=="system")?string("active", "") }" ><a href="/admin/system"><@spring.message code="system_management" /></a></li>
        </@security.authorize>
        <@security.authorize  access="hasRole('ROLE_PropertiesController$indexProperties')">
            <li class="${ ((sidebar!"")=="properties")?string("active", "") }" ><a href="/admin/properties"><@spring.message code="system_properties" /></a></li>
        </@security.authorize>
    </ul>
</div>