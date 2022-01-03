package tcp.server.create;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Application {

  public static void main(String[] args) {
    final DisposableServer server = TcpServer
        .create() // 인스턴스를 생성했고 설정할 준비가 됐고
        .bindNow(); // blocking 방식으로 서버가 시작됐고 초기화가 끝날때까지 대기

    server.onDispose().block();
  }

}
