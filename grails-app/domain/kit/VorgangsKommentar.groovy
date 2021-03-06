package kit



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
        text  sqlType: 'TEXT'
    }
}
