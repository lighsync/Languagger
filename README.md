# Languagger

**Languagger** — это простая Java-библиотека для локализации приложений с использованием JSON-файлов переводов.

## Структура проекта

```
Language.java
LICENSE
README.md
assets/
    lang/
        en_US.json
        ru_RU.json
deprecatedGarbage/
    Deprecated.java
```

- **Language.java** — основной класс API для работы с переводами.
- **assets/lang/** — папка с языковыми файлами (JSON).
- **deprecatedGarbage/Deprecated.java** — устаревшая версия API (не рекомендуется к использованию).
- **LICENSE** — лицензия Apache 2.0.
- **README.md** — этот файл.

## Как использовать

1. **Добавьте языковые файлы**  
   В папке `assets/lang/` должны находиться файлы переводов, например:  
   - `en_US.json`
   - `ru_RU.json`

2. **Используйте класс Language**

```java
// Получить перевод по ключу
String text = Language.getText("test");

// Получить текущий язык
String lang = Language.getCurrentLanguage();

// Загрузить новый язык по коду
Language.loadLanguage("ru_RU"); // например ru_RU
```

3. **Добавьте assets в classpath**  
   Убедитесь, что папка `assets/lang/` находится в classpath вашего приложения (например, поместите её в папку ресурсов вашего проекта).

## Пример языкового файла

**assets/lang/en_US.json**
```json
{
    "test": "Your translations here!"
}
```

**assets/lang/ru_RU.json**
```json
{
    "test": "Ваши переводы хранятся здесь!"
}
```

## Как работает API

- При инициализации класс [`Language`](Language.java) определяет язык системы (`ru_RU` или `en_US`).
- Загружает соответствующий JSON-файл из `assets/lang/`.
- Все переводы доступны через статический метод `getText(String key)`.
- Если ключ не найден — возвращается сам ключ.
- Также вы можете сменить язык конструкцией `loadLanguage(String langKey)`

## Лицензия

Проект распространяется под лицензией Apache 2.0. Подробнее см. [LICENSE](LICENSE).

---

**Автор:** Gleb Ustimenko для LighSync Games