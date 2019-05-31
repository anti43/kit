package kit

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ImpressumController {

    ImpressumService impressumService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond impressumService.list(params), model:[impressumCount: impressumService.count()]
    }


    def show(Long id) {
        respond impressumService.get(id)
    }

    def create() {
        respond new Impressum(params)
    }

    def save(Impressum impressum) {
        if (impressum == null) {
            notFound()
            return
        }

        try {
            impressumService.save(impressum)
        } catch (ValidationException e) {
            respond impressum.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'impressum.label', default: 'Impressum'), impressum.id])
                redirect impressum
            }
            '*' { respond impressum, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond impressumService.get(id)
    }

    def update(Impressum impressum) {
        if (impressum == null) {
            notFound()
            return
        }

        try {
            impressumService.save(impressum)
        } catch (ValidationException e) {
            respond impressum.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'impressum.label', default: 'Impressum'), impressum.id])
                redirect impressum
            }
            '*'{ respond impressum, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        impressumService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'impressum.label', default: 'Impressum'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'impressum.label', default: 'Impressum'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
