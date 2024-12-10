/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.simparer.common;

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;

public class Utils {
    public static final String key = "youareagooddalekyouareagooddalek";
    private static final String salt = "A long, but constant phrase that will be used each time as the salt.";
    private static final int iterations = 2000;
    private static final int keyLength = 128;
    private static final SecureRandom random = new SecureRandom();

    static {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

    }

    public static void main(String[] args) throws Exception {

        byte[] encrypted = Utils.encrypt(key, "password");
        String decrypted = Utils.decrypt(key, encrypted);
        System.out.println("'password' encrypted : " + encrypted + " decrypted : " + decrypted);
        /*
         * String passphrase = "The quick brown fox jumped over the lazy brown dog"; String plaintext = "hello world";
         * byte [] ciphertext = encrypt(passphrase, plaintext); String recoveredPlaintext = decrypt(passphrase,
         * ciphertext);
         *
         * System.out.println(recoveredPlaintext);
         */
    }

    public static byte[] encrypt(String passphrase, String plaintext) throws Exception {
        SecretKey key = generateKey(passphrase);

        Cipher cipher = Cipher.getInstance("AES/CTR/NOPADDING", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key, generateIV(cipher), random);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(String passphrase, byte[] ciphertext) throws Exception {
        SecretKey key = generateKey(passphrase);

        Cipher cipher = Cipher.getInstance("AES/CTR/NOPADDING", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key, generateIV(cipher), random);
        return new String(cipher.doFinal(ciphertext));
    }

    private static SecretKey generateKey(String passphrase) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(passphrase.toCharArray(), salt.getBytes("UTF-8"), iterations, keyLength);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWITHSHA256AND128BITAES-CBC-BC");
        return keyFactory.generateSecret(keySpec);
    }

    private static IvParameterSpec generateIV(Cipher cipher) throws Exception {
        byte[] ivBytes = new byte[cipher.getBlockSize()];
        random.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }

    public Utils() {
        // TODO Auto-generated constructor stub
    }

    /*
     * public static String encrypt(String inputData) {
     *
     * String encryptedData = null; try {
     *
     * byte[] encrypted=encrypt(key, inputData); encryptedData= new String(encrypted);
     *
     * } catch (Exception e) { System.out.println(e); }
     *
     * return encryptedData; }
     *
     * public static String decrypt(String encryptedData) {
     *
     * String outputData = null; try {
     *
     * outputData= decrypt(key,encryptedData.getBytes("UTF-8"));
     *
     * } catch (Exception e) { System.out.println(e); }
     *
     * return outputData; }
     */
    public static String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    // public static HttpSession session() {
    // ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
    // .currentRequestAttributes();
    // return attr.getRequest().getSession(true); // true == allow create
    // }
    // public static HttpServletRequest request() {
    // ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
    // .currentRequestAttributes();
    // return attr.getRequest();
    // }
}
