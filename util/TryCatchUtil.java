package util;

public class TryCatchUtil {

    public Float handleException(Float line, String fields) {
        try {
            line = Float.parseFloat(fields);
        } catch (NumberFormatException e) {
            line = 0F;
        }
        return line;
    }
}
