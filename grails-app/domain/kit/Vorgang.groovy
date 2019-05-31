package kit


import org.springframework.security.core.context.SecurityContextHolder

class Vorgang {

    Date dateCreated
    Date lastUpdated

    String bezeichnung
    String beschreibung, bemerkungen, begründung, vorschlagVon,vorschlagVonEmail
    Boolean oeffentlich = false
    Boolean initiatorVerstecken = false


    String status = "Neu"


    Date antragEingereichtAm
    Date antragEntschiedenAm


    static hasMany = [bilder: DateiAnhang, kategorien: VorgangsKategorie, ortschaften: Gemeindeteil]


    def beforeUpdate() {
    }


    static constraints = {
        bezeichnung()
        beschreibung widget: 'textarea', nullable: true
        bemerkungen widget: 'textarea', nullable: true
        vorschlagVon nullable: true
        vorschlagVonEmail nullable: true
        oeffentlich()
        initiatorVerstecken()

        antragEingereichtAm nullable: true
        antragEntschiedenAm nullable: true

        status inList: ["Neu", "Wird diskutiert", "Abgelehnt", "Angenommen", "Zuständigkeit unklar"]

        begründung widget: 'textarea', nullable: true

        bilder()

    }

    static mapping = {
        bezeichnung  sqlType: 'TEXT'
        beschreibung  sqlType: 'TEXT'
        bemerkungen  sqlType: 'TEXT'
        begründung  sqlType: 'TEXT'
        vorschlagVon  sqlType: 'TEXT'
    }

    DateiAnhang addImage(Map map) {
        DateiAnhang b = new DateiAnhang()
        b.pfad = map.file.getPath()
        b.name = map.name
        b.vorgang = this
        b.groesse = map.file.length()

        b.save(flush: true, failOnError: true)
        addToBilder(b)
        save(flush: true)
        return b
    }

    String toString() {
        "$bezeichnung ($dateCreated)"
    }

    String getChanges() {
        String t = ""
        this.dirtyPropertyNames.each {
            t = t + "<li><b>$it</b> <br>alt:${getPersistentValue(it as String)}<br>neu: ${this[(it as String)]}</li>"
        }
        return t
    }

    List<VorgangsLog> getLog() {
        VorgangsLog.findAllByVorgang(this)
    }
}
