package kit

import grails.validation.ValidationException
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

class VorgangsKommentarController {

    VorgangsKommentarService vorgangsKommentarService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort= params.sort?:'veroeffentlicht'
        params.order =  params.order?:"asc"
        respond vorgangsKommentarService.list(params), model:[vorgangsKommentarCount: vorgangsKommentarService.count()]
    }

    def show(Long id) {
        respond vorgangsKommentarService.get(id)
    }

    def publish(Long id){
        def  p = vorgangsKommentarService.get(id)
        p.veroeffentlicht = true
        vorgangsKommentarService.save(p)
        redirect controller: 'vorgang', action: 'show', id: p.vorgang.id
    }

    def create() {
        respond new VorgangsKommentar(params)
    }

    def save(VorgangsKommentar vorgangsKommentar) {
        if (vorgangsKommentar == null) {
            notFound()
            return
        }

        try {
            vorgangsKommentarService.save(vorgangsKommentar)
        } catch (ValidationException e) {
            respond vorgangsKommentar.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vorgangsKommentar.label', default: 'VorgangsKommentar'), vorgangsKommentar.id])
                redirect vorgangsKommentar
            }
            '*' { respond vorgangsKommentar, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vorgangsKommentarService.get(id)
    }

    def update(VorgangsKommentar vorgangsKommentar) {
        if (vorgangsKommentar == null) {
            notFound()
            return
        }

        try {
            vorgangsKommentarService.save(vorgangsKommentar)
        } catch (ValidationException e) {
            respond vorgangsKommentar.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vorgangsKommentar.label', default: 'VorgangsKommentar'), vorgangsKommentar.id])
                redirect vorgangsKommentar
            }
            '*'{ respond vorgangsKommentar, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vorgangsKommentarService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vorgangsKommentar.label', default: 'VorgangsKommentar'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vorgangsKommentar.label', default: 'VorgangsKommentar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
