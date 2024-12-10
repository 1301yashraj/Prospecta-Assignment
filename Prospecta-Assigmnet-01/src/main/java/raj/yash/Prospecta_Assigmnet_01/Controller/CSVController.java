package raj.yash.Prospecta_Assigmnet_01.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import raj.yash.Prospecta_Assigmnet_01.Service.CSVProcessingService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CSVController {

    @Autowired
    private CSVProcessingService csvProcessingService;

    @PostMapping("/upload")
    public ResponseEntity<byte[]> uploadCSV(@RequestParam("file")MultipartFile file)  {
        try{
            List<List<String>> processedData = csvProcessingService.processCSVFile(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            csvProcessingService.writeCSV(processedData, outputStream);

            // Return the processed CSV as a downloadable file
            byte[] csvContent = outputStream.toByteArray();
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Disposition", "attachment; filename=processed.csv")
                    .body(csvContent);

        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }
}
