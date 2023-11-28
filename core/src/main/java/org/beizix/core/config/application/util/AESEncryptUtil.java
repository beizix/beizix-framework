package org.beizix.core.config.application.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AESEncryptUtil {
  @Value("${encrypt.aes.key}")
  private String key;

  @Value("${encrypt.aes.iv}")
  private String iv;

  public String encrypt(String text)
      throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
          IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
    Cipher cipher = Cipher.getInstance(getTransformation());
    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
    IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

    byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public String decrypt(String cipherText)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
          InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(getTransformation());
    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
    IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

    byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
    byte[] decrypted = cipher.doFinal(decodedBytes);
    return new String(decrypted, StandardCharsets.UTF_8);
  }

  private String getTransformation() {
    return "AES/CBC/PKCS5Padding";
  }
}
