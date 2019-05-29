package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GemeindeteilController {

    GemeindeteilService gemeindeteilService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond gemeindeteilService.list(params), model:[gemeindeteilCount: gemeindeteilService.count()]
    }

    def show(Long id) {
        respond gemeindeteilService.get(id)
    }

    def create() {
        respond new Gemeindeteil(params)
    }

    def save(Gemeindeteil gemeindeteil) {
        if (gemeindeteil == null) {
            notFound()
            return
        }

        try {
            gemeindeteilService.save(gemeindeteil)
        } catch (ValidationException e) {
            respond gemeindeteil.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gemeindeteil.label', default: 'Gemeindeteil'), gemeindeteil.id])
                redirect gemeindeteil
            }
            '*' { respond gemeindeteil, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond gemeindeteilService.get(id)
    }

    def update(Gemeindeteil gemeindeteil) {
        if (gemeindeteil == null) {
            notFound()
            return
        }

        try {
            gemeindeteilService.save(gemeindeteil)
        } catch (ValidationException e) {
            respond gemeindeteil.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'gemeindeteil.label', default: 'Gemeindeteil'), gemeindeteil.id])
                redirect gemeindeteil
            }
            '*'{ respond gemeindeteil, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        gemeindeteilService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'gemeindeteil.label', default: 'Gemeindeteil'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gemeindeteil.label', default: 'Gemeindeteil'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
