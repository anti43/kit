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
                    <label for="bezeichnung">Betreff (worum geht es?):
                        <span class="required-indicator">*</span>
                    </label>
                    <input type="text" style="width: 100%;" name="bezeichnung" value="${params.bezeichnung?:''}" required="" id="bezeichnung">
                </div>
                <div class="fieldcontain">
                <label for="beschreibung">Beschreiben Sie Ihr Anliegen:</label>

                <textarea name="beschreibung" id="beschreibung" >${params.beschreibung?:''}</textarea>
            </div>
            <div class="fieldcontain">
                <label for="vorschlagVon">Ihr Name</label>
                <input type="text" name="vorschlagVon" value="" id="vorschlagVon">
            </div>
             <div class="fieldcontain">
                <label for="vorschlagVon">Ihre Emailadresse oder Telefonnummer für Rückfragen</label>
                <input type="text" name="vorschlagVonEmail" value="${params.vorschlagVonEmail?:''}" id="vorschlagVonEmail">
            </div>
            <div class="fieldcontain">
                <label for="initiatorVerstecken">Anonym bleiben (Ihr Name wird nicht angezeigt)</label>
                <input type="hidden" name="_initiatorVerstecken">
                <input type="checkbox" name="initiatorVerstecken" id="initiatorVerstecken">
            </div>

             <div class="fieldcontain">
                <label for="q">Ich bin ein echter Mensch</label>
                <input required type="checkbox" name="q" id="q"><br>
            </div>


        </fieldset>

            <p align="center"><g:submitButton name="create" class="btn btn-success centered" value="Absenden" /> </p>
        <br>
        <br>
    </g:form>
</div>
</body>
</html>
