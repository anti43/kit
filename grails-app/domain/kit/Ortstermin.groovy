package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType
import org.springframework.security.core.context.SecurityContextHolder

class Ortstermin {

    Date dateCreated
    Date lastUpdated

    Date termin
    String adresse
    Mandant mandant
    Vorgang vorgang

    String freitext

    static mapping = {
        freitext type: GormEncryptedStringType, sqlType: 'TEXT'
        adresse type: GormEncryptedStringType, sqlType: 'TEXT'
    }

    static constraints = {
        freitext nullable: true, widget: 'textarea'
        adresse nullable: false, widget: 'textarea'
        mandant display: false
    }

    def beforeInsert(){
        mandant = getCurrentMandant()
    }

    Mandant getCurrentMandant() {
        Benutzer.findByUsername(SecurityContextHolder.getContext().getAuthentication()?.name)?.mandant
    }

    String toString(){
        "$termin ($adresse)"
    }
}
