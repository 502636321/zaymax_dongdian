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
                    <form class="form-horizontal col-md-offset-1 col-md-10" action="/edit/${ (expatriate.id)!"" }" method="post" enctype="multipart/form-data" >
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
                                    <option>选择</option>
                                </select>
                            </div>
                            <label for="expatriateDate" class="col-md-2 control-label"><@spring.message code="expatriate_attribute_expatriate_date" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control form_datetime" id="expatriateDate" name="expatriateDate" readonly value="${ (expatriate.expatriateDate?string("yyyy-MM-dd"))! }" >
                            </div>
                        </div>
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
