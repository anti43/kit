package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MandantServiceSpec extends Specification {

    MandantService mandantService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Mandant(...).save(flush: true, failOnError: true)
        //new Mandant(...).save(flush: true, failOnError: true)
        //Mandant mandant = new Mandant(...).save(flush: true, failOnError: true)
        //new Mandant(...).save(flush: true, failOnError: true)
        //new Mandant(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //mandant.id
    }

    void "test get"() {
        setupData()

        expect:
        mandantService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Mandant> mandantList = mandantService.list(max: 2, offset: 2)

        then:
        mandantList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        mandantService.count() == 5
    }

    void "test delete"() {
        Long mandantId = setupData()

        expect:
        mandantService.count() == 5

        when:
        mandantService.delete(mandantId)
        sessionFactory.currentSession.flush()

        then:
        mandantService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Mandant mandant = new Mandant()
        mandantService.save(mandant)

        then:
        mandant.id != null
    }
}
