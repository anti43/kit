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
                    <li>
                        <a style="color:white" href="/vorgang/neu" class="btn btn-success">Neues Anliegen übermitteln</a>
                    </li>
                </g:if>
            </ul>
        </div>
        <div id="list-vorgang" class="content scaffold-list" role="main">
<g:if test="${!(springSecurityService?.currentUser instanceof kit.Benutzer)}">
            <h1>Öffentliche Vorgänge</h1>
</g:if><g:else>
            <h1>Öffentliche und Nicht-Öffentliche Vorgänge</h1>
        </g:else>
            <p>&nbsp;</p>
            <g:if test="${flash.message}">
                <div class="alert alert-info" role="status">${flash.message}</div>
            </g:if>

            <div class="card my-4">
                <h5 class="card-header">Suche</h5>

                <div class="card-body">
                    <div class="input-group">
                        <g:form controller="vorgang" action="suche" class="form-inline">
                            <input name="q" type="text" class="form-control" placeholder="Suche nach...">
                            <button class="btn btn-info" type="submit">Los!</button>
                        </g:form>&nbsp;
                        <g:form controller="vorgang" action="liste" class="form-inline">
                            <button class="btn btn-secondary" type="submit">Alle anzeigen</button>
                        </g:form>
                    </div>
                </div>
            </div>

            <g:if test="${vorgangList}">
                <table class="data">
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
                            <td>
                                <span class="badge badge-${it.getBadgeClass()}">
                                ${it.status}
                                </span>
                            </td>
                            <td>${it.oeffentlich}</td>
                            <td><a class="btn btn-success" href="/vorgang/show/${it.id}">Anzeigen</a></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <div class="pagination">
                    <g:paginate total="${vorgangCount ?: 0}" />
                </div>
            </g:if>

        </div>

    </body>
</html>