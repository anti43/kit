package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OrtsterminController {

    OrtsterminService ortsterminService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ortsterminService.list(params), model:[ortsterminCount: ortsterminService.count()]
    }

    def show(Long id) {
        respond ortsterminService.get(id)
    }

    def create() {
        respond new Ortstermin(params)
    }

    def save(Ortstermin ortstermin) {
        if (ortstermin == null) {
            notFound()
            return
        }

        try {
            ortstermin.mandant = ortstermin.getCurrentMandant()
            ortsterminService.save(ortstermin)
            ortstermin.vorgang.addToOrtstermine(ortstermin)
            ortstermin.vorgang.save()
        } catch (ValidationException e) {
            respond ortstermin.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ortstermin.label', default: 'Ortstermin'), ortstermin.id])
                redirect ortstermin
            }
            '*' { respond ortstermin, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond ortsterminService.get(id)
    }

    def update(Ortstermin ortstermin) {
        if (ortstermin == null) {
            notFound()
            return
        }

        try {
            ortsterminService.save(ortstermin)
        } catch (ValidationException e) {
            respond ortstermin.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ortstermin.label', default: 'Ortstermin'), ortstermin.id])
                redirect ortstermin
            }
            '*'{ respond ortstermin, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        ortsterminService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ortstermin.label', default: 'Ortstermin'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ortstermin.label', default: 'Ortstermin'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
