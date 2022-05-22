## Что это
Прохождение курса Accessing Relational Databases with Quarkus (Udemy)

в папке agoncal-course-quarkus-starting лежит оригинальный исходный код от автора с [github](git@github.com:agoncal/agoncal-course-quarkus-jpa-panache.git)

## Создание проекта
```bash
mkdir quarkus-course && cd quarkus-course

mvn io.quarkus.platform:quarkus-maven-plugin:2.9.0.Final:create \
    -DprojectGroupId=org.agoncal.course.quarkus.orm \
    -DprojectArtifactId=artist \
    -DpackageName="org.agoncal.quarkus.jdbc" \
    -Dextensions="jdbc-mysql, quarkus-agroal"

mvn io.quarkus.platform:quarkus-maven-plugin:2.8.3.Final:create \
    -DprojectGroupId=org.agoncal.course.quarkus.orm \
    -DprojectArtifactId=customer \
    -DpackageName="org.agoncal.quarkus.jpa" \
    -Dextensions="jdbc-mariadb, hibernate-orm"

mvn io.quarkus.platform:quarkus-maven-plugin:2.9.0.Final:create \
    -DprojectGroupId=org.agoncal.course.quarkus.orm \
    -DprojectArtifactId=vintage-store \
    -DpackageName="org.agoncal.quarkus.panache" \
    -Dextensions="jdbc-postgresql, hibernate-orm-panache"
```

## тесты
Запуск тестирования дочерних процессов параллельно:
```bash
mvn -T 1C test
```

## ORM
Чтобы научить Panache работать с POJO, надо прописать его как Entity (src/main/resources/META-INF/orm.xml):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm"
                 version="2.0">
    <entity class="org.agoncal.quarkus.jdbc.Artist">
        <table name="t_artists"/>
        <attributes>
            <id name="id">
                <generated-value strategy="AUTO"/>
            </id>
        </attributes>
    </entity>
</entity-mappings>
```
и создать репозиторий для этого POJO:
```java
//  src/main/java/org/agoncal/quarkus/panache/repository/ArtistRepository.java
package org.agoncal.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.agoncal.quarkus.jdbc.Artist;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {}
```

Чтобы научить Panache работать с существующими JPA Entities, надо создать для каждого PanacheRepository:
```java
//  src/main/java/org/agoncal/quarkus/panache/repository/CustomerRepository.java
package org.agoncal.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.jpa.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {}
```
Если этот JPA Entity находится в стороннем jar, надо в тот сторонний jar добавить маркер - пустой файл src/main/resources/META-INF/panache-archive.marker.

### Создать SQL-скрипты для создания БД
```properties
# src/main/resources/application.properties
quarkus.hibernate-orm.scripts.generation=create
quarkus.hibernate-orm.scripts.generation.create-target=create.sql
quarkus.hibernate-orm.scripts.generation.drop-target=drop.sql
```

### Профили
В режиме разработки можно посмотреть, какие порт, пользователь и пароль используются для отладочной БД. Это всё в [Config editor](http://localhost:8080/q/dev/io.quarkus.quarkus-vertx-http/config) по фильтру **datasource**

## Links
- [Исходники для курса](https://github.com:agoncal/agoncal-course-quarkus-jpa-panache.git)
- [bootstrap application online](https://code.quarkus.io/)
- [Support](https://quarkus.io/support/)
- [Discussions on github](https://github.com/quarkusio/quarkus/discussions)
- [Guides](https://quarkus.io/guides/)
- [Google groups](https://groups.google.com/g/quarkus-dev)
- [Official repo](https://github.com/quarkusio/)
- [Список сторонних расширений](https://github.com/quarkiverse)
- [Antonio Goncalves' blog](https://antoniogoncalves.org/)
- [курсы Antonio Goncalves](https://agoncal.teachable.com/)