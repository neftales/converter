import com.github.neftales.controllers.ConverterController
import org.scalatra.{CorsSupport, LifeCycle}

import javax.servlet.ServletContext
class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {

    context.mount(new ConverterController, "/converter", "converter")

    context.setInitParameter("org.scalatra.environment", "production")

    context.setInitParameter(CorsSupport.AllowedOriginsKey, "*")
    context.setInitParameter(CorsSupport.AllowedMethodsKey, "GET,PUT,POST")
    context.setInitParameter(CorsSupport.AllowedHeadersKey, "Content-Type")
    context.setInitParameter(CorsSupport.AllowCredentialsKey, "false")
  }
}

