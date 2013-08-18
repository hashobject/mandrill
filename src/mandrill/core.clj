(ns mandrill.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))


(defn make-url [route]
  (str "https://mandrillapp.com/api/1.0/" route ".json"))





(defn post-request
  "Make a generic POST HTTP request"
  [route body]
  (try
    (let [url (make-url route)
          json (json/generate-string body)
          resp (client/post url
                {:accept :json
                 :content-type :json
                 :body json})
          output (json/parse-string (:body resp))]
      output)
  (catch Exception e
     (println e)
    e)))



(defn ping [api-key]
  (post-request "users/ping" {:key api-key}))

(defn ping2 [api-key]
  (post-request "users/ping2" {:key api-key}))


