package kit


import org.hibernate.type.StringNVarcharType
import org.springframework.security.core.context.SecurityContextHolder

class DateiAnhang {

    Date dateCreated
    Date lastUpdated

    String pfad
    String name, beschreibung

    Vorgang vorgang

    long groesse

    static mapping = {
        pfad  sqlType: 'TEXT'
        name  sqlType: 'TEXT'
        beschreibung  widget: 'textarea', nullable: true
    }

    static constraints = {
        beschreibung nullable: true
    }

    String toString() {
        name
    }
}
