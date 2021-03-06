(defproject mandrill "0.1.0-SNAPSHOT"
  :description "Clojure wrapper for Mandrill API"
  :signing {:gpg-key "Hashobject Ltd <team@hashobject.com>"}
  :url "https://github.com/hashobject/mandrill"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "0.9.1"]
                 [cheshire "5.3.1"]])
