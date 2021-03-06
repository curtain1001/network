package org.example.network.tcp.server;

import org.example.network.tcp.parser.PayloadParserType;
import org.example.network.core.property.ValueObject;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetlinks.rule.engine.executor.PayloadType;
import org.springframework.util.StringUtils;

/**
 * @author bsetfeng
 * @author zhouhao
 * @since 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TcpServerProperties implements ValueObject {

	private String id;

	private NetServerOptions options;

	private PayloadType payloadType;

	private PayloadParserType parserType;

	private Map<String, Object> parserConfiguration = new HashMap<>();

	private String host;

	private int port;

	private boolean ssl;

	// 服务实例数量(线程数)
	private int instance = Runtime.getRuntime().availableProcessors();

	private String certId;

	public SocketAddress createSocketAddress() {
		if (StringUtils.isEmpty(host)) {
			host = "localhost";
		}
		return SocketAddress.inetSocketAddress(port, host);
	}

	@Override
	public Map<String, Object> values() {
		return parserConfiguration;
	}
}
