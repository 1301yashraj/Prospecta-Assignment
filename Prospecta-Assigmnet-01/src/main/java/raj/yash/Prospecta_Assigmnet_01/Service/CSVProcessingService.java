package raj.yash.Prospecta_Assigmnet_01.Service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CSVProcessingService {
    // Process CSV file and evaluate formulas
    public List<List<String>> processCSVFile(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<List<String>> data = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            List<String> row = new ArrayList<>(Arrays.asList(values));
            data.add(row);
        }
        reader.close();

        // Evaluate formulas in the CSV data
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                String value = data.get(i).get(j);
                if (value.startsWith("\"\"\"") && value.endsWith("\"\"\"")) {
                    value = value.substring(3, value.length() - 3);  // Remove the first and last quotes
                    data.get(i).set(j, evaluateFormula(value, data));
                }
            }
        }
        return data;
    }


    public String evaluateFormula(String formula, List<List<String>> data) {
        Pattern pattern = Pattern.compile("([A-Z]+[0-9]+)"); // Match cell reference like A1, B2, etc.
        Matcher matcher = pattern.matcher(formula);

        // Replace all matches in the formula with the actual cell value from the data
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String cellRef = matcher.group(0); // The cell reference (e.g., A1)
            char col = cellRef.charAt(0); // Get the column letter (e.g., 'A')
            int row = Integer.parseInt(cellRef.substring(1)) - 1; // Get the row index (e.g., 1 for A1)
            int colIndex = col - 'A'; // Convert column letter to index (A -> 0, B -> 1, etc.)
            // Get the value from the data and replace the match with it
            String cellValue = data.get(row).get(colIndex);
            if (cellValue.startsWith("=")) {
                cellValue = cellValue.substring(1);
            }
            matcher.appendReplacement(result, cellValue);
        }
        matcher.appendTail(result); // Append the remaining part of the string
        return result.toString();
    }


    public void writeCSV(List<List<String>> data, OutputStream outputStream) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream), CSVFormat.DEFAULT);
        for (List<String> row : data) {
            csvPrinter.printRecord(row);
        }
        csvPrinter.flush();
    }
}
