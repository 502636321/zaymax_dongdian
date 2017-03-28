<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />
    <#include "../../public/script.ftl" />
</head>

<body>
<#assign basic_item="employer" />
<#include "../basic_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
        <@spring.message code="employer_action_save" />
        </div>
        <div class="panel-body">
        <#include "../../public/ALERT_MESSAGE.ftl" />
            <form class="form-horizontal" action="/admin/basic/employer/save" method="post" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-10">
                        <input employer="text" class="form-control" id="name" name="name" value="${ (employer.name)!"" }" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="description" name="description" rows="5" >${ (employer.description)!"" }</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="/admin/basic/employer">返回</a>
                        <button employer="submit" class="btn btn-primary pull-right">确定</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
