(ns tic-tac-toe-reagent.core
  (:require [reagent.core :as reagent :refer [atom]]
            [tic-tac-toe-reagent.ui.view :as view]))

(enable-console-print!)

(println "This text is printed from src/tic-tac-toe-reagent/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defn app []
  [:div
    (view/header)
    (view/board)])

(reagent/render-component [app]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
