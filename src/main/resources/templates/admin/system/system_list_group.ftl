<div class="list-group col-md-3" >
    <a href="/admin/system/user" class="list-group-item ${ ((system_item!"")=="user")?string("active", "") }">
        <@spring.message code="user_index" /> ${ (userPage?exists)?string("[ " + ((userPage.totalElements)!0) + " ]", "") }
    </a>
    <a href="/admin/system/role" class="list-group-item ${ ((system_item!"")=="role")?string("active", "") }">
        <@spring.message code="role_index" /> ${ (rolePage?exists)?string("[ " + ((rolePage.totalElements)!0) + " ]", "") }
    </a>
    <a href="/admin/system/authority" class="list-group-item ${ ((system_item!"")=="authority")?string("active", "") }">
        <@spring.message code="authority_index" /> ${ (authorityPage?exists)?string("[ " + ((authorityPage.totalElements)!0) + " ]", "") }
    </a>
</div>