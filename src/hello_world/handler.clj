(ns hello-world.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hello-world.responses.recipe-responses :refer [all-recipes recipe-by-id]]
            [hello-world.responses.not-found :refer [not-found]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/recipes" [] (all-recipes))
  (GET "/recipes/:id" [id]
    (recipe-by-id (Integer/parseInt id)))
  (route/not-found not-found))

(def app
  (wrap-defaults app-routes site-defaults))
