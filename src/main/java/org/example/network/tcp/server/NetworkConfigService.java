package org.example.network.tcp.server;

import org.example.network.core.NetworkConfigManager;
import org.example.network.core.NetworkProperties;
import org.example.network.core.NetworkType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author 王超
 * @description TODO
 * @date 2022-05-09 2:03
 */
@Service
public class NetworkConfigService implements NetworkConfigManager {
    @Override
    public Mono<NetworkProperties> getConfig(NetworkType networkType, String id) {
        return Mono.empty();
    }
}
