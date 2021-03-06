<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../../public/meta.ftl" />
    <#include "../../public/stylesheet.ftl" />
    <#include "../../public/script.ftl" />
</head>

<body class="main-body">

<#include "../../public/navbar_header.ftl" />

<div class="container-fluid">
    <div class="row">
        <#assign sidebar="properties" />
        <#include "../../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading"><@spring.message code="system_properties" /></div>
                <div class="panel-body">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="/admin/properties/save"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
