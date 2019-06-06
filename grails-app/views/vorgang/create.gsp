<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorgang.label', default: 'Vorgang')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-vorgang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}">Startseite</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-vorgang" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="alert alert-warning" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.vorgang}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.vorgang}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.vorgang}" method="POST">
                <fieldset class="form">
                    <f:all bean="vorgang" except="bilder,ortschaften,kategorien"/>
                    <div class="fieldcontain">
                        <label for="ortschaften">Ortschaften</label><select name="ortschaften" id="ortschaften" multiple="">
                        <g:each in="${kit.Gemeindeteil.all.sort{it.name}}">
                            <option value="${it.id}">${it.name}</option>
                        </g:each>
                    </select>
                    </div>
                    <div class="fieldcontain">
                        <label for="kategorien">Kategorien</label><select name="kategorien" id="kategorien" multiple="">
                        <g:each in="${kit.VorgangsKategorie.all.sort{it.name}}">
                            <option value="${it.id}">${it.name}</option>
                        </g:each>
                    </select>
                    </div>
                </fieldset>
                <p align="center">
                    <input type="submit" value="Speichern" class="btn btn-success centered" />
                </p>
            </g:form>
        </div>
    </body>
</html>
