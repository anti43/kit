package kit

class Impressum {

    String bezeichnung
    String text

    static constraints = {
        text widget: 'textarea', nullable: false
        bezeichnung widget: 'textarea', nullable: false
    }

    static mapping = {
        text  sqlType: 'TEXT'
        bezeichnung  sqlType: 'TEXT'
    }
}
