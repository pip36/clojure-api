(ns hello-world.db.recipes.get-recipe-by-id
  (:require [next.jdbc :as jdbc]
            [honeysql.core :as sql]
            [hello-world.db.db :refer [ds]]
            [clojure.data.json :as json]))

(defn recipe-by-id-sql [id] {:select [:id :name]
                             :from   [:recipes]
                             :where  [:= :id id]})

(defn get-recipe-by-id [id]
  (jdbc/execute! ds (sql/format (recipe-by-id-sql id))))


(defn recipe-by-id [id]
  (let [recipe (get-recipe-by-id id)
        found? (not-empty recipe)]
    {:status (if found? 200 404)
     :headers {"Content-Type" "application/json"}
     :body (when found? (json/write-str recipe))}))
