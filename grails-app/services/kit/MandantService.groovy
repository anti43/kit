package kit

import grails.gorm.services.Service

@Service(Mandant)
interface MandantService {

    Mandant get(Serializable id)

    List<Mandant> list(Map args)

    Long count()

    void delete(Serializable id)

    Mandant save(Mandant mandant)

}