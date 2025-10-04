FROM openjdk:17
WORKDIR /app
COPY src ./src
RUN mkdir out && javac -d out $(ls src/**/*.java)
CMD ["java", "-cp", "out", "mx.unam.ciencias.myp.rockbuster.catalogo.Main"]
