Time compexity:
1.Execution starts with downloading Dicitionary files and ChEMBL data from the URL which is linear O(n)

2.Look up of the reversed lenght sorted List on the Dicitionary data with a complexity of O(n)

3.another loop to iterate through the chEmbl data making the complexity of O(m)
 results in a O(m*n) complexity.
 This can be an overhead when the data increases
 Hashmap with a 2^30 can handle though,but certainly this can be an improvement point
 
4.charater comparasion happens using KMP algorithm so by maintaining prefix we can improve the character comparision logic 
with a complexity ofO(m+n) as compared to O(m*n) with the brute force

5.Finally the code breaks out of the loop on the count of arguments that is passed else a default of 10 is choosen over it.
