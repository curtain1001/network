package org.example.network.core.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.jetlinks.core.message.MessageType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTypeMatcher {

	@Getter
	private Set<String> excludes;

	@Getter
	private Set<String> includes = new HashSet<>(Collections.singleton("*"));

	/**
	 * 设置为true时, 优选判断excludes
	 */
	@Setter
	@Getter
	private boolean excludeFirst = true;

	private long excludesMask;

	private long includesMask;

	public void setExcludes(Set<String> excludes) {
		this.excludes = excludes;
		init();
	}

	public void setIncludes(Set<String> includes) {
		this.includes = includes;
		init();
	}

	private long createMask(Collection<MessageType> messageTypes) {
		long mask = 0;

		for (MessageType messageType : messageTypes) {
			mask |= 1L << messageType.ordinal();
		}
		return mask;
	}

	protected void init() {
		if (!CollectionUtils.isEmpty(excludes)) {
			if (excludes.contains("*")) {
				excludesMask = createMask(Arrays.asList(MessageType.values()));
			} else {
				excludesMask = createMask(excludes.stream().map(String::toUpperCase).map(MessageType::valueOf)
						.collect(Collectors.toList()));
			}
		}
		if (!CollectionUtils.isEmpty(includes)) {
			if (includes.contains("*")) {
				includesMask = createMask(Arrays.asList(MessageType.values()));
			} else {
				includesMask = createMask(includes.stream().map(String::toUpperCase).map(MessageType::valueOf)
						.collect(Collectors.toList()));
			}
		}
	}

	public boolean match(MessageType type) {
		long mask = 1L << type.ordinal();
		if (includesMask != 0) {
			boolean include = (includesMask & mask) != 0;

			if (excludeFirst && excludesMask != 0) {
				return include && (excludesMask & mask) == 0;
			}

			return include;

		}
		if (excludesMask != 0) {
			return (excludesMask & mask) == 0;
		}
		return true;
	}
}
