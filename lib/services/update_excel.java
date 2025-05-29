import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class update_excel {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("../timetable.xlsm");
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            Workbook workbook = WorkbookFactory.create(fs);
            Sheet sheet = workbook.getSheetAt(0);

            // Adjust row and cell indices as per your sheet
            Row row = sheet.getRow(1); // Example: second row
            int pending1 = (int) row.getCell(1).getNumericCellValue(); // Column B
            int pending2 = (int) row.getCell(2).getNumericCellValue(); // Column C
            int pending3 = (int) row.getCell(3).getNumericCellValue(); // Column D

            int total = pending1 + pending2 + pending3;

            try (FileWriter fw = new FileWriter("../pending_output.txt")) {
                fw.write(String.valueOf(total));
            }

            fis.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            try (FileWriter fw = new FileWriter("../pending_output.txt")) {
                fw.write("-1"); // Error
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
