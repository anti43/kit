package kit

import grails.gorm.transactions.Transactional
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic

@SuppressWarnings('GrailsStatelessService')
@CompileStatic
@Transactional
class ImageService implements GrailsConfigurationAware  {

    String cdnFolder

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getRequiredProperty('grails.cdnFolder')
    }

    Map uploadFeatureImage(FeaturedImageCommand cmd) {

        String filename = UUID.randomUUID().toString()
        def folderPath = "${cdnFolder}/images/" as String
        def folder = new File(folderPath)
        if ( !folder.exists() ) {
            boolean created = folder.mkdirs()
            if(!created){
                throw new RuntimeException("cannot create ${folder}")
            }
        }
        def dest = new File(folder, filename)
        dest.createNewFile()
        dest.bytes = cmd.featuredImageFile.bytes
        log.warn("Created $dest")
        return [file: new File(folder, filename), name: cmd.featuredImageFile.originalFilename]
    }

    byte[] getBytes(String filename){
        def folderPath = "${cdnFolder}/images/" as String
        def folder = new File(folderPath)
        def dest = new File(folder, filename)
        return dest.bytes
    }
}
