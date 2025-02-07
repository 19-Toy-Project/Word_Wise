package com.wordwise.common.utils;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
public class FileUtil {

    public static String encodeFileToBase64(MultipartFile file) throws IOException{
        return new String(Base64.encode(file.getBytes()));
    }

    public static void fileValidator(MultipartFile file, Long size){
        log.info("fileSize={}",file.getSize());
        log.info("fileType={}",file.getContentType());

        if(file==null){
            throw new ApiException(ErrorStatus._NOT_FOUND_FILE);
        }
        if (file.getSize() > size) {
            throw new ApiException(ErrorStatus._FILE_SIZE_EXCEEDED);
        }
        if(!file.getContentType().equalsIgnoreCase("audio/wave")){
            throw new ApiException(ErrorStatus._UNSUPPORTED_FILE_TYPE);
        }
    }
}
