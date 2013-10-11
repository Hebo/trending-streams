(ns trends.streams
  (:require [org.httpkit.client :as http])
  (:require [clojure.data.json :as json]))

(def rankings (atom []))
(defn calculate-rankings
  [current]
  (swap! rankings
         ; TODO: check insertion times and filter by that
         (fn [r] (conj r current)))
  (println (str "Rankings: " @rankings))
  @rankings)

(defn parse-stream
  [stream]
    (str (get-in stream ["channel" "name"])
         ":"
         (get-in stream ["viewers"])
         " "))

(defn parse-res
  [res]
  (map parse-stream (get res "streams")))

(defn lookup 
  "lookup streams"
  []
  (http/get "https://api.twitch.tv/kraken/streams?limit=5"
    (fn [{:keys [status headers body error]}]
      (if error
        (println "Failed, exception is " error)
        (->> (json/read-str body)
            (parse-res)
            (calculate-rankings))))))
