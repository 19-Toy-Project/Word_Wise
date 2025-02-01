package com.wordwise.domain.sentence.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PapagoApiRequest {
    private String source;
    private String target;
    private String text;

    private PapagoApiRequest(String source,String target,String text){
        this.source=source;
        this.target=target;
        this.text=text;
    }
    public static PapagoApiRequest of(String source,String target,String text){
        return new PapagoApiRequest(
                source, target, text);
    }
}
