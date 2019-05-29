package kit

import grails.gorm.services.Service

@Service(VorgangsKommentar)
interface VorgangsKommentarService {

    VorgangsKommentar get(Serializable id)

    List<VorgangsKommentar> list(Map args)

    Long count()

    void delete(Serializable id)

    VorgangsKommentar save(VorgangsKommentar vorgangsKommentar)

}