FROM java:8-jdk
MAINTAINER Neftales Antunes <neftales@gmail.com>

ENV PORT 8080
EXPOSE 8080

ADD target/scala-2.11/convertAnyToOptionOfAny-assembly-0.1.0.jar /usr/local/
CMD java -jar /usr/local/convertAnyToOptionOfAny-assembly-0.1.0.jar
