# hotel-service-axon
This is proof of concept application for using LRA annotation on JAX-RS endpoints in the Axon Spirng-Boot application.
This service is the same as the **hotel-service** in the [LRA quickstart](https://github.com/jbosstm/quickstart/tree/master/rts/lra)
The REST layer is the same as original however the architecture of the application is based on event-sourcing and CQRS using Axon Framework.
The original **hotel-service** can be substituted by this service. Only one different is that this service use path `/hotel` instead of `/`



How to run

- First, the LRA quickstart repository should be cloned and set up. Following README in that [repo](https://github.com/jbosstm/quickstart/tree/master/rts/lra).
- When the LRA quickstarts is running, stop the original **hotel-service**
- Before continue, It needs to update original **trip-controller** in the LRA quickstart. Add suffix `/hotel` [here](https://github.com/jbosstm/quickstart/blob/master/rts/lra/trip-controller/src/main/java/io/narayana/rts/lra/demo/tripcontroller/TripController.java#L94)


```
git clone https://github.com/mkralik3/hotel-service-axon.git
cd hotel-service-axon
mvn clean install
docker run -it --rm --name my-axon-server -p 8024:8024 -p 8124:8124 axoniq/axonserver
java -Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true -Dorg.apache.catalina.connector.CoyoteAdapter.ALLOW_BACKSLASH=true -jar target/hotel-axon-0.0.1-SNAPSHOT.jar
```



