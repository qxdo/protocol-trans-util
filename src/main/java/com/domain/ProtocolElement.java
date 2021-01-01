package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.List;

import static com.domain.ElementType._byte_array;
import static com.domain.ElementType._string;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolElement {
    private String name;
    private ElementType type;
    private Object value;


    public int getLength() {
        if(type.equals(_string)) {
            return String.valueOf(value).getBytes().length;
        }
        if(type.equals(_byte_array)) {
            return ((byte[])value).length;
        }
        return this.type.getLength();
    }
}
