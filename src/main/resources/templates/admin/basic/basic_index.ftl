<!DOCTYPE html>
<html lang="en">
<head>
    <#include "../public/meta.ftl" />
    <#include "../public/stylesheet.ftl" />
    <!-- Custom styles for this template -->
    <link href="/css/dashboard.css" rel="stylesheet">

    <#include "../public/script.ftl" />
</head>

<body>

<#include "../public/navbar_header.ftl" />

<div class="container-fluid">
    <div class="row">
    <#assign sidebar="basic" />
        <#include "../admin_sidebar.ftl" />
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Dashboard</h1>
            <div class="panel panel-primary">
                <!-- Default panel contents -->
                <div class="panel-heading">Panel heading</div>
                <div class="panel-body">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="/admin/basic/education"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
