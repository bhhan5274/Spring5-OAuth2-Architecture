package io.github.bhhan.oauth2.server.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperTest {
    static class EmptyEvent {
    }

    static class SomeEvent {
        private BigDecimal amount;
        private String nullProperty;
        private String anotherProperty;

        public SomeEvent() {
        }

        public SomeEvent(BigDecimal amount, String anotherProperty) {
            this.amount = amount;
            this.anotherProperty = anotherProperty;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getNullProperty() {
            return nullProperty;
        }

        public void setNullProperty(String nullProperty) {
            this.nullProperty = nullProperty;
        }

        public String getAnotherProperty() {
            return anotherProperty;
        }

        public void setAnotherProperty(String anotherProperty) {
            this.anotherProperty = anotherProperty;
        }
    }

    static class SubsetEvent {
        private String amount;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    static class ThirdEvent {
        private String anotherProperty;
        private String amount;

        public ThirdEvent() {
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAnotherProperty() {
            return anotherProperty;
        }

        public void setAnotherProperty(String anotherProperty) {
            this.anotherProperty = anotherProperty;
        }
    }

    private final String amountAsString = "1345.13";
    private final SomeEvent original = new SomeEvent(new BigDecimal(amountAsString), "foo");

    private ObjectMapper objectMapper;

    @BeforeEach
    void init(){
        objectMapper = new GatewayConfig().objectMapper();
    }

    @Test
    void shouldSerdeEmpty() throws JsonProcessingException {

        String s = objectMapper.writeValueAsString(new EmptyEvent());
        EmptyEvent x = objectMapper.readValue(s, EmptyEvent.class);
        assertNotNull(x);
    }

    @Test
    void shouldSerdeSomeEvent() throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(original);
        SomeEvent x = objectMapper.readValue(s, SomeEvent.class);

        assertTrue(s.contains("nullProperty"));

        assertTrue(EqualsBuilder.reflectionEquals(original, x));
        assertEquals(original.getAmount(), x.getAmount());
        assertEquals(original.getAnotherProperty(), x.getAnotherProperty());
        assertEquals(original.getNullProperty(), x.getNullProperty());
    }

    @Test
    void shouldDeserIgnoringUnknowns() throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(original);
        SubsetEvent x = objectMapper.readValue(s, SubsetEvent.class);

        assertEquals(amountAsString, x.getAmount());
    }

    @Test
    void shouldDeserIgnoringUnknowns2() throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(original);
        ThirdEvent x = objectMapper.readValue(s, ThirdEvent.class);

        assertEquals(original.getAnotherProperty(), x.getAnotherProperty());
        assertEquals(amountAsString, x.getAmount());
    }
}
