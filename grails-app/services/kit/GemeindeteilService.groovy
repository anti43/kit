package kit

import grails.gorm.services.Service

@Service(Gemeindeteil)
interface GemeindeteilService {

    Gemeindeteil get(Serializable id)

    List<Gemeindeteil> list(Map args)

    Long count()

    void delete(Serializable id)

    Gemeindeteil save(Gemeindeteil gemeindeteil)

}