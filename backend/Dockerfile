ARG TARGET_FOLDER=target
FROM java:8-jdk
LABEL AUTHOR="Neftales Antunes <neftales@gmail.com>"

ENV PORT 8080
EXPOSE 8080

ADD ${TARGET_FOLDER}/scala-2.13/convertAnyToOptionOfAny-*.jar /usr/local/app.jar
CMD java -jar /usr/local/app.jar
