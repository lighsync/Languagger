/*
 * Language.java | (C) LighSync Games. 2023-2025.
 * Apache 2.0 License - автор: Gleb Ustimenko 
*/

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Language {
    private static final Map<String, String> translations = new HashMap<>();
    private static String currentLanguage;
    private static final String TRANSLATIONS_PATH = "/assets/lang";

    static {
        Locale locale = Locale.getDefault();
        String defaultLang = locale.getLanguage().equals("ru") ? "ru_RU" : "en_US";
        loadLanguage(defaultLang);
    }

    public static boolean loadLanguage(String langKey) {
        String resourcePath = TRANSLATIONS_PATH + langKey + ".json";

        try (InputStream in = Language.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                System.err.println("[Languagger]: Языковой файл не найден: " + resourcePath);
                return false;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder jsonContent = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONObject json = new JSONObject(jsonContent.toString());
            for (String key : json.keySet()) {
                translations.put(key, json.getString(key));
            }

            currentLanguage = langKey;
            System.out.println("[Languagger]: Загружен язык: " + currentLanguage);
            return true;
        } catch (Exception e) {
            System.err.println("[Languagger]: Ошибка загрузки перевода: " + currentLanguage);
            return false;
        }
    }

    public static String getText(String key) {
        return translations.getOrDefault(key, key);
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }
}
