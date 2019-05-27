package kit

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class BenutzerRolle implements Serializable {

	private static final long serialVersionUID = 1

	Benutzer benutzer
	Rolle rolle

	@Override
	boolean equals(other) {
		if (other instanceof BenutzerRolle) {
			other.benutzerId == benutzer?.id && other.rolleId == rolle?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (benutzer) {
            hashCode = HashCodeHelper.updateHash(hashCode, benutzer.id)
		}
		if (rolle) {
		    hashCode = HashCodeHelper.updateHash(hashCode, rolle.id)
		}
		hashCode
	}

	static BenutzerRolle get(long benutzerId, long rolleId) {
		criteriaFor(benutzerId, rolleId).get()
	}

	static boolean exists(long benutzerId, long rolleId) {
		criteriaFor(benutzerId, rolleId).count()
	}

	private static DetachedCriteria criteriaFor(long benutzerId, long rolleId) {
		BenutzerRolle.where {
			benutzer == Benutzer.load(benutzerId) &&
			rolle == Rolle.load(rolleId)
		}
	}

	static BenutzerRolle create(Benutzer benutzer, Rolle rolle, boolean flush = false) {
		def instance = new BenutzerRolle(benutzer: benutzer, rolle: rolle)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(Benutzer u, Rolle r) {
		if (u != null && r != null) {
			BenutzerRolle.where { benutzer == u && rolle == r }.deleteAll()
		}
	}

	static int removeAll(Benutzer u) {
		u == null ? 0 : BenutzerRolle.where { benutzer == u }.deleteAll() as int
	}

	static int removeAll(Rolle r) {
		r == null ? 0 : BenutzerRolle.where { rolle == r }.deleteAll() as int
	}

	static constraints = {
	    benutzer nullable: false
		rolle nullable: false, validator: { Rolle r, BenutzerRolle ur ->
			if (ur.benutzer?.id) {
				if (BenutzerRolle.exists(ur.benutzer.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['benutzer', 'rolle']
		version false
	}
}
