<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'faq.label', default: 'Faq')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-faq" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-faq" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                <th>Id</th>
                <th>Frage</th>
                <th>Antwort</th>
                </thead>
                <tbody>
                <g:each in="${faqList}">
                    <tr>
                        <td><a href="/faq/edit/${it.id}">${it.id}</a></td>
                        <td>${it.frage?.encodeAsRaw()}</td>
                        <td>${it.antwort?.encodeAsRaw()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${faqCount ?: 0}" />
            </div>
        </div>
    </body>
</html>