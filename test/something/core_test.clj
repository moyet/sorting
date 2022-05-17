(ns something.core-test
  (:require [clojure.test :refer :all]
            [sorting.core :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            ))

(deftest sorting-test
  (let [a-list (gen/sample gen/small-integer)]
    (testing "Quicksort and mergesort is the same"
      (is (= (flatten (qsort a-list)) (mergesort a-list)))
      )
    (testing "Bubblesort and mergesort is the same"
      (is (= (bubblesort a-list) (mergesort a-list)))
      )
    ))

(def property
  (prop/for-all [v (gen/vector gen/small-integer)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (or (empty? s)
                           (apply <= s))))))
