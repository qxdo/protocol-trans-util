package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Protocol {
    private List<ProtocolElement> elements;
    private ProtocolAttr type;
    private String host;
    private int port;
    private byte[] sendBody;

}
