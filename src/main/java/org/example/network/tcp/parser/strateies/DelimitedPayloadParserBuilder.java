package org.example.network.tcp.parser.strateies;

import org.example.network.tcp.parser.PayloadParserType;
import org.example.network.core.property.ValueObject;
import io.vertx.core.parsetools.RecordParser;
import org.apache.commons.lang3.StringEscapeUtils;

public class DelimitedPayloadParserBuilder extends VertxPayloadParserBuilder {
	@Override
	public PayloadParserType getType() {
		return PayloadParserType.DELIMITED;
	}

	@Override
	protected RecordParser createParser(ValueObject config) {

		return RecordParser.newDelimited(StringEscapeUtils.unescapeJava(config.getString("delimited")
				.orElseThrow(() -> new IllegalArgumentException("delimited can not be null"))));
	}

}
