Принятие и обработка сообщений от Kafka. 

Первое приложение — order-service_Kafka. 

Оно имеет эндпоинт, на который приходит POST-запрос с сущностью Order.

Эндпоинт принимает сущность и отправляет в Kafka событие OrderEvent.

Событие отправляется в топик order-topic.

Это приложение имеет KafkaListener, который будет слушать события по топику order-status-topic. 

Этот слушатель выводит в консоль информацию о событии в следующем формате:

log.info("Received message: {}", message);

log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);

Второе приложение — order-status-service_Kafka — не имеет эндпоинтов. 

Оно состоит из KafkaListener, который слушает топик order-topic. 

Когда в слушатель приходит событие, происходит отправка другого события в топик  order-status-topic. 

Это событие состоит из полей String status и Instat date.

Запустите контейнеры в docker для zookeeper и kafka.

Команды:

cd docker

docker-start.cmd start

Запустите приложения order-service_Kafka и order-status-service_Kafka. 

Можно отправить POST-запрос с сущностью Order.