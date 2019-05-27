package kit

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured("ROLE_ADMIN")
class MandantController {

    MandantService mandantService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def createBenutzer(Mandant mandant){
        forward controller: 'benutzer', action: 'show', id: mandant.createBenutzer(params.name, params.password).id
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond mandantService.list(params), model:[mandantCount: mandantService.count()]
    }

    def show(Long id) {
        respond mandantService.get(id)
    }

    def create() {
        respond new Mandant(params)
    }

    def save(Mandant mandant) {
        if (mandant == null) {
            notFound()
            return
        }

        try {
            mandantService.save(mandant)
        } catch (ValidationException e) {
            respond mandant.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mandant.label', default: 'Mandant'), mandant.id])
                redirect mandant
            }
            '*' { respond mandant, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond mandantService.get(id)
    }

    def update(Mandant mandant) {
        if (mandant == null) {
            notFound()
            return
        }

        try {
            mandantService.save(mandant)
        } catch (ValidationException e) {
            respond mandant.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mandant.label', default: 'Mandant'), mandant.id])
                redirect mandant
            }
            '*'{ respond mandant, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        mandantService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mandant.label', default: 'Mandant'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mandant.label', default: 'Mandant'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
