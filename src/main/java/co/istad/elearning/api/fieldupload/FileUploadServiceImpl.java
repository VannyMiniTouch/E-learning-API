package co.istad.elearning.api.fieldupload;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Value("${file-upload.base-uri}")
    private String baseUri;

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        String extension = this.extractExtension(file.getOriginalFilename());
        // Create new unique file name
        String newFileName = UUID.randomUUID() + "." + extension;
        String absolutePath = serverPath + newFileName;
        Path path = Paths.get(absolutePath);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return FileDto.builder()
                .name(newFileName)
                .extension(extension)
                .size(file.getSize())
                .uri(baseUri + newFileName)
                .build();
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> fileListDto = new ArrayList<>();
        files.forEach(file -> {
            fileListDto.add(this.uploadSingle(file));
        });
        return fileListDto;
    }

    @Override
    public FileDto findByName(String name) throws IOException {
        Path path = Paths.get(serverPath + name);
        Resource res = UrlResource.from(path.toUri());
        return FileDto.builder()
                .name(res.getFilename())
                .size(res.contentLength())
                .extension(this.extractExtension(res.getFilename()))
                .uri(baseUri + res.getFilename())
                .build();
    }

    //ref https://www.baeldung.com/java-list-directory-files
    @Override
    public Set<FileDto> findListFiles() throws IOException {
        try (
                Stream<Path> stream = Files.list(Paths.get(serverPath))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(file -> {
                        try {
                            return FileDto.builder()
                                    .name(file.getFileName().toString())
                                    .extension(this.extractExtension(file.getFileName().toString()))
                                    .size(Files.size(file) / 1024)
                                    .uri(baseUri + this.extractExtension(file.getFileName().toString()))
                                    .build();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toSet());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteFileByName(String name) throws FileNotFoundException {
        Path path = Paths.get(serverPath + name);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    @Override
    public void deleteAllFiles() throws IOException {
        try (
                Stream<Path> files = Files.list(Paths.get(serverPath))) {
            files.filter(file -> !Files.isDirectory(file)) // filter only file not directory
                    //.filter(Files::isRegularFile) // Filter out only regular files
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception ex) {
            throw new FileNotFoundException("Director path not existing");
        }
    }

    @Override
    public void downloadFile(String fileName, HttpServletResponse response) throws FileNotFoundException {
        File file = new File(serverPath, fileName);
        if (file.exists() && file.isFile()) {
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[(int) file.length()];
                inStream.read(buffer);
                outStream.write(buffer);
                inStream.close();
                outStream.flush();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong while get file");
            }
        } else {
            throw new FileNotFoundException("The file doesn't existing...!");
        }
    }

    private String extractExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        return fileName.substring(lastIndexOfDot + 1);
    }


}
