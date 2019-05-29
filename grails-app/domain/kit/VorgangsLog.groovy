package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType

class VorgangsLog {
    Date dateCreated

    Vorgang vorgang
    String text
    String benutzer

    static constraints = {
    }

    static mapping = {
        text type: GormEncryptedStringType, sqlType: 'TEXT'
        benutzer type: GormEncryptedStringType
    }

    String toString(){
        benutzer + ": " + text
    }
}
