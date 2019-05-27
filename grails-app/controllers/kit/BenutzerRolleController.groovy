package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BenutzerRolleController {

    BenutzerRolleService benutzerRolleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond benutzerRolleService.list(params), model:[benutzerRolleCount: benutzerRolleService.count()]
    }

    def show(Long id) {
        respond benutzerRolleService.get(id)
    }

    def create() {
        respond new BenutzerRolle(params)
    }

    def save(BenutzerRolle benutzerRolle) {
        if (benutzerRolle == null) {
            notFound()
            return
        }

        try {
            benutzerRolleService.save(benutzerRolle)
        } catch (ValidationException e) {
            respond benutzerRolle.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'benutzerRolle.label', default: 'BenutzerRolle'), benutzerRolle.id])
                redirect benutzerRolle
            }
            '*' { respond benutzerRolle, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond benutzerRolleService.get(id)
    }

    def update(BenutzerRolle benutzerRolle) {
        if (benutzerRolle == null) {
            notFound()
            return
        }

        try {
            benutzerRolleService.save(benutzerRolle)
        } catch (ValidationException e) {
            respond benutzerRolle.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'benutzerRolle.label', default: 'BenutzerRolle'), benutzerRolle.id])
                redirect benutzerRolle
            }
            '*'{ respond benutzerRolle, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        benutzerRolleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'benutzerRolle.label', default: 'BenutzerRolle'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'benutzerRolle.label', default: 'BenutzerRolle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
