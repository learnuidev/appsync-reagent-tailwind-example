(ns app.core
  (:require [reagent.dom :as rdom]
            [app.config :refer [env]]
            ["aws-amplify" :default Amplify :refer [Auth]]))

;;
(defn sign-in [username, password]
  (-> (.signIn Auth username password)
      (.then #(js/console.log %))
      (.catch #(js/console.log %))))

(comment
  (sign-in "hello@demo.com" "")
  (sign-in (-> env :auth :email) (-> env :auth :password)))

;; 1. Configuring User Pool
(defn configure-user-pool! []
  (.configure
   Amplify
   (clj->js {:Auth {:region (-> env :aws :region)
                    :userPoolId (-> env :aws :userPoolId)
                    :userPoolWebClientId (-> env :aws :userPoolWebClientId)
                    ::graphqlEndpoint (-> env :aws :graphqlEndpoint)
                    :mandatorySignIn true}})))

;; 2. Configure App Sync
(defonce app-config
  {:aws_appsync_graphqlEndpoint (-> env :aws :graphqlEndpoint)
   :aws_appsync_region (-> env :aws :region)
   :aws_appsync_authenticationType (-> env :aws :authenticationType)})

(defn configure-app! []
  (.configure Amplify (clj->js app-config)))

(defn app []
  [:div "Hello Twitter"])

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [app] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (configure-user-pool!)
  (configure-app!)
  (render))
