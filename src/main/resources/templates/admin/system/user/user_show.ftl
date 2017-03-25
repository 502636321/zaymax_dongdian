<#assign spring=JspTaglibs["http://www.springframework.org/tags"] />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard Template for Bootstrap</title>

    <#include "../../public/stylesheet.ftl" />

    <#include "../../public/script.ftl" />
</head>

<body>
<#assign system_item="user" />
<#include "../system_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
        <@spring.message code="user_action_show" />
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" value="${ (user.name)!"" }" disabled="disabled" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="describe" class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="describe" name="describe" rows="5" disabled="disabled" >${ (user.description)!"" }</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="/admin/system/user">返回</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
