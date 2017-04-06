<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="/bootstrap-3.3.7/docs/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-1.12.4.min.js"></script>
<script src="/jquery-validation-1.15.1/dist/jquery.validate.js"></script>
<script src="/jquery-validation-1.15.1/dist/additional-methods.js"></script>
<script src="/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
<script src="/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script src="/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/js/bootstrap3-typeahead.min.js"></script>
<script src="/js/multiselect.min.js"></script>
<script src="/js/tableHeadFixer.js"></script>
<script src="/js/printThis.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="/bootstrap-3.3.7/docs/assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
    $(function() {
        $('[data-toggle="tooltip"]').tooltip();
        $(".form_datetime").datetimepicker({
            format: 'yyyy-mm-dd',
            minView: 'month',
            language: 'zh-CN',
            autoclose: true,
            todayBtn: true
        });
    });
</script>