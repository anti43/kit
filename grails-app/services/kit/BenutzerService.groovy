package kit

import grails.gorm.services.Service

@Service(Benutzer)
interface BenutzerService {

    Benutzer get(Serializable id)

    List<Benutzer> list(Map args)

    Long count()

    void delete(Serializable id)

    Benutzer save(Benutzer benutzer)

}