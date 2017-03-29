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
                <div class="table-responsive" style="white-space: nowrap;" >
                <table class="table table-bordered" fixed-header>
                    <thead>
                    <tr>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_number" /></th>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_name" /></th>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_gender" /></th>
                        <th class="col-md-2" rowspan="2"><@spring.message code="expatriate_attribute_card_no" /></th>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_country" /></th>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_expatriate_date" /></th>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_contract_period" /></th>
                        <th class="col-md-1" rowspan="2"><@spring.message code="expatriate_attribute_employer" /></th>
                        <th class="col-md-1" colspan="5" ><@spring.message code="social_insurance_index" /></th>
                        <th class="col-md-1" colspan="3"><@spring.message code="commercial_insurance_index" /></th>
                        <th class="col-md-1" colspan="2"><@spring.message code="wages_index" /></th>
                        <th class="col-md-1" colspan="5"><@spring.message code="settlement_index" /></th>
                        <th class="col-md-2" rowspan="2"></th>
                    </tr>
                    <tr>
                        <th><@spring.message code="social_insurance_attribute_insurance_date" /></th>
                        <th><@spring.message code="social_insurance_attribute_personal_code" /></th>
                        <th><@spring.message code="social_insurance_attribute_radices" /></th>
                        <th><@spring.message code="social_insurance_attribute_company_radices" /></th>
                        <th><@spring.message code="social_insurance_attribute_personal_radices" /></th>

                        <th><@spring.message code="commercial_insurance_attribute_premium" /></th>
                        <th><@spring.message code="commercial_insurance_attribute_paid" /></th>
                        <th><@spring.message code="commercial_insurance_attribute_period" /></th>

                        <th><@spring.message code="wages_attribute_settlement_date" /></th>
                        <th><@spring.message code="wages_attribute_amount" /></th>

                        <th><@spring.message code="social_insurance_index" /></th>
                        <th><@spring.message code="commercial_insurance_index" /></th>
                        <th><@spring.message code="wages_index" /></th>
                        <th><@spring.message code="manage_cost_index" /></th>
                        <th><@spring.message code="service_cost_index" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list expatriatePage.content as expatriate >
                    <tr>
                        <td><a href="/show/${ (expatriate.id)!"" }">${ (expatriate.number)!"" }</a></td>
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
                        <td>${ (expatriate.commercialInsurance.startPeriod?string("yyyy-年MM月dd日"))!"" }-${ (expatriate.commercialInsurance.endPeriod?string("yyyy年MM月dd日"))!"" }</td>
                        <#--工资-->
                        <td>${ ((expatriate.wages.amount)!0)?string("#") }</td>
                        <td>${ (expatriate.wages.paymentDate?string("yyyy-MM-dd"))!"" }</td>
                        <#--费用结算-->
                        <td>
                            <#if (expatriate.socialInsurance.settlementState)?exists >
                                <@spring.message arguments="${ ((expatriate.socialInsurance.settlementDate)?string('yyyy-MM-dd'))!'' }" code="${ ((expatriate.socialInsurance.settlementState.ordinal()==0)!false)?string(expatriate.socialInsurance.settlementState, 'CfgSettlementState.By')}" />
                            </#if>
                        </td>
                        <td>
                            <#if (expatriate.commercialInsurance.settlementState)?exists >
                                <@spring.message arguments="${ ((expatriate.commercialInsurance.settlementDate)?string('yyyy-MM-dd'))!'' }" code="${ ((expatriate.commercialInsurance.settlementState.ordinal()==0)!false)?string(expatriate.commercialInsurance.settlementState, 'CfgSettlementState.By')}" />
                            </#if>
                        </td>
                        <td>
                            <#if (expatriate.wages.settlementState)?exists >
                                <@spring.message arguments="${ ((expatriate.wages.settlementDate)?string('yyyy-MM-dd'))!'' }" code="${ ((expatriate.wages.settlementState.ordinal()==0)!false)?string(expatriate.wages.settlementState, 'CfgSettlementState.By')}" />
                            </#if>
                        </td>
                        <td>
                            <#if (expatriate.manageCost.settlementState)?exists >
                                <@spring.message arguments="${ ((expatriate.manageCost.settlementDate)?string('yyyy-MM-dd'))!'' }" code="${ ((expatriate.manageCost.settlementState.ordinal()==0)!false)?string(expatriate.manageCost.settlementState, 'CfgSettlementState.By')}" />
                            </#if>
                        </td>
                        <td>
                            <#if (expatriate.serviceCost.settlementState)?exists >
                                <@spring.message arguments="${ ((expatriate.serviceCost.settlementDate)?string('yyyy-MM-dd'))!'' }" code="${ ((expatriate.serviceCost.settlementState.ordinal()==0)!false)?string(expatriate.serviceCost.settlementState, 'CfgSettlementState.By')}" />
                            </#if>
                        </td>
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
            </div>
            <#import "../public/pageable.ftl" as pageable />
            <@pageable.pageable data=expatriatePage url="/?1=1" />
        </div>
    </div>
</body>
</html>
