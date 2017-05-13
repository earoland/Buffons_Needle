Project 2, Buffon's Needle for CS370 Operating Systems
Authored by Ethan Roland and Nick Sprinkle, Due 4/7/2017 @23:59:59
This program estimates pi through a concurrent multithreaded application
of the famous experiment Buffon's Needle. Instead of prompting the user for 
input, it accepts command line arguments which are detailed in a usage alert 
if none are provided. 
    The command line arguments in order are as Follows:
    args[0] = The number of threads you wish to execute, suggestion of 20
    args[1] = the distance between lines, suggestion of 7
    args[2] = the length of the needles, suggestion of 5
    args[3] = the number of experiments to run, suggestion of 100 million
This program only accepts four command line arguments, any more or less will 
result in an error message. 
Additionally, this form of buffons needle does not support needle length 
being longer than the distance between lines, and will print out an error 
message if this condition is met.

