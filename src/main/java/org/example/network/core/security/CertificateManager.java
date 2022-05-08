package org.example.network.core.security;

import reactor.core.publisher.Mono;

/**
 * 证书管理接口
 *
 * @author zhouhao
 */
public interface CertificateManager {

	Mono<Certificate> getCertificate(String id);

}
