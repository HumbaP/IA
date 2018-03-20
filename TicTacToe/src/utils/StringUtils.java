package utils;

public class StringUtils {

    public static String changeOnIndex(int index,char replacer, String string){
        char[] stringArray=string.toCharArray();
        stringArray[index]=replacer;
        return String.valueOf(stringArray);
    }
}
