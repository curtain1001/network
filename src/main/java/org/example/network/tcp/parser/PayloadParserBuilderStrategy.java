package org.example.network.tcp.parser;

import org.example.network.core.property.ValueObject;

public interface PayloadParserBuilderStrategy {
	PayloadParserType getType();

	PayloadParser build(ValueObject config);
}
