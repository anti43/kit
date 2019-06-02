<!DOCTYPE html>
<g:set var="springSecurityService" bean="springSecurityService"/>
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
<g:if test="${springSecurityService.currentUser instanceof kit.Benutzer}">
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
</g:if>
            </ul>
        </div>

    <g:if test="${flash.message}">
        <div class="alert alert-warning col-lg-12" role="status">${flash.message}</div>
    </g:if>


    <!-- Post Content Column -->
    <div class="col-lg-8">
        <h1 class="mt-4">${vorgang.bezeichnung}</h1>
        <g:if test="${(!vorgang.initiatorVerstecken) && vorgang.vorschlagVon}">
            <p class="lead">
                von
                <a href="#">${vorgang.vorschlagVon}</a>
            </p>
        </g:if>
        <g:if test="${vorgang.antragEingereichtAm && !vorgang.antragEntschiedenAm}">
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

            <%
                def cssclass = 'danger'
                if(vorgang.status?.contains('Angenommen')){
                    cssclass = 'success'
                }
            %>
            <div class="alert alert-${cssclass}">
         <h2>Entscheidung bereits getroffen</h2>
         <blockquote class="blockquote">
             <p class="mb-0">
                    am&nbsp;${vorgang.antragEntschiedenAm.format('dd.MM.yyyy')}
                </p>
             <p>  <p>Ergebnis: <b>${vorgang.status}</b></p></p>
            </blockquote>
            </div>
        </g:if>
<g:else>
    <!-- Date/Time -->
    <div class="alert alert-info">
        <p>Erstellt: ${vorgang.dateCreated.format("dd.MM.yyyy")}</p>
        <p>Zuletzt bearbeitet: ${vorgang.lastUpdated.format("dd.MM.yyyy")}</p>
        <p>Aktueller Status: <b>${vorgang.status}</b></p>
        <p>Zuständigkeit: <b>${vorgang.werIstZustaendig}</b></p>
    </div>


</g:else>


        <!-- Preview Image -->
        <% if(vorgang.bilder){%>
        <img height="200px" class="img-fluid rounded" src="/dateiAnhang/render/${vorgang.bilder.sort{it.id}.find().id}" alt="">
        <hr>
        <%}%>

        <!-- Post Content -->
        <% if(vorgang.beschreibung){%>
        <h2>Beschreibung</h2>
        ${vorgang.beschreibung.encodeAsRaw()}

        <%}%>

        <% if(vorgang.bemerkungen){%>
        <h2>Bemerkungen</h2>
        <blockquote class="blockquote">
            <p class="mb-0">
                ${vorgang.bemerkungen.encodeAsRaw()}
            </p>
        </blockquote>
        <%}%>


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
                        Ihr Name (optional): <input name="name" type="text" value="${params.name?:''}"/><br>
                        Ich bin ein Mensch: <input type="checkbox" name="q" ><br>
                        Kommentar: <textarea name="text" class="form-control nomce" rows="3" value="${params.text?:''}"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Absenden</button>
                </g:form>
            </div>
        </div>
        <g:if test="${kit.VorgangsKommentar.countByVorgangAndVeroeffentlicht(vorgang, true)}">


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
        </g:if>
    </div>


    <!-- Sidebar Widgets Column -->
    <div class="col-md-4">


        <div class="card my-4">
            <fieldset class="buttons">
<g:if test="${springSecurityService.currentUser instanceof kit.Benutzer}">
                <g:link class="edit" action="edit" resource="${this.vorgang}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <g:if test="${!vorgang.oeffentlich}">
                    <g:link class="edit" action="publish" resource="${this.vorgang}">Veröffentlichen</g:link>
                </g:if><g:else>
                   <g:link class="edit" action="unpublish" resource="${this.vorgang}">Veröffentlichung aufheben</g:link>
            </g:else>
</g:if>
                <g:uploadForm name="uploadFeaturedImage" action="uploadImage" class="form form-inline" style="width: 250px">
                    <g:hiddenField name="id" value="${this.vorgang?.id}" />
                    <g:hiddenField name="version" value="${this.vorgang?.version}" />
                    <input type="file" name="featuredImageFile" id="featuredImageFile" onchange="$('#fileUpload').click()"/>
                    <input id="fileUpload" class="save" type="submit" value="Bild hochladen" onmousedown="if(!$('#featuredImageFile').val())$('#featuredImageFile').click();"/>
                </g:uploadForm>
            </fieldset>
        </div>

        <!-- Categories Widget -->
        <div class="card my-4">
            <h5 class="card-header">Anhänge</h5>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="list-unstyled mb-0">

                            <g:each in="${vorgang.bilder.sort{it.id}}">
                                <li>
                                    <a href="/dateiAnhang/render/${it.id}"><img src="/dateiAnhang/render/${it.id}" width="150px">
                                        ${it.name}
                                    </a>
                                <g:if test="${springSecurityService.currentUser instanceof kit.Benutzer}">
                                    <br><a href="/dateiAnhang/remove/${it.id}">Bild entfernen
                                    </a>
                                </g:if>
                                    <hr>
                                </li>
                            </g:each>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- Categories Widget -->
        <div class="card my-4">
            <h5 class="card-header">Kategorien</h5>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-12">
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
                    <div class="col-lg-12">
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
