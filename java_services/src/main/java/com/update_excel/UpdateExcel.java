package main.java.com.update_excel;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class UpdateExcel {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("../timetable.xlsm");
            POIFSFileSystem fs = new POIFSFileSystem(fis);
            Workbook workbook = WorkbookFactory.create(fs);
            Sheet sheet = workbook.getSheetAt(0);

            int totalPending = 0;

            // rows for codechef, leetcode, codeforces assumed at 2,3,4 (index 1,2,3)
            int[] rows = {1, 2, 3};
            for (int r : rows) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                Cell checkCell = row.getCell(8); // Column I (index 8)
                Cell pendingCell = row.getCell(9); // Column J (index 9)

                String check = (checkCell != null) ? checkCell.getStringCellValue().trim().toLowerCase() : "";
                double pending = (pendingCell != null) ? pendingCell.getNumericCellValue() : 0;

                // Optional: Only add pending if check is "no"
                // if (!check.equals("yes")) {
                //     totalPending += pending;
                // }

                // Or simply sum pending counts regardless of check
                totalPending += pending;
            }

            // Write total pending count to output file
            try (FileWriter fw = new FileWriter("../pending_output.txt")) {
                fw.write(String.valueOf(totalPending));
            }

            fis.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            try (FileWriter fw = new FileWriter("../pending_output.txt")) {
                fw.write("-1"); // Error code
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
