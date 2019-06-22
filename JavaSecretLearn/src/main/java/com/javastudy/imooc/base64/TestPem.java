package com.javastudy.imooc.base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

import java.io.FileReader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Base64;

/**
 * @author wangjingbiao
 * @PackageName com.javastudy.imooc.base64
 * @Classname TestPem
 * @Date 19-5-15 下午12:01
 * @Description TODO
 */
public class TestPem {

    public static PrivateKey getPemPrivateKey(String filename) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        PEMParser pemParser = new PEMParser(new FileReader(filename));
        Object readObject = pemParser.readObject();
        pemParser.close();

        PEMDecryptorProvider decprov = new JcePEMDecryptorProviderBuilder().build(null);
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        KeyPair kp = null;
        if (readObject instanceof PEMEncryptedKeyPair) {
            kp = converter.getKeyPair(((PEMEncryptedKeyPair) readObject).decryptKeyPair(decprov));
        } else {
            kp = converter.getKeyPair((PEMKeyPair) readObject);
        }
        return kp.getPrivate();

    }

    public static void main(String[] args) throws Exception {
        PrivateKey pemPrivateKey = getPemPrivateKey("/home/wangjingbiao/tmpFile/myCaCert/ca/auditadmin/privkey.pem");
    }
}
