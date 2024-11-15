****************
* Double-linked List
* CS221
* 04/11/2022
* Brian Wu
**************** 

## OVERVIEW:

 This program is a list that is doubly-linked with nodes and implements the IndexedUnsortedList implementation.
 It has many ways to add/modify data and has a working* list iterator.
*its a little buggy

## INCLUDED FILES:

* IUDoubleLinkedList.java - This file has the main bulk of code in it (source file)
* ListTester.java - This file contains all the testing to verify the source file (well it has most of it)
* Node.java - The Double-linked list uses nodes so this is a file for it to work that is editied to accept double-linked nodes
* IndexedUnsortedList.java - This is the interface that the list uses
* README - this file

## COMPILING AND RUNNING:

 When you are in the folder with all the files for this program, compile 
 the driver(testing file) and all other java files with the command:
 `$ javac *.java`
 
 From that point, run the testing file for the double-linked list file
 with this command:
 `$ java ListTester`

 Console output will give the results after the program finishes.


## PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 This program, while on first glance appears to be full of code
 and complicated algorithms, its rather simple. However, it is not complete.
 I would say that coding-wise I can comfortably say that completion is around
 85-95%. Several methods need a little fine tuning like the set, add(index, element),
 and list iterator add(e) methods. I think with a little more time say a couple days
 this could be in full formation.

 To put this program in a simple way. The user can create a list comprised 
 of nodes that are all connected and capable of going back and forth from each other.
 Indexing is not required! I must say, many methods do use indexing which are all coded
 to be friendly with the nodes. Adding data has a variety of options as well as 
 removing, collecting, and modifying what is already in the list. There is also a 
 list iterator that mostly works, allowing the user to seamlessly stride from start
 to end of the list.

 The IUDoubleLinkedList class is comprised of the LinkedList object that can 
 be constructed using a driver class. It takes in information from the Node class and
 the IndexedUnsortedList interface to create a system that is capable of using 
 nodes and have the methods provided in order to have an ideal pool of utility when 
 using a list. Inside the IUDoubleLinkedList class there is a ListIterator class that
 has modified methods from the ListIterator implementation that allow nodes to be used.
 
 I think that in its state, it doesn't need much change. If there was a way to directly
 input the Node class inside the IUDoubleLinkedList that would be nice, but I don't believe
 that it is possible to my knowledge. All the advice I have for improving this program is 
 to work from the methods provided and tune them up to a 100% working state.

## TESTING:

 Testing was a hassle for me and I understand the merits of it. It's something that I
 know I need to do, but its tedious and time consuming. The good thing about testing was
 that a majority of the plan was already previously setup. I'm glad to say at least around 
 60% of testing has been implemented. That may seem like a low number, but with all the stuff
 I've been dealing with I'm happy to see this result. This program is far from idiot-proof, but
 its in a position where at least someone that isn't intelligent as a above average being could
 probably figure out. The biggest bugs/issues that remain are several methods 
 incomplete and the lack of a complete testing file.

## DISCUSSION:
 
 Programming this whole idea, this project, was a nightmare and a well oiled machine at
 the same time. I've never seen so many things fail and succeed all at the same time. I'll
 break it down. Coding up the IUDoubleLinkedList was a therapeutic yet stressful process. Most
 solutions would be rather elegant, while others would be a whole beast of their own. I feel
 confident that I would be able to get this in full formation if I put the time like I did
 over this weekend. Fixing the set and add methods would be my priority. They seem to have conflicting
 cases where sometimes set would be allowed to create an element, and sometimes it wasn't allowed. 
 
 Testing was more secondary, and as time goes on I regret that I pushed it 
 towards the end. I really wanted my main class to be completed, believe me, it sometimes felt like
 I was running on a treadmill rather than down a path. Always on the move, but going nowhere. 
 Hitting thinking/mental blocks were what ultimately stretched out this whole project from a 5-day week to 
 the weekend desperately needing to be utilized. Most of the testing was completed but many more scenarios
 need to be implemented and tests need to be added to the ListIterator checkers. If I had a time machine
 you can bet I would be zooming to tell my former past to get going on that testing.
 
 To put it in a genuine way, this was the hardest thing I've ever done in Computer Science to date.
 In CS121, I had to make a snake move and eat things. It was also another thing that I ran into many
 issues, but I feel like I take too many things for granted in programming and it creates a sense of 
 overconfidence. I think it the fact of no collaboration made things difficult. I pushed myself to
 the limits and tried to complete this and I felt like I did. Nodes feel like second nature and its easy to 
 come up with ideas in my brain. I do feel a little sad as all this effort won't be reflected on 
 this grade, but I am ready to figure it out, once again. 
 
 
## EXTRA CREDIT:

 It would be nice if I did.


----------------------------------------------------------------------------

All content in a README file is expected to be written in clear English with
proper grammar, spelling, and punctuation. If you are not a strong writer,
be sure to get someone else to help you with proofreading. Consider all project
documentation to be professional writing for your boss and/or potential
customers.
