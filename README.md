# JHeaps Trace Drivers

Copyright (C) 2019-2019 Dimitrios Michail

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

***

## What is a heap trace?

A standard methodology when comparing different heap data structures is to perform 
trace-based simulation as described in 
the [5th DIMACS challenge](https://www.cs.amherst.edu/~ccmcgeoch/challenge5/). 
For the exact format of the trace files you can read
the [specification document](https://www.cs.amherst.edu/~ccmcgeoch/challenge5/documents/specs.ps).

More specifically, a workload is first executed using a reference
heap implementation and a file containing the sequence of operations is recorded. Afterwards,
the trace file is executed using different drivers, one for each heap in the comparison,
as well as using a so called *dummy* driver which simply parses the trace file but does
not execute any heap operations. The dummy driver is used in order to normalize the results,
meaning that the statistics of the dummy driver are subtracted from the statistics of each
heap driver. 

## What is this project?

This project contains heap drivers for various heaps using the [JHeaps](https://github.com/d-michail/jheaps) library. 
It uses maven and builds an uberjar using the maven shade plugin. This also makes it relatively easy to compile the 
driver natively using the native-tool from the [GraalVM](https://www.graalvm.org/).


