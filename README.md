# Лабораторна робота №2 — Алмазний фонд (XML, XSD, XSLT, Java)

## Опис
Java-додаток для роботи з даними про дорогоцінні та напівдорогоцінні камені з XML-файлу. Реалізовано парсинг SAX/DOM/StAX, валідацію за XSD, сортування через `Comparator`, XSLT-трансформацію (групування за типом цінності) та логування.

## XSD-схема
- Прості типи з переліками: `PreciousnessType`, `ColorType`.
- Граничні значення: `TransparencyType` (0–100%), `FacetsType` (4–15 грани).
- Обов'язкові атрибути: `id` (`xs:ID`) та `preciousness`.
- Кореневий елемент: `Gem`.

## Парсинг
- **SAX** — подійний, низьке споживання пам'яті.
- **DOM** — в-memory дерево.
- **StAX** — pull-парсинг (cursor API).

## Додаткові сервіси
- `XmlValidator` — валідація XML проти XSD.
- `XsltTransformer` — трансформація через XSLT.
- `GemstoneComparator` — сортування за вартістю, назвою, походженням, цінністю.
- `GameLogger` — централізоване логування (`java.util.logging`).

## Структура проєкту
```
lab2/
├── src/main/resources/gem.xsd
├── src/main/resources/gems.xml
├── src/main/resources/group-by-preciousness.xsl
├── src/main/java/com/lab2/gems/model/       # Gemstone, VisualParameters, GemCollection, enums
├── src/main/java/com/lab2/gems/parser/      # GemParser, Sax, Dom, Stax
├── src/main/java/com/lab2/gems/service/     # Validator, Comparator, XsltTransformer, GemService
├── src/main/java/com/lab2/gems/app/         # GemApp
├── src/test/                                # JUnit 4 тести (30 тестів)
└── .gitignore
```

## Як запустити
```bash
javac -d out -sourcepath src/main/java src/main/java/com/lab2/gems/**/*.java
java -cp out com.lab2.gems.app.GemApp
```

## Тести
JUnit 4, 30 тестів. Усі проходять:
```
OK (30 tests)
```

## Репозиторій
https://github.com/gachibasers/3kurslab2
