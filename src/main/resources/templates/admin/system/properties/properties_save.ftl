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
    <script>
    </script>
</head>

<body>
<#include "../../public/ALERT_MESSAGE.ftl" />
<form class="form-horizontal" action="/admin/properties/save" method="post" >
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
    <fieldset>
        <legend>历史记录</legend>
        <div class="form-group">
            <label for="wxAppId" class="col-md-2 control-label">更新时间</label>
            <div class="col-md-8">
                <select class="form-control" onchange="window.location.href='/admin/properties/save?id=' + this.value">
                    <#list historyProperties as properties >
                        <option value="${ (properties.id)!"" }" ${ (properties.id==.globals.properties.id)?string("selected=\"selected\"", "") }>${ (properties.createdDate?string("yyyy-MM-dd HH:mm"))!"" }</option>
                    </#list>
                </select>
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend>系统参数</legend>
        <div class="form-group">
            <label for="wxAppId" class="col-md-2 control-label"><@spring.message code="properties_attribute_system_title" /></label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="systemTitle" name="systemTitle" value="${ (properties.systemTitle)!"" }" >
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend>微信参数</legend>
        <div class="form-group">
            <label for="wxAppId" class="col-md-2 control-label"><@spring.message code="properties_attribute_wx_app_id" /></label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="wxAppId" name="wxAppId" value="${ (properties.wxAppId)!"" }" >
            </div>
        </div>
        <div class="form-group">
            <label for="wxAppSecret" class="col-md-2 control-label"><@spring.message code="properties_attribute_wx_app_secret" /></label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="wxAppSecret" name="wxAppSecret" value="${ (properties.wxAppSecret)!"" }" >
            </div>
        </div>
    </fieldset>
    <fieldset>
        <legend>短信参数</legend>
    </fieldset>
    <fieldset>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-8">
                <button type="submit" class="btn btn-primary pull-right">
                    <#if ((properties.id) == (lastProperties.id))!true >
                            <input type="hidden" name="restore" value="false">
                            <@spring.message code="button_yes" />
                        <#else >
                            <input type="hidden" name="restore" value="true">
                            <@spring.message code="button_restore" />
                    </#if>
                </button>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>
