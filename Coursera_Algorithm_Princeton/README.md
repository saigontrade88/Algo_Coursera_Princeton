## Programming Assignment 1: Percolation

### Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.

Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:

* Initialize all sites to be blocked.
* Repeat the following until the system percolates:
* Choose a site uniformly at random among all blocked sites.
* Open the site.
* The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

__Performance requirements.__
The constructor should take time proportional to n2; all methods should take constant time plus a constant number of calls to the union–find methods union(), find(), connected(), and count().

For more informations, check [the official assignment
description][1].

## Programming Assignment 2: Deques and Randomized Queues

### Write a generic data type for a deque and a randomized queue. The goal of this assignment is to implement elementary data structures using arrays and linked lists, and to introduce you to generics and iterators.

### Dequeue
A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure. 

__Performance requirements.__  Your deque implementation must support each deque operation (including construction) in constant worst-case time. A deque containing n items must use at most 48n + 192 bytes of memory and use space proportional to the number of items currently in the deque. Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.


### Randomized queue
A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.

__Performance requirements.__  Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time. That is, any sequence of m randomized queue operations (starting from an empty queue) must take at most cm steps in the worst case, for some constant c. A randomized queue containing n items must use at most 48n + 192 bytes of memory. Additionally, your iterator implementation must support operations next() and hasNext() in constant worst-case time; and construction in linear time; you may (and will need to) use a linear amount of extra memory per iterator.

[1]: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
