
# Optimal travel path problem

Travelling from point A to B in Sarajevo is becoming a huge headspace year after year. Everyone is trying to find an optimal way to get to their destination in a timely fashion manner. There are numerous ways to accomplish this task, from doing it on foot, by tram, by car, or even more recently, using electric scooters. Unfortunately, all those approaches have their drawbacks, and none really help anyone achieve ideal transportation.



## Instructions

To tackle this problem, a group of tech enthusiasts proposed designing a glass ball that could fit inside a human. Glass balls, including essential carry, are designed to fit one person in. People are transported by large cannons that shoot glasses between 20 strategically placed places in the city. The list of the points is contained in the data and each letter represents a certain location (A -> Alipašino, etc.).

Due to the nature of the technology that is built on quantum mechanics, travel time between points varies and, in some cases, can be surprising. Due to all of the mentioned facts, you are asked to create a program to find an optimal path between two places. To make things correct, you will also be presented with a list of conditions affecting specific routes and the time required to travel between them.


### Tasks

#### Task 1
Create a program that can keep information on a directed weighted graph. The program should be able to ingest the following file format:

A B 10

Where A represents Alipašino, B represents Baščaršija, and ten means 10 seconds that the glass capsule travels between those two places.

#### Task 2

Create a class for solving the shortest path algorithm between any two nodes in the graph. Cover up this class with tests where you will pass in different graph configurations with weights and test correct outputs. 

#### Task 3

For any given file X.txt, find the time required to travel between all mentioned places. All places must be mentioned in the file, using direct travel time, for example, the following file:

A B 10

B C 20

C A 5

has three places, and you are asked to give optimal travel time between A->B, A->C, B->A, B->C, C->A and C->B  

#### Task 4

Support previous tasks by including probability weights for specific routes in the constraints.csv file. For example:

A, B, high constructions, 0.2

Where A and B represent places, high construction represents the type of obstacle that can appear on a given route, and there is a 0.2 probability of such an obstacle occurring.

