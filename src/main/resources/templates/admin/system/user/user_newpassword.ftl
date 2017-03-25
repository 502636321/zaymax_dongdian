<!DOCTYPE html>
<html lang="en">
<head>
<#include "../../public/meta.ftl" />
<#include "../../public/stylesheet.ftl" />
<#include "../../public/script.ftl" />
    <script>
        $(function () {
            $('form[action="/admin/system/user/newpassword/${ (user.id)!"" }"]').validate({
                rules: {
                    'password': {
                        required : true,
                        rangelength: [6, 12]
                    },
                    'repassword': {
                        equalTo: '#password'
                    }
                },
                messages: {
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
        <@spring.message code="user_action_save" />
        </div>
        <div class="panel-body">
        <#include "../../public/ALERT_MESSAGE.ftl" />
            <form class="form-horizontal" action="/admin/system/user/newpassword/${ (user.id)!"" }" method="post" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label"><@spring.message code="user_attribute_new_password" /></label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="password" name="password" >
                    </div>
                    <label for="repassword" class="col-md-2 control-label"><@spring.message code="user_attribute_new_repassword" /></label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="repassword" name="repassword" >
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
