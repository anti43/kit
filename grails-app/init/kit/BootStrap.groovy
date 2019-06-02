package kit

import javax.print.DocFlavor

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

        String adminPass = System.getenv('ADMINPASS')
        if(!Benutzer.findByUsername('admin')){
            Benutzer b = new Benutzer()
            b.username = 'admin'
            b.password = adminPass
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

        String mainUser = System.getenv('USER')
        String mainUserPass = System.getenv('PASS')
        if(!Benutzer.findByUsername(mainUser)){
            Benutzer b = new Benutzer()
            b.username = mainUser
            b.password = mainUserPass
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

        if(Gemeindeteil.count==0){
            ["Alle",
             "Gemeinde",
             "Besch" ,
             "Borg",
             "Büschdorf",
             "Eft-Hellendorf",
             "Keßlingen",
             "Münzingen",
             "Nennig",
             "Oberleuken",
             "Oberperl",
             "Perl",
             "Sehndorf",
             "Sinz",
             "Tettingen-Butzdorf",
             "Wochern"].each{
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

        if(Faq.count == 0){
            Faq f = new Faq()
            f.frage="Was ist das hier?"
            f.antwort="Das Bürgerinformationsportal der Fraktion der Grünen im Gemeinderat Perl/OV Grüne Perl"
            f.save(flush: true, failOnError: true)

            Faq f2 = new Faq()
            f2.frage="Was passiert mit meinem Anliegen?"
            f2.antwort="Wir prüfen jedes Anliegen sehr gewissenhaft und entscheiden dann, " +
                    "ob wir als Fraktion im Gemeindderat einen entsprechenden Antrag stellen/tätig werden. " +
                    "Wir behalten uns vor, den Antrag umzuformulieren."
            f2.save(flush: true, failOnError: true)

            Faq f3 = new Faq()
            f3.frage="Werde ich benachrichtigt bei Änderungen/Rückfragen oder falls mein Antrag entschieden wurde?"
            f3.antwort="Ja, falls sie uns ihre Email-Adresse hinterlassen werden wir sie nach Möglichkeit kontaktieren."
            f3.save(flush: true, failOnError: true)
        }

        if(Impressum.count==0){
            Impressum i = new Impressum()
            i.bezeichnung = "Inhaltlich Verantwortlicher"
            i.text = "Bündnis90/Die Grünen OV Perl, vertreten durch Andreas Weber"
            i.save(flush: true, failOnError: true)
        }
    }
    def destroy = {
    }
}
