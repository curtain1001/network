package org.example.network.tcp.server;

import org.example.network.core.security.Certificate;
import org.example.network.core.security.CertificateManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author 王超
 * @description TODO
 * @date 2022-05-09 2:06
 */
@Service
public class CertificateService implements CertificateManager {

    @Override
    public Mono<Certificate> getCertificate(String id) {
        return Mono.empty();
    }
}
