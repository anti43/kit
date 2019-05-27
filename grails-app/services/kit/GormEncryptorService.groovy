package kit

import org.jasypt.encryption.pbe.PBEStringEncryptor
import org.jasypt.exceptions.EncryptionInitializationException
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor
import org.jasypt.salt.SaltGenerator
import org.springframework.beans.factory.InitializingBean
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class GormEncryptorService implements PBEStringEncryptor, InitializingBean, SaltGenerator {

    HibernatePBEStringEncryptor encryptor
    String registeredName
/**
 * Invoked by a BeanFactory after it has set all bean properties supplied
 * (and satisfied BeanFactoryAware and ApplicationContextAware).
 * <p>This method allows the bean instance to perform initialization only
 * possible when all bean properties have been set and to throw an
 * exception in the event of misconfiguration.
 * @throws Exception in the event of misconfiguration (such
 * as failure to set an essential property) or if initialization fails.
 */

    @Override
    void afterPropertiesSet() throws Exception {
        encryptor = new HibernatePBEStringEncryptor()
        encryptor.setSaltGenerator(this)
        encryptor.setPassword("nC3PbgAR8vC6hnd89SYyYTbS5x2rYax2dJnBCnxVAJFy7Uq3X2ub8RzY9gpSgCrz")

        HibernatePBEEncryptorRegistry.instance.registerPBEStringEncryptor('gormEncryptor', this)
    }
/**
 * Encrypt the input message
 *
 * @param message the message to be encrypted
 * @return the result of encryption
 */
    @Override
    String encrypt(String message) {
        return encryptor.encrypt(message)
    }

    /**
     * Decrypt an encrypted message
     *
     * @param encryptedMessage the encrypted message to be decrypted
     * @return the result of decryption
     */
    @Override
    String decrypt(String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage)
    }

    /**
     * <p>
     * Sets a password to be used by the encryptor.
     * </p>
     *
     * @param password the password to be used.
     */
    @Override
    void setPassword(String password) {
    }
/**
 * <p>
 * This method will be called for requesting the generation of a new
 * salt of the specified length.
 * </p>
 *
 * @param lengthBytes the requested length for the salt.
 * @return the generated salt.
 */

    @Override
    byte[] generateSalt(int lengthBytes) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
            String currentAuthName = authentication.getAuthorities().find { it?.authority?.startsWith('_') }?.authority
            log.trace('currentAuthName: ' + currentAuthName)
            def saltBytes
            try {
                saltBytes = currentAuthName.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new EncryptionInitializationException(
                        "Invalid charset specified")
            }

            if (saltBytes.length < lengthBytes) {
                throw new EncryptionInitializationException(
                        "Requested salt larger than set")
            }
            final byte[] generatedSalt = new byte[lengthBytes]
            System.arraycopy(saltBytes, 0, generatedSalt, 0, lengthBytes)
            return generatedSalt
        } catch (e) {
            log.error(e.message, e)
            throw e
        }
    }

    /**
     * <p>
     * Determines if the digests and encrypted messages created with a
     * specific salt generator will include (prepended) the unencrypted
     * salt itself, so that it can be used for matching and decryption
     * operations.
     * </p>
     * <p>
     * Generally, including the salt unencrypted in encryption results will
     * be mandatory for randomly generated salts, or for those generated in a
     * non-predictable manner.
     * Otherwise, digest matching and decryption operations will always fail.
     * For fixed salts, inclusion will be optional (and in fact undesirable
     * if we want to hide the salt value).
     * </p>
     *
     * @return whether the plain (unencrypted) salt has to be included in
     *         encryption results or not.
     */
    @Override
    boolean includePlainSaltInEncryptionResults() {
        return false
    }
}
