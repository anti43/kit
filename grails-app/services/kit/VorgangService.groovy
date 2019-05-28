package kit

class VorgangService {

    def springSecurityService

    Vorgang get(Serializable id) {
        Benutzer benutzer = springSecurityService.currentUser as Benutzer
        Vorgang.findByMandantAndId(benutzer.mandant, id as Long)
    }

    List<Vorgang> list(Map args) {
        Benutzer benutzer = springSecurityService.currentUser as Benutzer
        Vorgang.findAllByMandant(benutzer.mandant, args)
    }

    Long count() {
        Benutzer benutzer = springSecurityService.currentUser as Benutzer
        Vorgang.countByMandant(benutzer.mandant)
    }

    void delete(Serializable id) {
        def a = get(id)
        a.bilder*.delete()
        a.delete()
    }

    Vorgang save(Vorgang vorgang) {
        Benutzer benutzer = springSecurityService.currentUser as Benutzer

        if (!vorgang.mandant || vorgang.mandant == benutzer.mandant) {

            Vorgang v = vorgang.save(flush: true, failOnError: true)
            return v
        }
        throw new RuntimeException("Mandant mismatch (${vorgang.mandant} vs ${benutzer.mandant})")
    }

}