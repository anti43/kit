package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BenutzerServiceSpec extends Specification {

    BenutzerService benutzerService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Benutzer(...).save(flush: true, failOnError: true)
        //new Benutzer(...).save(flush: true, failOnError: true)
        //Benutzer benutzer = new Benutzer(...).save(flush: true, failOnError: true)
        //new Benutzer(...).save(flush: true, failOnError: true)
        //new Benutzer(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //benutzer.id
    }

    void "test get"() {
        setupData()

        expect:
        benutzerService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Benutzer> benutzerList = benutzerService.list(max: 2, offset: 2)

        then:
        benutzerList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        benutzerService.count() == 5
    }

    void "test delete"() {
        Long benutzerId = setupData()

        expect:
        benutzerService.count() == 5

        when:
        benutzerService.delete(benutzerId)
        sessionFactory.currentSession.flush()

        then:
        benutzerService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Benutzer benutzer = new Benutzer()
        benutzerService.save(benutzer)

        then:
        benutzer.id != null
    }
}
