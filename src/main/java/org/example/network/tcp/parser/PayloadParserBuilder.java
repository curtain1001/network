package org.example.network.tcp.parser;

import org.example.network.core.property.ValueObject;

public interface PayloadParserBuilder {

	PayloadParser build(PayloadParserType type, ValueObject configuration);

}
