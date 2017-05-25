package lab.aop;

public class AopLog {
    private static StringBuffer value = new StringBuffer();

    public static void append(String str){
        value.append(str);
    }

    public static String getStringValue(){
        return value.toString();
    }

    public static void clear(){
        value = new StringBuffer();
    }

    public static void appendf(String format, String... args) {
        append(String.format(format, args));
    }
}
