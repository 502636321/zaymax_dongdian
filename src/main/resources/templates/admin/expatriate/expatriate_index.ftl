<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <#include "../public/script.ftl" />
</head>

<body class="main-body">

<#include "../public/navbar_header.ftl" />

<div class="container-fluid">
    <div class="row">
    <#assign sidebar="expatriate" />
    <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading clearfix">
                    <a class="pull-right btn btn-primary" href="/save" >
                        <i class="glyphicon glyphicon-plus"></i><@spring.message code="button_add" />
                    </a>
                    <form method="post" action="/admin/basic/country" >
                        <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                        <div class="input-group pull-left col-md-3 col-xs-4">
                            <input type="text" class="form-control" name="name" value="${ (expatriate.name)!"" }" placeholder="<@spring.message code="input_search" />">
                            <div class="input-group-btn">
                                <button class="btn btn-primary"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-body">
                    <#include "../public/ALERT_MESSAGE.ftl" />
                </div>

                <!-- Table -->
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_number" /></th>
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_name" /></th>
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_gender" /></th>
                        <th class="col-md-2"><@spring.message code="expatriate_attribute_card_no" /></th>
                        <th class="col-md-2"><@spring.message code="domain_attribute_last_modified_by" /></th>
                        <th class="col-md-3"><@spring.message code="domain_attribute_last_modified_date" /></th>
                        <th class="col-md-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list expatriatePage.content as expatriate >
                    <tr>
                        <td><a href="/show/${ (expatriate.number)!"" }">${ (expatriate.number)!"" }</a></td>
                        <td>${ (expatriate.name)!"" }</td>
                        <td><@spring.message code="${ (expatriate.gender)!'blank' }" /></td>
                        <td>${ (expatriate.cardNO)!"" }</td>
                        <td></td>
                        <td>${ (expatriate.lastModifiedDate)!"" }</td>
                        <td>
                            <a href="/edit/${ (expatriate.id)!"" }" >
                                <i class="glyphicon glyphicon-edit" ></i><@spring.message code="button_edit" />
                            </a>
                            <a href="/delete/${ (expatriate.id)!"" }" onclick="return window.confirm('<@spring.message code="employer_action_delete_confirm" arguments="${ (expatriate.name)!\"\" }" />')" >
                                <i class="glyphicon glyphicon-remove"></i><@spring.message code="button_delete" />
                            </a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
            <#import "../public/pageable.ftl" as pageable />
            <@pageable.pageable data=expatriatePage url="/?1=1" />
        </div>
    </div>
</body>
</html>
