package kit



class VorgangsLog {
    Date dateCreated

    Vorgang vorgang
    String text
    String benutzer
    String komplett

    static constraints = {
    }

    static mapping = {
        komplett  sqlType: 'TEXT'
        text  sqlType: 'TEXT'
    }

    String toString(){
        dateCreated.format("dd.MM.yyyy") + " (" + benutzer + ") " + text
    }
}
