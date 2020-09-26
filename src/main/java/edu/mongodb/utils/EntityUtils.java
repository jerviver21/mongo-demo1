package edu.mongodb.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

public class EntityUtils {
	protected static Logger log = Logger.getLogger(EntityUtils.class.getName());
	
	public static <T> T updateEntity(final T newEntity, final T existingEntity, final Class<T> typeParameterClass) {
		T t = newEntity;
		try {
			ReflectionUtils.doWithFields(typeParameterClass, docField -> {
				ReflectionUtils.makeAccessible(docField);
				final Object fieldValue = docField.get(newEntity);
				if (fieldValue == null) {
					docField.set(newEntity, docField.get(existingEntity));
				}
			});
			t = (T)BeanUtils.cloneBean(newEntity);
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error updating entity", e);
		}
		return t;
	}

}
