package edu.mongodb.listeners;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class GenericCascadeListener extends AbstractMongoEventListener<Object> {
	private ReactiveMongoTemplate mongoTemplate;
	
	public GenericCascadeListener (ReactiveMongoTemplate reactiveMongoTemplate) {
		this.mongoTemplate = reactiveMongoTemplate;
	}
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent event) {
		Object document = event.getSource();
		ReflectionUtils.doWithFields(document.getClass(), docField -> {
			ReflectionUtils.makeAccessible(docField);
			if (docField.isAnnotationPresent(DBRef.class)) {
				final Object fieldValue = docField.get(document);
				this.mongoTemplate.save(fieldValue);
			}
		});
		
	}
	
	

}
