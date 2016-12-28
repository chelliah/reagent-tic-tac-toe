(ns tic-tac-toe-reagent.ui.view
  (:require
   [reagent.core :as reagent :refer [atom]]
   [tic-tac-toe-reagent.controller.game :as game]))

(defn o []
  [:svg
    [:ellipse.o {:cx 100 :cy 100 :rx 85 :ry 85}]])

(defn x []
  [:svg
    [:line.x.x1 {:x1 30 :y1 170 :x2 170 :y2 30}]
    [:line.x.x2 {:x1 30 :y1 30 :x2 170 :y2 170}]])


(defn tile [& {:keys [row column]}]
  (let [tile-value  (game/get-tile-value :row row :column column)]
    [:div.tile
     {:on-click #(game/mark-tile :row row :column column)}
     (cond
       (= "X" tile-value) (x)
       (= "O" tile-value) (o)
       :else "")]))

(defn header []
  [:div
   [:h1 (get (game/get-app-state) :text)]
   [:h1 "Turn Number: " (inc (get (game/get-app-state) :turn))]])

(defn board []
  [:div.board
   (doall
    (for [row (range (get (game/get-app-state) :board-size))]
      [:div.row
       (doall
        (for [column (range (get (game/get-app-state) :board-size))]
          (tile :row row :column column)))]))])
