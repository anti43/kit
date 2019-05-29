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
            a.displayName = "Administrativer Benutzer"
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

        if(Mandant.count==1){
            ["Grüne OV Perl", "Ortsrat Besch"].each{
                Mandant a = new Mandant()
                a.displayName = "$it"
                a.logo = "https://upload.wikimedia.org/wikipedia/commons/5/51/B%C3%BCndnis_90_-_Die_Gr%C3%BCnen_Logo_%28transparent%29.svg"//"""https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Wappen_Besch.svg/1280px-Wappen_Besch.svg.png"
                a.save(flush: true, failOnError: true)

                a.createBenutzer("$it", 'RandomPassowrd1')
            }

            ["Alle", "Perl", "Besch", "Nennig"].each{
                Gemeindeteil g = new Gemeindeteil()
                g.name = it
                g.save(flush: true, failOnError: true)
            }
        }

        if (VorgangsKategorie.count == 0) {
            VorgangsKategorie.withNewTransaction {
                ["Umweltschutz","Verkehr","Infrastruktur"].each {
                    VorgangsKategorie a = new VorgangsKategorie()
                    a.name = "$it"
                    a.save(flush: true, failOnError: true)
                }
            }
        }
    }
    def destroy = {
    }
}
