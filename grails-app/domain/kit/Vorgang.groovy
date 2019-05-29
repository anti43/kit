package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType
import org.springframework.security.core.context.SecurityContextHolder

class Vorgang {

    Date dateCreated
    Date lastUpdated

    String bezeichnung
    String beschreibung, bemerkungen, begründung, vorschlagVon
    Boolean oeffentlich = false
    Boolean initiatorVerstecken = false
    Mandant mandant

    String status


    Date antragEingereichtAm
    Date antragEntschiedenAm


    static hasMany = [bilder: DateiAnhang, kategorien: VorgangsKategorie, ortschaften: Gemeindeteil]


    def beforeUpdate() {
    }

    Mandant getCurrentMandant() {
        Benutzer.findByUsername(SecurityContextHolder.getContext().getAuthentication()?.name)?.mandant
    }

    static constraints = {
        bezeichnung()
        beschreibung widget: 'textarea', nullable: true
        bemerkungen widget: 'textarea', nullable: true
        vorschlagVon nullable: true
        oeffentlich()
        initiatorVerstecken()

        antragEingereichtAm nullable: true
        antragEntschiedenAm nullable: true

        status inList: ["Eingegangen", "Abgelehnt", "Angenommen", "Zuständigkeit unklar"]

        begründung widget: 'textarea', nullable: true


        mandant display: false
        bilder()

    }

    static mapping = {
        bezeichnung type: GormEncryptedStringType, sqlType: 'TEXT'
        beschreibung type: GormEncryptedStringType, sqlType: 'TEXT'
        bemerkungen type: GormEncryptedStringType, sqlType: 'TEXT'
        begründung type: GormEncryptedStringType, sqlType: 'TEXT'
        vorschlagVon type: GormEncryptedStringType, sqlType: 'TEXT'
    }

    DateiAnhang addImage(Map map) {
        DateiAnhang b = new DateiAnhang()
        b.pfad = map.file.getPath()
        b.name = map.name
        b.vorgang = this
        b.groesse = map.file.length()
        b.mandant = mandant
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
            t = t + "$it: ${getPersistentValue(it as String)} -> ${this[(it as String)]}"
        }
        return t
    }

    List<VorgangsLog> getLog() {
        VorgangsLog.findAllByVorgang(this)
    }
}
