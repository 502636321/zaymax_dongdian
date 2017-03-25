<!DOCTYPE html>
<html lang="en">
<head>
<#include "../../public/meta.ftl" />
<#include "../../public/stylesheet.ftl" />
<#include "../../public/script.ftl" />
    <script>
        $(function () {
            $('form[action="/admin/system/user/save"]').validate({
                rules: {
                    'username': {
                        required : true,
                        rangelength: [6, 12],
                        remote: '/admin/system/user/exist'
                    },
                    'name': {
                        required : true
                    },
                    'password': {
                        required : true,
                        rangelength: [6, 12]
                    },
                    'repassword': {
                        equalTo: '#password'
                    }
                },
                messages: {
                    'username': {
                        required: '<@spring.message code="user_attribute_username_rule_required" />',
                        rangelength: '<@spring.message code="user_attribute_username_rule_rangelength" />',
                        remote: '<@spring.message code="user_attribute_username_rule_exist" />'
                    },
                    'name': {
                        required: '<@spring.message code="user_attribute_name_rule_required" />',
                    },
                    'password': {
                        required : '<@spring.message code="user_attribute_password_rule_required" />',
                        rangelength: '<@spring.message code="user_attribute_password_rule_rangelength" />'
                    },
                    'repassword': {
                        equalTo: '<@spring.message code="user_attribute_password_rule_equal_to" />'
                    }
                }
            });
        });
    </script>
</head>

<body>
<#assign system_item="user" />
<#include "../system_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
        <@spring.message code="user_action_edit" />
        </div>
        <div class="panel-body">
            <#include "../../public/ALERT_MESSAGE.ftl" />
            <form class="form-horizontal" action="/admin/system/user/edit/${ (user.id)!"" }" method="post" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <input type="hidden" name="id" value="${ (user.id)!"" }" >
                <div class="form-group">
                    <label for="username" class="col-md-2 control-label"><@spring.message code="user_attribute_username" /></label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="username" name="username" value="${ (user.username)!"" }" disabled="disabled" >
                    </div>
                    <label for="name" class="col-md-2 control-label"><@spring.message code="user_attribute_name" /></label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="name" name="name" value="${ (user.name)!"" }" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="description" name="description" rows="5" >${ (user.description)!"" }</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="/admin/system/user">返回</a>
                        <button type="submit" class="btn btn-primary pull-right">确定</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
