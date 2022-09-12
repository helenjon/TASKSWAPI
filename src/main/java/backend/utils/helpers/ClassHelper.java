package backend.utils.helpers;

import backend.utils.Log;

public class ClassHelper {
    public ClassHelper() {
    }

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String className = stElements[3].getClassName();

        try {
            return Class.forName(className).getName();
        } catch (ClassNotFoundException var3) {
            Log.error(var3.getMessage());
            return null;
        }
    }
}
