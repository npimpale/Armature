package com.persistent.securityPractice.armature.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class RepositorySerializer extends JsonSerializer<REPOSITORIES> {

	@Override
	public void serialize(REPOSITORIES repo, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		generator.writeStartObject();
		generator.writeFieldName("value");
		generator.writeString(repo.value());
		generator.writeEndObject();

	}

}
