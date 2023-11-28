package org.beizix.core.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.beizix.core.config.application.util.AESEncryptUtil;

@SpringBootTest
@TestPropertySource("classpath:application-override.properties")
class AESEncryptUtilTest {
  static {
    System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
  }

  @Autowired
  AESEncryptUtil aesEncryptUtil;

  private final String phoneNo = "01012341234";
  private final String encryptedPhoneNo = "dQSU9oEA+WD1WKQZIRaR9g==";

  @Test
  @DisplayName("operate - AESEncryptUtilTest (암호화)")
  void encrypt()
      throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
          IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    assertEquals(encryptedPhoneNo, aesEncryptUtil.encrypt(phoneNo), "operate - encrypt");
  }

  @Test
  @DisplayName("operate - AESEncryptUtilTest (복호화)")
  void decrypt()
      throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException,
          IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    assertEquals(phoneNo, aesEncryptUtil.decrypt(encryptedPhoneNo), "operate - decrypt");
  }
}
