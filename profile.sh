#!/bin/bash

cd ./bin

echo "Testing with radius fixed at 25."

for N in {0..2050..10}	
do
	echo Testing $N x $N working area.
	TIME=$(java Assig1 ../res/testimage3.png 25 25 $N $N)
	echo $N, $TIME >> ../res/radius25.csv
done

read -p "Press Enter to continue to Radius test" -n 1 -s

for RADIUS in {25..100..1}
do	
	echo Testing radius = $RADIUS.
	TIME=$(java Assig1 ../res/testimage3.png $RADIUS $RADIUS)
	echo $RADIUS, $TIME >> ../res/wholepicture.csv

done
