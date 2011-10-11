package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class MD5 {
    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String hash(byte[] data) {
        MessageDigest dig;
        try {
            dig = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No MD5 implementation found in the system.");
        }
        byte[] messageDigest = dig.digest(data);
        StringBuffer result = new StringBuffer();
        for (byte b : messageDigest) {
            int value = 0xFF & b;
            result.append(hexChars[value / 16]);
            result.append(hexChars[value % 16]);
        }
        return result.toString();
    }
}
