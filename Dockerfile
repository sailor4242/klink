FROM java:8-jre
MAINTAINER DZavorin <kai_vasya@mail.ru>
ADD ./target/klink.jar /app/
CMD ["java", "-jar", "/app/klink.jar"]
EXPOSE 8081