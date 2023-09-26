# CraftingInterpreter
《CraftingInterpreter》做的编译器代码
"Lox" 解释器和编译器

前端 : 词法分析、语法分析=>AST(抽象语法树)  
中端 : 静态分析、生成中间代码(IR)、优化  
后端 : 生成最终代码(虚拟机或特定架构的机器语言或其他高级语言)  

前端是把源代码字符串转换为结构化数据，中端是针对变形后的结构化数据反复做优化，后端是从优化后的数据生成真实的机器指令。  

## 词法分析
词素只是源代码的子字符串<del>(类似单词啦)</del>，即具有意义的最小单位  
生成词素与类型(或者还可能有其他数据)的对应和绑定，就生成了 Token  
扫描器从头开始，可以利用正则表达式将字符分组为词素(lexeme)  
最大匹配原则/* (当两个语法规则都能匹配扫描器正在处理的一大块代码时，哪个规则相匹配的字符最多，就使用哪个规则) */的情况下，我们需要将所有可能的字符开头的先标记为变量(或曰标识符)，之后分析是否为保留字  

## 语法分析
后序遍历表达式树可以得到结果  
CFG : 上下文无关文法，生成有效的语句，根据CFG构造语法树  
元编程 : 即生成代码的代码，构造表达式类  
处理树形结构，访问者模式，利用java的泛型来获得表达式的返回值  
递归求解，将所有的Expr都递归直到终止符，在程序里体现的就是递归visit所有expr  
parenthesize作用一开始的表达式(语法树的root) => 遍历expr => 调用所有的expr的accept => accept调用visit
### 5.5 作业
1. 化简，跟形式语言与自动机里面的化简一样，
    表示的表达式应该是表达式里嵌套着的用逗号连接的表达式，同时支持对象调用(有'.'的存在)
2. 略
3. 转化成RPN并返回结果字符串的代码见RPN.java(只要对操作数进行判断然后调换一下builder添加的顺序即可)

=> 解析表达式
考虑到表达式的优先级和结合性，否则会产生歧义
根据优先级来定义生成语法规则，高级匹配低级，重新定义语法树
不应该存在左递归(=> 左边的第一个符号和右边的符号相同)
**使用递归下降分析**
一种自顶而下的解析器，将语法规则直接翻译成命令式语言的函数，规则如下
|语法记号 | 代码表示 |
|---------|------------|
|终结符| 匹配并消费一个词法标记|
|非终结符 |调用规则对应的函数|
| if 或者 | switch |语句|
|* 或者 + | while 或者 for 循环|
|? |if 语句|

# Lua 源码分析
