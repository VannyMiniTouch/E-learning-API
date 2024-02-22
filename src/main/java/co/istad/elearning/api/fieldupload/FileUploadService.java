package co.istad.elearning.api.fieldupload;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface FileUploadService {

    FileDto uploadSingle(MultipartFile file);

    List<FileDto> uploadMultiple(List<MultipartFile> files);

    FileDto findByName(String name) throws IOException;

    Set<FileDto> findListFiles() throws IOException;

    void deleteFileByName(String name) throws FileNotFoundException;

    void deleteAllFiles() throws IOException;

    public void downloadFile(String fileName, HttpServletResponse response) throws FileNotFoundException;
}
