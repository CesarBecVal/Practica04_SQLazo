FROM openjdk:17 

WORKDIR /app

COPY src ./src
    
RUN javac $(find src -name "*.java")

CMD ["java", "mx.unam.ciencias.myp.rockbuster.catalogo.Main"]