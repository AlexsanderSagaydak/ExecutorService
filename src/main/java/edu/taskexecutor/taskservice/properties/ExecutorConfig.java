package edu.taskexecutor.taskservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for the task executor service.
 *
 * This class binds to properties defined with the prefix `executor-config` in the
 * application's configuration file (e.g., `application.yml` or `application.properties`).
 * It is used to configure the executor with properties such as initial delay,
 * core pool size, and delay between runs.
 *
 * Example configuration in `application-dev.yml`:
 *
 * executor-config:
 *   initialDelay: 0 - no need pause before run
 *   corePoolSize: 1 - initial size
 *   delayBetweenRuns: 60 - in seconds
 *
 * An instance of this class is automatically created and managed by the Spring
 * container, and can be injected wherever needed.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "executor-config")
public class ExecutorConfig {

    public Long initialDelay;
    public int corePoolSize;
    public Long delayBetweenRuns;
}
