public class ConcatenateStrings {
    public static String concatenate(String[] strings) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : strings) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }
    public static void main(String[] args) {
        String[] strings = {"rick", " is", " a", " srm", " student"};
        String result = concatenate(strings);
        System.out.println("Concatenated String: " + result);
    }
}