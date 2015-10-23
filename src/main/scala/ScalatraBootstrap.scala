import org.scalatra.{CorsSupport, LifeCycle}

import javax.servlet.ServletContext
class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    // TODO possibly toggle
    context.initParameters("org.scalatra.environment") = "production"

    context.setInitParameter(CorsSupport.AllowedOriginsKey, "*")
    context.setInitParameter(CorsSupport.AllowedMethodsKey, "GET,PUT,POST")
    context.setInitParameter(CorsSupport.AllowedHeadersKey, "Content-Type")
    context.setInitParameter(CorsSupport.AllowCredentialsKey, "true")
  }
}

