package com.wordwise.domain.sentence.request;

import lombok.Getter;

@Getter
public class EtriApiRequest {
    private Argument argument;

    private EtriApiRequest(Argument argument){
        this.argument=argument;
    }
    public static EtriApiRequest of(Argument argument){
        return new EtriApiRequest(argument);
    }

    @Getter
    public static class Argument{
        private final String language_code;
        private final String script;
        private final String audio;

        private Argument(String language_code, String script, String audio){
            this.language_code=language_code;
            this.script=script;
            this.audio=audio;
        }
        public static Argument of(String language_code, String script, String audio){
            return new Argument(language_code,script,audio);
        }
    }

}
