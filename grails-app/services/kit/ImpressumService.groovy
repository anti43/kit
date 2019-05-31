package kit

import grails.gorm.services.Service

@Service(Impressum)
interface ImpressumService {

    Impressum get(Serializable id)

    List<Impressum> list(Map args)

    Long count()

    void delete(Serializable id)

    Impressum save(Impressum impressum)

}