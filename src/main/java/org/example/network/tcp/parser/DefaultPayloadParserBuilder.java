package org.example.network.tcp.parser;

import org.example.network.tcp.parser.strateies.DelimitedPayloadParserBuilder;
import org.example.network.tcp.parser.strateies.DirectPayloadParserBuilder;
import org.example.network.tcp.parser.strateies.FixLengthPayloadParserBuilder;
import org.example.network.tcp.parser.strateies.ScriptPayloadParserBuilder;
import org.example.network.core.property.ValueObject;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DefaultPayloadParserBuilder implements PayloadParserBuilder, BeanPostProcessor {

	private final Map<PayloadParserType, PayloadParserBuilderStrategy> strategyMap = new ConcurrentHashMap<>();

	public DefaultPayloadParserBuilder() {
		register(new FixLengthPayloadParserBuilder());
		register(new DelimitedPayloadParserBuilder());
		register(new ScriptPayloadParserBuilder());
		register(new DirectPayloadParserBuilder());
	}

	@Override
	public PayloadParser build(PayloadParserType type, ValueObject configuration) {
		return Optional.ofNullable(strategyMap.get(type)).map(builder -> builder.build(configuration))
				.orElseThrow(() -> new UnsupportedOperationException("unsupported parser:" + type));
	}

	public void register(PayloadParserBuilderStrategy strategy) {
		strategyMap.put(strategy.getType(), strategy);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof PayloadParserBuilderStrategy) {
			register(((PayloadParserBuilderStrategy) bean));
		}
		return bean;
	}
}
