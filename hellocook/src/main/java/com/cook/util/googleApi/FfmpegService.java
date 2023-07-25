package com.cook.util.googleApi;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FfmpegService {

    private String mp3FilePath;
    private String wavFilePath;

    public String getMp3FilePath() {
        return mp3FilePath;
    }

    public void setMp3FilePath(String mp3FilePath) {
        this.mp3FilePath = mp3FilePath;
    }

    public String getWavFilePath() {
        return wavFilePath;
    }

    public void setWavFilePath(String wavFilePath) {
        this.wavFilePath = wavFilePath;
    }

    public void mp3ToWav(String mp3FilePath, String wavFilePath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-i", mp3FilePath, wavFilePath); //명령어 만들기 processBuilder를 사용하면. 환경변수 등록된 프로그램 사용 가능.
        //환경변수에 없으면 풀 경로 적어줘야함
        Process process;
       
        try {
            process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("FFmpeg conversion 처리에서 문제됨. " + exitCode);
            }
        } catch (IOException e) {
            throw new IOException("실패", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("FFmpeg conversion process was interrupted.", e);
        }
    }
}
