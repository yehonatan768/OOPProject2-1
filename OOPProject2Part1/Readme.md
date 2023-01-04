In this project we are going to create files with random number of lines, and count them using 3 ways:

1.Using the normal way which is less efficient: Iterating over the files and counting line by line and finally returning the answer, without using Threads.

2.Using Threads which is more efficient.

3.Using Thread Pool.


In the first part of the project I created 1000 files, with a random number of lines using seed, and counted the running time it took for each function to get executed.

Turned out, it took for the first function much more time to run compared to the last two functions, to be exact, 1.5077061 seconds, whereas the last two functions

took 0.6815452 second , 0.7497736 seconds respectively, which are roughly the same.

The reason is that using threads to count the number of lines in a certain number of files can be faster than using a single thread

because it allows you to divide the work among multiple threads, which can run concurrently and potentially make use of multiple processor cores.

This can lead to a significant speedup if the files are large and there are enough processor cores available to run the threads concurrently.

However, there are some limitations to using threads to count the number of lines in a certain number of files.

First, there is overhead associated with creating and managing threads, which can reduce the overall performance gain.

Second, if the files are small and the overhead of creating and managing threads is significant, then using threads may actually be slower than using a single thread.

Finally, if the number of threads is too large, then it can lead to resource contention and degrade performance.

Overall, using threads can be a useful technique for improving the performance of certain types of tasks,

but it is important to consider the specific characteristics of the task and the resources available when deciding whether and how to use threads.