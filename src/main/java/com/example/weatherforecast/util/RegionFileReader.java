package com.example.weatherforecast.util;

import com.example.weatherforecast.web.dto.MedTermRegionDto;
import com.example.weatherforecast.web.dto.ShortTermRegionDto;
import lombok.experimental.UtilityClass;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
@UtilityClass
public class RegionFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionFileReader.class);

    static {
        // 압축 비율 제한을 0.005로 설정
        ZipSecureFile.setMinInflateRatio(0.005);
    }

    public List<MedTermRegionDto> parseMedTermRegion(String filePath){

        List<MedTermRegionDto> regionDataList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                MedTermRegionDto regionData = MedTermRegionDto.builder()
                        .region(getStringValue(row.getCell(0)))
                        .regId(getStringValue(row.getCell(1))).build();
                LOGGER.info("region data: {}", regionData);
                regionDataList.add(regionData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionDataList;
    }


    public List<ShortTermRegionDto> parseShortTermRegion(){

        String filePath = "src\\main\\assets\\기상청_단기예보_격자_위경도.xlsx";

        List<ShortTermRegionDto> regionDataList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // 헤더 행 건너뛰기

                ShortTermRegionDto regionData = ShortTermRegionDto.builder()
                        .regionCode(getStringValue(row.getCell(1)))
                        .level1(getStringValue(row.getCell(2)))
                        .level2(getStringValue(row.getCell(3)))
                        .level3(getStringValue(row.getCell(4)))
                        .gridX(getIntValue(row.getCell(5)))
                        .gridY(getIntValue(row.getCell(6)))
                        .longitudeDegrees(getDoubleValue(row.getCell(7)))
                        .longitudeMinutes(getDoubleValue(row.getCell(8)))
                        .longitudeSeconds(getDoubleValue(row.getCell(9)))
                        .latitudeDegrees(getDoubleValue(row.getCell(10)))
                        .latitudeMinutes(getDoubleValue(row.getCell(11)))
                        .latitudeSeconds(getDoubleValue(row.getCell(12)))
                        .build();


//                LOGGER.info("region code: {}", getIntValue(row.getCell(1)));
//                LOGGER.info("region 1: {}", getStringValue(row.getCell(2)));
//                LOGGER.info("X: {}", getIntValue(row.getCell(5)));
//                LOGGER.info("Y: {}", getIntValue(row.getCell(6)));
//                LOGGER.info("LongitudeDegrees: {}", getDoubleValue(row.getCell(7)));
                regionDataList.add(regionData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionDataList;
    }

    private String getStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> Double.toString(cell.getNumericCellValue());
            default -> "";
        };
    }

    private int getIntValue(Cell cell) {
        if (cell == null) return 0;
        return switch (cell.getCellType()) {
            case NUMERIC -> (int) cell.getNumericCellValue();
            case STRING -> Integer.parseInt(cell.getStringCellValue());
            default -> 0;
        };
    }

    private double getDoubleValue(Cell cell) {
        if (cell == null) return 0.0;
        return switch (cell.getCellType()) {
            case NUMERIC -> cell.getNumericCellValue();
            case STRING -> Double.parseDouble(cell.getStringCellValue());
            default -> 0.0;
        };
    }

    private Long getLongValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return (long)cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Long.parseLong(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                LOGGER.error("NumberFormatException for cell value: {}", cell.getStringCellValue(), e);
                return 0L;
            }
        }
        return 0L;
    }


}
