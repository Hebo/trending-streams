(ns trends.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [trends.streams :as streams]
            [util :as util]))

(defroutes app-routes
  (GET "/" [] (apply str (last @streams/history)))
  (GET "/lookup" [] (do
                      (streams/lookup)
                      "done!"))
  (route/resources "/")
  (route/not-found "Not Found"))



(def app (handler/site app-routes))

(defn start
  []
  (let [immediately 0
       every-minute (* 60 1000)]
    (util/periodically streams/lookup immediately every-minute)))

(defn shutdown
  []
  (util/shutdown))
