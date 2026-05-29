package br.com.fiap.agrospace.security;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

@Component
public class JwtUtil {

    private static final String SECRET = "agrospace-secret-key-2026";
    private static final long EXPIRATION_SECONDS = 3600;

    public String gerarToken(String username) {
        long issuedAt = Instant.now().getEpochSecond();
        long expiration = issuedAt + EXPIRATION_SECONDS;

        String header = """
                {"alg":"HS256","typ":"JWT"}
                """.trim();

        String payload = String.format("""
                {"sub":"%s","iat":%d,"exp":%d}
                """.trim(), username, issuedAt, expiration);

        String encodedHeader = encode(header);
        String encodedPayload = encode(payload);

        String signature = sign(encodedHeader + "." + encodedPayload);

        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    public boolean validarToken(String token) {
        try {
            String[] parts = token.split("\\.");

            if (parts.length != 3) {
                return false;
            }

            String signature = sign(parts[0] + "." + parts[1]);

            return signature.equals(parts[2]);
        } catch (Exception e) {
            return false;
        }
    }

    public String extrairUsername(String token) {
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);

        String marker = "\"sub\":\"";
        int start = payload.indexOf(marker) + marker.length();
        int end = payload.indexOf("\"", start);

        return payload.substring(start, end);
    }

    private String encode(String value) {
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private String sign(String value) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(keySpec);

            byte[] signatureBytes = mac.doFinal(value.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(signatureBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar assinatura JWT", e);
        }
    }
}