<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li class="${ ((sidebar!"")=="expatriate")?string("active", "") }" ><a href="/"><@spring.message code="expatriate_index" /> ${ (expatriatePage?exists)?string("[ " + ((expatriatePage.totalElements)!0) + " ]", "") }</a></li>
    </ul>
    <ul class="nav nav-sidebar">
        <li class="${ ((sidebar!"")=="properties")?string("active", "") }" ><a href="/admin/properties"><@spring.message code="system_properties" /></a></li>
        <li class="${ ((sidebar!"")=="basic")?string("active", "") }" ><a href="/admin/basic"><@spring.message code="basic_configuration" /></a></li>
        <li class="${ ((sidebar!"")=="system")?string("active", "") }" ><a href="/admin/system"><@spring.message code="system_management" /></a></li>
    </ul>
</div>