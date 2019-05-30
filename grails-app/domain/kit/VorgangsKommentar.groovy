package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType

class VorgangsKommentar {
    Date dateCreated

    String benutzer
    Vorgang vorgang
    String text
    boolean veroeffentlicht = false

    static hasMany = [vorgaenge:Vorgang]
    static constraints = {
        text widget: 'textarea', nullable: false
    }

    static mapping = {
        benutzer type: GormEncryptedStringType
        text type: GormEncryptedStringType, sqlType: 'TEXT'
    }
}
