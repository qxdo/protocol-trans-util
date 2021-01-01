package com.protocol;



import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is a protocol Request demo, you only just need definition your protocol here.
 *
 */
public class TestProtocolRequest extends ProtocolRequest {

    @Override
    protected Map<String, String> getProtocolMap() {
        Map<String, String> protocolMap = new LinkedHashMap<>();
        /**
         * here is just a demo protocol, you can def your own protocol use the ElementType as your variables.
         */
        protocolMap.put("bodySize", "_body_size");
        protocolMap.put("body", "_byte_array");
        return protocolMap;
    }

}
