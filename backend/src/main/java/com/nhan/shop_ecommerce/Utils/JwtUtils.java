package com.nhan.shop_ecommerce.Utils;

import com.nhan.shop_ecommerce.domain.User;
import com.nhan.shop_ecommerce.enums.ErrorCode;
import com.nhan.shop_ecommerce.exception.AppException;
import com.nhan.shop_ecommerce.repository.InvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    @Value("${jwt.signKey}")
    private String SIGN_KEY;
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(UUID.randomUUID().toString())
                .subject(user.getEmail())
                .issuer("DoanNgocNhan")
                .issueTime(Date.from(Instant.now()))
                .expirationTime(Date.from(Instant.now().plusSeconds(3600))) // 3600 = 1 hour
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGN_KEY));
        } catch (JOSEException e) {
            throw new AppException(ErrorCode.TOKEN_CANNOT_CREATE);
        }
        return jwsObject.serialize();
    }
    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getRoles() == null && user.getRoles().isEmpty()) return "";
        var roles = user.getRoles();
        for (var role : roles) {
            String roleName = role.getName();
            String formatRoleName = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;
            stringJoiner.add(formatRoleName);
            for(var permission : role.getPermissions()){
                stringJoiner.add(permission.getName());
            }
        }
        return stringJoiner.toString();
    }
    public SignedJWT verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(SIGN_KEY.getBytes());
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        Boolean verified = signedJWT.verify(jwsVerifier);
        if (!verified || Date.from(Instant.now()).after(expirationTime)) {
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }

}
