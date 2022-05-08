package org.example.network.tcp.parser.strateies;

import org.example.network.core.property.ValueObject;
import org.example.network.tcp.parser.DirectRecordParser;
import org.example.network.tcp.parser.PayloadParser;
import org.example.network.tcp.parser.PayloadParserBuilderStrategy;
import org.example.network.tcp.parser.PayloadParserType;
import lombok.SneakyThrows;

public class DirectPayloadParserBuilder implements PayloadParserBuilderStrategy {

	@Override
	public PayloadParserType getType() {
		return PayloadParserType.DIRECT;
	}

	@Override
	@SneakyThrows
	public PayloadParser build(ValueObject config) {
		return new DirectRecordParser();
	}
}
