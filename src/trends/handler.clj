(ns trends.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [trends.streams :as streams]))

(defroutes app-routes
  (GET "/" [] (do
    (println streams/aloha)
    "Hello World"))
  (GET "/lookup" [] (streams/lookup))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
