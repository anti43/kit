package kit

import com.sun.javafx.collections.MappingChange
import grails.converters.JSON
import grails.validation.Validateable
import grails.validation.ValidationException
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

class VorgangController {

    VorgangService vorgangService
    ImageService imageService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vorgangService.list(params), model:[vorgangCount: vorgangService.count()]
    }

    def show(Long id) {
        //respond vorgangService.get(id)
        redirect action: 'edit', id: id
    }

    def create() {
        respond new Vorgang(params)
    }

    def save(Vorgang vorgang) {
        if (vorgang == null) {
            notFound()
            return
        }

        try {
            vorgang.mandant = vorgang.getCurrentMandant()
            vorgangService.save(vorgang)
        } catch (ValidationException e) {
            respond vorgang.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vorgang.label', default: 'Vorgang'), vorgang.id])
                redirect vorgang
            }
            '*' { respond vorgang, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vorgangService.get(id)
    }

    def update(Vorgang vorgang) {
        if (vorgang == null) {
            notFound()
            return
        }

        try {
            vorgangService.save(vorgang)
        } catch (ValidationException e) {
            respond vorgang.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vorgang.label', default: 'Vorgang'), vorgang.id])
                redirect vorgang
            }
            '*'{ respond vorgang, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vorgangService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vorgang.label', default: 'Vorgang'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def uploadImage(FeaturedImageCommand cmd) {

        Vorgang vorgang = vorgangService.get(cmd.id)

        if (cmd.hasErrors()) {
            flash.message = cmd.errors as String
            respond(cmd, model: [vorgang: vorgang], view: 'show')
            return
        }

        Map file = imageService.uploadFeatureImage(cmd)
        if (file == null) {
            notFound()
            return
        }

        vorgang.addImage(file)

        render (['file': file.name] as JSON)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vorgang.label', default: 'Vorgang'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

class FeaturedImageCommand implements Validateable {
    MultipartFile featuredImageFile
    Long id

    static constraints = {
        featuredImageFile  validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

            ['jpeg', 'jpg', 'png', 'pdf'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
