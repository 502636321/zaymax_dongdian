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
        <!-- Default panel contents -->
        <div class="panel-heading">
        <@spring.message code="authority_action_show" />
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" value="${ (authority.name)!"" }" disabled="disabled" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="describe" class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="describe" name="describe" rows="5" disabled="disabled" >${ (authority.description)!"" }</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="/admin/system/authority">返回</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
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
