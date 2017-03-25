<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <li><a href="#"><@spring.message code="index" /> <span class="sr-only">(current)</span></a></li>
        <li class="${ ((sidebar!"")=="work_order")?string("active", "") }" ><a href="/admin/work_order"><@spring.message code="index_work_order" /></a></li>
        <li class="${ ((sidebar!"")=="engineer")?string("active", "") }" ><a href="/admin/engineer"><@spring.message code="index_engineer" /></a></li>
        <li><a href="#"><@spring.message code="index_report" /></a></li>
    </ul>
    <ul class="nav nav-sidebar">
        <li class="${ ((sidebar!"")=="properties")?string("active", "") }" ><a href="/admin/properties"><@spring.message code="system_properties" /></a></li>
        <li class="${ ((sidebar!"")=="basic")?string("active", "") }" ><a href="/admin/basic"><@spring.message code="basic_configuration" /></a></li>
        <li class="${ ((sidebar!"")=="system")?string("active", "") }" ><a href="/admin/system"><@spring.message code="system_management" /></a></li>
    </ul>
</div>