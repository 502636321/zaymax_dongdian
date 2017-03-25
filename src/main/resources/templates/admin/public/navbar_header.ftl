<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${ (lastProperties.systemTitle)!"未设置" }</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><@spring.message code="applicatoin_navbar_settings" /></a></li>
                <li><a href="#"><@spring.message code="applicatoin_navbar_profile" /></a></li>
                <li><a href="#"><@spring.message code="applicatoin_navbar_help" /></a></li>
                <li><a href="javascript:document.getElementById('logout-form').submit();"><@spring.message code="applicatoin_navbar_logout" /></a></li>
            </ul>
            <form method="post" action="/logout" id="logout-form" style="display: none;" >
                <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }"/>
                <#--退出系统-->
            </form>
        </div>
    </div>
</nav>