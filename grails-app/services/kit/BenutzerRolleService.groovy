package kit

import grails.gorm.services.Service

@Service(BenutzerRolle)
interface BenutzerRolleService {

    BenutzerRolle get(Serializable id)

    List<BenutzerRolle> list(Map args)

    Long count()

    void delete(Serializable id)

    BenutzerRolle save(BenutzerRolle benutzerRolle)

}