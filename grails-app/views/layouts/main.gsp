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
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-info fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img height="50px" src="${System.getProperty('LOGO', '')}">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">KIT (${System.getProperty('MANDANT', 'Grüne OV Perl')})
                        <!--span class="sr-only">(current)</span-->
                    </a>
                </li>
<g:if test="${springSecurityService.currentUser instanceof kit.Benutzer}">
                <li class="nav-item"> <a href="/vorgang/create" class="nav-link">Neuen Vorgang anlegen</a></li>
             <li class="nav-item"> <a href="/vorgangsKommentar/index" class="nav-link">Moderation</a></li>
</g:if>

                <li class="nav-item"> <g:link controller="faq" class="nav-link">FAQ</g:link></li>
                <li class="nav-item"> <g:link controller="impressum" class="nav-link">Impressum</g:link></li>

                <g:if test="${springSecurityService.currentUser instanceof kit.Benutzer}">
                    <li class="nav-item"> <g:link controller="logoff" class="nav-link">Logout</g:link></li>
                </g:if>
                <g:else>
                   <li class="nav-item"> <g:link controller="login" class="nav-link">Login</g:link></li>
                </g:else>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <g:layoutBody/>
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<footer class="bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Erstellt von Andreas Weber 2019</p>
    </div>
    <!-- /.container -->
</footer>



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



