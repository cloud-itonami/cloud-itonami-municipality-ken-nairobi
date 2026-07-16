(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest nairobi-has-spec-basis
  (let [sb (facts/spec-basis "nairobi")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://") sb))
    (is (every? :ordinance/number sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "mombasa")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["nairobi" "mombasa"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["mombasa"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["nairobi.finance-act-2023"]
         (mapv :ordinance/id (facts/by-topic "nairobi" :taxation))))
  (is (empty? (facts/by-topic "nairobi" :labor)))
  (is (empty? (facts/by-topic "mombasa" :taxation))))
