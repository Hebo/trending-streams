;http://pragprog.com/magazines/2011-07/create-unix-services-with-clojure
(ns util
  (:import (java.util.concurrent ScheduledThreadPoolExecutor TimeUnit)))

(def ^:private num-threads 1)
(def ^:private pool (atom nil))

(defn- thread-pool []
  (swap! pool (fn [p] (or p (ScheduledThreadPoolExecutor. num-threads)))))

(defn periodically
  "Schedules function f to run every 'delay' milliseconds after a
  delay of 'initial-delay'."
  [f initial-delay delay]
  (.scheduleWithFixedDelay (thread-pool)
                           f
                           initial-delay delay TimeUnit/MILLISECONDS))
(defn shutdown
  "Terminates all periodic tasks."
  []
  (swap! pool (fn [p] (when p (.shutdown p)))))


(defn find-first
  "Returns the first item of coll for which (pred item) returns logical true.
  Consumes sequences up to the first match, will consume the entire sequence
  and return nil if no match is found."
  [pred coll]
  (first (filter pred coll)))
