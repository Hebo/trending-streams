# trends

FIXME

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

REPL

    lein repl
    (use 'trends.handler)
    (use 'ring.util.serve)
    (serve app)

Reload

    (use 'trends.streams :reload-all)

## License

Copyright © 2013 FIXME
