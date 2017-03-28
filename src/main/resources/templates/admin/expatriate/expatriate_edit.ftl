<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <#include "../public/script.ftl" />
    <script>
        $(function () {
            $('input[name="country.name"]').typeahead({
                source: function (query, process) {
                    return $.get('/admin/basic/auto_complete/country', { name: query }, function (data) {
                        return process(data);
                    });
                },
                autoSelect: true
            });
            $('form[action="/admin/expatriate/edit/${ (expatriate.id)!"" }"]').validate({
                rules: {
                    'name': {
                        required : true
                    }
                },
                messages: {
                    'name': {
                        required: '<@spring.message code="expatriate_attribute_name_rule_required" />',
                    }
                }
            });
        });
    </script>
</head>

<body class="main-body">

<#include "../public/navbar_header.ftl" />

<div class="container-fluid">
    <div class="row">
    <#assign sidebar="expatriate" />
    <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                <#include "../public/ALERT_MESSAGE.ftl" />
                    <form class="form-horizontal col-md-offset-1 col-md-10" action="/edit/${ (expatriate.id)!"" }" method="post" >
                        <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                        <div class="form-group">
                            <label for="number" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_number" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="number" name="number" disabled value="${ (expatriate.number)!"" }" >
                            </div>
                            <label for="name" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_name" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="name" name="name" value="${ (expatriate.name)!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="gender" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_gender" /></label>
                            <div class="col-md-3">
                                <select class="form-control" name="gender" >
                                    <option></option>
                                    <#list genders as gender >
                                        <option value="${ (gender.name()) }" ${ ((gender.ordinal()==expatriate.gender.ordinal())!false)?string("selected=\"selected\"", "") } ><@spring.message code="${ gender }" /></option>
                                    </#list>
                                </select>
                            </div>
                            <label for="cardNO" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_card_no" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="cardNO" name="cardNO" value="${ (expatriate.cardNO)!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passportNO" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_passport_no" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="passportNO" name="passportNO" value="${ (expatriate.passportNO)!"" }" >
                            </div>
                            <label for="contactMobile" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_contact_mobile" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="contactMobile" name="contactMobile" value="${ (expatriate.contactMobile)!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="country.id" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_country" /></label>
                            <div class="col-md-3">
                                <select name="country.id" class="form-control" >
                                    <option><@spring.message code="select_default_option" /></option>
                                    <#list countries as country >
                                        <option value="${ (country.id)!"" }" ${ ((expatriate.country.id==country.id)!false)?string("selected=\"selected\"", "") } >${ (country.name)!"" }</option>
                                    </#list>
                                </select>
                            </div>
                            <label for="expatriateDate" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_expatriate_date" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control form_datetime" id="expatriateDate" name="expatriateDate" readonly value="${ (expatriate.expatriateDate?string("yyyy-MM-dd"))! }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="number" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_contract_period" /></label>
                            <div class="col-md-3">
                                <select name="contractPeriod" class="form-control" >
                                    <option><@spring.message code="select_default_option" /></option>
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
                                <select name="employer.id" class="form-control" >
                                    <option><@spring.message code="select_default_option" /></option>
                                    <#list employers as employer >
                                        <option value="${ (employer.id)!"" }" ${ ((expatriate.employer.id==employer.id)!false)?string("selected=\"selected\"", "") } >${ (employer.name)!"" }</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="employer.id" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_address" /></label>
                            <div class="col-md-8">
                                <textarea name="address" class="form-control">${ ((expatriate.address)!"")?trim }</textarea>
                            </div>
                        </div>
                        <fieldset>
                            <legend>社会保险</legend>
                            <div class="form-group">
                                <label for="insuranceDate" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_insurance_date" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control form_datetime" id="insuranceDate" name="socialInsurance.insuranceDate" readonly value="${ (expatriate.socialInsurance.insuranceDate?string("yyyy-MM-dd"))!"" }" >
                                </div>
                                <label for="personalCode" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_personal_code" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="personalCode" name="socialInsurance.personalCode" value="${ (expatriate.socialInsurance.personalCode)!"" }" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="radices" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_radices" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="radices" name="socialInsurance.radices" value="${ ((expatriate.socialInsurance.radices)!0)?string("#") }" >
                                </div>
                                <label for="companyRadices" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_company_radices" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="companyRadices" name="socialInsurance.companyRadices" value="${ ((expatriate.socialInsurance.companyRadices)!0)?string("#") }" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="personalRadices" class="col-md-2 control-label"><@spring.message code="social_insurance_attribute_personal_radices" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="personalRadices" name="socialInsurance.personalRadices" value="${ ((expatriate.socialInsurance.personalRadices)!0)?string("#") }" >
                                </div>
                                <label for="personalCode" class="col-md-2 control-label"></label>
                                <div class="col-md-3">
                                </div>
                            </div>
                        </fieldset>
                        <fieldset>
                            <legend><@spring.message code="commercial_insurance_index" /></legend>
                            <div class="form-group">
                                <label for="premium" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_premium" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="premium" name="commercialInsurance.premium" value="${ ((expatriate.commercialInsurance.premium)!0)?string("#") }" >
                                </div>
                                <label for="paid" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_paid" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="paid" name="commercialInsurance.paid" value="${ ((expatriate.commercialInsurance.paid)!0)?string("#") }" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="startPeriod" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_start_period" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control form_datetime" id="startPeriod" name="commercialInsurance.startPeriod" readonly value="${ (expatriate.commercialInsurance.startPeriod?string("yyyy-MM-dd"))!"" }" >
                                </div>
                                <label for="endPeriod" class="col-md-2 control-label"><@spring.message code="commercial_insurance_attribute_end_period" /></label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control form_datetime" id="endPeriod" name="commercialInsurance.endPeriod" readonly value="${ (expatriate.commercialInsurance.endPeriod?string("yyyy-MM-dd"))!"" }" >
                                </div>
                            </div>
                        </fieldset>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <button type="submit" class="btn btn-primary pull-right" >确定</button>
                                <a type="button" class="btn btn-default" href="/" ><@spring.message code="button_return" /></a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="table-responsive">

            </div>
        </div>
    </div>
</div>
</body>
</html>
