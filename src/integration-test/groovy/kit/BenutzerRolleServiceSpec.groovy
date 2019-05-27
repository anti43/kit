package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BenutzerRolleServiceSpec extends Specification {

    BenutzerRolleService benutzerRolleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new BenutzerRolle(...).save(flush: true, failOnError: true)
        //new BenutzerRolle(...).save(flush: true, failOnError: true)
        //BenutzerRolle benutzerRolle = new BenutzerRolle(...).save(flush: true, failOnError: true)
        //new BenutzerRolle(...).save(flush: true, failOnError: true)
        //new BenutzerRolle(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //benutzerRolle.id
    }

    void "test get"() {
        setupData()

        expect:
        benutzerRolleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<BenutzerRolle> benutzerRolleList = benutzerRolleService.list(max: 2, offset: 2)

        then:
        benutzerRolleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        benutzerRolleService.count() == 5
    }

    void "test delete"() {
        Long benutzerRolleId = setupData()

        expect:
        benutzerRolleService.count() == 5

        when:
        benutzerRolleService.delete(benutzerRolleId)
        sessionFactory.currentSession.flush()

        then:
        benutzerRolleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        BenutzerRolle benutzerRolle = new BenutzerRolle()
        benutzerRolleService.save(benutzerRolle)

        then:
        benutzerRolle.id != null
    }
}
