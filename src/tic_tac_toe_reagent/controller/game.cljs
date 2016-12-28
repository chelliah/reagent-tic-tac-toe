(ns tic-tac-toe-reagent.controller.game
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "This text is printed from src/tic-tac-toe-reagent/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload
(defn new-board [n]
  (vec (repeat n (vec (repeat n 0)))))

(def winning-sets
  '([[0 0] [0 1] [0 2]]
    [[1 0] [1 1] [1 2]]
    [[2 0] [2 1] [2 2]]
    [[0 0] [1 0] [2 0]]
    [[0 1] [1 1] [2 1]]
    [[0 2] [1 2] [2 2]]
    [[0 0] [1 1] [2 2]]
    [[0 2] [1 1] [2 0]]))

(def turns ["X" "O"])

(def app-state (atom {:text "Tic Tac Toe"
                      :board-size 3
                      :turn 0
                      :board (new-board 3)}))


(defn get-tile-value [& {:keys [row column]}]
  (nth (nth (get @app-state :board) (int row)) (int column)))


(defn set-tile-value [& {:keys [row column]}]
  (let [board         (get @app-state :board)
        new-tile-val  (nth turns (mod (get @app-state :turn) 2))]
    (assoc-in board [row column] new-tile-val)))


(defn unmarked? [& {:keys [row column]}]
  (= 0 (get-tile-value :row row :column column)))

(defn mark-tile [& {:keys [row column]}]
  (if (unmarked? :row row :column column)
    (let [new-board  (set-tile-value :row row :column column)]
      (swap! app-state assoc :board new-board)
      (swap! app-state update-in [:turn] inc))))

(defn init-game []
  (swap! app-state assoc :board (new-board 3)))

(defn get-app-state []
  @app-state)
