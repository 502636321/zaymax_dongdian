<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <!-- Custom styles for this template -->
    <link href="/css/dashboard.css" rel="stylesheet">

    <#include "../public/script.ftl" />
</head>

<body>

<#include "../public/navbar_header.ftl" />

<div class="container-fluid">
    <div class="row">
        <#assign sidebar="work_order" />
        <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary pull-right">查询</button>
                                <a type="button" class="btn btn-success" href="/admin/work_order/save" >增加</a>
                            </div>
                        </div>
                    </form>
                    <#include "../public/ALERT_MESSAGE.ftl" />
                </div>

                <!-- Table -->
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>名字</th>
                        <th>学历</th>
                        <th>出生日期</th>
                        <th>身份证号码</th>
                        <th>手机号码</th>
                        <th>邮件地址</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list workOrderPage.content as workOrder >
                    <tr>
                        <td>${ (workOrder.name)!"" }</td>
                        <td>${ (workOrder.education.name)!"" }</td>
                        <td>${ ((workOrder.birthdate)?string("yyyy-MM-dd"))!"" }</td>
                        <td>${ (workOrder.cardNo)!"" }</td>
                        <td>${ (workOrder.mobile)!"" }</td>
                        <td>${ (workOrder.email)!"" }</td>
                        <td>
                            <a href="/admin/work_order/edit/${ (workOrder.id)!"" }" >
                                <i class="glyphicon glyphicon-edit" ></i>
                            </a>
                            <a href="/admin/work_order/delete/${ (workOrder.id)!"" }" onclick="return window.confirm('<@spring.message code="work_order_action_delete_confirm" arguments="${ (user.name)!\"\" }" />')" >
                                <i class="glyphicon glyphicon-remove"></i>
                            </a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <#import "../public/pageable.ftl" as pageable />
                <@pageable.pageable data=workOrderPage url="/admin/work_order" />
        </div>
    </div>
</div>

</body>
</html>
