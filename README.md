# CreditConveyor
Главная страница приложения - http://localhost:8080/swagger-ui/index.html#/

Начать работу с приложением можно запустив файл docker-compose.yml в корне проекта.

С внутренней структурой приложения, sequence-диаграммой и Business-flow можно ознакомиться открыв файлы с соответствующими именами в корне проекта.

Логика работы:
Пользователь отправляет заявку на кредит.
МС Заявка осуществляет прескоринг заявки и если прескоринг проходит, то заявка сохраняется в МС Сделка и отправляется в КК.
КК возвращает через МС Заявку пользователю 4 предложения (сущность "LoanOffer") по кредиту с разными условиями (например без страховки, со страховкой, с зарплатным клиентом, со страховкой и зарплатным клиентом) или отказ.
Пользователь выбирает одно из предложений, отправляется запрос в МС Заявка, а оттуда в МС Сделка, где заявка на кредит и сам кредит сохраняются в базу.
МС Сделка отправляет клиенту письмо с текстом "Ваша заявка предварительно одобрена, завершите оформление".
Клиент отправляет запрос в МС Сделка со всеми своими полными данными о работодателе и прописке. Происходит скоринг данных в КК, КК рассчитывает все данные по кредиту (ПСК, график платежей и тд), МС Сделка сохраняет обновленную заявку и сущность кредит сделанную на основе CreditDTO полученного из КК со статусом CALCULATED в БД.
После валидации МС Сделка отправляет письмо на почту клиенту с одобрением или отказом. Если кредит одобрен, то в письме присутствует ссылка на запрос "Сформировать документы"
Клиент отправляет запрос на формирование документов в МС Сделка, МС Сделка отправляет клиенту на почту документы для подписания и ссылку на запрос на согласие с условиями.
Клиент может отказаться от условий или согласиться. Если согласился - МС Сделка на почту отправляет код и ссылку на подписание документов, куда клиент должен отправить полученный код в МС Сделка.
Если полученный код совпадает с отправленным, МС Сделка выдает кредит (меняет статус сущности "Кредит" на ISSUED, а статус заявки на CREDIT_ISSUED)
