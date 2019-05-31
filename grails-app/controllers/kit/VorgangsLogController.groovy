package kit

import grails.validation.ValidationException
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*

class VorgangsLogController {

    VorgangsLogService vorgangsLogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Long id) {
        respond vorgangsLogService.get(id)
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
