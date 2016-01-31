package com.persistent.securityPractice.armature.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class StatusSerializer extends JsonSerializer<STATUS>{

	@Override
	public void serialize(STATUS status, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		generator.writeStartObject();
	    generator.writeFieldName("value");
	    generator.writeString(status.value());
	    generator.writeEndObject();
		// TODO Auto-generated method stub
		
	}

}
