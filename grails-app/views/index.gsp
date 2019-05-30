<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>

</head>

<body>
<g:set var="springSecurityService" bean="springSecurityService"/>
<g:set var="vorgangList" value="${kit.Vorgang.findAllByOeffentlich(true, [max: 10]).sort {it.lastUpdated.time}.reverse()}"/>
<!-- Post Content Column -->
<div class="col-lg-8">
    <h1 class="mt-4">KIT - Kommunale Intelligenz und Transparenz</h1>
    <p class="lead">
        für
        <a href="#">${springSecurityService.currentUser?.mandant?.displayName}</a>
    </p>

    <hr>

    <!-- Date/Time -->
    <p>Zuletzt aktualisiert: ${(vorgangList?.find()?.lastUpdated?:new Date()).format('dd.MM.yyyy')}</p>

    <hr>
    <p>Letzte Vorgänge: (<a href="/vorgang/index">Alle anzeigen</a> )</p>

    <table>
        <thead>
        <th>Id</th>
        <th>Aktualisiert am</th>
        <th>Bezeichnung</th>
        <th>Status</th>
        </thead>
        <tbody>
        <g:each in="${vorgangList}">
            <tr>
                <td><a href="/vorgang/show/${it.id}">${it.id}</a></td>
                <td>${it.lastUpdated.format('dd.MM.yyyy')}</td>
                <td>${it.bezeichnung}</td>
                <td>${it.status}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <hr>
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

                        <g:each in="${kit.VorgangsKategorie.all}">
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
        <h5 class="card-header">Ortschaften</h5>

        <div class="card-body">
            <div class="row">
                <div class="col-lg-10">
                    <ul class="list-unstyled mb-0">

                        <g:each in="${kit.Gemeindeteil.all}">
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
        <h5 class="card-header">Informationen</h5>

        <div class="card-body">
         </div>
    </div>

</div>

</body>
</html>
