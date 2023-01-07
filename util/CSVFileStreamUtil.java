package util;

import entity.Products;
import enums.ErrorCodeEnum;
import exception.InvalidCsvLineException;


public class CSVFileStreamUtil {

    public static Products getProducts(String line) {
        TryCatchUtil tryCatchUtil = new TryCatchUtil();
        String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (fields.length < 13) {
            throw new InvalidCsvLineException(ErrorCodeEnum.INVALID_CSV_LINE);
        }
        Float line4 = tryCatchUtil.handleException(0F, fields[4]);
        Float line5 = tryCatchUtil.handleException(0F, fields[5]);
        Float line11 = tryCatchUtil.handleException(0F, fields[11]);
        Float line12 = tryCatchUtil.handleException(0F, fields[12]);
        Float line13 = tryCatchUtil.handleException(0F, fields[13]);

        return new Products(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], fields[3],
                line4, line5, Boolean.parseBoolean(fields[6]),
                fields[7], fields[8], fields[9], fields[10], line11, line12,
                line13);
    }


}
