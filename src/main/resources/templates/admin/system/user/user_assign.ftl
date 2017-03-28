<#assign spring=JspTaglibs["http://www.springframework.org/tags"] />
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />

    <#include "../../public/script.ftl" />
    <script>
        $(function () {
            $('#search').multiselect({
                search: {
                    left: '<input country="text" name="q" class="form-control" placeholder="Search..." />',
                    right: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
                },
                fireSearch: function(value) {
                    return value.length > 1;
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
        <@spring.message code="user_action_assign" />
        </div>
        <div class="panel-body">
        <#include "../../public/ALERT_MESSAGE.ftl" />
            <form class="form-horizontal" action="/admin/system/user/assign/${ (user.id)!"" }" method="post" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="form-group">
                    <div class="col-md-10 col-md-offset-1">
                        <div class="col-md-5">
                            <select name="from" id="search" class="form-control" size="8" multiple="multiple">
                                <#list roles as role >
                                    <option value="${ (role.id)!"" }">${ (role.name)!"" }</option>
                                </#list>
                            </select>
                        </div>

                        <div class="col-md-2">
                            <button type="button" id="search_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                            <button type="button" id="search_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                            <button type="button" id="search_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                            <button type="button" id="search_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                        </div>

                        <div class="col-md-5">
                            <select name="to" id="search_to" class="form-control" size="8" multiple="multiple">
                                <#list userRoles as userRole >
                                    <option value="${ (userRole.role.id)!"" }">${ (userRole.role.name)!"" }</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-1 col-md-10">
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
