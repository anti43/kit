package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DateiAnhangController {

    DateiAnhangService daService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond daService.list(params), model:[bildCount: daService.count()]
    }

    def show(Long id) {
        respond daService.get(id)
    }

    def create() {
        respond new DateiAnhang(params)
    }

    def save(DateiAnhang bild) {
        if (bild == null) {
            notFound()
            return
        }

        try {
            bild.mandant = bild.getCurrentMandant()
            daService.save(bild)
            bild.vorgang.addToBilder(bild)
            bild.vorgang.save()
        } catch (ValidationException e) {
            respond bild.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dateiAnhang.label', default: 'DateiAnhang'), bild.id])
                redirect bild
            }
            '*' { respond bild, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond daService.get(id)
    }

    def update(DateiAnhang bild) {
        if (bild == null) {
            notFound()
            return
        }

        try {
            daService.save(bild)
        } catch (ValidationException e) {
            respond bild.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dateiAnhang.label', default: 'DateiAnhang'), bild.id])
                redirect bild
            }
            '*'{ respond bild, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        daService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dateiAnhang.label', default: 'DateiAnhang'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dateiAnhang.label', default: 'DateiAnhang'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
