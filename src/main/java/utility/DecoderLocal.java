package utility;

import lombok.experimental.UtilityClass;

import static java.util.Base64.*;

@UtilityClass
public final class DecoderLocal {
    public static String decodeValue(String inputValueToDecode){
        return new String(getDecoder().decode(inputValueToDecode));
    }
}
