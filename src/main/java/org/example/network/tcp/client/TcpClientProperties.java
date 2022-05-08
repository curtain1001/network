package org.example.network.tcp.client;

import org.example.network.tcp.parser.PayloadParserType;
import org.example.network.core.property.ValueObject;
import io.vertx.core.net.NetClientOptions;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TcpClientProperties implements ValueObject {

	private String id;

	private int port;

	private String host;

	private String certId;

	private boolean ssl;

	private PayloadParserType parserType;

	private Map<String, Object> parserConfiguration = new HashMap<>();

	private NetClientOptions options;

	private boolean enabled;

	@Override
	public Map<String, Object> values() {
		return parserConfiguration;
	}
}
