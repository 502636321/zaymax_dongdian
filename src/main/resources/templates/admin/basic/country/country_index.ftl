<#assign spring=JspTaglibs["http://www.springframework.org/tags"] />
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />
    <#include "../../public/script.ftl" />
</head>

<body>
<#assign basic_item="country" />
<#include "../basic_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <div class="panel-heading clearfix" >
            <a class="pull-right btn btn-primary" href="/admin/basic/country/save" data-toggle="tooltip" data-placement="bottom" title="<@spring.message code="button_add" />" >
                <i class="glyphicon glyphicon-plus"></i>
            </a>
            <form method="post" action="/admin/basic/country" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="input-group pull-left col-md-3 col-xs-4">
                        <input type="text" class="form-control" name="name" value="${ (country.name)!"" }" placeholder="<@spring.message code="input_search" />">
                        <div class="input-group-btn">
                            <button class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                </div>
            </form>
        </div>
        <div class="panel-body">
        <#include "../../public/ALERT_MESSAGE.ftl" />
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="col-md-2"><@spring.message code="country_attribute_name" /></th>
                <th class="col-md-2"><@spring.message code="domain_attribute_last_modified_by" /></th>
                <th class="col-md-3"><@spring.message code="domain_attribute_last_modified_date" /></th>
                <th class="col-md-2"></th>
            </tr>
            </thead>
            <tbody>
            <#list countryPage.content as country >
            <tr>
                <td><a href="/admin/basic/country/show/${ (country.id)!"" }">${ (country.name)!"" }</a></td>
                <td>${ ((country.lastModifiedBy.name)!(country.lastModifiedBy.username))!"" }</td>
                <td>${ ((country.lastModifiedDate)!"")?string("yyyy-MM-dd HH:mm") }</td>
                <td>
                    <a href="/admin/basic/country/edit/${ (country.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_edit" />" >
                        <i class="glyphicon glyphicon-edit" ></i>
                    </a>
                    <a href="/admin/basic/country/delete/${ (country.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_delete" />" onclick="return window.confirm('<@spring.message code="country_action_delete_confirm" arguments="${ (country.name)!\"\" }" />')" >
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <#import "../../public/pageable.ftl" as pageable />
    <@pageable.pageable data=countryPage url='/admin/basic/country?name=${ (country.name)!"" }' />
</div>
</html>
