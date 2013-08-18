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
     (let [exception-info (.getData e)]
     (select-keys
       (into {} (map (fn [[k v]] [(keyword k) v])
         (json/parse-string
             (get-in exception-info [:object :body]))))
             (vector :status :message :code))))))


(defn users->info [api-key]
  (post-request "users/info" {:key api-key}))


(defn users->ping [api-key]
  (post-request "users/ping" {:key api-key}))

(defn users->ping2 [api-key]
  (post-request "users/ping2" {:key api-key}))

(defn users->senders [api-key]
  (post-request "users/senders" {:key api-key}))

(defn messages->send [message]
  (post-request "messages/send" message))

(messages->send {:key ""
                 :message {:text "Hello"
                           :subjet "Hey Anton. API test"
                           :from_email "team@communi.st"
                           :from_name "team communi.st"
                           :to: [{:name "Anton Podviaznikov"
                                  :email "podviaznikov@gmail.com"}]
                           :async false}})



