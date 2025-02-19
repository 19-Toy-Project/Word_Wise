package com.wordwise.common.utils;

import com.wordwise.common.apipayload.status.ErrorStatus;
import com.wordwise.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
public class FileUtil {

    public static String encodeFileToBase64(File file) throws IOException{
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] fileBytes=fileInputStream.readAllBytes();
        fileInputStream.close();
        return Base64.toBase64String(fileBytes);
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
        if((!file.getContentType().equalsIgnoreCase("audio/wav"))&&(!file.getContentType().equalsIgnoreCase("audio/wave"))){
            throw new ApiException(ErrorStatus._UNSUPPORTED_FILE_TYPE);
        }
    }

    //16kHZ로 변환
    public static File convertTo16kHz(MultipartFile multipartFile) throws IOException{
        //MultipartFile을 File로 변환
        File inputFile=File.createTempFile("input",".wav");
        multipartFile.transferTo(inputFile);

        File outputFile=File.createTempFile("output",".wav");

        //FFmpeg 실행 객체 생성
        FFmpeg ffmpeg=new FFmpeg(new File("/usr/bin/ffmpeg").getAbsolutePath());
        FFprobe ffprobe=new FFprobe(new File("/usr/bin/ffprobe").getAbsolutePath());

       //FFmpeg 객체 변환
       FFmpegBuilder builder=new FFmpegBuilder()
               .setInput(inputFile.getAbsolutePath())
               .overrideOutputFiles(true)
               .addOutput(outputFile.getAbsolutePath())
               .setAudioSampleRate(16000) //16kHz로 변환
               .done();

       //실행
        FFmpegExecutor executor=new FFmpegExecutor(ffmpeg,ffprobe);
        executor.createJob(builder).run();

        inputFile.delete();

        return outputFile;
    }


}
