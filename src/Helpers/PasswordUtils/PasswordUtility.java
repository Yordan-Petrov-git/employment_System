package Helpers.PasswordUtils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtility {

    private static final SecureRandom RAND = new SecureRandom();//Random for the salt

    public static Optional<String> generateSalt(final int length) {
        //Method to generate secure random salt 515 bit
        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;//Key bit length
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";//Algorithm for encryption

    public static Optional<String> hashPassword(String password, String salt) {
        //Hashing algorithm for the password
        char[] chars = password.toCharArray();//Gets password in character array
        byte[] bytes = salt.getBytes();//Gets salt in byte array

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();//Cleans memory for security purposes
        }
    }

    public static boolean verifyPassword(String password, String key, String salt) {
        //Method that verifies if the password mathes with the genereated key and its unique salt
        Optional<String> optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);

    }

}
