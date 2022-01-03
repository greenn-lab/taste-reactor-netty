package tcp.server.warmup;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

/**
 * <h1>Eager Initialization</h1>
 * 기본적으로 <em>TcpServer</em> 리소스의 초기화는 요청했을 한다. 이말인즉, 초기화와 로드에 부가적으로 필요한 시간을 흡수하는 <string>bind
 * operation</string> 이라는... <strike>뭔소린지...</strike>
 *
 * @see <a href="https://projectreactor.io/docs/netty/release/reference/index.html#_eager_initialization">Documentation</a>
 */
public class Application {

  public static void main(String[] args) {
    final TcpServer server = TcpServer.create().handle((in, out) -> in.receive().then());

    server.warmup() // 보안을 위해 이벤트 루프 그룹들과 네이티브 전송 라이브러리, 네이티브 라이브러리를 초기화하고 로드
        .block();

    final DisposableServer disposableServer = server.bindNow();
    disposableServer.onDispose().block();

  }

}
