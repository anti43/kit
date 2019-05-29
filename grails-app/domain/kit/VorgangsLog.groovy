package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType

class VorgangsLog {
    Date dateCreated

    Vorgang vorgang
    String text
    String benutzer
    String komplett

    static constraints = {
    }

    static mapping = {
        komplett type: GormEncryptedStringType, sqlType: 'TEXT'
        text type: GormEncryptedStringType, sqlType: 'TEXT'
        benutzer type: GormEncryptedStringType
    }

    String toString(){
        dateCreated.format("dd.MM.yyyy") + " (" + benutzer + ") " + text
    }
}
