# MultiLevel Cache

## Problem statement
Design and implement a Multiple Level Cache Management System with N levels: L1,L2,...Ln. Each layer will store Key-Value pairs of data. L1 is the top most layer with the most priority. LN is the last layer with least priority. Each layer has its capacity, read time, write time. 

## Mandatory Requirements
1. **READ KEY Operation**: Read will start from L1 level. Keep doing this until the value of KEY is
found at any level or the last level has been reached. If the value of KEY is found at any level, then
this VALUE should also be written into all previous levels of cache which have higher priority than
this level. Total Read time is the sum of Read time of all levels where READ operation was
attempted and Write time of all levels where WRITE operation was attempted. You have to print the
VALUE of KEY and total time taken to read it.

2. **WRITE KEY Operation**: Write will start from L1 level. Write the value of KEY at this level and all levels below it. If at any level value of KEY is the same as the given VALUE then don’t write again and return. Total Write time is the sum of WRITE time of all levels where WRITE operation was attempted and Read time of levels where READ operation was attempted. You have to print the total time taken to write this KEY-VALUE information.

3. **STAT Operation**:

*Stat operations prints three information*:
a) What is the current usage of each level of cache, i.e. Filled / Total size
b) What is the average read time of this Multi-Level Cache System for last 5 READ operation?
c) What is the average write time of this Multi-Level Cache System for last 5 WRITE operation?

**Good to Have/Extensions**
1. Implementation of storage change based on Level
2. Implement Cache Manager as a library
3. Extend STAT Operations data points

