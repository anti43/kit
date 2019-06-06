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
        <blockquote>
            <p class="mb-0">
                am&nbsp;${vorgang.antragEingereichtAm.format('dd.MM.yyyy')}
            </p>
        </blockquote>
    </div>
</g:if>


<g:if test="${vorgang.antragEntschiedenAm}">

    <%
        def cssclass = vorgang.getBadgeClass()
    %>
    <div class="alert alert-${cssclass}">
        <h2>Entscheidung bereits getroffen</h2>
        <blockquote>
            <p class="mb-0">
                am&nbsp;${vorgang.antragEntschiedenAm.format('dd.MM.yyyy')}
            </p>

            <p>

            <p>Ergebnis: <span class="badge badge-${vorgang.getBadgeClass()}">${vorgang.status}</span></p></p>
        </blockquote>
    </div>
</g:if>
<g:else>
    <!-- Date/Time -->
    <div class="alert alert-info">
        <p>Erstellt: ${vorgang.dateCreated.format("dd.MM.yyyy")}</p>

        <p>Zuletzt bearbeitet: ${vorgang.lastUpdated.format("dd.MM.yyyy")}</p>

        <p>Aktueller Status:
            <span class="badge badge-${vorgang.getBadgeClass()}">${vorgang.status}</span>
        </p>

        <p>Zuständigkeit: <b>${vorgang.werIstZustaendig}</b></p>
    </div>

</g:else>


<!-- Preview Image -->
<g:if test="${(vorgang.bilder)}">
    <img style="max-height: 300px;" class="img-fluid rounded"
         src="/dateiAnhang/render/${vorgang.bilder.sort { it.id }.last().id}" alt="">
    <hr>
</g:if>


<!-- Post Content -->
<% if (vorgang.beschreibung) { %>
<h2>Beschreibung</h2>
${vorgang.beschreibung.encodeAsRaw()}

<% } %>

<% if (vorgang.bemerkungen) { %>
<h2>Bemerkungen</h2>
<blockquote>
    <p class="mb-0">
        ${vorgang.bemerkungen.encodeAsRaw()}
    </p>
</blockquote>
<% } %>


<g:if test="${vorgang.begründung}">
    <h2>Begründung der Entscheidung</h2>
    <blockquote>
        <p class="mb-0">
            ${vorgang.begründung.encodeAsRaw()}
        </p>
    </blockquote>
</g:if>

