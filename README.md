#SimpleShapeFinder
=============

## COMP251

### Winter 2013

#### Homework 1


#### Question 2

Clearly (and succinctly) describe your algorithm, using words and/or high-level pseudo-code. Assume that Bresenham’s algorithm takes time *2πr* for a given *r*. For an *n × n* image input and an arbitrary but constant single radius input (ie *r = maxr = minr*):

	The algorithm used in the code provided finds a circle as follow:
	- Tries finding a circle at r (*2πr*)
	- Tries finding a circle at r - 1 (*2πr*)
	- Tries finding a circle at r + 1 (*2πr*)

	It tries to do it at all potential circle centers on the image.
	The coordinates used vary as follow (if image has size *w × h*):
	r + 1 <= x <= w - (r + 1)
	r + 1 <= y <= h - (r + 1)
	
	Thus it will parse (w - 2(r + 1)) × (h - 2(r + 1)) if it goes over the entire image.	


(a) **10** Analyze the worst-case time complexity of your approach in terms of *n*. You must provide a clear description of how you derive the complexity. Provide proof of an appropriate complexity class appropriate complexity class.

	In the worst case, the image is monochrome, and thus contain no informations allowing us to find circles.
	The algorithm will have to parse the entire image according to the bounds specified above,
	using Bresenham’s algorithm three times, trying to determine if a circle exist at this location.

	Therefore the program will try (w - 2(r + 1)) × (h - 2(r + 1)) × 3(2πr) points.
	with an *n × n* image, we get:
	
	The program will take time (n - 2(r + 1))^2 × 3(2πr) to end which correspond to a **O(6πrn^2)** complexity.
	(All variables but *n* are constant.)


(b) **5** Analyze the best-case time complexity of your approach in terms of *n*. You must provide a clear description of how you derive the complexity. Provide proof of an appropriate complexity class.

	In the best case, the second points he checks using Bresenham’s algorithm tells his that it is not a circle.
	Then Bresenham’s algorithm will take time 1 to return.
	
	However, the program will still have to parse the entire image to try out all the points, summing up to:
	(n - 2(r + 1))^2 × 1

	The program will take time (n - 2(r + 1))^2 × 3(2πr) to end which correspond to a **O(n^2)** complexity.


(c) **5** Suppose that *r* is not a constant, but rather a function of *n*. Is there an *r* that maximizes the worst-case time complexity for a given *n*? Justify your answer. 

	We have seen previously that *r* reduces the number of values to parse from *n × n* to:
	*(n - 2(r+1)) × (n - 2(r+1))*
	
	Also, finding out if a particular circle exist or not uses Bresenham’s algorithm three times.
	The total time taken would then be given by the formula:
	
	*6πr(n - 2(r+1))^2*
	
	That expands to:
	
	*6 π n^2 r - 24 π n r^2 - 24 π n r + 24 π r^3 + 48 π r^2 + 24 π r*

	If we treat n as a constant, it simplifies to:
	
	*24 pi r^3 + (48 π - 24 π n) r^2 + (6 π n^2 + 24 π) r*
	
	Which is a polynomial of degree three that has a maxima.


#### Question 3

(a) **10** Experimentally measure the performance of your algorithm in relation to *n*. Use image 3 as input, and vary the last (two) command-line parameter(s) as a proxy for a range of image inputs of increas- ing size. Keep radius constant at 25. Be sure to measure only time to detect the circles, not the time to emit output. Discuss and explain the behaviour—does it match your calculated time complexity?

	Blabla


(b) **5** Now, experimentally measure performance in terms of radius. Use image 3 again, analyzing the entire image, but varying radius from 4 up to 200. Again, plot performance and discuss the results. 

	Blabla


#### Question 4

**5** The algorithm given is quite fragile, and does not always find things which look circular to humans. 
Come up with a more robust way of tracing out the circles that detects as many of what a human observer might consider circles in images 1 and 3 (small) as possible. Concisely describe your algorithm changes. 

	The problem we encounter here is that the program is too strict in determining what is a circle.
	One common way to resolve this problem is to use a threashold value.
	Instead of saying that ALL points on the circle must be of the same color, modify the
	algorithm so it says that a certain figure is a circle if 95% of the points its made of are of the same color.

	
