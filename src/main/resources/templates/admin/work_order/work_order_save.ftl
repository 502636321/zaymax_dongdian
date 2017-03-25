<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <!-- Custom styles for this template -->
    <link href="/css/dashboard.css" rel="stylesheet">
    <link href="/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <#include "../public/script.ftl" />
    <script src="/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
        <script src="/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script>
        $(function () {
            $(".form_datetime").datetimepicker({
                format: 'yyyy-mm-dd',
                minView: 'month',
                language: 'zh-CN',
                autoclose: true,
                todayBtn: true
            });
            $.validator.addMethod("isMobile", function(value, element) {
                var length = value.length;
                var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写您的手机号码");

            $('form[action="/admin/work_order/save"]').validate({
                rules: {
                    'name': {
                        required : true
                    },
                    'birthdate': {
                        required : true
                    },
                    'mobile': {
                        required : true,
                        isMobile: true
                    },
                    'email': {
                        required : true,
                        email: true
                    },
                    'education.id': {
                        required : true
                    },
                    'educationPathFile': {
                        required : true,
                        extension: 'png|jpg'
                    },
                    'cardNo': {
                        required : true,
                        rangelength: [14, 16]
                    }
                },
                messages: {
                    'name': {
                        required: '<@spring.message code="work_order_attribute_name_rule_required" />'
                    },
                    'birthdate': {
                        required: '<@spring.message code="work_order_attribute_birthdate_rule_required" />'
                    },
                    'mobile': {
                        required: '<@spring.message code="work_order_attribute_mobile_rule_required" />',
                        isMobile: '<@spring.message code="work_order_attribute_mobile_rule_is_mobile" />'
                    },
                    'email': {
                        required: '<@spring.message code="work_order_attribute_email_rule_required" />',
                        email: '<@spring.message code="work_order_attribute_email_rule_email" />'
                    },
                    'education.id': {
                        required : '<@spring.message code="work_order_attribute_education_rule_required" />'
                    },
                    'educationPathFile': {
                        required : '<@spring.message code="work_order_attribute_education_path_rule_required" />',
                        extension: '<@spring.message code="work_order_attribute_education_path_rule_accept" />'
                    },
                    'cardNo': {
                        required : '<@spring.message code="work_order_attribute_card_no_rule_required" />',
                        rangelength: '<@spring.message code="work_order_attribute_card_no_rule_rangelength" />'
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
        <#assign sidebar="work_order" />
        <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                    <#include "../public/ALERT_MESSAGE.ftl" />
                    <form class="form-horizontal col-md-offset-1 col-md-10" action="/admin/work_order/save" method="post" enctype="multipart/form-data" >
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label"><@spring.message code="work_order_attribute_name" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="name" name="name" value="${ (work_order.name)!"" }" >
                            </div>
                            <label for="birthdate" class="col-md-2 control-label"><@spring.message code="work_order_attribute_birthdate" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control form_datetime" id="birthdate" name="birthdate" readonly value="${ ((work_order.birthdate)?string("yyyy-MM-dd"))!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobile" class="col-md-2 control-label"><@spring.message code="work_order_attribute_mobile" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="mobile" name="mobile" value="${ (work_order.mobile)!"" }" >
                            </div>
                            <label for="email" class="col-md-2 control-label"><@spring.message code="work_order_attribute_email" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="email" name="email" value="${ (work_order.email)!"" }" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-md-2 control-label"><@spring.message code="work_order_attribute_education" /></label>
                            <div class="col-md-3">
                                <select class="form-control" name="education.id" >
                                    <option value="" ><@spring.message code="select_option_default" /></option>
                                    <#list educations as education >
                                        <option value="${ (education.id)!"" }" >${ (education.name)!"" }</option>
                                    </#list>
                                </select>
                            </div>
                            <label for="educationPathFile" class="col-md-2 control-label"><@spring.message code="work_order_attribute_education_path" /></label>
                            <div class="col-md-3">
                                <input type="file" class="form-control" id="educationPathFile" name="educationPathFile" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cardNo" class="col-md-2 control-label"><@spring.message code="work_order_attribute_card_no" /></label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" id="cardNo" name="cardNo">
                            </div>
                            <label for="cardPathFile" class="col-md-2 control-label"><@spring.message code="work_order_attribute_card_path" /></label>
                            <div class="col-md-3">
                                <input type="file" class="form-control" id="cardPathFile" name="cardPathFile">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <button type="submit" class="btn btn-primary pull-right" >确定</button>
                                <a type="button" class="btn btn-default" href="/admin/work_order" >返回</a>
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
