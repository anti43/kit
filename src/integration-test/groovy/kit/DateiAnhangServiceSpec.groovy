package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DateiAnhangServiceSpec extends Specification {

    DateiAnhangService dateiAnhangService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DateiAnhang(...).save(flush: true, failOnError: true)
        //new DateiAnhang(...).save(flush: true, failOnError: true)
        //DateiAnhang dateiAnhang = new DateiAnhang(...).save(flush: true, failOnError: true)
        //new DateiAnhang(...).save(flush: true, failOnError: true)
        //new DateiAnhang(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dateiAnhang.id
    }

    void "test get"() {
        setupData()

        expect:
        dateiAnhangService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DateiAnhang> dateiAnhangList = dateiAnhangService.list(max: 2, offset: 2)

        then:
        dateiAnhangList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dateiAnhangService.count() == 5
    }

    void "test delete"() {
        Long dateiAnhangId = setupData()

        expect:
        dateiAnhangService.count() == 5

        when:
        dateiAnhangService.delete(dateiAnhangId)
        sessionFactory.currentSession.flush()

        then:
        dateiAnhangService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DateiAnhang dateiAnhang = new DateiAnhang()
        dateiAnhangService.save(dateiAnhang)

        then:
        dateiAnhang.id != null
    }
}
