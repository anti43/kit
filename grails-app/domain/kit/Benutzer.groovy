package kit

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class Benutzer implements Serializable {

    private static final long serialVersionUID = 1

    Date dateCreated
    Date lastUpdated

    Mandant mandant

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Rolle> getAuthorities() {
        ((BenutzerRolle.findAllByBenutzer(this) as List<BenutzerRolle>)*.rolle + [new Rolle(authority: mandant.authority)]) as Set<Rolle>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
        password column: '`password`'
    }

}
