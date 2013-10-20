(defproject trends "0.1.0-SNAPSHOT"
  :description "Website to show the trending streams on Twitch TV."
  :url "https://github.com/Hebo/trending-streams"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring-serve "0.1.2"]
                 [http-kit "2.1.10"]
                 [org.clojure/data.json "0.2.3"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler trends.handler/app
         :destroy trends.handler/shutdown}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
