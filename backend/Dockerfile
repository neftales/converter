FROM java:8-jdk
MAINTAINER Neftales Antunes <neftales@gmail.com>

ENV PORT 8080
EXPOSE 8080

ADD target/scala-2.13/convertAnyToOptionOfAny-*.jar /usr/local/app.jar
CMD java -jar /usr/local/app.jar
