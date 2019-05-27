package kit

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.CompileStatic
import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.engine.event.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEvent

@SuppressWarnings(['UnnecessaryGetter', 'LineLength', 'Instanceof'])
@CompileStatic
class BenutzerPasswordEncoderListener extends AbstractPersistenceEventListener {

    @Autowired
    SpringSecurityService springSecurityService

    BenutzerPasswordEncoderListener(Datastore datastore) {
        super(datastore)
    }

    @Override
    protected void onPersistenceEvent(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof Benutzer) {
            Benutzer u = (event.entityObject as Benutzer)
            if (u.password && (event.eventType == EventType.PreInsert || (event.eventType == EventType.PreUpdate && u.isDirty('password')))) {
                event.getEntityAccess().setProperty('password', encodePassword(u.password))
            }
        }
    }

    @Override
    boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        eventType == PreUpdateEvent || eventType == PreInsertEvent
    }

    private String encodePassword(String password) {
        return springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}