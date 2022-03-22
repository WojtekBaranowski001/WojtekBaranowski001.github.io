breed [ zombies zombie ] ;breed for zombies
breed [ humans person ] ;breed for humans


globals [convert_probability student_id student_username vis_rad] ;global variables

patches-own[solid] ;variable for patches to set patches solid
humans-own [vis_ang] ;variable for humans to set vis_ang to 90 degrees

to setup_world ;setup_world function
  clear-all ;this clears the world
  reset-ticks ;this resets the ticks
  set convert_probability (convert_probability_percantage) ;links convert_probability global with the slider in the interface
  setup-patches ;call setup-patches function
  set vis_rad (random 10) + 10 ;set vis_rad to between 10 and 20 patches




  set student_id "18025815" ;set student_id
  set student_username "wb19aab" ;set student_username

  create-zombies 5 [ ;create 5 zombies
    setxy random-xcor random-ycor ;set random x and y
    set color red ;set color of zombies to red
    set size 4 ;set size of zombies to 4
    set shape "person" ;set shape of zombies to "person"
    move-to one-of patches with [pcolor = green] ;spawn zombies only on green patches

  ]

  create-humans 15 [ ;create 15 humans
    setxy random-xcor random-ycor ;set random x and y
    set color blue ;set color of humans to blue
    set size 5 ;set size to 5
    set shape "person" ;set shape of human to "person"

  ]
  ask humans [ ;ask humans to do whatever is in the brackets
    ask patches in-radius 5 [ ;ask 5 patches in the humans radius
      set pcolor green ;to set up orange color

    ]
  ]

  ask zombies [ ;ask zombies to do whatever is in the brakcets
    move-to one-of patches with [ ;spawn zombies only on...
      pcolor = green ;green patches

    ]
  ]

  ask patches [ ;ask patches to
    set pcolor green ;set color to green
  ]

  ask patches [ ;ask patches to do whatever is in the brackets
    set solid false ;set solid false
  ]
  ask n-of 100 patches with [pcolor = green][ ;spawn 100 patches on green patches
    set pcolor brown ;with color brown
    set solid true ;set solid true
  ]

end
to setup-patches ;setup-patches function
  ask patches [ ;ask patches in the bracket to...
    set pcolor green ;set their color to green
    set solid false ;set solid to false
  ]
  ask n-of 100 patches [ ;ask 100 patches to...
    set pcolor brown ;set color brown
    set solid true ;set solid true
  ]
end



to run_model ;run_model function

  make_zombies_move ;call function make_zombies_move
  make_humans_move ;call function make_humans_move
  tick ;add 1 tick
  reset_patch_colour ;call reset_patch_colour function


  if not any? humans [ ;if there's no humans left netlogo will display the message bellowe
    user-message (word "All humans have been infected")
    stop ;stop the simulation
  ] ;if there's no humans left stop the simulation
  if ticks = 50000 [stop] ;if tick counter is 5000 stop the simulation
  if not any? zombies [ ;if there's no zombies left netlogo will display the message bellow
    user-message (word "All zombies have been killed")
    stop ;stop the simulation
  ]


end

to make_zombies_move ;make_zombies_move function
  ask zombies [ ;ask zombies to do whatever is in the bracket
    set color red ;set color of zombies to red
    ;pen-down ;to check if zombies go over the solid blocks
    right random 45 ;random 45 degrees turn to right
    left random 45;random 45 degrees turn to left


    if show_smell_rad = true [ ;if show_smell_rad is turned on
    ask patches in-radius 10 with [pcolor = green][ ;ask 10 patches in radius with pen color green to
      set pcolor yellow ;set pen color yellow

    ]
  ]
  if any? humans in-radius 5 [ ;if humans in radius of 8 patches
      let my-human one-of humans in-radius 5
      set heading towards my-human ;set zombie heading towards the human
      fight_convert my-human ;call convert_probability global and let them fight based on the slider

  ]

    forward 0.5 ;set speed of zombies to 0.5
    detect_patch ;call detect_patch function

  ]



end

to fight_convert [my-human]
  ifelse (convert_probability_percantage * 10) <= random 100 [
    ask my-human [
      set breed zombies ;set human breed to zombie
      set color red ;set their color to red
      set size 4 ;set their size to 4
      set shape "person" ;set their shape to "person"
      forward 0.5 ;set speed to 0.5
    ]
  ][
    die
  ]
end


to reset_patch_colour ;to reset_patch_colour function this function is used so that smell_radius for zombies, vis_cone and vis_rad colours are not going over green patches.
  ask patches with [pcolor = orange][ ;ask patches with pen color orange
    set pcolor green ;to set pen color green
  ]
  ask patches with [pcolor = yellow][ ;ask patches with pen color yellow
    set pcolor green;to set pen color green
  ]
end

to make_humans_move ;make_humans_move function

  ask humans [ ;ask humans to do whatever is in the brackets
    ;pen-down ;to check if humans go over the solid blocks
    set color blue ;set color blue
    let seen [false] ;set seen to false
    set vis_ang 90 ;set vis_ang for humans to 90 degrees





    ask zombies in-cone vis_rad vis_ang [ ;ask zombies in the visiual cone to
      set color red ;set color white if they seen
      set seen true ;set seen true
    ]


    if show_vis_cone = true [ ;if show_vis_cone is turned on
      ask patches in-cone vis_rad vis_ang with [pcolor = green] [ ;ask patches in vis_rad and vis_ang with pen color green
        set pcolor orange ;set pen color to orange

      ]
    ]
    if seen = true [ ;if seen true
      right 180 ;turn 180 degrees to avoid the zombies

    ]


    detect_patch ;call detect_patch function
    left random 45 ;turn left radnom 45 degrees
    right random 45 ;turn right radnom 45 degrees
    forward 1 + random-float 0.1 ;humans speed
  ]
end

to detect_patch ;to detect_patch function
  if [solid] of patch-ahead 100 = true [ ;if there is solid brown patch ahead of zombie and human
    right 45 ;turn right 45
  ]
end
@#$#@#$#@
GRAPHICS-WINDOW
263
18
776
532
-1
-1
5.0
1
10
1
1
1
0
1
1
1
-50
50
-50
50
1
1
1
ticks
30.0

BUTTON
17
34
117
67
NIL
setup_world\n
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL
1

BUTTON
121
35
210
68
NIL
run_model\n
T
1
T
OBSERVER
NIL
NIL
NIL
NIL
0

SWITCH
16
132
156
165
show_vis_cone
show_vis_cone
0
1
-1000

SLIDER
16
80
236
113
convert_probability_percantage
convert_probability_percantage
0
10
8.0
1
1
NIL
HORIZONTAL

SWITCH
17
183
160
216
show_smell_rad
show_smell_rad
0
1
-1000

PLOT
797
20
1263
299
Population
NIL
NIL
0.0
10.0
0.0
10.0
true
true
"" ""
PENS
"Zombies" 1.0 0 -5298144 true "" "plot count zombies"
"Humans" 1.0 0 -14070903 true "" "plot count humans"

MONITOR
800
326
858
371
Zombies
count zombies
17
1
11

MONITOR
879
326
936
371
Humans
count humans
17
1
11

@#$#@#$#@
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270
@#$#@#$#@
NetLogo 6.2.0
@#$#@#$#@
setup
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 0.0 1.0
0.0 1 1.0 0.0
0.2 0 0.0 1.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180
@#$#@#$#@
0
@#$#@#$#@
