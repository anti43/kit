package kit

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FaqServiceSpec extends Specification {

    FaqService faqService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Faq(...).save(flush: true, failOnError: true)
        //new Faq(...).save(flush: true, failOnError: true)
        //Faq faq = new Faq(...).save(flush: true, failOnError: true)
        //new Faq(...).save(flush: true, failOnError: true)
        //new Faq(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //faq.id
    }

    void "test get"() {
        setupData()

        expect:
        faqService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Faq> faqList = faqService.list(max: 2, offset: 2)

        then:
        faqList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        faqService.count() == 5
    }

    void "test delete"() {
        Long faqId = setupData()

        expect:
        faqService.count() == 5

        when:
        faqService.delete(faqId)
        sessionFactory.currentSession.flush()

        then:
        faqService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Faq faq = new Faq()
        faqService.save(faq)

        then:
        faq.id != null
    }
}
