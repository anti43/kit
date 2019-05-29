<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorgangsLog.label', default: 'VorgangsLog')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-vorgangsLog" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}">Startseite</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-vorgangsLog" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <ol class="property-list vorgangsLog alert alert-info">

                <li class="fieldcontain">
                    <span id="vorgang-label" class="property-label">Vorgang</span>
                    <div class="property-value" aria-labelledby="vorgang-label"><a href="/vorgang/show/${vorgangsLog.vorgang.id}">${vorgangsLog.vorgang}</a></div>
                </li>

                <li class="fieldcontain">
                    <span id="benutzer-label" class="property-label">Benutzer</span>
                    <div class="property-value" aria-labelledby="benutzer-label">${vorgangsLog.benutzer}</div>
                </li>



            </ol>
            <div class="property-list vorgangsLog">

            <h3>Änderungen:</h3>
            <div class="well">

                <div class="property-value" aria-labelledby="komplett-label">
                    ${vorgangsLog.komplett.encodeAsRaw()}
                </div>
            </div>
            </div>
        </div>
    </body>
</html>
