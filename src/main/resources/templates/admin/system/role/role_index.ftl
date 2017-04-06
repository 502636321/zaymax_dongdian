<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />
    <#include "../../public/script.ftl" />
</head>

<body>
<#assign system_item="role" />
<#include "../system_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <div class="panel-heading clearfix">
            <a class="btn btn-primary pull-right" href="/admin/system/role/save" data-toggle="tooltip"
               data-placement="bottom" title="<@spring.message code="button_add" />">
                <i class="glyphicon glyphicon-plus"></i>
            </a>
            <form method="post" action="/admin/system/role">
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="input-group pull-left col-md-3 col-xs-4">
                    <input type="text" class="form-control" name="name" value="${ (role.name)!"" }"
                           placeholder="<@spring.message code="input_search" />">
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
                <th class="col-md-2">修改人</th>
                <th class="col-md-3">修改时间</th>
                <th class="col-md-2"></th>
            </tr>
            </thead>
            <tbody>
            <#list rolePage.content as role >
            <tr>
                <td><a href="/admin/system/role/show/${ (role.id)!"" }">${ (role.name)!"" }</a></td>
                <td></td>
                <td>${ ((role.lastModifiedDate)!"")?string("yyyy-MM-dd HH:mm") }</td>
                <td>
                    <a href="/admin/system/role/edit/${ (role.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_edit" />" >
                        <i class="glyphicon glyphicon-edit" ></i>
                    </a>
                    <a href="/admin/system/role/authority/${ (role.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_assign_authority" />" >
                        <i class="glyphicon glyphicon-screenshot"></i>
                    </a>
                    <a href="/admin/system/role/delete/${ (role.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_delete" />" onclick="return window.confirm('<@spring.message code="role_action_delete_confirm" arguments="${ (role.name)!\"\" }" />')" >
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <#import "../../public/pageable.ftl" as pageable />
    <@pageable.pageable data=rolePage url="/admin/system/role" />
    </div>

</body>
</html>
