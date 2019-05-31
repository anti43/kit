package kit

class Faq {

    String frage
    String antwort

    static constraints = {
        frage widget: 'textarea', nullable: false
        antwort widget: 'textarea', nullable: false
    }

    static mapping = {
        frage  sqlType: 'TEXT'
        antwort  sqlType: 'TEXT'
    }
}
