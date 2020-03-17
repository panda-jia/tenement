package online.yang.cloud.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author 孙嘉
 * created in 2020/2/5 18:05
 */

public class MD5Tools {

        public static String reverseToMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
