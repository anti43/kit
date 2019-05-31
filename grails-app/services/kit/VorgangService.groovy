package kit

class VorgangService {

    def springSecurityService

    Vorgang get(Serializable id) {
        Vorgang.findById(id as Long)
    }

    List<Vorgang> list(Map args) {
        Vorgang.findAll(args)
    }

    Long count() {
        Vorgang.count()
    }

    void delete(Serializable id) {
        def a = get(id)
        a.bilder*.delete()
        a.delete()
    }

    Vorgang save(Vorgang vorgang) {

            Vorgang v = vorgang.save(flush: true, failOnError: true)
            return v
    }

}