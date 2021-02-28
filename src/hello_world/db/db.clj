(ns hello-world.db.db
  (:require [next.jdbc :as jdbc]
            [hello-world.config :refer [db]]))

(def ds (jdbc/get-datasource db))
