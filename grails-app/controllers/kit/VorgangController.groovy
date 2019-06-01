package kit

import com.sun.javafx.collections.MappingChange
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable
import grails.validation.ValidationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.multipart.MultipartFile

import static org.springframework.http.HttpStatus.*

class VorgangController {

    VorgangService vorgangService
    ImageService imageService
    SpringSecurityService springSecurityService
    def mailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def neu() {
        respond new Vorgang(params)
    }


    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def uebermitteln(Vorgang vorgang) {
        if (vorgang == null) {
            notFound()
            return
        }

        vorgang.ortschaften = [] as Set
        vorgang.kategorien = [] as Set
        vorgang.oeffentlich = true

        try {
            VorgangsKategorie.all.each { VorgangsKategorie it ->
                if (vorgang.bezeichnung?.toLowerCase()?.contains(it.name.toLowerCase()) ||
                        vorgang.beschreibung?.toLowerCase()?.contains(it.name.toLowerCase())
                ) {
                    vorgang.kategorien << (it)
                }
            }
            Gemeindeteil.all.each { Gemeindeteil it ->
                if (vorgang.bezeichnung?.toLowerCase()?.contains(it.name.toLowerCase()) ||
                        vorgang.beschreibung?.toLowerCase()?.contains(it.name.toLowerCase().split('-').find())
                ) {
                    vorgang.ortschaften << (it)
                }
            }

            vorgangService.save(vorgang)


            def benutzer = 'Anonym'
            if (vorgang.vorschlagVon && !vorgang.initiatorVerstecken) {
                benutzer = vorgang.vorschlagVon
            }

            String t = "erstellt"
            VorgangsLog vl = new VorgangsLog()
            vl.vorgang = vorgang
            vl.text = t
            vl.komplett = t
            vl.benutzer = benutzer as String
            vl.save(flush: true, failOnError: true)
        } catch (ValidationException e) {
            respond vorgang.errors, view: 'create'
            return
        }

        flash.message = "Ihr Anliegen wurde übermittelt. Sie können nun noch Bilder hinzufügen (Schaltfläche 'Bild hochladen')."
        
        mailService.sendMail {
           to System.getProperty('ADMIN_EMAIL')
           from System.getProperty('ADMIN_EMAIL') 
           subject "Neuer Vorgang $vorgang"
           text 'Ein neuer Vorgang wurde angelegt'
        }

        redirect action: 'show', id: vorgang.id

    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def suche() {
        params.max = Math.min(params.max ?: 10, 100)
        String q = params.remove('q')
        def x = Vorgang.findAllByBezeichnungRlikeAndOeffentlich(q, true, params)
        def y = Vorgang.findAllByBeschreibungRlikeAndOeffentlich(q, true, params)
        def z = (x + y).sort { it.lastUpdated.time }.reverse()
        flash.message = "Ergebnis für Suchwort '$q'"
        respond z, model: [vorgangCount: z.size()]
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def liste(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Vorgang.findAllByOeffentlich(true, params), model: [vorgangCount: Vorgang.countByOeffentlich(true)]
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Vorgang.findAll(params), model: [vorgangCount: vorgangService.count()]
    }

    def publish(Long id){
        def x = vorgangService.get(id)
        x.oeffentlich = true
        x.save(flush: true)
        redirect action:'show', id:id
    }

    def unpublish(Long id){
        def x = vorgangService.get(id)
        x.oeffentlich = false
        x.save(flush: true)
        redirect action:'show', id:id
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def show(Long id) {
        def x = vorgangService.get(id)
        respond x
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def comment() {
        String text = params.text
        if (text?.trim()) {
            String name = params.name ?: springSecurityService.currentUser.username
            VorgangsKommentar k = new VorgangsKommentar()
            k.benutzer = name
            k.text = text
            k.vorgang = Vorgang.get(params.id)
            k.save(flush: true, failOnError: true)
            flash.message = "Vielen Dank, ihr Kommentar wird in Kürze moderiert werden."
            
            mailService.sendMail {
               to System.getProperty('ADMIN_EMAIL')
               from System.getProperty('ADMIN_EMAIL') 
               subject "Neuer Kommentar an Vorgang ${Vorgang.get(params.id)}"
               text 'Ein neuer Kommentar wurde angelegt: $text'
            }
        }
        redirect action: 'show', id: params.id
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
            params.getList('kategorien').each {
                vorgang.kategorien << (VorgangsKategorie.get(it))
            }
            params.getList('ortschaften').each {
                vorgang.addToOrtschaften(Gemeindeteil.get(it))
            }

            vorgangService.save(vorgang)


            def benutzer = SecurityContextHolder.getContext().getAuthentication()?.name
            String t = "erstellt"
            VorgangsLog vl = new VorgangsLog()
            vl.vorgang = vorgang
            vl.text = t
            vl.komplett = t
            vl.benutzer = benutzer as String
            vl.save(flush: true, failOnError: true)
        } catch (ValidationException e) {
            respond vorgang.errors, view: 'create'
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
        log.info params as String
        if (vorgang == null) {
            notFound()
            return
        }

        try {
            def x = vorgang.getChanges()
            def benutzer = SecurityContextHolder.getContext().getAuthentication()?.name
            if (x) {
                String t = "bearbeitet: ${vorgang.dirtyPropertyNames}"
                VorgangsLog vl = new VorgangsLog()
                vl.vorgang = vorgang
                vl.text = t
                vl.komplett = x
                vl.benutzer = benutzer as String
                vl.save(flush: true, failOnError: true)
            }
            params.getList('kategorien').each {
                vorgang.kategorien << (VorgangsKategorie.get(it))
            }
            params.getList('ortschaften').each {
                vorgang.addToOrtschaften(Gemeindeteil.get(it))
            }
            vorgangService.save(vorgang)
        } catch (ValidationException e) {
            respond vorgang.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vorgang.label', default: 'Vorgang'), vorgang.id])
                redirect vorgang
            }
            '*' { respond vorgang, [status: OK] }
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
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def uploadImage(FeaturedImageCommand cmd) {

        Vorgang vorgang = vorgangService.get(cmd.id)

        if (cmd.hasErrors()) {
            flash.message = "Bitte eine gültige Datei auswählen (Bild oder PDF)"
            respond(cmd, model: [vorgang: vorgang], view: 'show')
            return
        }

        Map file = imageService.uploadFeatureImage(cmd)
        if (file == null) {
            notFound()
            return
        }

        vorgang.addImage(file)

        flash.message = "Datei $file hinzugefügt!"

        redirect action: 'show', id: vorgang.id
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vorgang.label', default: 'Vorgang'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}

class FeaturedImageCommand implements Validateable {
    MultipartFile featuredImageFile
    Long id

    static constraints = {
        featuredImageFile validator: { val, obj ->
            if (val == null) {
                return false
            }
            if (val.empty) {
                return false
            }

            ['jpeg', 'jpg', 'png', 'pdf'].any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
