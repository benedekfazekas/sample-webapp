(ns sample-webapp.repo
  (:require [cheshire.core :refer :all]))

(defn mock-book [isdn]
  (when (= isdn "1")
    (generate-string {:title "On enthusiastic programming"
                      :author "paci"
                      :isdn "1"})))

(defn mock-author [name]
  (when (= name "paci")
    (generate-string {:name "paci"
                      :genre "technical"})))

(defn mock-library [post-code]
  (when (= post-code "se103zf")
    (generate-string {:name "On the corner library"
                      :postcode "se103zf"
                      :location "my small town"})))
