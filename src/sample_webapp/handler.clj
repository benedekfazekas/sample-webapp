(ns sample-webapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.reload :refer [wrap-reload]]
            [sample-webapp.repo :refer [mock-book mock-library mock-author]]))

(defmacro get-by-id-route
  "Generates a route for a get by id type GET for the given entity

   The generated route expects identifier at the end of the URL.
   The repo function should be a function which expects the a single parameter, the id
   and either returns a string result or nil if entity not found"
  [entity-name repo-fn]
  (let [url (str "/api/" entity-name "/:id")]
    `(GET ~url request#
          (let [id# (-> request# :params :id)]
            (if-let [result# (~repo-fn id#)]
              {:status 200
               :body result#}
              {:status 404
               :body "not found beeee"})))))

(defroutes app-routes
  (get-by-id-route "book" mock-book)
  (get-by-id-route "author" mock-author)
  (get-by-id-route "library" mock-library)
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-file-info
      wrap-reload))
