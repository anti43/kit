package kit

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

class DateiAnhangController {

    DateiAnhangService dateiAnhangService
    ImageService imageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Transactional
    def remove(Long id){
        def d = DateiAnhang.get(id)
        def vid = d.vorgang
        vid.removeFromBilder(d)
        vid.save()
        d.delete()
        redirect controller: 'vorgang', action: 'show', id: vid.id
    }



    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def render(Long id){
        def d = DateiAnhang.get(id)
        render file: imageService.getBytes(d.pfad), fileName: d.name
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dateiAnhangService.list(params), model:[bildCount: dateiAnhangService.count()]
    }

    def show(Long id) {
        respond dateiAnhangService.get(id)
    }

    def create() {
        respond new DateiAnhang(params)
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def save(DateiAnhang bild) {
        if (bild == null) {
            notFound()
            return
        }

        try {
            dateiAnhangService.save(bild)
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
        respond dateiAnhangService.get(id)
    }

    def update(DateiAnhang bild) {
        if (bild == null) {
            notFound()
            return
        }

        try {
            dateiAnhangService.save(bild)
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

        dateiAnhangService.delete(id)

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
