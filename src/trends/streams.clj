(ns trends.streams
  (:require [org.httpkit.client :as http])
  (:require [clojure.data.json :as json]
            [util :as util]))

(defn ^:private streams-difference
  [[prev curr]]
  (for [stream prev
        :let [n (util/find-first #(= (get %1 :name) (get stream :name)) curr)]
        :when (not (nil? n))]
    {:stream n
     :delta (- (:viewers n)
               (:viewers stream))}))

(defrecord Stream [name status viewers])
(def history (atom []))

(defn trending
  "Get the current trending streams"
  []
  (streams-difference @history))

(defn calculate-rankings
  [current]
  (swap! history
         ; TODO: check insertion times and filter by that
         (fn [r] (take-last 2 (conj r current)))))

(defn parse-stream
  [stream]
  (Stream. (get-in stream ["channel" "name"])
           (get-in stream ["channel" "status"])
           (get-in stream ["viewers"])))

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
