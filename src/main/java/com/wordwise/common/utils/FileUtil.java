package com.wordwise.common.utils;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtil {

    public static String encodeFileToBase64(MultipartFile file) throws IOException{
        return new String(Base64.encode(file.getBytes()));
    }

    public static void fileValidator(MultipartFile file, Long size){
        if(file==null){
            throw new ApiException(ErrorStatus._NOT_FOUND_FILE);
        }
        if (file.getSize() > size) {
            throw new ApiException(ErrorStatus._FILE_SIZE_EXCEEDED);
        }
    }
}
