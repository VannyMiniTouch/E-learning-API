package co.istad.elearning.api.fieldupload;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/single",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    FileDto uploadSingle(@RequestPart MultipartFile file) {
        return fileUploadService.uploadSingle(file);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/multiple",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<FileDto> uploadMultiple(@RequestPart List<MultipartFile> files) {
        return fileUploadService.uploadMultiple(files);
    }

    @GetMapping("/{name}")
    FileDto findByName(@PathVariable String name) throws IOException {
        return fileUploadService.findByName(name);
    }

    @GetMapping
    public Set<FileDto> findListFiles() throws IOException {
        return fileUploadService.findListFiles();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteFileByName(@PathVariable String name) throws FileNotFoundException {
        fileUploadService.deleteFileByName(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deleteAll")
    public void deleteAllFiles() throws IOException {
        fileUploadService.deleteAllFiles();
    }

    @GetMapping("/{name}/download")
    public void downloadFil (@PathVariable String name, HttpServletResponse res) throws FileNotFoundException {
        fileUploadService.downloadFile(name, res);
    }






}
