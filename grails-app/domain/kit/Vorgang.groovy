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


    Date antragEingereichtAm
    Date antragAngenommenAm
    Date antragAbgelehntAm

    static hasMany = [bilder: DateiAnhang, ortstermine: Ortstermin]

    def beforeInsert() {
        mandant = getCurrentMandant()
    }

    Mandant getCurrentMandant() {
        Benutzer.findByUsername(SecurityContextHolder.getContext().getAuthentication()?.name)?.mandant
    }

    static constraints = {
        bezeichnung()
        beschreibung widget: 'textarea', nullable: true
        bemerkungen widget: 'textarea', nullable: true
        vorschlagVon widget: 'textarea', nullable: true
        oeffentlich()
        initiatorVerstecken()

        antragEingereichtAm nullable: true
        antragAbgelehntAm nullable: true
        antragAngenommenAm  nullable: true

        begründung widget: 'textarea', nullable: true


        mandant display: false
        bilder()
        ortstermine()

    }

    static mapping = {
        bezeichnung type: GormEncryptedStringType
        beschreibung type: GormEncryptedStringType
        bemerkungen type: GormEncryptedStringType
        begründung type: GormEncryptedStringType
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
}
