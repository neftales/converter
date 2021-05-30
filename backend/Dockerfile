ARG TARGET_FOLDER=target
FROM java:8-jdk
LABEL AUTHOR="Neftales Antunes <neftales@gmail.com>"

ARG TARGET_FOLDER
ENV PORT 8080
EXPOSE 8080

ADD ${TARGET_FOLDER}/scala-2.13/converter-api-*.jar /usr/local/app.jar
CMD java -jar /usr/local/app.jar
