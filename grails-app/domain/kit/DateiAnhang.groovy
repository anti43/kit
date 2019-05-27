package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType
import org.springframework.security.core.context.SecurityContextHolder

class DateiAnhang {

    Date dateCreated
    Date lastUpdated

    String pfad
    String name, beschreibung
    Mandant mandant
    Vorgang vorgang

    long groesse

    static mapping = {
        pfad type: GormEncryptedStringType, sqlType: 'TEXT'
        name type: GormEncryptedStringType, sqlType: 'TEXT'
        beschreibung type: GormEncryptedStringType, widget: 'textarea', nullable: true
    }

    static constraints = {
        beschreibung nullable: true
        mandant display: false
    }

    def beforeInsert() {
        mandant = getCurrentMandant()
    }

    Mandant getCurrentMandant() {
        Benutzer.findByUsername(SecurityContextHolder.getContext().getAuthentication()?.name)?.mandant
    }


    String toString() {
        name
    }
}
