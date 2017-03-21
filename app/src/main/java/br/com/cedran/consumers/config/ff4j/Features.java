package br.com.cedran.consumers.config.ff4j;

import lombok.Getter;

@Getter
public enum Features {

    //@formatter:off
    FEATURE_A("feature-a", "Feature A", "A", false, null),
    PROCESS_IMPORTANT_MESSAGE("process-important-message", "Process Important Messages", "messages", false, "importantMessage");
    //@formatter:on

    private final String key;
    private final String description;
    private final String group;
    private final boolean defaultValue;
    private final String queue;

    Features(final String key, final String group, final String description, final boolean defaultValue, final String queue) {
        this.key = key;
        this.description = description;
        this.defaultValue = defaultValue;
        this.group = group;
        this.queue = queue;
    }

}
