<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />
    <#include "../../public/script.ftl" />
</head>

<body>
<#assign basic_item="employer" />
<#include "../basic_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <div class="panel-heading clearfix">
            <a class="pull-right btn btn-primary" href="/admin/basic/employer/save" >
                <i class="glyphicon glyphicon-plus"></i><@spring.message code="button_add" />
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
                <th class="col-md-2">名称</th>
                <th class="col-md-2"><@spring.message code="domain_attribute_last_modified_by" /></th>
                <th class="col-md-3"><@spring.message code="domain_attribute_last_modified_date" /></th>
                <th class="col-md-2"></th>
            </tr>
            </thead>
            <tbody>
            <#list employerPage.content as employer >
            <tr>
                <td><a href="/admin/basic/employer/show/${ (employer.id)!"" }">${ (employer.name)!"" }</a></td>
                <td>${ ((employer.lastModifiedBy.name)!(employer.lastModifiedBy.username))!"" }</td>
                <td>${ (employer.lastModifiedDate)!"" }</td>
                <td>
                    <a href="/admin/basic/employer/edit/${ (employer.id)!"" }" >
                        <i class="glyphicon glyphicon-edit" ></i><@spring.message code="button_edit" />
                    </a>
                    <a href="/admin/basic/employer/delete/${ (employer.id)!"" }" onclick="return window.confirm('<@spring.message code="employer_action_delete_confirm" arguments="${ (employer.name)!\"\" }" />')" >
                        <i class="glyphicon glyphicon-remove"></i><@spring.message code="button_delete" />
                    </a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <#import "../../public/pageable.ftl" as pageable />
    <@pageable.pageable data=employerPage url="/admin/basic/employer" />
</div>
</html>
