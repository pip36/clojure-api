(ns hello-world.db.recipes.get-all-recipes
  (:require [next.jdbc :as jdbc]
            [honeysql.core :as sql]
            [hello-world.db.db :refer [ds]]
            [clojure.data.json :as json]))

(def all-recipes-sql {:select [:id :name]
                      :from   [:recipes]})

(defn get-all-recipes []
  (jdbc/execute! ds (sql/format all-recipes-sql)))

(defn all-recipes []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str (get-all-recipes))})


