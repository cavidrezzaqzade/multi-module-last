package az.gov.mia.grps.auth.utility;

/**
 * @author Cavid Aslanov
 * @time 16/02/2021 - 3:07 PM
 **/

import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author Rasim R. Imanov
 */
@Component
public class SShaPasswordEncoder implements PasswordEncoder {

    private SecureRandom secureRandom;

    @Override
    public String encode(CharSequence rawPassword) {
        return encode(rawPassword, generateSalt());
    }

    public String encode(CharSequence rawPwd, byte[] salt) {
        byte[] hash = this.encode(Utf8.encode(rawPwd), salt);
        System.out.println("Utf8.decode(Base64.encode(hash)) "+Utf8.decode(Base64.encode(hash)));
        return Utf8.decode(Base64.encode(hash));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            System.out.println("rawPassword: "+rawPassword);
            System.out.println("encodedPassword: "+encodedPassword);
            byte[] salt = extractSalt(encodedPassword);
            byte[] saltedPwd = encode(Utf8.encode(rawPassword), salt);

            System.out.println("Utf8.decode(Base64.encode(saltedPwd)) "+Utf8.decode(Base64.encode(saltedPwd)));

            return Utf8.decode(Base64.encode(saltedPwd)).equals(encodedPassword.replace("{SHA-512}",""));
        } catch (Exception ex) {
            LoggerFactory.getLogger(this.getClass().getName()).error(null, ex);
            return false;
        }
    }

    private byte[] encode(byte[] password, byte[] salt) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA");
            digester.update(plus(password, salt));
//            digester.update(salt);
            byte[] pwdSaltedHash = digester.digest();
            byte[] pwdSaltedHashWithSalt = plus(pwdSaltedHash, salt);
            return pwdSaltedHashWithSalt;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public byte[] generateSalt() {
        if (this.secureRandom == null) {
            this.secureRandom = new SecureRandom();
        }
        byte[] b = new byte[8];
        secureRandom.nextBytes(b);
        return b;
    }

    public byte[] extractSalt(String hashPwd) {
        System.out.println("password:  "+ hashPwd);
        String clearPassword = hashPwd.replace("{SHA-512}","");
        System.out.println("my clear password"+ clearPassword);
        //System.out.println(hashPwd.replaceFirst("\\{SHA\\-512\\}",""));
       // System.out.println(hashPwd.replace("\\{SHA\\-512\\}",""));
        byte[] mySalt = split(Base64.decode(Utf8.encode(clearPassword)), 20)[1];
        System.out.println("Utf8.encode(clearPassword) "+Utf8.encode(clearPassword));

        System.out.println("mySalt :" +mySalt);
        return mySalt;
    }

    public byte[][] split(byte[] arr, int length) {
        byte[] b1 = Arrays.copyOf(arr, length);
        byte[] b2 = Arrays.copyOfRange(arr, length, arr.length);
        return new byte[][]{b1, b2};
    }

    public byte[] plus(byte[] arr1, byte[] arr2) {
        byte[] arr = new byte[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, arr, 0, arr1.length);
        System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        return arr;
    }

    public static final class Utf8 {

        private static final Charset CHARSET = Charset.forName("UTF-8");

        /**
         * Get the bytes of the String in UTF-8 encoded form.
         */
        public static byte[] encode(CharSequence string) {
            try {
                ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
                byte[] bytesCopy = new byte[bytes.limit()];
                System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());

                return bytesCopy;
            } catch (CharacterCodingException e) {
                throw new IllegalArgumentException("Encoding failed", e);
            }
        }

        /**
         * Decode the bytes in UTF-8 form into a String.
         */
        public static String decode(byte[] bytes) {
            try {
                return CHARSET.newDecoder().decode(ByteBuffer.wrap(bytes)).toString();
            } catch (CharacterCodingException e) {
                throw new IllegalArgumentException("Decoding failed", e);
            }
        }
    }

}