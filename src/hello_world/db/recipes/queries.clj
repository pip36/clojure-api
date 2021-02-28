(ns hello-world.db.recipes.queries
  (:require [next.jdbc :as jdbc]
            [honeysql.core :as sql]
            [hello-world.db.db :refer [ds]]))

(defn get-all-recipes []
  (jdbc/execute! ds (sql/format {:select [:id :name]
                                 :from   [:recipes]})))

(defn get-recipe-by-id [id]
  (jdbc/execute! ds (sql/format {:select [:id :name]
                                 :from   [:recipes]
                                 :where  [:= :id id]})))
