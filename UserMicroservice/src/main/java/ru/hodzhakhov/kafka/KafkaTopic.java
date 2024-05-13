package ru.hodzhakhov.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopic {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic createCatTopic() {
    return TopicBuilder.name("create_cat").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic updateCatTopic() {
    return TopicBuilder.name("update_cat").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic deleteCatTopic() {
    return TopicBuilder.name("delete_cat").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic friendCatTopic() {
    return TopicBuilder.name("friend_cat").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic unfriendCatTopic() {
    return TopicBuilder.name("unfriend_cat").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic addOwnerTopic() {
    return TopicBuilder.name("add_owner").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic createOwnerTopic() {
    return TopicBuilder.name("create_owner").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic updateOwnerTopic() {
    return TopicBuilder.name("update_owner").partitions(3).replicas(1).build();
  }

  @Bean
  public NewTopic deleteOwnerTopic() {
    return TopicBuilder.name("delete_owner").partitions(3).replicas(1).build();
  }
}
