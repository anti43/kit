package kit

import com.bloomhealthco.jasypt.GormEncryptedStringType
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Mandant implements Serializable {

	private static final long serialVersionUID = 1

    Date dateCreated
    Date lastUpdated

    String authority = "dummy"
    String logo
	String displayName

	static constraints = {
		authority nullable: false, blank: false, unique: true
        logo nullable: true
	}

	static mapping = {
		cache true
		displayName type: 'text'
	}

    def beforeInsert(){
        authority = '_' + UUID.randomUUID()
        log.error("New $this: $authority")
    }

    def beforeUpdate(){
        if(isDirty('authority')){
            throw new IllegalAccessException('authority cannot be changed because the encryption salt is based upon it.')
        }
    }

    Benutzer createBenutzer(String name, String pw) {
        Benutzer b = new Benutzer()
        b.username = name
        b.password = pw
        b.mandant = this
        b.save(flush: true, failOnError: true)

        BenutzerRolle bn = new BenutzerRolle()
        bn.rolle = Rolle.findByAuthority('ROLE_USERS')
        bn.benutzer = b
        bn.save(flush: true, failOnError: true)

        return b
    }

    String toString(){
        displayName
    }
}
