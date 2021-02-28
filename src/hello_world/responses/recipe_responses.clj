(ns hello-world.responses.recipe-responses
  (:require
   [hello-world.db.recipes.queries :refer [get-all-recipes
                                           get-recipe-by-id
                                           add-recipe
                                           delete-recipe-by-id]]
   [clojure.data.json :as json]))

(defn all-recipes []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str (get-all-recipes))})

(defn recipe-by-id [id]
  (let [recipe (get-recipe-by-id id)
        found? (not-empty recipe)]
    {:status (if found? 200 404)
     :headers {"Content-Type" "application/json"}
     :body (when found? (json/write-str recipe))}))

(defn add-new-recipe [recipe]
  {:status 201
   :headers {"Content-Type" "application/json"}
   :body {:id (:recipes/id (first (add-recipe recipe)))}})

(defn delete-recipe [id]
  (delete-recipe-by-id id)
  {:status 204})

