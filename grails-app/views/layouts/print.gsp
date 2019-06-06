<!doctype html>
<html lang="en" class="no-js">
<g:set var="springSecurityService" bean="springSecurityService"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="KIT"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/skins/lightgray/skin.min.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/skins/lightgray/skin.mobile.min.css"/>

    <g:layoutHead/>
    <asset:javascript src="application.js"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/tinymce.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/jquery.tinymce.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/autolink/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/contextmenu/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/image/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/imagetools/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/paste/plugin.min.js"></script>


</head>

<body>


<!-- Page Content -->
<div class="container">

    <div class="row">

        <g:layoutBody/>
    </div>
    <!-- /.row -->
</div>
<!-- /.container -->



<asset:javascript src="application.js"/>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-75465590-2"></script>
<script>



    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());
    gtag('config', 'UA-75465590-2', { 'anonymize_ip': true });

    $(document).ready(function () {
        tinymce.init({
            selector: 'textarea:not(.nomce)',
            height: 250,
            theme: 'modern',
            plugins: '',
            toolbar1: 'formatselect | bold italic | link | numlist | removeformat',
            image_advtab: true,
            //templates: "/template/list",

            setup: function (editor) {
                editor.getElement().removeAttribute('required');
            }
        });

        $('table.data').DataTable({
            "bInfo": false,
            "paging": false,
            "bPaginate": false,
            "searching": false,
            "ordering": false
        });
    });
</script>
</body>

</html>



