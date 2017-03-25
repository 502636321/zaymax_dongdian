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
        <#assign sidebar="engineer" />
        <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                    <form class="form-horizontal" action="/admin/engineer" method="post" >
                        <div class="form-group">
                            <label for="name" class="col-md-1 control-label"><@spring.message code="engineer_attribute_name" /></label>
                            <div class="col-md-2">
                                <input type="text" class="form-control" name="name" value="${ (engineer.name)!"" }" >
                            </div>
                            <label for="education.id" class="col-md-1 control-label"><@spring.message code="engineer_attribute_education" /></label>
                            <div class="col-md-2">
                                <select class="form-control" name="education.id" >
                                    <option value="" ><@spring.message code="select_option_default" /></option>
                                    <#list educations as education >
                                    <option value="${ (education.id)!"" }" ${ (((engineer.education.id)!"")==((education.id)!""))?string("selected=\"selected\"", "") } >${ (education.name)!"" }</option>
                                    </#list>
                                </select>
                            </div>
                            <label for="start$birthdate" class="col-md-1 control-label"><@spring.message code="engineer_attribute_start$birthdate" /></label>
                            <div class="col-md-2" >
                                <input type="text" class="form-control form_datetime" name="start$birthdate" value="${ ((engineer.start$birthdate)?string("yyyy-MM-dd"))!"" }" >
                                <span class="add-on"><i class="icon-remove"></i></span>
                            </div>
                            <label for="end$birthdate" class="col-md-1 control-label"><@spring.message code="engineer_attribute_end$birthdate" /></label>
                            <div class="col-md-2" >
                                <input type="text" class="form-control form_datetime" name="end$birthdate" value="${ ((engineer.end$birthdate)?string("yyyy-MM-dd"))!"" }" >
                                <span class="add-on"><i class="icon-remove"></i></span>
                            </div>
                            <label for="education.id" class="col-md-1 control-label"><@spring.message code="engineer_attribute_education" /></label>
                            <div class="col-md-2">
                                <select class="form-control" name="education.id" >
                                    <option value="" ><@spring.message code="select_option_default" /></option>
                                <#list educations as education >
                                    <option value="${ (education.id)!"" }" ${ (((engineer.education.id)!"")==((education.id)!""))?string("selected=\"selected\"", "") } >${ (education.name)!"" }</option>
                                </#list>
                                </select>
                            </div>
                            <label for="start$birthdate" class="col-md-1 control-label"><@spring.message code="engineer_attribute_start$birthdate" /></label>
                            <div class="col-md-2" >
                                <input type="text" class="form-control form_datetime" name="start$birthdate" value="${ ((engineer.start$birthdate)?string("yyyy-MM-dd"))!"" }" >
                                <span class="add-on"><i class="icon-remove"></i></span>
                            </div>
                            <label for="end$birthdate" class="col-md-1 control-label"><@spring.message code="engineer_attribute_end$birthdate" /></label>
                            <div class="col-md-2" >
                                <input type="text" class="form-control form_datetime" name="end$birthdate" value="${ ((engineer.end$birthdate)?string("yyyy-MM-dd"))!"" }" >
                                <span class="add-on"><i class="icon-remove"></i></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary pull-right">查询</button>
                                <a type="button" class="btn btn-success" href="/admin/engineer/save" >增加</a>
                            </div>
                        </div>
                    </form>
                    <#include "../public/ALERT_MESSAGE.ftl" />
                </div>

                <!-- Table -->
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><@spring.message code="engineer_attribute_name" /></th>
                        <th>学历</th>
                        <th>出生日期</th>
                        <th>身份证号码</th>
                        <th>手机号码</th>
                        <th>邮件地址</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list engineerPage.content as engineer >
                    <tr>
                        <td>${ (engineer.name)!"" }</td>
                        <td>${ (engineer.education.name)!"" }</td>
                        <td>${ ((engineer.birthdate)?string("yyyy-MM-dd"))!"" }</td>
                        <td>${ (engineer.cardNo)!"" }</td>
                        <td>${ (engineer.mobile)!"" }</td>
                        <td>${ (engineer.email)!"" }</td>
                        <td>
                            <a href="/admin/engineer/edit/${ (engineer.id)!"" }" >
                                <i class="glyphicon glyphicon-edit" ></i>
                            </a>
                            <a href="/admin/engineer/delete/${ (engineer.id)!"" }" onclick="return window.confirm('<@spring.message code="engineer_action_delete_confirm" arguments="${ (user.name)!\"\" }" />')" >
                                <i class="glyphicon glyphicon-remove"></i>
                            </a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <#import "../public/pageable.ftl" as pageable />
                <@pageable.pageable data=engineerPage url="/admin/engineer" />
        </div>
    </div>
</div>

</body>
</html>
