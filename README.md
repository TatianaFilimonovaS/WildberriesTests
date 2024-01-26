# Wildberries Tests

#Окружение
Тест написан для проверки работы функционала сайта https://www.wildberries.ru/ на Desktop-версии в браузере Chrome, версия 120. Стек: Java, Selenium.

#Запуск теста

1. Установить Maven
2. Скачать WebDriver, версия должна совпадать с версией браузера, в котором будет проводиться тестирование
3. Выполнить команду mvn test

#Краткое описание тестов
Тест 1 - проверяем открытие сайта https://www.wildberries.ru/
Тест 2 - проверяем название вкладки
Тест 3 - на главной странице вводим в поле поиска название товара, проверяем, что открылась соответствующая страница
Тест 4 - проверяем наличие дропдаун списка в поле поиска на главной странице
Тест 5 - на главной странице проверяем наличие и функционал кнопки На верх
Тест 6 - на главной странице проверяем кликабельность кнопки "Работа в Wildberries" и открытие соответствующей страницы
Тест 7 - на странице Работа в Wildberries проверяем кликабельность ссылки на соц. сеть Вконтакте
Тест 8 - из меню на главной странице выбираем джинсы женские, проверяем переход на соответствующую страницу
Тест 9 - на странице Джинсы женские сортируем товары по рейтингу
Тест 10 - на странице Джинсы женские проверяем наличие формы "Все фильтры"
Тест 11 - кликаем на первую карточку на странице с результатами поиска
Тест 12 - на странице товара проверяем наличие поп-ап окна "Ценовая история". !Тест flaky, так как кнопка "Ценовая история" переодичекси не прогружается
Тест 13 - кликаем на баннер на главной странице
Тест 14 - проверяем наличие кнопок "Предыдущий" и "Следующий" на главном баннере
Тест 15 - проверяем всплывающее окно со списком стран и валютой при наведении курсора на кнопку "Войти"
Тест 16 - пытаемся ввести некорректный номер телефона и проверяем наличии предупреждения об ошибке
Тест 17 - на странице продукта присутствует символ лайка
Тест 18 - на странице с результатами поиска раскладка карточек с товарами меняется с 5-ти товаров на 4-ре
Тест 19 - проверяем сообщение о пустой корзине при отсутствие в ней товаров 
Тест 20 - на странице с результатами поиска нажать на кнопку покупки в карточке первого товара