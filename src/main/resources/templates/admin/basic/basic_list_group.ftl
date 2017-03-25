<div class="list-group col-md-3" >
    <a href="/admin/basic/education" class="list-group-item ${ ((basic_item!"")=="education")?string("active", "") }">
        <@spring.message code="education_index" />
    </a>
    <a href="/admin/basic/nature" class="list-group-item ${ ((basic_item!"")=="nature")?string("active", "") }">
        <@spring.message code="nature_index" />
    </a>
    <a href="/admin/basic/product" class="list-group-item ${ ((basic_item!"")=="product")?string("active", "") }">
        <@spring.message code="product_index" />
    </a>
    <a href="/admin/basic/project" class="list-group-item ${ ((basic_item!"")=="project")?string("active", "") }">
        <@spring.message code="project_index" />
    </a>
    <a href="/admin/basic/source" class="list-group-item ${ ((basic_item!"")=="source")?string("active", "") }">
        <@spring.message code="source_index" />
    </a>
    <a href="/admin/basic/type" class="list-group-item ${ ((basic_item!"")=="type")?string("active", "") }">
    <@spring.message code="type_index" />
    </a>
</div>