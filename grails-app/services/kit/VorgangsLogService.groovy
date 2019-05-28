package kit

import grails.gorm.services.Service

@Service(VorgangsLog)
interface VorgangsLogService {

    VorgangsLog get(Serializable id)

    List<VorgangsLog> list(Map args)

    Long count()

    void delete(Serializable id)

    VorgangsLog save(VorgangsLog vorgangsLog)

}