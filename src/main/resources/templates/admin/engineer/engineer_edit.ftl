<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <!-- Custom styles for this template -->
    <link href="/css/dashboard.css" rel="stylesheet">
    <link href="/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <#include "../public/script.ftl" />
    <script>
        $(function () {
            $('form[action="/admin/engineer/edit/${ (engineer.id)!"" }"]').validate({
                rules: {
                    'name': {
                        required : true
                    }
                },
                messages: {
                    'name': {
                        required: '<@spring.message code="engineer_attribute_name_rule_required" />',
                    }
                }
            });
        });
    </script>
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
                <#include "../public/ALERT_MESSAGE.ftl" />
                    <form class="form-horizontal col-md-offset-1 col-md-10" action="/admin/engineer/edit/${ (engineer.id)!"" }" method="post" enctype="multipart/form-data" >
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label"><@spring.message code="engineer_attribute_name" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="name" name="name" value="${ (engineer.name)!"" }" >
                            </div>
                            <label for="birthdate" class="col-md-2 control-label"><@spring.message code="engineer_attribute_birthdate" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control form_datetime" id="birthdate" name="birthdate" readonly value="${ ((engineer.birthdate)?string("yyyy-MM-dd"))!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobile" class="col-md-2 control-label"><@spring.message code="engineer_attribute_mobile" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="mobile" name="mobile" value="${ (engineer.mobile)!"" }" >
                            </div>
                            <label for="email" class="col-md-2 control-label"><@spring.message code="engineer_attribute_email" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="email" name="email" value="${ (engineer.email)!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-md-2 control-label"><@spring.message code="engineer_attribute_education" /></label>
                            <div class="col-md-3">
                                <select class="form-control" name="education.id" >
                                    <option ><@spring.message code="select_option_default" /></option>
                                    <#list educations as education >
                                    <option value="${ (education.id)!"" }" ${ (((engineer.education.id)!"")==((education.id)!""))?string("selected=\"selected\"", "") } >${ (education.name)!"" }</option>
                                    </#list>
                                </select>
                            </div>
                            <label for="educationPathFile" class="col-md-2 control-label"><@spring.message code="engineer_attribute_education_path" /></label>
                            <div class="col-md-3">
                                <input type="file" class="form-control" id="educationPathFile" name="educationPathFile" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cardNo" class="col-md-2 control-label"><@spring.message code="engineer_attribute_card_no" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="cardNo" name="cardNo">
                            </div>
                            <label for="cardPathFile" class="col-md-2 control-label"><@spring.message code="engineer_attribute_card_path" /></label>
                            <div class="col-md-3">
                                <input type="file" class="form-control" id="cardPathFile" name="cardPathFile">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <button type="submit" class="btn btn-primary pull-right" >确定</button>
                                <a type="button" class="btn btn-default" href="/admin/engineer" >返回</a>
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
