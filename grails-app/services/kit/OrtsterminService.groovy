package kit

import grails.gorm.services.Service

@Service(Ortstermin)
interface OrtsterminService {

    Ortstermin get(Serializable id)

    List<Ortstermin> list(Map args)

    Long count()

    void delete(Serializable id)

    Ortstermin save(Ortstermin ortstermin)

}