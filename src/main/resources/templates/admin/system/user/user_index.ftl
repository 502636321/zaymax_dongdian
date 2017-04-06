<!DOCTYPE html>
<html lang="en">
<head>
<#include "../../public/meta.ftl" />
<#include "../../public/stylesheet.ftl" />

<#include "../../public/script.ftl" />
</head>

<body>
<#assign system_item="user" />
<#include "../system_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <div class="panel-heading clearfix">
            <a class="btn btn-primary pull-right" href="/admin/system/user/save" data-toggle="tooltip"
               data-placement="bottom" title="<@spring.message code="button_add" />">
                <i class="glyphicon glyphicon-plus"></i>
            </a>
            <form method="post" action="/admin/system/user">
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="input-group pull-left col-md-3 col-xs-4">
                    <input type="text" class="form-control" name="name" value="${ (user.name)!"" }"
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
                <th class="col-md-2">用户名</th>
                <th class="col-md-2">名称</th>
                <th class="col-md-1">修改人</th>
                <th class="col-md-2">修改时间</th>
                <th class="col-md-2"></th>
            </tr>
            </thead>
            <tbody>
            <#list userPage.content as user >
            <tr ${ (true==((user.locked)!false))?string("class=\"danger\"", "") } >
                <td><a href="/admin/system/user/show/${ (user.id)!"" }">${ (user.username)!"" }</a></td>
                <td>${ (user.name)!"" }</td>
                <td></td>
                <td>${ ((user.lastModifiedDate)!"")?string("yyyy-MM-dd HH:mm") }</td>
                <td>
                    <a href="/admin/system/user/edit/${ (user.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_edit" />" >
                        <i class="glyphicon glyphicon-edit" ></i>
                    </a>
                    <a href="/admin/system/user/locked/${ (user.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_lock" />" >
                        <i class="glyphicon glyphicon-lock"></i>
                    </a>
                    <a href="/admin/system/user/assign/${ (user.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_assign_role" />" >
                        <i class="glyphicon glyphicon-eye-open"></i>
                    </a>
                    <a href="/admin/system/user/newpassword/${ (user.id)!"" }" data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_newpassword" />" >
                        <i class="glyphicon glyphicon-retweet"></i>
                    </a>
                    <a href="/admin/system/user/delete/${ (user.id)!"" }"
                       data-toggle="tooltip" data-placement="top" title="<@spring.message code="button_delete" />"
                       onclick="return window.confirm('<@spring.message code="user_action_delete_confirm" arguments="${ (user.name)!\"\" }" />')" >
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <#import "../../public/pageable.ftl" as pageable />
    <@pageable.pageable data=userPage url="/admin/system/user" />
    </div>
</body>
</html>
