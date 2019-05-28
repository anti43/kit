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

    <g:layoutHead/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/tinymce.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/jquery.tinymce.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/autolink/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/contextmenu/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/image/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/imagetools/plugin.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/plugins/paste/plugin.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/skins/lightgray/skin.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.4/skins/lightgray/skin.mobile.min.css" />

</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-info fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">
            <img height="50px" src="${springSecurityService.currentUser?.mandant?.logo}">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">KIT (${springSecurityService.currentUser?.username})
                        <!--span class="sr-only">(current)</span-->
                    </a>
                </li>

                <li class="nav-item"> <g:link controller="logout" class="nav-link">Logout</g:link></li>

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
</body>

</html>



