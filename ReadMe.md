1. We store Web content in Txt as input.
2.We use a trie tree to store every word of the input. At the leaf node, we have an occurenceList,a hashmap, which store the list of files that contains the keyword and the frequency in each file. 
3.Then the results will be sorted by the frequency. If the word is not found, a similar word will be chosen to be searched.

************************************************************************
Demo:
When we are unable to find the keyword or similar word:

-----------------Here are documents--------------------
.DS_Store
1.txt
10.txt
11.txt
2.txt
3.txt
4.txt
5.txt
6.txt
7.txt
8.txt
9.txt
a.txt
b.txt
www.csdn.com.txt
--------------------------------------------------------
Search Engine has initialized successfully!
Enter the word you want to search for. If the word is not included, a similar word will be chosen.
excellent
Unable to find the keyword: excellent

*****************************************************************************
When we can find the keyword:

-----------------Here are documents--------------------
.DS_Store
1.txt
10.txt
11.txt
2.txt
3.txt
4.txt
5.txt
6.txt
7.txt
8.txt
9.txt
a.txt
b.txt
www.csdn.com.txt
--------------------------------------------------------
Search Engine has initialized successfully!
Enter the word you want to search for. If the word is not included, a similar word will be chosen.
work
The word "work" is founded!
------------------------Here are results------------------------
The file: 8.txt. Keyword appears 3 time(s).
The file: 6.txt. Keyword appears 2 time(s).
The file: 5.txt. Keyword appears 2 time(s).
The file: 1.txt. Keyword appears 1 time(s).
The file: 3.txt. Keyword appears 1 time(s).
The file: 10.txt. Keyword appears 1 time(s).

***************************************************************************

When we cannot find the keyword but we found a similar word:

-----------------Here are documents--------------------
.DS_Store
1.txt
10.txt
11.txt
2.txt
3.txt
4.txt
5.txt
6.txt
7.txt
8.txt
9.txt
a.txt
b.txt
www.csdn.com.txt
--------------------------------------------------------
Search Engine has initialized successfully!
Enter the word you want to search for. If the word is not included, a similar word will be chosen.
asdlwkel
The word "as" is founded!
------------------------Here are results------------------------
The file: 6.txt. Keyword appears 6 time(s).
The file: 5.txt. Keyword appears 5 time(s).
The file: 10.txt. Keyword appears 5 time(s).
The file: 8.txt. Keyword appears 3 time(s).
The file: 4.txt. Keyword appears 3 time(s).
The file: 7.txt. Keyword appears 2 time(s).
The file: 3.txt. Keyword appears 2 time(s).
The file: 9.txt. Keyword appears 2 time(s).
The file: 2.txt. Keyword appears 1 time(s).
The file: 1.txt. Keyword appears 1 time(s).

***********************************************************************

