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

(defn messages->send [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=send"
  (post-request "messages/send" data))

(defn messages->search [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=search"
  (post-request "messages/search" data))

(defn messages->search-time-series [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=search-time-series"
  (post-request "messages/search-time-series" data))

(defn messages->info [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=info"
  (post-request "messages/info" data))

(defn messages->parse [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=parse"
  (post-request "messages/parse" data))

(defn messages->send-raw [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=send-raw"
  (post-request "messages/send-raw" data))

(defn messages->list-scheduled [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=list-scheduled"
  (post-request "messages/list-scheduled" data))

(defn messages->cancel-scheduled [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=cancel-scheduled"
  (post-request "messages/cancel-scheduled" data))

(defn messages->reschedule  [data]
  "Documentation for the 'data' paramerer located on
  https://mandrillapp.com/api/docs/messages.JSON.html#method=reschedule "
  (post-request "messages/reschedule" data))
