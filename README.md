# gifClien
Rich GIFs - Find & Share on GIPHY
Find GIFs with the latest and newest hashtags! Search, discover and share your favorite Rich GIFs. The best GIFs are on GIPHY.

Alfa - тестовое задание

Создать сервис на Spring boot 2, который обращается к сервису курсов валют, и отдает gif в ответ
 • если курс по отношению к рублю стал выше, то отдаем рандомную отсюда https://giphy.com/search/rich
 • если ниже - отсюда https://giphy.com/search/broke
Ссылки:
 • rest api курсов валют -c
 • rest api гифок - https://developers.giphy.com/docs/api#quick-start-guide

Что важно:
- Сервис на spring boot 2 + java/kotlin
- Запросы приходят на http endpoint, туда передается код валюты
- Для взаимодействия с внешними сервисами используется Feign
- Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и тд) вынесены в настройки
- На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или wiremock
- Для сборки должен использоваться gradle
- Результатом выполнения должен быть репо на github с инструкцией по запуску


По запуску:
- Ссылка для Gif localhost:8080/gif
- Ссылка для Currency localhost:8080/usd
- Для тестов закомитить @Bean Clock в классе GifAndUsdServiceApplication
