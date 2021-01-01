package com.protocol;

import com.domain.ElementType;
import com.domain.ProtocolElement;
import com.google.protobuf.InvalidProtocolBufferException;
import com.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ProtocolRequest implements  NetworkRequest {

    protected abstract Map<String, String> getProtocolMap();

    private Object[] objects;

    private List<ProtocolElement> convert() {
        List<ProtocolElement> elements = new ArrayList<>();
        Map<String, String> protocolMap = getProtocolMap();
        protocolMap.forEach((name, type) -> {
            elements.add(ProtocolElement.builder()
                    .name(name)
                    .type(ElementType.valueOf(type))
                    .build());
        });
        return elements;
    }


    private List<ProtocolElement> convertValue() {
        List<ProtocolElement> elements = convert();
        if(this.objects.length > 0 && this.objects.length == elements.size()) {
            for(int i = 0; i < objects.length; i++) {
                elements.get(i).setValue(objects[i]);
            }
        }
        return elements;
    }


    public List<ProtocolElement> unpackAll(byte[] dataBuf) throws InvalidProtocolBufferException {
        List<ProtocolElement> elements = convert();

        List<ProtocolElement> elements1 = unpackAll(elements, dataBuf);

        System.out.println("unpackAll:{} " + JSONUtil.beanToJSON(elements1));
        return elements1;
    }



    public byte[] packAll() {
        List<ProtocolElement> elements = convertValue();
        System.out.println("packAll:{} " + JSONUtil.beanToJSON(elements));
        return packAll(elements);
    }

    public void setParams(Object... objects) {
        this.objects = objects;
    }
}
