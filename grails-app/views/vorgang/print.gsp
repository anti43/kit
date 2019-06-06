<!DOCTYPE html>
<g:set var="springSecurityService" bean="springSecurityService"/>
<html>
<head>
    <meta name="layout" content="print"/>
    <g:set var="entityName" value="${message(code: 'vorgang.label', default: 'Vorgang')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<!-- Post Content Column -->
<div class="col-lg-12">
    <g:render template="details"/>
</div>


<div class="col-lg-12">
    <g:each in="${vorgang.bilder.sort { it.id }.take(vorgang.bilder.size()-1)}">
        <img  style="max-height: 500px;max-width: 80%" src="/dateiAnhang/render/${it.id}"><br>
        ${it.name}
        <hr>
    </g:each>

</div>
</body>
</html>
