package com.wordwise.common.utils;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtil {

    public static String encodeFileToBase64(MultipartFile file) throws IOException{
        return new String(Base64.encode(file.getBytes()));
    }
}
