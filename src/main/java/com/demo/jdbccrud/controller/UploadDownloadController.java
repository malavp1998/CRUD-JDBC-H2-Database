package com.demo.jdbccrud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
public class UploadDownloadController {

    private static final String FILES_PATH = "src/main/resources/files";

    @PostMapping("/upload")
    private static ResponseEntity<String> uploadFile(@RequestParam("file") final MultipartFile file) {
        try {
            write(file);
            return ResponseEntity.ok("");
        } catch (final Exception e) {
            log.debug("Exception in recognize Api", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to Recognize Due to some error.");
        }
    }

    @PostMapping(path = "/download")
    public static ResponseEntity<Resource> downloadFile(@RequestParam("filename") final String filename) throws IOException {
        try {
            final Path dir = Paths.get(FILES_PATH);
            final Path filepath = Paths.get(dir.toString(), filename);
            final File file = filepath.toFile();
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (final Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    private static void write(final MultipartFile file) throws IOException {
        final Path dir = Paths.get(FILES_PATH);
        final Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());

        final OutputStream os = Files.newOutputStream(filepath);
        os.write(file.getBytes());
    }


}
