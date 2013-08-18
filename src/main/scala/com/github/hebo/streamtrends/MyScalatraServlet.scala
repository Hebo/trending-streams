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
  
  get("/trending") {
    <html>
      <body>
        <h1>Trending Streams!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
}
