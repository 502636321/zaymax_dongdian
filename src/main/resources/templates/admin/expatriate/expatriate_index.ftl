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
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_country" /></th>
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_expatriate_date" /></th>
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_contract_period" /></th>
                        <th class="col-md-1"><@spring.message code="expatriate_attribute_employer" /></th>
                        <th class="col-md-1" colspan="5"><@spring.message code="social_insurance_index" /></th>
                        <th class="col-md-1" colspan="3"><@spring.message code="commercial_insurance_index" /></th>
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
                        <td>${ (expatriate.country.name)!"" }</td>
                        <td>${ (expatriate.expatriateDate?string("yyyy-MM-dd"))!"" }</td>
                        <td>${ (expatriate.contractPeriod)!0 }</td>
                        <td>${ (expatriate.employer.name)!"" }</td>
                        <#--社会保险-->
                        <td>${ (expatriate.socialInsurance.insuranceDate?string("yyyy-MM-dd"))!"" }</td>
                        <td>${ (expatriate.socialInsurance.personalCode)!"" }</td>
                        <td>${ ((expatriate.socialInsurance.radices)!0)?string("#") }</td>
                        <td>${ ((expatriate.socialInsurance.companyRadices)!0)?string("#") }</td>
                        <td>${ ((expatriate.socialInsurance.personalRadices)!0)?string("#") }</td>
                        <#--商业保险-->
                        <td>${ ((expatriate.commercialInsurance.premium)!0)?string("#") }</td>
                        <td>${ ((expatriate.commercialInsurance.paid)!0)?string("#") }</td>
                        <td>${ (expatriate.commercialInsurance.startPeriod?string("yyyy-MM-dd"))!"" }-${ (expatriate.commercialInsurance.endPeriod?string("yyyy-MM-dd"))!"" }</td>
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
