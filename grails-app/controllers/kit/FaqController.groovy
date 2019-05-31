package kit

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FaqController {

    FaqService faqService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond faqService.list(params), model:[faqCount: faqService.count()]
    }

    def show(Long id) {
        respond faqService.get(id)
    }

    def create() {
        respond new Faq(params)
    }

    def save(Faq faq) {
        if (faq == null) {
            notFound()
            return
        }

        try {
            faqService.save(faq)
        } catch (ValidationException e) {
            respond faq.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'faq.label', default: 'Faq'), faq.id])
                redirect faq
            }
            '*' { respond faq, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond faqService.get(id)
    }

    def update(Faq faq) {
        if (faq == null) {
            notFound()
            return
        }

        try {
            faqService.save(faq)
        } catch (ValidationException e) {
            respond faq.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'faq.label', default: 'Faq'), faq.id])
                redirect faq
            }
            '*'{ respond faq, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        faqService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'faq.label', default: 'Faq'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'faq.label', default: 'Faq'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
