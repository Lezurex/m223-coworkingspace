package com.lezurex.m223.coworkingspace.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.enterprise.context.ApplicationScoped;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@ApplicationScoped
public class HashingService {

  public String hashPassword(String password, byte[] salt) {

    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
    try {
      var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      var hash = factory.generateSecret(spec).getEncoded();
      return Base64.getEncoder().encodeToString(salt) + "$"
          + Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String hashPassword(String password) {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    return hashPassword(password, salt);
  }

  public boolean verifyPassword(String password, String hash) {
    var splittedSalt = hash.split("\\$")[0];
    var salt = Base64.getDecoder().decode(splittedSalt);
    var hashedPassword = hashPassword(password, salt);
    return hashedPassword.equals(hash);
  }

}
