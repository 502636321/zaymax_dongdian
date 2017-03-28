<div class="list-group col-md-3" >
    <a href="/admin/basic/country" class="list-group-item ${ ((basic_item!"")=="country")?string("active", "") }">
        <@spring.message code="country_index" /> ${ (countryPage?exists)?string("[ " + ((countryPage.totalElements)!0) + " ]", "") }
    </a>

    <a href="/admin/basic/employer" class="list-group-item ${ ((basic_item!"")=="employer")?string("active", "") }">
        <@spring.message code="employer_index" /> ${ (employerPage?exists)?string("[ " + ((employerPage.totalElements)!0) + " ]", "") }
    </a>
</div>