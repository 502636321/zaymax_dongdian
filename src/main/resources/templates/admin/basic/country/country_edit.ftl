<#assign spring=JspTaglibs["http://www.springframework.org/tags"] />
<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />
    <#include "../../public/script.ftl" />
</head>

<body>
<#assign basic_item="country" />
<#include "../basic_list_group.ftl" />
<div class="table-responsive col-md-9">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">
        <@spring.message code="country_action_edit" />
        </div>
        <div class="panel-body">
        <#include "../../public/ALERT_MESSAGE.ftl" />
            <form class="form-horizontal" action="/admin/basic/country/edit/${ (country.id)!"" }" method="post" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">名称</label>
                    <div class="col-sm-10">
                        <input country="text" class="form-control" id="name" name="name" value="${ (country.name)!"" }" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">描述</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" id="description" name="description" rows="5" >${ (country.description)!"" }</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="/admin/basic/country">返回</a>
                        <button country="submit" class="btn btn-primary pull-right">确定</button>
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
