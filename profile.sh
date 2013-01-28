#!/bin/bash

cd ./bin

for N in {0..2050..10}	
do
	TIME=$(java Assig1 ../res/testimage3.png 25 25 $N $N)
	echo $N, $TIME >> ../radius25
done

read -p "Press Enter to continue to Radius test" -n 1 -s

for RADIUS in {0..25..1}
do	

	TIME=$(java Assig1 ../res/testimage3.png $RADIUS $RADIUS)
	echo $RADIUS, $TIME >> ../wholepicture

done
