(ns sorting.core)

(defn foo
  "I don't do a whole lot."
  ([x]
   (println x "Hello, World!")))

(defn qsort
  [xs]
  (if (> (count xs) 1)
    (let [
          f (first xs)
          r (rest xs)
          upper (filter #(< f %) r)
          lower (filter #(<= % f) r)
          ]
      (flatten [(qsort lower) f (qsort upper)])
      )
    xs
    )
  )

(defn bubble
  [x ys]
  (let [y (first ys)]
    (if (nil? y)
      x
    (if (< x y)
      (list x ys)
      (list y (bubble x (rest ys)))))))

(defn bubblesort
  [xs]
    (if (= 1 (count xs))
      xs
  (let
      [
       f (first xs)
       r (rest xs)
       ]
    (flatten (bubble f (bubblesort r))
    ))))

(defn merging
  [xs ys]
  (let [
        x (first xs)
        y (first ys)
        ]
    (if (nil? x)
      ys
      (if (nil? y)
        xs
        (if (< x y)
          (list x (merging (rest xs) ys))
          (list y (merging  xs (rest ys)))
          )
        )
        )))

(defn split-in-two
  [xs]
  (let [
        c (count xs)
        halfed (/ c 2)
        flored (Math/ceil halfed)
        ]
  (partition-all flored xs)
  )
  )

(defn mergesort
  [xs]
  (let [a (count xs)
        [first second] (split-in-two xs)
        ]
    (if (< a 2)
      xs
      (flatten (merging (mergesort first) (mergesort second)))
    )))
