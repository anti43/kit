<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>

</head>
<body>
<g:set var="springSecurityService" bean="springSecurityService"/>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Aktionen<span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name } }">
                <li class="dropdown-item"> <g:link controller="${c.logicalPropertyName}">${c.name}</g:link></li>
            </g:each>
        </ul>
    </li>


</content>



<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>KIT (${springSecurityService.currentUser?.username})</h1>



    </section>
</div>

</body>
</html>
