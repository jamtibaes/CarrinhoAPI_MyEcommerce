FROM openjdk:17
COPY ./build/libs/CarrinhoAPI-0.0.1-SNAPSHOT.jar CarrinhoAPI-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "CarrinhoAPI-0.0.1-SNAPSHOT.jar"]