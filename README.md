VMRouter
========

Comp 429 - Networking Virtual Router Class Project


=============================================================================================
12/15/12
Fixed bug where routing table has entry for non existing port. Rechecked routing operations.
=============================================================================================
12/14/12
Router works on the local machine. usend and asend are now disabled since they weren't
using packets or frames.   
=============================================================================================
12/13/12
PortAdmin method getPort now returns the right port number for a destination IP.  
=============================================================================================
12/13/12
Added network ID and subnet mask to IPv4 class
Ports now have a subnet and network ID
=============================================================================================
12/10/12
created kill command file to crash the router
create 49 ports
make 1025 routing entries
=============================================================================================
12/10/12
Added code to limit maximum number of ports and routing table entries
48 ports
1024 routing entries + default route
=============================================================================================
12/10/12
Lines starting with '//' are now ignored (comments)
=============================================================================================
12/10/12
Added command 'troute' to test the routing method. 
Ex: troute 150.129.0.0
=============================================================================================
12/6/12
Added method getSampleData(int dataLength) to VRMUtil to create sample data. Max 1024 bytes
=============================================================================================
12/3/12
The router shell now executes system commands either in Windows or Linux i.e. ls, dir 
command options are passed to the system as well. 
Note: some commands are meaningless since a new, temporary, command shell has to be invoked
for example: trying to change directories has no effect on the current runtime environment
since it was executed in a temporary shell, however 'dir > test' does what it's supposed to
under windows (and 'ls > test' under Linux)
=============================================================================================
12/3/12
Added command line processing. The command line may include a parameter naming a file with 
commands. The command line parameter is processed as an include command
Ex: java -jar vrm.jar setup
=============================================================================================
Note: for now the program exits after it's done processing commands if redirecting the input 
with '<' DO NOT REDIRECT INPUT WITH '<' pass the include file as parameter instead. 
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================
=============================================================================================

