(ns sample-webapp.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [sample-webapp.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/api/book/1"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"isbn\":\"1\",\"title\":\"On enthusiastic programming\",\"author\":\"paci\"}"))))

  (testing "not-found book"
    (let [response (app (mock/request :get "/api/book/2"))]
      (is (= (:status response) 404)))))
