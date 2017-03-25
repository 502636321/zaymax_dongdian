<!DOCTYPE html>
<html lang="en">
<head>
<#include "admin/public/meta.ftl" />
<#include "admin/public/stylesheet.ftl" />
    <!-- Custom styles for this template -->
    <link href="/css/base.css" rel="stylesheet">

    <#include "admin/public/script.ftl" />
</head>
<body class="login-body">

<div class="container">

    <form class="form-signin" method="post" action="/login">
        <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
        <h2 class="form-signin-heading"><@spring.message code="title_login" /></h2>
        <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
            ${ (Session.SPRING_SECURITY_LAST_EXCEPTION.message)!"" }
        </#if>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="inputEmail" name="username" class="form-control" placeholder="<@spring.message code="user_attribute_username" />" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="<@spring.message code="user_attribute_password" />" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> <@spring.message code="checkbox_remember" />
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><@spring.message code="button_login"/></button>
    </form>

</div> <!-- /container -->
</body>
</html>
