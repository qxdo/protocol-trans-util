package com.protocol;

import com.domain.ProtocolElement;
import com.google.protobuf.InvalidProtocolBufferException;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import static com.domain.ElementType.*;

public interface NetworkRequest {

    default byte[] packAll(List<ProtocolElement> protoAndData) {
        ByteBuffer buffer = ByteBuffer.allocate(getProtocolLength(protoAndData));
        for (ProtocolElement element : protoAndData) {
            switch (element.getType()) {
                case _int:
                case _body_size:
                    buffer.putInt(Integer.parseInt(String.valueOf(element.getValue())));
                    break;
                case _float:
                    buffer.putFloat(Float.parseFloat(String.valueOf(element.getValue())));
                    break;
                case _char:
                    buffer.putChar((Character) element.getValue());
                    break;
                case _long:
                    buffer.putLong(Long.parseLong(String.valueOf(element.getValue())));
                    break;
                case _string:
                    buffer.put(String.valueOf(element.getValue()).getBytes());
                    break;
                case _byte_array:
                    buffer.put((byte[]) element.getValue());
                    break;
                default:
                    System.out.println("not support type now, please use another type.");
                    break;
            }
        }
        return buffer.array();
    }

    default List<ProtocolElement> unpackAll(List<ProtocolElement> proto, byte[] dataBinary) throws InvalidProtocolBufferException {
        return unpackBodyFromByteArray(proto, dataBinary);
    }


    static List<ProtocolElement> unpackBodyFromByteArray(List<ProtocolElement> elements, byte[] body) throws InvalidProtocolBufferException {
        ByteBuffer buffer = ByteBuffer.wrap(body);
        int index = 0;
        int bodySize = 0;
        for (ProtocolElement element : elements) {
            switch (element.getType()) {
                case _body_size:
                    bodySize = buffer.getInt(index);
                    element.setValue(bodySize);
                    index += _body_size.getLength();
                    break;
                case _int:
                    element.setValue(buffer.getInt(index));
                    index += _int.getLength();
                    break;
                case _float:
                    element.setValue(buffer.getFloat(index));
                    index += _float.getLength();
                    break;
                case _char:
                    element.setValue(buffer.getChar(index));
                    index += _char.getLength();
                    break;
                //下面是不定长度的内容,不定长的话需要在字段前加bodySize字段
                case _string:
                    element.setValue(new String(Arrays.copyOfRange(body, index, index + bodySize)));
                    index += bodySize;
                    break;
                case _byte_array:
                    element.setValue(Arrays.copyOfRange(body, index, index + bodySize));
                    index += bodySize;
                    break;
                default:
                    System.out.println("not support your type:" + element.getType());
                    break;
            }
        }
        return elements;
    }


    static int getProtocolLength(List<ProtocolElement> elements) {
        int length = 0;
        for(ProtocolElement element : elements) {
            length += element.getLength();
        }
        return length;
    }
}
