package cl.ionix.prueba.application;

import cl.ionix.prueba.application.port.interactor.IDESService;
import cl.ionix.prueba.domain.enums.ErrorEnum;
import cl.ionix.prueba.infrastructurecross.application.Setting;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Service
public class DESService implements IDESService {

    @Autowired
    private Setting setting;

    private static final String DES = "DES";

    @Override
    public String cifrar(String valor) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec keySpec = new DESKeySpec(setting.getDesLlave().getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            byte[] cleartext = valor.getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            return Base64.encodeBase64String(cipher.doFinal(cleartext));
        } catch (Exception ex) {
            throw new GenericException(ErrorEnum.CIFRAR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
