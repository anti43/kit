package kit

import grails.gorm.services.Service

@Service(VorgangsKategorie)
interface VorgangsKategorieService {

    VorgangsKategorie get(Serializable id)

    List<VorgangsKategorie> list(Map args)

    Long count()

    void delete(Serializable id)

    VorgangsKategorie save(VorgangsKategorie vorgangsKategorie)

}