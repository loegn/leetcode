import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String PASSWORD = "+XK7rtPmWbP0FW938za8O81eipTraxju7I23wzloR0Y=";
    private static final String DEFAULTCHARSET = "UTF-8";
    private static final String KEY_MD5 = "MD5";
    private static MessageDigest MD5DIGEST;

    public AESUtil() {
    }

    public static String encrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, getSecretKey());
            byte[] result = cipher.doFinal(byteContent);
//            return parseByte2HexStr(result);
            return new String(result, "utf-8");
        } catch (Exception var4) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, (String) null, var4);
            return null;
        }
    }

    public static String decrypt(String hexContent) throws Exception {
//        byte[] bytes = parseHexStr2Byte(hexContent);
        byte[] bytes = hexContent.getBytes("UTF-8");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, getSecretKey());
        byte[] result = cipher.doFinal(bytes);
        return new String(result, "utf-8");
    }

    private static SecretKeySpec getSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = null;
        kg = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed("+XK7rtPmWbP0FW938za8O81eipTraxju7I23wzloR0Y=".getBytes());
        kg.init(128, random);
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for (int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        String content = "i哦的撒哈佛i让疲倦送i大幅降低";
        try {
            String key = PASSWORD;
            Cipher encode = Cipher.getInstance("AES");
            byte[] bytes = content.getBytes("utf-8");
//            encode.init(Cipher.ENCRYPT_MODE, key);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            MD5DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
        }

    }

}
