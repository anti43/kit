package kit

import grails.gorm.services.Service

@Service(Faq)
interface FaqService {

    Faq get(Serializable id)

    List<Faq> list(Map args)

    Long count()

    void delete(Serializable id)

    Faq save(Faq faq)

}