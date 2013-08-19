package com.github.hebo.streamtrends

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends StreamtrendsStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
//  get("/trending") {
//    val streams = (1 to 10).map { _ => new TwitchStream }
//
//    contentType="text/html"
//    jade("/trending", "streams" -> streams)
//  }
}
