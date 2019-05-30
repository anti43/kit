<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorgang.label', default: 'Vorgang')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-vorgang" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}">Startseite</a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-vorgang" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="alert alert-warning" role="status">${flash.message}</div>
            </g:if>
            <g:form resource="${this.vorgang}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.vorgang}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                </fieldset>
            </g:form>
        </div>



    <g:set var="springSecurityService" bean="springSecurityService"/>

    <!-- Post Content Column -->
    <div class="col-lg-8">
        <h1 class="mt-4">${vorgang.bezeichnung}</h1>
        <g:if test="${!vorgang.initiatorVerstecken}">
            <p class="lead">
                von
                <a href="#">${vorgang.vorschlagVon}</a>
            </p>
        </g:if>
        <g:if test="${vorgang.antragEingereichtAm}">
            <div class="alert alert-success">
                <h2>Antrag im zuständigen Gremium eingreicht</h2>
                <blockquote class="blockquote">
                    <p class="mb-0">
                        am&nbsp;${vorgang.antragEingereichtAm.format('dd.MM.yyyy')}
                    </p>
                </blockquote>
            </div>
        </g:if>

        <g:if test="${vorgang.antragEntschiedenAm}">
            <div class="alert alert-danger">
         <h2>Entscheidung bereits getroffen</h2>
         <blockquote class="blockquote">
             <p class="mb-0">
                    am&nbsp;${vorgang.antragEntschiedenAm.format('dd.MM.yyyy')}
                </p>
             <p>  <p>Ergebnis: <b>${vorgang.status}</b></p></p>
            </blockquote>
            </div>
        </g:if>

        <!-- Date/Time -->
        <div class="alert alert-info">
            <p>Erstellt: ${vorgang.dateCreated.format("dd.MM.yyyy")}</p>
            <p>Zuletzt bearbeitet: ${vorgang.lastUpdated.format("dd.MM.yyyy")}</p>
            <p>Aktueller Status: <b>${vorgang.status}</b></p>
        </div>



        <!-- Preview Image -->
        <% if(vorgang.bilder){%>
        <img class="img-fluid rounded" src="/dateiAnhang/render/${vorgang.bilder.find().id}" alt="">
        <hr>
        <%}%>

        <!-- Post Content -->
        <h2>Beschreibung</h2>
        ${vorgang.beschreibung.encodeAsRaw()}


        <h2>Bemerkungen</h2>
        <blockquote class="blockquote">
            <p class="mb-0">
                ${vorgang.bemerkungen.encodeAsRaw()}
            </p>
        </blockquote>



         <g:if test="${vorgang.begründung}">
             <h2>Begründung der Entscheidung</h2>
             <blockquote class="blockquote">
                 <p class="mb-0">
                     ${vorgang.begründung.encodeAsRaw()}
                 </p>
             </blockquote>
         </g:if>
        <hr>
        <!-- Comments Form -->
        <div class="card my-4">
            <h5 class="card-header">Kommentar hinterlassen:</h5>

            <div class="card-body">
                <g:form action="comment" id="${vorgang.id}">
                    <div class="form-group">
                        Ihr Name (optional): <input name="name" type="text"/><br>
                        Kommentar: <textarea name="text" class="form-control nomce" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Absenden</button>
                </g:form>
            </div>
        </div>
<h2>Kommentare:</h2><br>
         <g:each in="${kit.VorgangsKommentar.findAllByVorgangAndVeroeffentlicht(vorgang, true)}">
             <!-- Single Comment -->
             <div class="media mb-4">
                 <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">

                 <div class="media-body">
                     <h5 class="mt-0">${it.benutzer}</h5>
                     <h6 class="mt-0">${it.dateCreated.format("dd.MM.yyyy")}</h6>
                     ${it.text.encodeAsRaw()}
                          </div>
             </div>
        </g:each>

    </div>

    <!-- Sidebar Widgets Column -->
    <div class="col-md-4">


        <!-- Search Widget -->
        <div class="card my-4">
            <h5 class="card-header">Suche</h5>

            <div class="card-body">
                <div class="input-group">
                    <g:form controller="vorgang" action="suche">
                        <input name="q" type="text" class="form-control" placeholder="Suche nach...">
                        <span class="input-group-btn">
                            <button class="btn btn-secondary" type="submit">Los!</button>
                        </span>
                    </g:form>

                </div>
            </div>
        </div>

        <!-- Categories Widget -->
        <div class="card my-4">
            <h5 class="card-header">Kategorien</h5>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-10">
                        <ul class="list-unstyled mb-0">

                            <g:each in="${vorgang.kategorien}">
                                <li>
                                    <a href="/vorgangsKategorie/vorgaenge/${it.name}">${it.name}</a>
                                </li>
                            </g:each>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="card my-4">
            <h5 class="card-header">Betroffene Ortschaften</h5>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-10">
                        <ul class="list-unstyled mb-0">

                            <g:each in="${vorgang.ortschaften}">
                                <li>
                                    <a href="/gemeindeteil/vorgaenge/${it.name}">${it.name}</a>
                                </li>
                            </g:each>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


        <!-- Side Widget -->
        <div class="card my-4">
            <h5 class="card-header">Historie</h5>

            <div class="card-body">

            <ol>
                <g:each in="${this.vorgang.getLog()}">
                    <li  class="small"><a href="/vorgangsLog/show/${it.id}">${it.toString().encodeAsRaw()}</a>  </li>
                </g:each>
            </ol>
              </div>
        </div>

    </div>
    </body>
</html>
