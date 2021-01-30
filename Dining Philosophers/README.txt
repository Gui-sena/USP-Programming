In computer science, the dining philosophers problem is an example problem often used in concurrent algorithm design to 
illustrate synchronization issues and techniques for resolving them.
The problem statement is as such:
  Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair of adjacent philosophers.
  Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when they have both left and right forks. 
  Each fork can be held by only one philosopher and so a philosopher can use the fork only if it is not being used by another philosopher.
  After an individual philosopher finishes eating, they need to put down both forks so that the forks become available to others. 
  A philosopher can only take the fork on their right or the one on their left as they become available and they cannot start eating before getting both forks.
  Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite supply and an infinite demand are assumed.
  The problem is how to design a discipline of behavior (a concurrent algorithm) such that no philosopher will starve;
  i.e., each can forever continue to alternate between eating and thinking, assuming that no philosopher can know when others may want to eat or think.
  
The following project proposes a solution to this famous problem by using mutexes to maintain mutual exclusion and avoid resource starvation and deadlocks.
Solution number one proposes using only one mutexm while solution number two uses a mutex for each philosopher. 
Although the socond solution works better and allows a better efficiency, both solutions satisfy the requirements of the problem.
