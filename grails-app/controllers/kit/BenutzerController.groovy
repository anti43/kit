package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BenutzerController {

    BenutzerService benutzerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond benutzerService.list(params), model:[benutzerCount: benutzerService.count()]
    }

    def show(Long id) {
        respond benutzerService.get(id)
    }

    def create() {
        respond new Benutzer(params)
    }

    def save(Benutzer benutzer) {
        if (benutzer == null) {
            notFound()
            return
        }

        try {
            benutzerService.save(benutzer)
        } catch (ValidationException e) {
            respond benutzer.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'benutzer.label', default: 'Benutzer'), benutzer.id])
                redirect benutzer
            }
            '*' { respond benutzer, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond benutzerService.get(id)
    }

    def update(Benutzer benutzer) {
        if (benutzer == null) {
            notFound()
            return
        }

        try {
            benutzerService.save(benutzer)
        } catch (ValidationException e) {
            respond benutzer.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'benutzer.label', default: 'Benutzer'), benutzer.id])
                redirect benutzer
            }
            '*'{ respond benutzer, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        benutzerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'benutzer.label', default: 'Benutzer'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'benutzer.label', default: 'Benutzer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
