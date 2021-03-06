(ns sample-webapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.reload :refer [wrap-reload]]
            [sample-webapp.repo :refer [mock-book mock-library mock-author]]
            [clojure.string :as str]))

(defmacro GET-by-id
  "Generates a route for a get by id type GET for the given entity

   The generated route expects identifier at the end of the URL with a compojure
   style ':id' reference.

   The repo function should be a function which expects a single parameter, the id,
   and either returns a string result or nil if entity not found"
  [url repo-fn]
  (let [id (-> url (str/split #"[/:]") last keyword)]
    `(GET ~url request#
          (let [id# (-> request# :params ~id)]
            (if-let [result# (~repo-fn id#)]
              {:status 200
               :body result#}
              {:status 404
               :body "not found beeee"})))))

(defroutes app-routes
  (GET-by-id "/api/book/:isdn" mock-book)
  (GET-by-id "/api/author/:name" mock-author)
  (GET-by-id "/api/library/:post-code" mock-library)
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-file-info
      wrap-reload))
