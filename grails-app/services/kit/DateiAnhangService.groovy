package kit

import grails.gorm.services.Service

@Service(DateiAnhang)
interface DateiAnhangService {

    DateiAnhang get(Serializable id)

    List<DateiAnhang> list(Map args)

    Long count()

    void delete(Serializable id)

    DateiAnhang save(DateiAnhang dateiAnhang)

}