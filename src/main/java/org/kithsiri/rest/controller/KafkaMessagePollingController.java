package org.kithsiri.rest.controller;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.kithsiri.rest.model.ShoppingCart;
import org.kithsiri.rest.repo.ShoppingCartRepo;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaMessagePollingController {
	
	private ShoppingCartRepo shoppingCartRepo;
	
	public KafkaMessagePollingController(ShoppingCartRepo shoppingCartRepo) {
		this.shoppingCartRepo = shoppingCartRepo;
	}
	
	@RequestMapping(value = "/producer/consume-message/{topic}", method = { RequestMethod.GET })
	@ResponseBody
	public String consumeMessage(@PathVariable String topic) {
		
		ConsumerFactory<String, Object> consumerFactory = getConsumerFactoryInstance();

		Consumer<String, Object> consumer = consumerFactory.createConsumer();
		
		consumer.subscribe(Collections.singletonList("shopping-cart-event-topic"));
		
		// poll messages from yesterday
		ConsumerRecords<String, Object> consumerRecords = consumer.poll(Duration.ofDays(1));

		consumerRecords.forEach(action -> {
			ShoppingCart shoppingCart = (ShoppingCart) action.value();
			this.shoppingCartRepo.save(shoppingCart);
		});
		
		return  "success";
	}
	
	public ConsumerFactory<String, Object> getConsumerFactoryInstance() {
		Map<String, Object> configs = new java.util.HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "anyIdForGroup");
		configs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
		configs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000);
		ConsumerFactory<String, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(configs);
		return consumerFactory;
	}
}
