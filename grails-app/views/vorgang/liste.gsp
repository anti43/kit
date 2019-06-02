<!DOCTYPE html>
<g:set var="springSecurityService" bean="springSecurityService"/>
<html>

    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorgang.label', default: 'Vorgang')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-vorgang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}">Startseite</a></li>
                <g:if test="${!(springSecurityService?.currentUser instanceof kit.Benutzer)}">
                    <div class="btn btn-success"> <a style="color:white" href="/vorgang/neu" class="nav-link">Neues Anliegen übermitteln</a></div>
                </g:if>
            </ul>
        </div>
        <div id="list-vorgang" class="content scaffold-list" role="main">
            <h1>Alle öffentlichen Vorgänge</h1>
            <g:if test="${flash.message}">
                <div class="alert alert-warning" role="status">${flash.message}</div>
            </g:if>

            <table>
                <thead>
                <th>Id</th>
                <th>Aktualisiert am</th>
                <th>Bezeichnung</th>
                <th>Status</th>
                <th>Öffentlich</th>
                <th>Anzeigen</th>
                </thead>
                <tbody>
                <g:each in="${vorgangList}">
                    <tr>
                        <td><a href="/vorgang/show/${it.id}">${it.id}</a></td>
                        <td>${it.lastUpdated.format('dd.MM.yyyy')}</td>
                        <td>${it.bezeichnung}</td>
                        <td>${it.status}</td>
                        <td>${it.oeffentlich}</td>
                        <td><a class="btn btn-success" href="/vorgang/show/${it.id}">Anzeigen</a></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${vorgangCount ?: 0}" />
            </div>
        </div>
    </body>
</html>