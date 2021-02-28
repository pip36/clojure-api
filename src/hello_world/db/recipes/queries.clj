(ns hello-world.db.recipes.queries
  (:require [next.jdbc :as jdbc]
            [honeysql.core :as sql]
            [honeysql.helpers :refer [insert-into values delete-from where]]
            [hello-world.db.db :refer [ds]]))

(defn get-all-recipes []
  (jdbc/execute! ds (sql/format {:select [:id :name]
                                 :from   [:recipes]})))

(defn get-recipe-by-id [id]
  (jdbc/execute! ds (sql/format {:select [:id :name]
                                 :from   [:recipes]
                                 :where  [:= :id id]})))

(defn add-recipe [recipe]
  (let [{name :name} recipe]
    (jdbc/execute! ds (-> (insert-into :recipes)
                          (values [{:name name}])
                          (sql/format))
                   {:return-keys [:id]})))

(defn delete-recipe-by-id [id]
  (jdbc/execute! ds (-> (delete-from :recipes)
                        (where [:= :id id])
                        (sql/format))))
