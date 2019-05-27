package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class VorgangServiceSpec extends Specification {

    VorgangService vorgangService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Vorgang(...).save(flush: true, failOnError: true)
        //new Vorgang(...).save(flush: true, failOnError: true)
        //Vorgang vorgang = new Vorgang(...).save(flush: true, failOnError: true)
        //new Vorgang(...).save(flush: true, failOnError: true)
        //new Vorgang(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //vorgang.id
    }

    void "test get"() {
        setupData()

        expect:
        vorgangService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Vorgang> vorgangList = vorgangService.list(max: 2, offset: 2)

        then:
        vorgangList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        vorgangService.count() == 5
    }

    void "test delete"() {
        Long vorgangId = setupData()

        expect:
        vorgangService.count() == 5

        when:
        vorgangService.delete(vorgangId)
        sessionFactory.currentSession.flush()

        then:
        vorgangService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Vorgang vorgang = new Vorgang()
        vorgangService.save(vorgang)

        then:
        vorgang.id != null
    }
}
