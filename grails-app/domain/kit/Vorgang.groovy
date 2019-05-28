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

    VorgangsKategorie vorgangsKategorie

    static hasMany = [bilder: DateiAnhang]

    def beforeInsert() {
        mandant = getCurrentMandant()
        def benutzer = SecurityContextHolder.getContext().getAuthentication()?.name
        String t = "Vorgang $this erstellt von $benutzer"
        VorgangsLog vl = new VorgangsLog()
        vl.vorgang = this
        vl.text = t
        vl.save(flush: true, failOnError: true)
    }

    def beforeUpdate() {

        def x = getChanges()
        def benutzer = SecurityContextHolder.getContext().getAuthentication()?.name
        if (x) {
            String t = "Vorgang $this bearbeitet von $benutzer:\n${x}"
            VorgangsLog vl = new VorgangsLog()
            vl.vorgang = this
            vl.text = t
            vl.save(flush: true, failOnError: true)
        }
    }

    Mandant getCurrentMandant() {
        Benutzer.findByUsername(SecurityContextHolder.getContext().getAuthentication()?.name)?.mandant
    }

    static constraints = {
        bezeichnung()
        vorgangsKategorie nullable: true
        beschreibung widget: 'textarea', nullable: true
        bemerkungen widget: 'textarea', nullable: true
        vorschlagVon widget: 'textarea', nullable: true
        oeffentlich()
        initiatorVerstecken()

        antragEingereichtAm nullable: true
        antragAbgelehntAm nullable: true
        antragAngenommenAm nullable: true

        begründung widget: 'textarea', nullable: true


        mandant display: false
        bilder()

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
