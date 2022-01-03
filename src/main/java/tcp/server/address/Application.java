package tcp.server.address;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Application {

  public static void main(String[] args) {
    final DisposableServer server = TcpServer.create()
        .host("localhost") // TCP 서버 호스트 설정
        .port(8080) // TCP 서버 포트 설정
        .bindNow();

    server.onDispose().block();
  }
}
