(ns hello-world.handler
  (:require [compojure.core :refer [GET POST DELETE defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [hello-world.responses.recipe-responses :refer [all-recipes
                                                            recipe-by-id
                                                            delete-recipe
                                                            add-new-recipe]]
            [hello-world.responses.not-found :refer [not-found]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/recipes" [] (all-recipes))
  (POST "/recipes" {recipe :body} (add-new-recipe recipe))
  (GET "/recipes/:id" [id]
    (recipe-by-id (Integer/parseInt id)))
  (DELETE "/recipes/:id" [id] (delete-recipe (Integer/parseInt id)))
  (route/not-found not-found))

(def app
  (wrap-json-response
   (wrap-json-body (wrap-defaults app-routes api-defaults) {:keywords? true})))
