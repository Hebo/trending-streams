import akka.actor.{ActorSystem, Props}
import com.github.hebo.streamtrends._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  val system = ActorSystem()

  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new PageRetriever(system), "/page/*")
  }

  override def destroy(context:ServletContext) {
    system.shutdown()
  }
}
