(ns app.core
  (:require [reagent.dom :as rdom]))

(defn app []
  [:div.py-3.px-3 "Hello Twitter"])

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [app] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (render))
