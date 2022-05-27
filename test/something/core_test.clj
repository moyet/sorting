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
      (is (= (flatten (qsort a-list)) (mergesort a-list))))
    (testing "Bubblesort and mergesort is the same"
      (is (= (bubblesort a-list) (mergesort a-list))))
    (testing "Bubblesort works on a known problem"
      (let [a-list '(65 324 432 123 44 -12 321 342 54 12 34 23231 3) ]
        (is (= (bubblesort a-list) '(-12 3 12 34 44 54 65 123 321 324 342 432 23231) ))))))

(def property
  (prop/for-all [v (gen/vector gen/small-integer)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (or (empty? s)
                           (apply <= s))))))
