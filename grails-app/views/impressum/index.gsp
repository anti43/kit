<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'impressum.label', default: 'Impressum')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-impressum" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>

            </ul>
        </div>
        <div id="list-impressum" class="content scaffold-list" role="main">
            <h1>Impressum</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table>
                <thead>
                <th>Id</th>
                <th>Bezeichnung</th>
                <th>Text</th>
                </thead>
                <tbody>
                <g:each in="${impressumList}">
                    <tr>
                        <td><a href="/impressum/edit/${it.id}">${it.id}</a></td>
                        <td>${it.bezeichnung?.encodeAsRaw()}:</td>
                        <td>${it.text?.encodeAsRaw()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${impressumCount ?: 0}" />
            </div>
        </div>
    </body>
</html>