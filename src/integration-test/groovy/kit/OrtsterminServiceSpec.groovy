package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OrtsterminServiceSpec extends Specification {

    OrtsterminService ortsterminService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Ortstermin(...).save(flush: true, failOnError: true)
        //new Ortstermin(...).save(flush: true, failOnError: true)
        //Ortstermin ortstermin = new Ortstermin(...).save(flush: true, failOnError: true)
        //new Ortstermin(...).save(flush: true, failOnError: true)
        //new Ortstermin(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //ortstermin.id
    }

    void "test get"() {
        setupData()

        expect:
        ortsterminService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Ortstermin> ortsterminList = ortsterminService.list(max: 2, offset: 2)

        then:
        ortsterminList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        ortsterminService.count() == 5
    }

    void "test delete"() {
        Long ortsterminId = setupData()

        expect:
        ortsterminService.count() == 5

        when:
        ortsterminService.delete(ortsterminId)
        sessionFactory.currentSession.flush()

        then:
        ortsterminService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Ortstermin ortstermin = new Ortstermin()
        ortsterminService.save(ortstermin)

        then:
        ortstermin.id != null
    }
}
