(ns trends.streams
  (:require [org.httpkit.client :as http])
  (:require [clojure.data.json :as json]))

(defn parse-stream
  [stream]
    (str (get-in stream ["channel" "name"])
         ":"
         (get-in stream ["viewers"])
         " "))

(defn parse-res
  [res]
  (-> (get res "streams")
      (->> (map parse-stream)
            println)))

(defn lookup 
  "lookup streams"
  []
  (http/get "https://api.twitch.tv/kraken/streams?limit=5"
    (fn [{:keys [status headers body error]}]
      (if error
        (println "Failed, exception is " error)
        (parse-res (json/read-str body))))))
