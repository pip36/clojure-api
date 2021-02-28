(ns hello-world.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.data.json :as json]
            [hello-world.db.recipes.get-all-recipes :refer [all-recipes]]
            [hello-world.db.recipes.get-recipe-by-id :refer [recipe-by-id]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/recipes" [] (all-recipes))
  (GET "/recipes/:id" [id]
    (recipe-by-id (Integer/parseInt id)))
  (route/not-found {:status 200
                    :headers {"Content-Type" "application/json"}
                    :body (json/write-str {:msg "Page Not Found "})}))

(def app
  (wrap-defaults app-routes site-defaults))
