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
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-vorgang" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form resource="${this.vorgang}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.vorgang}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                </fieldset>
            </g:form>
        </div>

    ${this.vorgang.getLog()}

    <g:set var="springSecurityService" bean="springSecurityService"/>

    <!-- Post Content Column -->
    <div class="col-lg-8">
        <h1 class="mt-4">${vorgang.bezeichnung}</h1>
        <p class="lead">
            von
            <a href="#">${vorgang.vorschlagVon}</a>
        </p>

        <hr>

        <!-- Date/Time -->
        <p>${vorgang.dateCreated.format("dd.MM.yyyy")}</p>

        <hr>

        <!-- Preview Image -->
        <% if(vorgang.bilder){%>
        <img class="img-fluid rounded" src="/dateiAnhang/render/${vorgang.bilder.find().id}" alt="">
        <hr>
        <%}%>

        <!-- Post Content -->
        <h2>Beschreibung</h2>
        ${vorgang.beschreibung.encodeAsRaw()}


        <blockquote class="blockquote">
            <p class="mb-0">
                ${vorgang.bemerkungen}
            </p>
        </blockquote>

        <hr>

        <h2>Begründung der Entscheidung</h2>
        <blockquote class="blockquote">
            <p class="mb-0">
                ${vorgang.begründung}
            </p>
        </blockquote>

        <hr>
        <!-- Comments Form -->
        <div class="card my-4">
            <h5 class="card-header">Kommentar hinterlassen:</h5>

            <div class="card-body">
                <g:form action="comment" id="${vorgang.id}">
                    <div class="form-group">
                        <input name="name" type="text"/>
                        <textarea name="text" class="form-control nomce" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Absenden</button>
                </g:form>
            </div>
        </div>

         <g:each in="${kit.VorgangsKommentar.findAllByVorgangAndVeroeffentlicht(vorgang, true)}">
             <!-- Single Comment -->
             <div class="media mb-4">
                 <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">

                 <div class="media-body">
                     <h5 class="mt-0">${it.benutzer}</h5>
                     ${it.text}
                          </div>
             </div>
        </g:each>

    </div>

    <!-- Sidebar Widgets Column -->
    <div class="col-md-4">

        <!-- Search Widget -->
        <div class="card my-4">
            <h5 class="card-header">Search</h5>

            <div class="card-body">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                        <button class="btn btn-secondary" type="button">Go!</button>
                    </span>
                </div>
            </div>
        </div>

        <!-- Categories Widget -->
        <div class="card my-4">
            <h5 class="card-header">Categories</h5>

            <div class="card-body">
                <div class="row">
                    <div class="col-lg-6">
                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#">Web Design</a>
                            </li>
                            <li>
                                <a href="#">HTML</a>
                            </li>
                            <li>
                                <a href="#">Freebies</a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-lg-6">
                        <ul class="list-unstyled mb-0">
                            <li>
                                <a href="#">JavaScript</a>
                            </li>
                            <li>
                                <a href="#">CSS</a>
                            </li>
                            <li>
                                <a href="#">Tutorials</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Side Widget -->
        <div class="card my-4">
            <h5 class="card-header">Side Widget</h5>

            <div class="card-body">
                You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
            </div>
        </div>

    </div>
    </body>
</html>
