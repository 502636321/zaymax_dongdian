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

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="/bootstrap-3.3.7/docs/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<#assign system_item="authority" />
<#include "../system_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <div class="panel-heading">
        <@spring.message code="authority_index" />
            <a class="pull-right" href="/admin/system/authority/save" >
                <i class="glyphicon glyphicon-plus"></i>
            </a>
        </div>
        <div class="panel-body">
        <#include "../../public/ALERT_MESSAGE.ftl" />
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="col-md-2">名称</th>
                <th class="col-md-2">修改人</th>
                <th class="col-md-3">修改时间</th>
                <th class="col-md-2"></th>
            </tr>
            </thead>
            <tbody>
            <#list authorityPage.content as authority >
            <tr>
                <td><a href="/admin/system/authority/show/${ (authority.id)!"" }"><@spring.message code='${ (authority.authority)!"blank" }' /></a></td>
                <td></td>
                <td>${ ((authority.lastModifiedDate)!"")?string("yyyy-MM-dd HH:mm") }</td>
                <td>
                    <a href="/admin/system/authority/edit/${ (authority.id)!"" }" >
                        <i class="glyphicon glyphicon-edit" ></i>
                    </a>
                    <a href="/admin/system/authority/delete/${ (authority.id)!"" }" onclick="return window.confirm('<@spring.message code="authority_action_delete_confirm" arguments="${ (authority.name)!\"\" }" />')" >
                        <i class="glyphicon glyphicon-remove"></i>
                    </a>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <#import "../../public/pageable.ftl" as pageable />
    <@pageable.pageable data=authorityPage url="/admin/system/authority" />
    </div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="/bootstrap-3.3.7/docs/assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
