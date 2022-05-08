package org.example.network.tcp.server;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.example.network.tcp.parser.PayloadParserBuilder;
import org.example.network.tcp.parser.PayloadParserBuilderStrategy;
import org.example.network.tcp.parser.PayloadParserType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DefaultPayloadParserBuilder implements PayloadParserBuilder, BeanPostProcessor {

    private Map<PayloadParserType, PayloadParserBuilderStrategy> strategyMap = new ConcurrentHashMap<>();

    public DefaultPayloadParserBuilder(){
        register(new FixLengthPayloadParserBuilder());
        register(new DelimitedPayloadParserBuilder());
        register(new ScriptPayloadParserBuilder());
        register(new DirectPayloadParserBuilder());
    }
    @Override
    public PayloadParser build(PayloadParserType type, ValueObject configuration) {
        return Optional.ofNullable(strategyMap.get(type))
                .map(builder -> builder.build(configuration))
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
