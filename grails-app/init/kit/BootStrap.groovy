package kit

class BootStrap {

    def init = { servletContext ->
        if(!Rolle.findByAuthority('ROLE_USERS')){
            Rolle r = new Rolle(authority: 'ROLE_USERS')
            r.save(flush: true)
        }

        if(!Rolle.findByAuthority('ROLE_ADMIN')){
            Rolle r = new Rolle(authority: 'ROLE_ADMIN')
            r.save(flush: true)
        }

        if(!Benutzer.findByUsername('admin')){
            Mandant a = new Mandant()
            a.displayName = "Admin"
            a.save(flush: true, failOnError: true)

            Benutzer b = new Benutzer()
            b.username = 'admin'
            b.password = 'RandomHorse5'
            b.mandant = a
            b.save(flush: true, failOnError: true)

            BenutzerRolle bn = new BenutzerRolle()
            bn.rolle = Rolle.findByAuthority('ROLE_USERS')
            bn.benutzer = b
            bn.save(flush: true, failOnError: true)

            BenutzerRolle bn2 = new BenutzerRolle()
            bn2.rolle = Rolle.findByAuthority('ROLE_ADMIN')
            bn2.benutzer = b
            bn2.save(flush: true, failOnError: true)
        }

        if (Mandant.count == 1) {
            Mandant.withNewTransaction {
                3.times {
                    Mandant a = new Mandant()
                    a.displayName = "mandant$it"
                    a.save(flush: true, failOnError: true)

                    a.createBenutzer("user$it", 'password')
                }
            }

        }
    }
    def destroy = {
    }
}
