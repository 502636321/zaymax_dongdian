<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <#include "../public/script.ftl" />
</head>

<body class="main-body" >

<#include "../public/navbar_header.ftl" />

<div class="container-fluid">
    <div class="row">
    <#assign sidebar="expatriate" />
    <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">&nbsp;</div>
                <div class="panel-body">
                <#include "../public/ALERT_MESSAGE.ftl" />
                    <form class="form-horizontal col-md-offset-1 col-md-10" id="expatriate_show_form" >
                        <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                        <div class="form-group">
                            <label for="number" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_number" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="number" name="number" disabled value="${ (expatriate.number)!"" }" >
                            </div>
                            <label for="name" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_name" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="name" name="name" disabled="disabled" value="${ (expatriate.name)!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="gender" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_gender" /></label>
                            <div class="col-md-3">
                                <select class="form-control" name="gender" disabled="disabled" >
                                    <option value=""></option>
                                <#list genders as gender >
                                    <option value="${ (gender.name()) }" ${ ((gender.ordinal()==expatriate.gender.ordinal())!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ gender }" /></option>
                                </#list>
                                </select>
                            </div>
                            <label for="cardNO" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_card_no" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="cardNO" name="cardNO" value="${ (expatriate.cardNO)!"" }" disabled="disabled" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passportNO" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_passport_no" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="passportNO" name="passportNO" value="${ (expatriate.passportNO)!"" }" disabled="disabled" >
                            </div>
                            <label for="contactMobile" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_contact_mobile" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="contactMobile" name="contactMobile" value="${ (expatriate.contactMobile)!"" }" disabled="disabled" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="country.id" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_country" /></label>
                            <div class="col-md-3">
                                <select name="country.id" class="form-control" disabled="disabled" >
                                    <option value=""><@spring.message code="select_default_option" /></option>
                                <#list countries as country >
                                    <option value="${ (country.id)!"" }" ${ ((expatriate.country.id==country.id)!false)?string("selected=\"selected\"", "") } >${ (country.name)!"" }</option>
                                </#list>
                                </select>
                            </div>
                            <label for="expatriateDate" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_expatriate_date" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="expatriateDate" name="expatriateDate" readonly value="${ (expatriate.expatriateDate?string("yyyy-MM-dd"))! }" disabled="disabled" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="number" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_contract_period" /></label>
                            <div class="col-md-3">
                                <select name="contractPeriod" class="form-control" disabled="disabled" >
                                    <option value="" ><@spring.message code="select_default_option" /></option>
                                <#list 1..60 as i >
                                    <option value="${ i }" ${ ((expatriate.contractPeriod==i)!false)?string("selected=\"selected\"", "") } >${ i }</option>
                                </#list>
                                </select>
                            </div>
                            <label for="name" class="col-md-2 control-label"></label>
                            <div class="col-md-3">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="employer.id" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_employer" /></label>
                            <div class="col-md-8">
                                <select name="employer.id" class="form-control" disabled="disabled" >
                                    <option value=""><@spring.message code="select_default_option" /></option>
                                <#list employers as employer >
                                    <option value="${ (employer.id)!"" }" ${ ((expatriate.employer.id==employer.id)!false)?string("selected=\"selected\"", "") } >${ (employer.name)!"" }</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="employer.id" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_address" /></label>
                            <div class="col-md-8">
                                <textarea name="address" class="form-control" disabled="disabled" >${ ((expatriate.address)!"")?trim }</textarea>
                            </div>
                        </div>
                        <fieldset>
                            <legend><@spring.message code="social_insurance_index" /></legend>
                            <div class="form-group">
                                <label for="insuranceDate" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_insurance_date" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="insuranceDate" name="socialInsurance.insuranceDate" readonly value="${ (expatriate.socialInsurance.insuranceDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                                <label for="personalCode" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_personal_code" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="personalCode" name="socialInsurance.personalCode" value="${ (expatriate.socialInsurance.personalCode)!"" }" disabled="disabled" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="radices" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_radices" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="radices" name="socialInsurance.radices" value="${ ((expatriate.socialInsurance.radices)!0)?string("#") }" disabled="disabled" >
                                </div>
                                <label for="companyRadices" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_company_radices" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="companyRadices" name="socialInsurance.companyRadices" value="${ ((expatriate.socialInsurance.companyRadices)!0)?string("#") }" disabled="disabled" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="personalRadices" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_personal_radices" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="personalRadices" name="socialInsurance.personalRadices" value="${ ((expatriate.socialInsurance.personalRadices)!0)?string("#") }" disabled="disabled" >
                                </div>
                                <label for="personalCode" class="col-md-2 control-label"></label>
                                <div class="col-md-3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="socialInsurance.settlementState" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_settlement_state" /></label>
                                <div class="col-md-3">
                                    <select name="socialInsurance.settlementState" class="form-control" onchange="disabledSettlementDate(this, '.socialInsurance_settlementDate')" disabled="disabled" >
                                        <option value=""></option>
                                    <#list settlementes as settlement >
                                        <option value="${ settlement.name() }" ${ ((expatriate.socialInsurance.settlementState==settlement)!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ settlement!'blank' }" arguments="" /></option>
                                    </#list>
                                    </select>
                                </div>
                                <label for="socialInsurance.settlementDate" class="col-md-2 control-label socialInsurance_settlementDate" style="${ ((expatriate.socialInsurance.settlementState.ordinal()==0)!true)?string("display: none;", "") }"><@spring.message code="social_insurance_attribute_settlement_date" /></label>
                                <div class="col-md-3 socialInsurance_settlementDate" style="${ ((expatriate.socialInsurance.settlementState.ordinal()==0)!true)?string("display: none;", "") }">
                                    <input type="text" class="form-control" id="socialInsurance.settlementDate" name="socialInsurance.settlementDate" readonly value="${ (expatriate.socialInsurance.settlementDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend><@spring.message code="commercial_insurance_index" /></legend>
                            <div class="form-group">
                                <label for="premium" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_premium" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="premium" name="commercialInsurance.premium" value="${ ((expatriate.commercialInsurance.premium)!0)?string("#") }" disabled="disabled" >
                                </div>
                                <label for="paid" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_paid" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="paid" name="commercialInsurance.paid" value="${ ((expatriate.commercialInsurance.paid)!0)?string("#") }" disabled="disabled" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="startPeriod" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_start_period" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control form_datetime" id="startPeriod" name="commercialInsurance.startPeriod" readonly value="${ (expatriate.commercialInsurance.startPeriod?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                                <label for="endPeriod" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_end_period" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control form_datetime" id="endPeriod" name="commercialInsurance.endPeriod" readonly value="${ (expatriate.commercialInsurance.endPeriod?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="commercialInsurance.settlementState" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_settlement_state" /></label>
                                <div class="col-md-3">
                                    <select name="commercialInsurance.settlementState" class="form-control" onchange="disabledSettlementDate(this, '.commercialInsurance_settlementDate')" disabled="disabled" >
                                        <option value=""></option>
                                    <#list settlementes as settlement >
                                        <option value="${ settlement.name() }" ${ ((expatriate.commercialInsurance.settlementState==settlement)!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ settlement!'blank' }" /></option>
                                    </#list>
                                    </select>
                                </div>
                                <label for="commercialInsurance.settlementDate" class="col-md-2 control-label commercialInsurance_settlementDate" style="${ ((expatriate.commercialInsurance.settlementState.ordinal()==0)!true)?string("display: none;", "") }"><@spring.message code="commercial_insurance_attribute_settlement_date" /></label>
                                <div class="col-md-3 commercialInsurance_settlementDate" style="${ ((expatriate.commercialInsurance.settlementState.ordinal()==0)!true)?string("display: none;", "") }">
                                    <input type="text" class="form-control form_datetime" id="commercialInsurance.settlementDate" name="commercialInsurance.settlementDate" readonly value="${ (expatriate.commercialInsurance.settlementDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend><@spring.message code="wages_index" /></legend>
                            <div class="form-group">
                                <label for="wages.amount" class="col-md-2 control-label"><@spring.message code="wages_attribute_amount" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="wages.amount" name="wages.amount" value="${ ((expatriate.wages.amount)!0)?string("#") }" disabled="disabled" >
                                </div>
                                <label for="wages.paymentDate" class="col-md-2 control-label"><@spring.message code="wages_attribute_payment_date" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control form_datetime" id="wages.paymentDate" name="wages.paymentDate" readonly value="${ (expatriate.wages.paymentDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="wages.settlementState" class="col-md-2 control-label"><@spring.message code="wages_attribute_settlement_state" /></label>
                                <div class="col-md-3">
                                    <select name="wages.settlementState" class="form-control" onchange="disabledSettlementDate(this, '.wages_settlementDate')" disabled="disabled" >
                                        <option value=""></option>
                                    <#list settlementes as settlement >
                                        <option value="${ settlement.name() }" ${ ((expatriate.wages.settlementState==settlement)!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ settlement!'blank' }" /></option>
                                    </#list>
                                    </select>
                                </div>
                                <label for="wages.settlementDate" class="col-md-2 control-label wages_settlementDate" style="${ ((expatriate.wages.settlementState.ordinal()==0)!true)?string("display: none;", "") }"><@spring.message code="wages_attribute_settlement_date" /></label>
                                <div class="col-md-3 wages_settlementDate" style="${ ((expatriate.wages.settlementState.ordinal()==0)!true)?string("display: none;", "") }">
                                    <input type="text" class="form-control form_datetime" id="wages.settlementDate" name="wages.settlementDate" readonly value="${ (expatriate.wages.settlementDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend><@spring.message code="manage_cost_index" /></legend>
                            <div class="form-group">
                                <label for="manageCost.settlementState" class="col-md-2 control-label"><@spring.message code="manage_cost_attribute_settlement_state" /></label>
                                <div class="col-md-3">
                                    <select name="manageCost.settlementState" class="form-control" onchange="disabledSettlementDate(this, '.manageCost_settlementDate')" disabled="disabled" >
                                        <option value=""></option>
                                    <#list settlementes as settlement >
                                        <option value="${ settlement.name() }" ${ ((expatriate.manageCost.settlementState==settlement)!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ settlement!'blank' }" /></option>
                                    </#list>
                                    </select>
                                </div>
                                <label for="manageCost.settlementDate" class="col-md-2 control-label manageCost_settlementDate" style="${ ((expatriate.manageCost.settlementState.ordinal()==0)!true)?string("display: none;", "") }"><@spring.message code="manage_cost_attribute_settlement_date" /></label>
                                <div class="col-md-3 manageCost_settlementDate" style="${ ((expatriate.manageCost.settlementState.ordinal()==0)!true)?string("display: none;", "") }">
                                    <input type="text" class="form-control form_datetime" id="manageCost.settlementDate" name="manageCost.settlementDate" readonly value="${ (expatriate.manageCost.settlementDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend><@spring.message code="service_cost_index" /></legend>
                            <div class="form-group">
                                <label for="serviceCost.settlementState" class="col-md-2 control-label"><@spring.message code="service_cost_attribute_settlement_state" /></label>
                                <div class="col-md-3">
                                    <select name="serviceCost.settlementState" class="form-control" onchange="disabledSettlementDate(this, '.serviceCost_settlementDate')" disabled="disabled" >
                                        <option value=""></option>
                                    <#list settlementes as settlement >
                                        <option value="${ settlement.name() }" ${ ((expatriate.serviceCost.settlementState==settlement)!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ settlement!'blank' }" /></option>
                                    </#list>
                                    </select>
                                </div>
                                <label for="manageCost.serviceCost" class="col-md-2 control-label serviceCost_settlementDate" style="${ ((expatriate.serviceCost.settlementState.ordinal()==0)!true)?string("display: none;", "") }"><@spring.message code="service_cost_attribute_settlement_date" /></label>
                                <div class="col-md-3 serviceCost_settlementDate" style="${ ((expatriate.serviceCost.settlementState.ordinal()==0)!true)?string("display: none;", "") }">
                                    <input type="text" class="form-control form_datetime" id="serviceCost.settlementDate" name="serviceCost.settlementDate" readonly value="${ (expatriate.serviceCost.settlementDate?string("yyyy-MM-dd"))!"" }" disabled="disabled" >
                                </div>
                            </div>
                        </fieldset>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <button type="button" class="btn btn-primary pull-right" onclick="$('#expatriate_show_form').printThis()" ><@spring.message code="button_print" /></button>
                                <a type="button" class="btn btn-default" href="/" ><@spring.message code="button_return" /></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
