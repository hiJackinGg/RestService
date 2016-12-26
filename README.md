# RestService

[![Build Status](https://travis-ci.org/hiJackinGg/RestService.svg?branch=master)](https://travis-ci.org/hiJackinGg/RestService)

You can run project via maven command: mvn jetty:run. Then access URI. Example: localhost:8080/hello/contacts?nameFilter=[your regular expression]

(To compile and run project you need Maven and Java8.)

Есть таблица contacts в SQL базе. В ней миллионы строк.
Данный REST сервис hello с ресурсом
/hello/contacts?nameFilter=val

Запрос к contacts возвращает контакты из таблицы БД contacts. Параметр nameFilter обязателен. В него передаётся регулярное выражение. В возвращаемых данных нет записей, в которых contacts.name совпадает с регулярным выражением.

Массив контактов возвращается в json формате

contacts: [ Contact, ... ]

Contact
{
	“id”: integer,
 	“name”: string
}

Пример запросов
/hello/contacts?nameFilter=^A.*$ - возвращает контакты, которые НЕ начинаются с A
/hello/contacts?nameFilter=^.*[aei].*$ - возвращает контакты, которые НЕ содержат букв a, e, i

Особенности реализации<br>
Фильтр применяется в java коде, (не в SQL).
В реализации есть возможность обрабатывать полученные данные многопоточно (при загрузке большого объема данных в память) либо в одном потоке.
Есть возможность вытаскивать данные из БД сразу все или по частям (с использование paging).
Добавлены тесты.



