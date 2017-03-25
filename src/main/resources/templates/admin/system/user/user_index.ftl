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
        <div class="panel-heading">
            <@spring.message code="user_index" />(${ (userPage.totalElements)!0 })
            <a class="pull-right" href="/admin/system/user/save" >
                <i class="glyphicon glyphicon-plus"></i>
            </a>
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
                    <a href="/admin/system/user/edit/${ (user.id)!"" }" >
                        <i class="glyphicon glyphicon-edit" ></i>
                    </a>
                    <a href="/admin/system/user/locked/${ (user.id)!"" }" >
                        <i class="glyphicon glyphicon-lock"></i>
                    </a>
                    <a href="/admin/system/user/assign/${ (user.id)!"" }" >
                        <i class="glyphicon glyphicon-eye-open"></i>
                    </a>
                    <a href="/admin/system/user/newpassword/${ (user.id)!"" }" >
                        <i class="glyphicon glyphicon-retweet"></i>
                    </a>
                    <a href="/admin/system/user/delete/${ (user.id)!"" }" onclick="return window.confirm('<@spring.message code="user_action_delete_confirm" arguments="${ (user.name)!\"\" }" />')" >
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
