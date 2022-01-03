package tcp.server.address;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class MultipleAddressApplication {

  public static void main(String[] args) {
    final DisposableServer server1 = TcpServer.create()
        .host("localhost") // 첫번째 tcp 호스트 설정
        .port(8080) // 첫번째 tcp 포트 설정
        .bindNow();

    final DisposableServer server2 = TcpServer.create()
        .host("0.0.0.0") // 두번째 tcp 호스트 설정
        .port(8081) // 두번째 tcp 포트 설정
        .bindNow();

    Mono.when(server1.onDispose(), server2.onDispose())
        .block();
  }

}
