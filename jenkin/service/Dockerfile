FROM adoptopenjdk/openjdk11:ubi
# copy the packaged jar file into our docker image
COPY ./app.jar /app.jar
# set the startup command to execute the jar
CMD ["java", "-jar"  ,"/app.jar"]
