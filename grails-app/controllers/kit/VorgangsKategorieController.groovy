package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class VorgangsKategorieController {

    VorgangsKategorieService vorgangsKategorieService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def vorgaenge(String id){

        flash.message = "Alle Vorgänge for Kategorie $id"
        render view: '/vorgang/index', model: [vorgangList: Vorgang.createCriteria().list {
            kategorien {
                'in'('name', [id])
            }
        }]
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vorgangsKategorieService.list(params), model:[vorgangsKategorieCount: vorgangsKategorieService.count()]
    }

    def show(Long id) {
        respond vorgangsKategorieService.get(id)
    }

    def create() {
        respond new VorgangsKategorie(params)
    }

    def save(VorgangsKategorie vorgangsKategorie) {
        if (vorgangsKategorie == null) {
            notFound()
            return
        }

        try {
            vorgangsKategorieService.save(vorgangsKategorie)
        } catch (ValidationException e) {
            respond vorgangsKategorie.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vorgangsKategorie.label', default: 'VorgangsKategorie'), vorgangsKategorie.id])
                redirect vorgangsKategorie
            }
            '*' { respond vorgangsKategorie, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vorgangsKategorieService.get(id)
    }

    def update(VorgangsKategorie vorgangsKategorie) {
        if (vorgangsKategorie == null) {
            notFound()
            return
        }

        try {
            vorgangsKategorieService.save(vorgangsKategorie)
        } catch (ValidationException e) {
            respond vorgangsKategorie.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vorgangsKategorie.label', default: 'VorgangsKategorie'), vorgangsKategorie.id])
                redirect vorgangsKategorie
            }
            '*'{ respond vorgangsKategorie, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vorgangsKategorieService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vorgangsKategorie.label', default: 'VorgangsKategorie'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vorgangsKategorie.label', default: 'VorgangsKategorie'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
