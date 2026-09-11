(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest nairobi-has-culture-basis
  (let [sb (facts/spec-basis "nairobi")]
    (is (= 8 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "nairobi" (:culture/municipality %)) sb))
    (is (every? #(= "KEN" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "mombasa")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["nairobi" "mombasa"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["mombasa"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 5 (count (facts/by-kind "nairobi" :dish))))
  (is (= ["nairobi.product.kenyan-coffee"]
         (mapv :culture/id (facts/by-kind "nairobi" :product))))
  (is (empty? (facts/by-kind "nairobi" :craft)))
  (is (empty? (facts/by-kind "mombasa" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
