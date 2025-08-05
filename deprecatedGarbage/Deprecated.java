package deprecatedGarbage;
// Language.java (languagger) | (C) LighSync Games. 2023-2025. This API written by: Gleb Ustimenko for LighSync Games, and LighSync Games Launcher
// This Language API under Apache 2.0 License

import org.json.JSONObject;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Deprecated {
    private static Map<String, String> translations = new HashMap<>();
    private static String currentLanguage;
    private static final String APPDATA_PATH = System.getenv("APPDATA") + File.separator + ".fnag";

    static {
        loadLanguage();
    }

    private static void loadLanguage() {
        Locale locale = Locale.getDefault();
        currentLanguage = locale.getLanguage().equals("ru") ? "ru_RU" : "en_US";
        String langFilePath = APPDATA_PATH + File.separator + "lang" + File.separator + currentLanguage + ".json";
        File langFile = new File(langFilePath);

        if (!langFile.exists()) {
            try {
                Download.downloadLanguageFile(currentLanguage);
            } catch (IOException e) {
                System.err.println("Failed to download language file for " + currentLanguage + ": " + e.getMessage());
                currentLanguage = "en_US";
                langFilePath = APPDATA_PATH + File.separator + "lang" + File.separator + currentLanguage + ".json";
                langFile = new File(langFilePath);
                if (!langFile.exists()) {
                    try {
                        Download.downloadLanguageFile(currentLanguage);
                    } catch (IOException ex) {
                        System.err.println("Failed to download fallback language file (en_US): " + ex.getMessage());
                        return;
                    }
                }
            }
        }

        try (FileReader reader = new FileReader(langFile)) {
            StringBuilder jsonContent = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                jsonContent.append((char) c);
            }

            JSONObject json = new JSONObject(jsonContent.toString());
            for (String key : json.keySet()) {
                translations.put(key, json.getString(key));
            }
        } catch (IOException e) {
            System.err.println("Failed to load language file " + langFilePath + ": " + e.getMessage());
        }
    }

    public static String getText(String key) {
        return translations.getOrDefault(key, key);
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }
}