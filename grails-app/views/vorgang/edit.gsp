<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorgang.label', default: 'Vorgang')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-vorgang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-vorgang" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.vorgang}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.vorgang}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.vorgang}" method="PUT">
                <g:hiddenField name="version" value="${this.vorgang?.version}" />
                <fieldset class="form">
                    <f:all bean="vorgang"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
            <input id="fileupload" type="file" name="featuredImageFile" data-url="/vorgang/uploadImage/${this.vorgang.id}">
            <script>
                var y = $('a[href="/dateiAnhang/create?akte.id=${vorgang.id}"]');
                var x = $('#fileupload');

                y.detach();

                x.fileupload({
                    dataType: 'json',
                    add: function (e, data) {
                        data.context = $('<img/>').prop('href', "assets/loading.gif").appendTo($('div.nav'));
                        data.submit();
                    },
                    done: function (e, data) {
                        console.log('ok');
                        window.location = window.location;
                    },
                    error: function (e, data) {
                        console.log('error');
                        window.location = window.location;
                    }
                });</script>
        </div>
    </body>
</html>
