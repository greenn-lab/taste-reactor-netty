package http.server.routing;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class Application {

  public static void main(String[] args) {
    DisposableServer server =
        HttpServer.create()
            .route(routes ->
                routes.get("/hello",
                        (request, response) -> response.sendString(Mono.just("Hello World!")))
                    .post("/echo",
                        (request, response) -> response.send(request.receive().retain()))
                    .get("/path/{param}",
                        (request, response) -> response.sendString(
                            Mono.just(request.param("param"))))
                    .ws("/ws",
                        (wsInbound, wsOutbound) -> wsOutbound.send(wsInbound.receive().retain())))
            .bindNow();

    server.onDispose()
        .block();
  }

}
