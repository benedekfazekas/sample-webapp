(ns sample-webapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.reload :refer [wrap-reload]]
            [cheshire.core :refer :all]))

(defroutes app-routes
  (GET "/api/book/:isbn" [isbn]
       (if (= isbn "1")
         {:status 200
          :body (generate-string {:title "On enthusiastic programming"
                                  :author "paci"
                                  :isbn "1"})}
         {:status 404
          :body "not found beeee"}))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-file-info
      wrap-reload))
