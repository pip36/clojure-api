(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/data.json "1.0.0"]
                 [com.layerware/hugsql "0.5.1"]
                 [seancorfield/next.jdbc "1.1.613"]
                 [honeysql "1.0.461"]
                 [org.postgresql/postgresql "42.2.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler hello-world.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
