package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ImpressumServiceSpec extends Specification {

    ImpressumService impressumService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Impressum(...).save(flush: true, failOnError: true)
        //new Impressum(...).save(flush: true, failOnError: true)
        //Impressum impressum = new Impressum(...).save(flush: true, failOnError: true)
        //new Impressum(...).save(flush: true, failOnError: true)
        //new Impressum(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //impressum.id
    }

    void "test get"() {
        setupData()

        expect:
        impressumService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Impressum> impressumList = impressumService.list(max: 2, offset: 2)

        then:
        impressumList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        impressumService.count() == 5
    }

    void "test delete"() {
        Long impressumId = setupData()

        expect:
        impressumService.count() == 5

        when:
        impressumService.delete(impressumId)
        sessionFactory.currentSession.flush()

        then:
        impressumService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Impressum impressum = new Impressum()
        impressumService.save(impressum)

        then:
        impressum.id != null
    }
}
