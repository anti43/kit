package kit

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class VorgangsLogController {

    VorgangsLogService vorgangsLogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vorgangsLogService.list(params), model:[vorgangsLogCount: vorgangsLogService.count()]
    }

    def show(Long id) {
        respond vorgangsLogService.get(id)
    }

    def create() {
        respond new VorgangsLog(params)
    }

    def save(VorgangsLog vorgangsLog) {
        if (vorgangsLog == null) {
            notFound()
            return
        }

        try {
            vorgangsLogService.save(vorgangsLog)
        } catch (ValidationException e) {
            respond vorgangsLog.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vorgangsLog.label', default: 'VorgangsLog'), vorgangsLog.id])
                redirect vorgangsLog
            }
            '*' { respond vorgangsLog, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vorgangsLogService.get(id)
    }

    def update(VorgangsLog vorgangsLog) {
        if (vorgangsLog == null) {
            notFound()
            return
        }

        try {
            vorgangsLogService.save(vorgangsLog)
        } catch (ValidationException e) {
            respond vorgangsLog.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vorgangsLog.label', default: 'VorgangsLog'), vorgangsLog.id])
                redirect vorgangsLog
            }
            '*'{ respond vorgangsLog, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vorgangsLogService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vorgangsLog.label', default: 'VorgangsLog'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vorgangsLog.label', default: 'VorgangsLog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
