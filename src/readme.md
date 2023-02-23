Personal project I made for sorting list of directories for necessary rules.

We have a List<String> with many values like a _"/1_atlas/example.feature"_.
Directories can be with a multi level hierarchy, also include english and russian symbols and numbers.

**Our goal** it is sorting input list for some rules:
1. Directories higher than files
2. Directories and files must be sorted alphabetically, separately from each other, for the same nesting level
3. Case insensitive
4. Numbers sensitive
5. These four rules must apply for any level nested


**Output** list:
<br>

<span style="color: red; opacity: 0.9;">/1_a/1_Test.feature<br></span>
<span style="color: yellow; opacity: 0.9;">/10_d/2_Test.feature<br></span>
<span style="color: green; opacity: 0.9;">/11_e/11_Test.feature<br></span>
<span style="color: orange; opacity: 1;">/atlas/brash/aorting.feature<br></span>
<span style="color: orange; opacity: 0.9;">/atlas/brash/gorting.feature<br></span>
<span style="color: orange; opacity: 0.9;">/atlas/trash/ban/1_yorting.feature<br></span>
<span style="color: orange; opacity: 0.8;">/atlas/trash/ban/2_yorting.feature<br></span>
<span style="color: orange; opacity: 0.8;">/atlas/trash/ban/10_yorting.feature<br></span>
<span style="color: orange; opacity: 0.7;">/atlas/trash/ban/worting.feature<br></span>
<span style="color: orange; opacity: 0.7;">/atlas/trash/ban/yorting.feature<br></span>
<span style="color: orange; opacity: 0.6;">/atlas/trash/bang/worting.feature<br></span>
<span style="color: orange; opacity: 0.6;">/atlas/trash/aorting.feature<br></span>
<span style="color: orange; opacity: 0.5;">/atlas/corting.feature<br></span>
<span style="color: blue; opacity: 1;">/cards/exampleForCardsTeam.feature<br></span>
<span style="color: blue; opacity: 0.8;">/cards/Выпуск доп.карты.feature<br></span>
<span style="color: violet; opacity: 0.9;">/BackSystemsDisable.feature<br></span>
<span style="opacity: 0.9;">/example.feature</span>
<br>

**Setup**<br>
For work you need two classes - DirectoriesSorter and TrueListComparator. 
Then call DirectoriesSorter.sortDirs(list).



