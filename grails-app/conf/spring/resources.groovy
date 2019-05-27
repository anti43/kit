import kit.BenutzerPasswordEncoderListener
import kit.GormEncryptorService

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(BenutzerPasswordEncoderListener, ref('hibernateDatastore'))
    hibernateStringEncryptor(GormEncryptorService){
        registeredName = "gormEncryptor"
    }
}
