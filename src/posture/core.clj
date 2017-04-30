(ns posture.core
  (:use [clojure.java.shell :only [sh]])
  (:gen-class))

(def NOTIFY-INTERVAL-MINUTES 2)
(def MESSAGES ["Sit up straight!"
               "Remember your posture!"
               "Scoot your butt all the way to the back of your seat."
               "I know it hurts sometimes, but thats your back growing stronger."
               "Place your feet flat on the floor."
               "Build up your back strength! It will get easier!"
               "Dont cross your legs."
               "Make sure you aren't hunched."
               "Dont let your headphones pull your head down."
               "Roll your shoulders back."])

(defn complete-sound
  "Makes a sound to signify that a notification has gone off."
  []
  (sh "paplay" "/usr/share/sounds/freedesktop/stereo/complete.oga"))

(defn notify-send
  "Sends a notification to the screen in the form of a notification bubble."
  [text]
  (complete-sound)
  (sh "notify-send" text))

(defn -main
  "Reminds me to check my posture at a given interval of time."
  [& args]
  (while true
    (Thread/sleep (* NOTIFY-INTERVAL-MINUTES 60000))
    (let [index (rand-int (count MESSAGES))]
      (notify-send (MESSAGES index)))))
