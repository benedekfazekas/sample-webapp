(ns sample-webapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.reload :refer [wrap-reload]]
            [cheshire.core :refer :all]))

(defroutes app-routes
  (GET "/api/book/:id" [id]
       (if (= id "1")
         {:status 200
          :body (generate-string {:id 1
                                  :title "On enthusiastic programming"
                                  :author "paci"
                                  :isbn "3930238-39390293-3030233"})}
         {:status 404
          :body "not found beeee"}))
  (GET "/api/author/:id" [id]
       (if (= id "1")
         {:status 200
          :body (generate-string {:name "paci"
                                  :id 1
                                  :genre "technical"})}
         {:status 404
          :body "not found beeee"}))
    (GET "/api/library/:id" [id]
       (if (= id "1")
         {:status 200
          :body (generate-string {:name "On the corner library"
                                  :id 1
                                  :location "my small town"})}
         {:status 404
          :body "not found beeee"}))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-file-info
      wrap-reload))
