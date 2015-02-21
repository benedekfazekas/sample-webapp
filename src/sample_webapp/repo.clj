(ns sample-webapp.repo
  (:require [cheshire.core :refer :all]))

(defn mock-book [id]
  (when (= id "1")
    (generate-string {:id 1
                      :title "On enthusiastic programming"
                      :author "paci"
                      :isbn "3930238-39390293-3030233"})))

(defn mock-author [id]
  (when (= id "1")
    (generate-string {:name "paci"
                      :id 1
                      :genre "technical"})))

(defn mock-library [id]
  (when (= id "1")
    (generate-string {:name "On the corner library"
                      :id 1
                      :location "my small town"})))
