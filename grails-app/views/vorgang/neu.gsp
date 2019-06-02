<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<a href="#create-vorgang" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Startseite</a></li>
    </ul>
</div>

<div id="create-vorgang" class="content scaffold-create" role="main">
    <h1>Neues Anliegen übermitteln</h1>
    <g:if test="${flash.message}">
        <div class="alert alert-warning" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.vorgang}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.vorgang}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.vorgang}" method="POST" action="uebermitteln">
        <fieldset class="form">

            <fieldset class="form">
                <div class="fieldcontain required">
                    <label for="bezeichnung">Bezeichnung
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" name="bezeichnung" value="${params.bezeichnung?:''}" required="" id="bezeichnung">
                </div>
                <div class="fieldcontain">
                <label for="beschreibung">Beschreibung</label>

                <textarea name="beschreibung" id="beschreibung" >${params.beschreibung?:''}</textarea>
            </div>
            <div class="fieldcontain">
                <label for="vorschlagVon">Ihr Name</label>
                <input type="text" name="vorschlagVon" value="" id="vorschlagVon">
            </div>
             <div class="fieldcontain">
                <label for="vorschlagVon">Ihre Emailadresse für Rückfragen</label>
                <input type="email" name="vorschlagVonEmail" value="${params.vorschlagVonEmail?:''}" id="vorschlagVonEmail">
            </div>
            <div class="fieldcontain">
                <label for="initiatorVerstecken">Anonym bleiben (Ihr Name wird nicht angezeigt)</label>
                <input type="hidden" name="_initiatorVerstecken">
                <input type="checkbox" name="initiatorVerstecken" id="initiatorVerstecken">
            </div>

             <div class="fieldcontain">
                <label for="q">Ich bin ein Mensch</label>
                <input type="checkbox" name="q" id="q"><br>
            </div>


        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="Absenden"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
